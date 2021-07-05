# QuizApp

O projeto consiste na realização de um app android de Quiz.

As Questões foram feitas em hardcode, sem acessar o API (tive problemas).

O programa possui alguns problemas que foram identificados, mas não corrigidos a tempo. Como trabalho futuro teria os seguintes pontos:

1 - Implementar uma função getQuestions() que acessa o API de banco de questões para gerar as questões

2 - Avaliar a resposta através de uma requisição post no banco de dados, para isso ter uma função getAnswer e buildURL

3 - Implementar um cronometro no jogo, reiniciando o gif em cada nova pergunta e ao zerar o tempo a resposta ser a opção selecionada até então.

4 - Implementar um banco de dados local para ver os highscores

5 - Há um bug na aplicação, caso vc escolha uma opção de resposta, responda e antes de clicar no botão
"next question", alterar a resposta, é possível rsponder uma mesma questão inúmeras vezes, possibilitando ter um score acima de 10 pontos

- 
"# QuizProject" 
