package org.example;

//Dado um número de três dígitos (centenas, dezenas e unidades), verifique se
//a sequência dos algarismos é ou não crescente.

public class Exercicio9 {
    public static String crescente(int x) {

        if (x <= 99 || x > 999) {
            return "NaN";
        }

        String y = Integer.toString(x);
        int posicao = 0;
        char digito0 = y.charAt(posicao);
        char digito1 = y.charAt(posicao + 1);
        char digito2 = y.charAt(posicao + 2);

        if (digito0 < digito1 && digito1 < digito2) {
            return "crescente"; }

        else {
            return "nao crescente";
        }
    }
}
