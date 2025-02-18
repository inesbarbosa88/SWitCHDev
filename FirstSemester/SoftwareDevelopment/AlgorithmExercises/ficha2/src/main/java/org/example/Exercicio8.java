package org.example;

//Dados dois números (X e Y) indique se um é múltiplo do outro, retornando:
// -1 se X é múltiplo de Y
// 1 se Y é múltiplo de X
// 0 se nenhuma das anteriores

public class Exercicio8 {
    public static int multiplo (int x, int y){
        if (x==y){
            return 0;
        }
        if (x==0) {
            return 1;
        }
        else if (y==0 || x%y==0){
            return -1;
        }
        else if (y%x==0){
            return 1;
        }
        else {
            return 0;
        }
    }
}
