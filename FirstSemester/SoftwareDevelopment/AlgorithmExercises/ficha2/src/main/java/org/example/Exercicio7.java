package org.example;

public class Exercicio7 {

    public static String tempo(int s) {

        if (s < 0) {
            return "-1";
        }
        else {
            int h = s / 3600;
            int m = (s % 3600) / 60;
            int seg = s % 60;

            if ((h >= 6 && h < 12) || (h == 12 && m == 0 && seg == 0)) {
                return "Bom dia";
            }
            else if ((h >= 12 && h < 20) || (h == 20 && m == 0 && seg == 0)) {
                return "Boa tarde";
            }
            else {
                return "Boa noite";
            }
        }
    }
}