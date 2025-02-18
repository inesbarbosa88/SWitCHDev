package org.example;

//A partir de cinco valores que indicam as distâncias diárias em milhas percorrida
//por um estafeta, durante uma semana de trabalho (5 dias), calcule a
//distância média diária em quilómetros. A conversão faz-se com base na fórmula:
//1 milha = 1609 metros.

public class Exercicio13 {
    public static Double milha(double d1, double d2, double d3, double d4, double d5) {
        if (d1 >= 0 && d2 >= 0 && d3 >= 0 && d4 >= 0 && d5 >= 0) {
            Double media = (d1 + d2 + d3 + d4 + d5) / 5;
            Double mediakm = media * 1.60934;
            return mediakm;
        }
        else return Double.NaN;
    }
}
