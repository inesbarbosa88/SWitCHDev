package org.example;

//Pretende-se calcular o salário semanal de um empregado a partir do número
//de horas que trabalhou. Sabe-se que o número de horas semanais de trabalho é
//36 e que o preço da hora é de 7,5e. Se o empregado fizer horas extra (mais de
//36 horas) recebe 10e por cada uma das 5 primeiras horas extra e 15e por cada
//uma das restantes horas extra.

public class Exercicio18 {
    public static double salario (double n_horas_trabalho){
        if (n_horas_trabalho<0){
            return Double.NaN;
        }
        if (n_horas_trabalho<=36){
            return 7.5*n_horas_trabalho;
        }
        if (n_horas_trabalho<=41){
            return (n_horas_trabalho-36)*10+270;
        }
        else {
            return (n_horas_trabalho-41)*15+320;
        }
    }
}
