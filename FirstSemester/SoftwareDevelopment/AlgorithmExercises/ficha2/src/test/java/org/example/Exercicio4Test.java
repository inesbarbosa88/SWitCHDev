package org.example;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class Exercicio4Test {

    @ParameterizedTest
    @CsvSource({

            "-5,-5",
            "0,0",
            "4,8",
            "1,-1",
            "2,0",
            "-2,-2",
            "-1,-1"

    })
    public void testExercicio4_com_parametros(int x, int ResultEsperado) {
        double r = Exercicio4.funcao(x);
        assertEquals(ResultEsperado, r);
    }
}


