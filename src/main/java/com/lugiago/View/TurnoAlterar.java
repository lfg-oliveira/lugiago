package com.lugiago.View;

import com.lugiago.Controller.FuncionarioController;
import com.lugiago.Controller.TurnosController;
import com.lugiago.Model.Funcionario;
import com.lugiago.Model.Turno;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;

public class TurnoAlterar extends javax.swing.JFrame {

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private final List<Funcionario> funcionarios = FuncionarioController.getFuncionarios();

    private List<Turno> turnos = TurnosController.getTurnos();

    private Turno turnoSelecionado = null;
    private Funcionario funcionarioSelecionado = null;
    private LocalDateTime inicioTurno = null;

    /**
     * Inicializa o formulário de alteração de turnos, também aplica máscaras ao
     * campos de Datas Popula a combobox e adiciona os listeners que farão a
     * dinâmica de processamento de datas para auxiliar o usuário a inserir o
     * horário de inicio dos turnos. Implementa também os eventos na JTable para
     * que seja possível selecionar os turnos a serem alterados.
     */
    public TurnoAlterar() {
        initComponents();

        // Popula a tabela com a carga inicial de turnos.
        preencherTabela(this.jTableTurnosSimplificado);

        // Popula o combobox de Plantonistas.
        List<String> funcionariosModelStringList = new ArrayList<>();
        funcionariosModelStringList.add("Selecione...");
        for (Funcionario prox : funcionarios) {
            funcionariosModelStringList.add(prox.getId() + "- " + prox.getNome());
        }
        jCBPlantonista.setModel(new DefaultComboBoxModel(funcionariosModelStringList.toArray()));

        // Configura máscaras e eventos para o calcula das datas de turno.
        try {

            new MaskFormatter("##/##/#### ##:##:##").install(jFormattedDateFieldInicio);
            new MaskFormatter("##/##/#### ##:##:##").install(jFormattedDateFieldFim);
            new MaskFormatter("##/##/#### ##:##:##").install(jFormattedDateFieldFimDescanso);

            jFormattedDateFieldInicio.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    processarDataFinal();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    processarDataFinal();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    processarDataFinal();
                }
            });

            // Adiciona evento as células, ao serem clicadas configuram o Turno a ser alterado.
            ListSelectionModel selectionModel = jTableTurnosSimplificado.getSelectionModel();
            selectionModel.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    int linhaSelecionada = jTableTurnosSimplificado.getSelectedRow();
                    int idTurnoSelecionado = (int) jTableTurnosSimplificado.getModel().getValueAt(linhaSelecionada, 4);

                    turnoSelecionado = turnos.stream().filter(t -> idTurnoSelecionado == t.getId()).findFirst().orElse(null);
                    funcionarioSelecionado = funcionarios.stream().filter(f -> turnoSelecionado.getIdFuncionario() == f.getId()).findFirst().orElse(null);;

                    jCBPlantonista.setSelectedIndex(funcionarios.indexOf(funcionarioSelecionado) + 1);

                    jTFCargo.setText(funcionarioSelecionado.getCargo());
                    jTFCodigo.setText(funcionarioSelecionado.getCodigo());

                    jFormattedDateFieldInicio.setText(turnoSelecionado.getDataInicial().format(dtf));

                    jCBPlantonista.setEditable(true);
                    jCBPlantonista.setEnabled(true);

                    jFormattedDateFieldInicio.setEditable(true);
                    jFormattedDateFieldInicio.setEnabled(true);

                    jBtnSalvar.setEnabled(true);
                    jBtnLimpar.setEnabled(true);
                }
            });

        } catch (ParseException ex) {
            Logger.getLogger(TurnoAlterar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Realiza o calculo da diferença de horas entre os turnos e o aplica nos
     * campos.
     */
    private void processarDataFinal() {
        String dtInicioStr = jFormattedDateFieldInicio.getText();

        try {
            inicioTurno = LocalDateTime.parse(dtInicioStr, dtf);
            jFormattedDateFieldFim.setText(dtf.format(inicioTurno.plusHours(12)));
            jFormattedDateFieldFimDescanso.setText(dtf.format(inicioTurno.plusHours(48)));
        } catch (DateTimeParseException ex) {
            inicioTurno = null;
            jFormattedDateFieldFim.setText("");
            jFormattedDateFieldFimDescanso.setText("");
        }
    }

    /**
     * Recebe um elemento JTable e o popula com os dados do banco, também
     * realiza o controle de ocultação da coluna de Ids dos Turnos.
     *
     * @param jTabela JTable a ser populado
     */
    public final void preencherTabela(JTable jTabela) {

        turnos = TurnosController.getTurnos();

        DefaultTableModel dtm = (DefaultTableModel) jTableTurnosSimplificado.getModel();

        dtm.setRowCount(turnos.size());

        jTabela.setModel(dtm);

        if (jTabela.getColumnCount() < 5) {
            jTabela.addColumn(new TableColumn(4));
        }

        int posicaoLinha = 0;

        for (int i = 0; i < turnos.size(); i++) {
            jTabela.setValueAt(turnos.get(i).getNomeFuncionario(), posicaoLinha, 0);
            jTabela.setValueAt(turnos.get(i).getDataInicial().format(dtf), posicaoLinha, 1);
            jTabela.setValueAt(turnos.get(i).getDataFinal().format(dtf), posicaoLinha, 2);
            jTabela.setValueAt(turnos.get(i).getDataFinal().plusHours(36).format(dtf), posicaoLinha, 3);
            jTabela.setValueAt(turnos.get(i).getId(), posicaoLinha, 4);
            posicaoLinha += 1;
        }

        jTabela.removeColumn(jTableTurnosSimplificado.getColumnModel().getColumn(4));
    }

    /**
     * Limpa os valores de Turno e desabilita os controles de alteração.
     */
    public void limpar() {
        turnoSelecionado = null;

        jCBPlantonista.setSelectedIndex(0);
        jCBPlantonista.setEditable(false);
        jCBPlantonista.setEnabled(false);

        jFormattedDateFieldInicio.setEditable(false);
        jFormattedDateFieldInicio.setEnabled(false);

        jTFCargo.setText("");
        jTFCodigo.setText("");
        jFormattedDateFieldInicio.setText("");

        jBtnSalvar.setEnabled(false);
        jBtnLimpar.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPWrapper = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTurnosSimplificado = new javax.swing.JTable();
        jPHorario = new javax.swing.JPanel();
        jFormattedDateFieldInicio = new javax.swing.JFormattedTextField();
        jLabelHorarioInicio = new javax.swing.JLabel();
        jLabelHorarioFim = new javax.swing.JLabel();
        jFormattedDateFieldFim = new javax.swing.JFormattedTextField();
        jFormattedDateFieldFimDescanso = new javax.swing.JFormattedTextField();
        jLabelHorarioFimDescanso = new javax.swing.JLabel();
        jPFuncionario = new javax.swing.JPanel();
        jCBPlantonista = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jTFCargo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTFCodigo = new javax.swing.JTextField();
        jPControles = new javax.swing.JPanel();
        jBtnCancelar = new javax.swing.JButton();
        jBtnSalvar = new javax.swing.JButton();
        jBtnLimpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Turnos - Cadastro");

        jPWrapper.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTableTurnosSimplificado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nome", "Inicio Plantão", "Fim Plantão", "Fim Descanso", "Id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableTurnosSimplificado.setColumnSelectionAllowed(true);
        jTableTurnosSimplificado.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableTurnosSimplificado);
        jTableTurnosSimplificado.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTableTurnosSimplificado.getColumnModel().getColumnCount() > 0) {
            jTableTurnosSimplificado.getColumnModel().getColumn(0).setResizable(false);
            jTableTurnosSimplificado.getColumnModel().getColumn(1).setResizable(false);
            jTableTurnosSimplificado.getColumnModel().getColumn(2).setResizable(false);
            jTableTurnosSimplificado.getColumnModel().getColumn(3).setResizable(false);
            jTableTurnosSimplificado.getColumnModel().getColumn(4).setResizable(false);
        }

        jPHorario.setBorder(javax.swing.BorderFactory.createTitledBorder("Horários"));
        jPHorario.setPreferredSize(new java.awt.Dimension(200, 100));

        jFormattedDateFieldInicio.setEditable(false);
        jFormattedDateFieldInicio.setEnabled(false);

        jLabelHorarioInicio.setText("Inicio Turno:");

        jLabelHorarioFim.setText("Fim Turno:");

        jFormattedDateFieldFim.setEditable(false);

        jFormattedDateFieldFimDescanso.setEditable(false);

        jLabelHorarioFimDescanso.setText("Fim Desc.:");

        javax.swing.GroupLayout jPHorarioLayout = new javax.swing.GroupLayout(jPHorario);
        jPHorario.setLayout(jPHorarioLayout);
        jPHorarioLayout.setHorizontalGroup(
            jPHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPHorarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelHorarioFim)
                    .addComponent(jLabelHorarioFimDescanso)
                    .addComponent(jLabelHorarioInicio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFormattedDateFieldFimDescanso)
                    .addComponent(jFormattedDateFieldFim)
                    .addGroup(jPHorarioLayout.createSequentialGroup()
                        .addComponent(jFormattedDateFieldInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPHorarioLayout.setVerticalGroup(
            jPHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPHorarioLayout.createSequentialGroup()
                .addGroup(jPHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelHorarioInicio)
                    .addComponent(jFormattedDateFieldInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedDateFieldFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelHorarioFim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedDateFieldFimDescanso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelHorarioFimDescanso))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPFuncionario.setBorder(javax.swing.BorderFactory.createTitledBorder("Funcionário"));

        jCBPlantonista.setEnabled(false);
        jCBPlantonista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBPlantonistaActionPerformed(evt);
            }
        });

        jLabel1.setText("Cargo:");

        jTFCargo.setEditable(false);
        jTFCargo.setEnabled(false);

        jLabel2.setText("Plantonista:");

        jLabel3.setText("Código: ");

        jTFCodigo.setEditable(false);
        jTFCodigo.setEnabled(false);

        javax.swing.GroupLayout jPFuncionarioLayout = new javax.swing.GroupLayout(jPFuncionario);
        jPFuncionario.setLayout(jPFuncionarioLayout);
        jPFuncionarioLayout.setHorizontalGroup(
            jPFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFuncionarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPFuncionarioLayout.createSequentialGroup()
                        .addComponent(jTFCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCBPlantonista, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPFuncionarioLayout.setVerticalGroup(
            jPFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFuncionarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBPlantonista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(12, 12, 12)
                .addGroup(jPFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTFCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPControles.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jBtnCancelar.setText("Cancelar");
        jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarActionPerformed(evt);
            }
        });

        jBtnSalvar.setText("Salvar");
        jBtnSalvar.setEnabled(false);
        jBtnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSalvarActionPerformed(evt);
            }
        });

        jBtnLimpar.setText("Limpar");
        jBtnLimpar.setEnabled(false);
        jBtnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPControlesLayout = new javax.swing.GroupLayout(jPControles);
        jPControles.setLayout(jPControlesLayout);
        jPControlesLayout.setHorizontalGroup(
            jPControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPControlesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnLimpar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPControlesLayout.setVerticalGroup(
            jPControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPControlesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnLimpar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnCancelar)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPWrapperLayout = new javax.swing.GroupLayout(jPWrapper);
        jPWrapper.setLayout(jPWrapperLayout);
        jPWrapperLayout.setHorizontalGroup(
            jPWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPWrapperLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPWrapperLayout.createSequentialGroup()
                        .addComponent(jPHorario, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPControles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPWrapperLayout.setVerticalGroup(
            jPWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPWrapperLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPWrapperLayout.createSequentialGroup()
                        .addComponent(jPFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPHorario, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                            .addComponent(jPControles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPWrapper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBtnCancelarActionPerformed

    /**
     * Evento de ação no ComboBox de plantonistas no qual irá obter os dados do
     * plantonista selecionado e aplicá-los ao formulário
     */
    private void jCBPlantonistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBPlantonistaActionPerformed
        try {
            funcionarioSelecionado = funcionarios.get(jCBPlantonista.getSelectedIndex() - 1);

            jTFCargo.setText(funcionarioSelecionado.getCargo());
            jTFCodigo.setText(funcionarioSelecionado.getCodigo());
        } catch (IndexOutOfBoundsException ex) {
            jTFCargo.setText("");
            jTFCodigo.setText("");

            funcionarioSelecionado = null;
        }
    }//GEN-LAST:event_jCBPlantonistaActionPerformed

    /**
     * Evento do botão de salvamento, irá validar as informações do formulários
     * e enviar os dados para persistencia via controller.
     */
    private void jBtnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSalvarActionPerformed
        if (funcionarioSelecionado != null && inicioTurno != null) {
            if (TurnosController.validaDisponibiliade(funcionarioSelecionado.getId(), inicioTurno, turnoSelecionado.getId())) {
                boolean resultado = TurnosController.alterar(turnoSelecionado.getId(), funcionarioSelecionado.getId(), inicioTurno);
                JOptionPane.showMessageDialog(null, resultado ? "Turno alterado com sucesso!!" : "Não foi possível alterar os Turnos!!", resultado ? "Mensagem" : "Erro", resultado ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
                preencherTabela(jTableTurnosSimplificado);
                limpar();
            } else {
                JOptionPane.showMessageDialog(null, "Turno viola a regra 12/36!!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Informações insuficientes para cadastrar Turno!!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBtnSalvarActionPerformed

    /**
     * Evento do botão de limpeza, aplica a funcionalidade do método limpar().
     */
    private void jBtnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLimparActionPerformed
        limpar();
    }//GEN-LAST:event_jBtnLimparActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TurnoAlterar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TurnoAlterar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TurnoAlterar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TurnoAlterar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TurnoAlterar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JButton jBtnLimpar;
    private javax.swing.JButton jBtnSalvar;
    private javax.swing.JComboBox<String> jCBPlantonista;
    private javax.swing.JFormattedTextField jFormattedDateFieldFim;
    private javax.swing.JFormattedTextField jFormattedDateFieldFimDescanso;
    private javax.swing.JFormattedTextField jFormattedDateFieldInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelHorarioFim;
    private javax.swing.JLabel jLabelHorarioFimDescanso;
    private javax.swing.JLabel jLabelHorarioInicio;
    private javax.swing.JPanel jPControles;
    private javax.swing.JPanel jPFuncionario;
    private javax.swing.JPanel jPHorario;
    private javax.swing.JPanel jPWrapper;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFCargo;
    private javax.swing.JTextField jTFCodigo;
    private javax.swing.JTable jTableTurnosSimplificado;
    // End of variables declaration//GEN-END:variables
}
