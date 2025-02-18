package org.example;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class Exercicio16Test {

    @ParameterizedTest
    @CsvSource({
            "24:60, 24:60,Não aplicável",
            "25:70, 25:70,Não aplicável",
            "12:00,25:00,Não aplicável",
            "123:60,126:50,Não aplicável",
            "-1:50,-3:40,Não aplicável",
            "13:50,02:30,16:20",
            "20:00,04:00,00:00",
            "20:00,05:01,01:01",
            "20:00,14:10,10:10",
            "00:00,00:00,Não aplicável"

    })
    public void testExercicio16_com_parametros(String partida,String duracao, String resultadoEsperado) {
        String r = Exercicio16.comboio(partida,duracao);
        assertEquals(resultadoEsperado, r);
    }
}