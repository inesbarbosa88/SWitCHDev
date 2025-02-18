package org.example;

//Sabendo a hora de início de processamento de uma tarefa numa determinada
//máquina e o tempo que a tarefa demora a processar, calcule a hora de fim
//de processamento. O tempo de início de processamento é indicado em horas,
//minutos e segundos e o tempo de duração do processamento, em segundos. O
//tempo de fim de processamento deve ser indicado em horas, minutos e segundos.

public class Exercicio17 {
    public static String tarefa(int horas_tarefa, int minutos_tarefa, int segundos_tarefa, int duracao_em_segundos) {
        if (horas_tarefa < 0 || horas_tarefa >= 24 || minutos_tarefa < 0 || minutos_tarefa >= 60 || segundos_tarefa < 0 || segundos_tarefa >= 60 || duracao_em_segundos<=0) {
            return "Não aplicável";
        }
        int segundos_totais_inicio_tarefa = horas_tarefa * 3600 + 60 * minutos_tarefa + segundos_tarefa;
        int tempo_fim_processamento_em_segundos = segundos_totais_inicio_tarefa + duracao_em_segundos;

        if (tempo_fim_processamento_em_segundos >= 24 * 3600) {
            tempo_fim_processamento_em_segundos = tempo_fim_processamento_em_segundos - 24 * 3600;
        }

        int h = tempo_fim_processamento_em_segundos / 3600;
        int m = (tempo_fim_processamento_em_segundos % 3600) / 60;
        int seg = tempo_fim_processamento_em_segundos % 60;

        String resultado;

        resultado=  (h < 10 ? "0" + h : h) + ":" + (m < 10 ? "0" + m : m) + ":" + (seg < 10 ? "0" + seg : seg);

        return resultado;
    }
}

