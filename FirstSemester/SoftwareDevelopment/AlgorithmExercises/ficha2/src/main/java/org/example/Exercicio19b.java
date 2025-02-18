package org.example;

import java.util.Arrays;

public class Exercicio19b {
    public static double jardinagemb(String kit, String dia, double distancia) {

        String[] dias_uteis = {"segunda", "terca", "quarta", "quinta", "sexta"};
        boolean dia_util = Arrays.asList(dias_uteis).contains(dia);

        if (!dia_util && !dia.equals("sabado") && !dia.equals("domingo") && !dia.equals("feriado")) {
            return -1;
        }
        else if (distancia <= 0) {
            return -1;
        }
        else {
            switch (kit) {
                case "A":
                    return ((dia_util ? 30 : 40) + 2 * distancia);
                case "B":
                    return ((dia_util ? 50 : 70) + 2 * distancia);
                case "C":
                    return ((dia_util ? 100 : 140) + 2 * distancia);
                default:
                    return -1;
            }
        }

    }
}