package org.example;

public class Exercicio4 {
    public static int funcao (int x){
        int result;
        if (x<0){
            result= x;
        }
        else if (x==0){
            result=0;
        }
        else {
            result=x*x-2*x;
        }
        return result;
    }
}
