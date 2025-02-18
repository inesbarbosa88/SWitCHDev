package org.example;

//Função que calcule a distância entre dois pontos no plano, A(x1,y1) e B(x2,y2).

public class Exercicio3 {
    public static double distancia (int x1, int x2,int y1,int y2) {
        double d= Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
        return Math.round(d*100)/100.0;
    }
}
