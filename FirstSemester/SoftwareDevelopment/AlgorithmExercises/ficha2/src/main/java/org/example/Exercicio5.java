package org.example;

public class Exercicio5 {
    public static String conta (double area) {
        String resultado;
        if (area > 0) {
        double aresta = Math.sqrt(area/6);
        double volume = Math.pow(aresta, 3);

            if (volume <= 1) {
                resultado = "1 pequeno"; }
            else if (volume <= 2) {
                resultado = "2 medio"; }
            else {
                resultado = "3 grande";}
        }
        else {
            resultado="-1";
        }
        return resultado;
    }
}
