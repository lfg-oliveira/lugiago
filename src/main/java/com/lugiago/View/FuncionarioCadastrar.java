package com.lugiago.View;

import com.lugiago.Controller.FuncionarioController;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class FuncionarioCadastrar extends javax.swing.JFrame {

    private final List<String> cargos = new ArrayList<>();

    /**
     * Inicializa o formulário e preenche o model da combobox.
     */
    public FuncionarioCadastrar() {
        cargos.add("Enfermeiro");
        cargos.add("Médico");
        cargos.add("Residente");

        initComponents();

        jCBProfissao.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jBSalvar = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();
        jCBProfissao = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jTFNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextFieldCodigo = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Funcionário - Cadastro");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jBSalvar.setText("Salvar");
        jBSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalvarActionPerformed(evt);
            }
        });

        jBCancelar.setText("Cancelar");
        jBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelarActionPerformed(evt);
            }
        });

        jCBProfissao.setModel(new DefaultComboBoxModel(cargos.toArray()));
        jCBProfissao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBProfissaoActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome:");

        jLabel2.setText("Profissão:");

        jLabel3.setText("Código");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCBProfissao, 0, 170, Short.MAX_VALUE)
                            .addComponent(jFormattedTextFieldCodigo)
                            .addComponent(jTFNome)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jBCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBProfissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jFormattedTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBCancelar)
                    .addComponent(jBSalvar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Evento do botão de Cancelamento, fecha a tela e retorna ao menu.
     */
    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBCancelarActionPerformed

    /**
     * Evento do botão de salvamento, irá validar as informações do formulários
     * e enviar os dados para persistencia via controller.
     */
    private void jBSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalvarActionPerformed
        String nome = jTFNome.getText();
        String id = jFormattedTextFieldCodigo.getText();
        int idCargo = jCBProfissao.getSelectedIndex();

        if (nome.isBlank()) {
            JOptionPane.showMessageDialog(null, "Nome inválido!!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (idCargo == -1) {
            JOptionPane.showMessageDialog(null, "Cargo inválido!!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!jFormattedTextFieldCodigo.isEditValid()) {
            JOptionPane.showMessageDialog(null, "Código inválido!!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (FuncionarioController.grava(idCargo + 1, nome, id)) {
            JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário!!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        jCBProfissao.setSelectedIndex(idCargo);
    }//GEN-LAST:event_jBSalvarActionPerformed

    /**
     * Evento de ação no ComboBox de profissões no qual irá validar qual a
     * máscara de código respectiva para o profissional selecionado.
     */
    private void jCBProfissaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBProfissaoActionPerformed
        try {
            switch (jCBProfissao.getSelectedIndex()) {
                case 0:
                    jFormattedTextFieldCodigo.setValue("");
                    new MaskFormatter("'COREN'-AA ###.###-AA").install(jFormattedTextFieldCodigo);
                    break;
                case 1:
                case 2:
                    jFormattedTextFieldCodigo.setValue("");
                    new MaskFormatter("CRM/AA ######").install(jFormattedTextFieldCodigo);
                    break;
                default:
                    throw new AssertionError();

            }
        } catch (ParseException ex) {
            Logger.getLogger(FuncionarioCadastrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jCBProfissaoActionPerformed

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
            java.util.logging.Logger.getLogger(FuncionarioCadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FuncionarioCadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FuncionarioCadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FuncionarioCadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FuncionarioCadastrar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBSalvar;
    private javax.swing.JComboBox<String> jCBProfissao;
    private javax.swing.JFormattedTextField jFormattedTextFieldCodigo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTFNome;
    // End of variables declaration//GEN-END:variables
}
