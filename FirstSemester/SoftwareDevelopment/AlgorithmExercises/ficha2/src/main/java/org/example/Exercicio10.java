package org.example;

//Calcule o preço de saldo de um artigo. Os descontos variam em função do
//preço, conforme:
// 60% se Preço > 200
// 40% se 100 < Preço <= 200
// 30% se 50 < Preço <= 100
// 20% se Preço <= 50

public class Exercicio10 {
    public static double desconto (double p){
        if (p<=0){
            return Double.NaN;
        }
        else if (p<=50){
                return (p-(0.2*p));
            }
        else if (p>50 && p<=100){
                return p-(0.3*p);
            }
        else if (p>100 && p<=200){
                return (p-(0.4*p));
            }
        else {
                return (p-(0.6*p));
            }
        }
    }

