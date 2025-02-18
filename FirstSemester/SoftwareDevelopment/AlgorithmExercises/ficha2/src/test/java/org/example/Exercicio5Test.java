package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Exercicio5Test {
    @ParameterizedTest
    @CsvSource({
            "0.00, -1",                     // área não positiva
            "-2, -1",                    // área não positiva
            "0.36, 1 pequeno",         // volume <= 1
            "1, 1 pequeno",         // volume <= 1
            "2, 1 pequeno",           // volume entre 1 e 2
            "8, 2 medio"             // volume entre 1 e 2
    })
    public void testExercicio5_com_parametros(double area, String resultadoEsperado) {
        String r = Exercicio5.conta(area);
        assertEquals(resultadoEsperado, r);
    }

    @Test
    void should_return_3_grande_if_area_equals_36() {
        String r = Exercicio5.conta(36);
        assertEquals("3 grande", r);
    }
    @Test
    void should_return__grande_if_area_equals_() {
        String r = Exercicio5.conta(6);
        assertEquals("1 pequeno", r);
    }
}