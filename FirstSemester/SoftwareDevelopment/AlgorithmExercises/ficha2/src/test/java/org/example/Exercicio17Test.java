package org.example;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class Exercicio17Test {

    @ParameterizedTest
    @CsvSource({
            "-2,-4,-3,-5,Não aplicável",
            "0,0,0,0,Não aplicável",
            "25,100,350,0,Não aplicável",
            "24,5,30,30,Não aplicável",
            "23,30,00,3600,00:30:00",
            "12,30,30,3600,13:30:30",
            "00,00,00,86400,00:00:00"


    })
    public void testExercicio17_com_parametros(int horas_tarefa, int minutos_tarefa, int segundos_tarefa, int duracao_em_segundoso, String resultadoEsperado) {
        String r = Exercicio17.tarefa(horas_tarefa, minutos_tarefa, segundos_tarefa, duracao_em_segundoso);
        assertEquals(resultadoEsperado, r);
    }
}