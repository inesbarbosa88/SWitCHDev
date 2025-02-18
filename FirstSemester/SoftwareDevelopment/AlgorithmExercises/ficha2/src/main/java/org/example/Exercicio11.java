package org.example;

//O departamento que controla o índice de poluição do meio ambiente mantém
//3 grupos de indústrias que são altamente poluentes. O índice de poluição
//aceitável varia de 0 até 0,3. Se o índice subir para além de 0,3, as indústrias
//do 1º grupo são intimadas a suspenderem as suas atividades, se o índice crescer
//para além de 0,4, as indústrias do 1º e 2º grupo são intimadas a suspenderem as
//suas atividades e se o índice superar os 0,5, os 3 grupos devem ser notificados
//a paralisarem as suas atividades. A função deve retornar a notificação a ser
//emitida, de acordo com o índice de poluição verificado.

public class Exercicio11 {
    public static String notificacao (double i){
        if (i < 0){
            return "Não aplicável";
        }
       else if (i<=0.3){
            return "Indice Poluição Aceitável";
       }
       else if (i<=0.4){
           return "Indice Poluição Não Aceitável: Grupo 1 deverá suspender atividade";
       }
       else if (i<=0.5){
           return "Indice Poluição Não Aceitável: Grupo 1 e Grupo 2 deverão suspender atividade";
       }
       else{
           return "Indice Poluição Não Aceitável: Grupo 1, 2 e 3 deverão suspender atividade";
       }
    }
}

