package org.example;

//Dados 3 valores (a, b, c) representativos das medidas dos lados de um
//triângulo, classifique-o quanto aos lados (equilátero, isósceles e escaleno).

public class Exercicio14 {
    public static String triangulo (double a,double b, double c){
        if (a<=0||b<=0 || c<=0) {
            return "NaN";
        }
        else if (a==b && b==c){
            return "Equilátero";
        }
        else if (a!=b && b!=c && a!=c){
            return "Escaleno";
        }
        else{
            return "Isósceles";
        }
    }
}
