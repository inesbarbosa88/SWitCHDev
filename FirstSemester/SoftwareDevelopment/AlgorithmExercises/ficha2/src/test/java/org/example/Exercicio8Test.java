package org.example;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class Exercicio8Test {

    @ParameterizedTest
    @CsvSource({
            "2,1,-1",
            "1,2,1",
            "3,2,0",
            "0,2,1",
            "2,0,-1",
            "-3,1,-1",
            "2,2,0"

    })
    public void testExercicio8_com_parametros(int x, int y,int resultadoEsperado) {
        int r = Exercicio8.multiplo(x,y);
        assertEquals(resultadoEsperado, r);
    }
}