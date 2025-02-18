package org.example;

//Dados 3 valores (a, b, c) representativos das medidas dos ângulos de um triângulo,
//classifique-o quanto aos ângulos (retângulo, acutângulo e obtusângulo).
//NB: Um triângulo diz-se retângulo se tem um ângulo reto, obtusângulo se tem
//um ângulo obtuso (maior do que 90º) ou acutângulo se todos os ângulos são
//agudos (menores do que 90º).

public class Exercicio15 {
    public static String triangulo (double a, double b, double c){
        if (a<=0 || b<=0 || c<=0){
            return "triangulo invalido";
        }
        else if (a + b + c != 180){
            return "triangulo invalido";
        }
        else if (a==90 || b==90 || c==90){
            return "Retângulo";
        }
        else if (a>90 || b>90 || c>90){
            return "Obtusângulo";
        }
        else {
            return "Acutângulo";
        }
    }
}
