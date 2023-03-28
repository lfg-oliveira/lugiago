# Trabalho1 de Programação Orientada a Objetos III

Este trabalho pode ser resolvido em até 4 alunos por equipe.
A nota desse trabalho será pontuada de 0 até 4, dependendo de quanto a solução estiver
correta.
Trabalhos que apresentem erros de compilação terão pontuação 0.
Trabalhos que se apresentarem como plágio de outras soluções (produzidas por amigos,
publicadas em meios eletrônicos ou não, produzidas por ferramentas computacionais)
terão pontuação 0.
Exigências:

- O código fonte deve estar comentado.
- O projeto maven deve ser entregue
- Apenas um dos membros da equipe deve postar o código.

## Descrição do trabalho:
Em um hospital, cada funcionário possui um código profissional, nome e cargo.
O cargo pode ser: enfermeiro, médico e residente. Se o cargo for enfermeiro o código é
COREN, se for médico ou residente o código é CRM.
A cada mês deve ser previsto em cada dia o quadro de funcionários. Neste hospital, os
funcionários são alocados para dar plantão, no regime 12/36, ou seja, trabalham 12
horas ininterruptas e devem passar em seguida 36 horas de descanso, fora do ambiente
de trabalho. O turno diurno começa às 7h da manhã e termina às 7h da noite. O turno
noturno começa às 7 horas da noite e termina às 7h da manhã.
## Seu trabalho consiste em fazer um programa, contendo as seguintes funcionalidades:
a) Cadastro de funcionários

Deve ler quantos funcionários serão cadastrados.
Deve ler as informações do respectivo funcionário e armazená-las num
vetor.
Seu programa não pode possibilitar cadastro de funcionários com o
mesmo código.

  b) Planejamento de turnos
Deve ler para cada enfermeiro, que dias de abril e que turno ele deseja realizar.

Deve ler para cada médico, que dias de abril e que turno ele deseja realizar.
Deve ler para cada residente, que dias de abril e que turno ele deseja realizar.
Para cada um desses profissionais, deve avisar e recusar a informação de datas e
turnos que violem a regra 12/36.

  c) Troca de plantões

O sistema deve possibilitar que um funcionário troque o dia de seu plantão,
desde que ele não viole a regra 12/36.

  d) Impressão dos turnos cadastrados em cada dia do mês de abril com o nome dos profissionais e seus cargos.
