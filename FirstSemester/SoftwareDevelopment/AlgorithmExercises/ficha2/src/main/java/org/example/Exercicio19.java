package org.example;
import java.util.Arrays;


public class Exercicio19 {
    public static String jardinagem(String kit, String dia) {

        String[] tipo_de_kit = {"A", "B", "C"};

        if (!Arrays.asList(tipo_de_kit).contains(kit)) {
            return "Não aplicável";
        }

        String[] dia_da_semana = {"segunda", "terça", "quarta", "quinta", "sexta", "sabado", "domingo", "feriado"};

        if (!Arrays.asList(dia_da_semana).contains(dia)) {
            return "Não aplicável";
        }

        String[] dia_util = {"segunda", "terça", "quarta", "quinta", "sexta"};

        if (kit.equals("A")) {
            return Arrays.asList(dia_util).contains(dia) ? "30 euros" : "40 euros";
        }
        else if (kit.equals("B")) {
            return Arrays.asList(dia_util).contains(dia) ? "50 euros" : "70 euros";
        }
        else {
            return Arrays.asList(dia_util).contains(dia) ? "100 euros" : "140 euros";
        }
    }
}
