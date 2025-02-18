package org.example;

//Conhecida a hora de partida (horas e minutos) e a duração da viagem
//(horas e minutos), calcule a hora de chegada de um determinado comboio (horas
//e minutos). Considere que a duração da viagem nunca é superior a 24 horas.

public class Exercicio16 {
    public static String comboio(String partida, String duracao){ // "12:30" "03:27"
        if (partida.length() != 5 || duracao.length() != 5) {
            return "Não aplicável";
        }

        int hora_partida = Integer.parseInt(partida.substring(0, 2));
        int minutos_partida = Integer.parseInt(partida.substring(partida.length()-2));
        int hora_duracao = Integer.parseInt(duracao.substring(0, 2));
        int minutos_duracao = Integer.parseInt(duracao.substring(duracao.length()-2));

        if(hora_partida>=24 || minutos_partida>=60 || hora_duracao>=24 || minutos_duracao>=60){
            return "Não aplicável";
        }

        int partida_em_segundos = 3600*hora_partida + 60*minutos_partida;
        int duracao_em_segundos = 3600*hora_duracao + 60*minutos_duracao;

        if (partida_em_segundos < 0 || duracao_em_segundos<=0) {
            return "Não aplicável";
        }

        int chegada_em_segundos = partida_em_segundos + duracao_em_segundos;

        if (chegada_em_segundos >= 24*3600) {
            chegada_em_segundos = chegada_em_segundos - 24*3600;
        }

        int hora_chegada = chegada_em_segundos/3600;
        int minutos_chegada = (chegada_em_segundos%3600)/60;

        String str_hora_chegada;
        if (hora_chegada < 10) {
            str_hora_chegada = "0" + hora_chegada;
        }
        else {
            str_hora_chegada = Integer.toString(hora_chegada);
        }

        // operador ternário - faz exatamente a mesma coisa que o if/else acima
        String str_minutos_chegada = minutos_chegada < 10 ? "0" + minutos_chegada : Integer.toString(minutos_chegada);

        String resultado = str_hora_chegada + ":" + str_minutos_chegada;
        return resultado;
    }
}
