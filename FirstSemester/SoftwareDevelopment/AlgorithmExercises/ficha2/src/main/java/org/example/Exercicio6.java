package org.example;

//Dada uma quantidade de tempo em segundos correspondente a um determinado
//momento do dia, converta-o para o formato horas : minutos : segundos.

public class Exercicio6 {
    public static String tempo (int s){
        String resultado;
        if (s<0 || s>86400){
            resultado= "-1";
        }
        else {
            int h= s/3600;
            int m= (s%3600)/60;
            int seg= s%60;
            resultado= h + ":" + m + ":" + seg;
        }
               return resultado;
    }
}
