package org.example;

public class Exercicio12 {
    public static String jardinagem(double quantidade_grama, int quantidade_arvores, int quantidade_arbusto) {
        if (quantidade_grama<= 0 || quantidade_arvores<=0 || quantidade_arbusto<=0) {
            return "Nao aplicavel";
        }
        else {
            Double preco_grama = 10 * quantidade_grama + 5 * quantidade_grama * 600;
            Double tempo_grama = 5 * quantidade_grama;
            int preco_arvores = 20 * quantidade_arvores + 10 * quantidade_arvores * 600;
            int tempo_arvores = 10 * quantidade_arvores;
            Double preco_arbusto = 15 * quantidade_arbusto + 6.40 * quantidade_arbusto * 600;
            Double tempo_arbusto = 6.40 * quantidade_arbusto;
            String resultado = "Grama:" + preco_grama + "euros," + tempo_grama + "minutos." + "Árvores:" + preco_arvores + "euros," + tempo_arvores + "minutos." + "Arbustos:" + preco_arbusto + "euros," + tempo_arbusto + "minutos.";
            return resultado;
        }
    }
}