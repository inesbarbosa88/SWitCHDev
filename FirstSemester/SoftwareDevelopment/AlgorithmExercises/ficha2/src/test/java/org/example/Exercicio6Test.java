package org.example;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class Exercicio6Test {

    @ParameterizedTest
    @CsvSource({
            "-2, -1",
            "0, 0:0:0",
            "60,0:1:0",
            "3600,1:0:0",
            "96401, -1"

    })
    public void testExercicio6_com_parametros(int s, String resultadoEsperado) {
        String r = Exercicio6.tempo(s);
        assertEquals(resultadoEsperado, r);
    }
}