package org.example;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class Exercicio18Test {

    @ParameterizedTest
    @CsvSource({
            "-2,NaN",
            "0,0",
            "20,150",
            "35,262.5",
            "37,280",
            "36,270",
            "40,310",
            "41,320",
            "41.5,327.5",
            "42,335",
            "50,455"
    })
    public void testExercicio18_com_parametros(double n_horas_trabalho,double resultadoEsperado) {
        double r = Exercicio18.salario(n_horas_trabalho);
        assertEquals(resultadoEsperado, r);
    }
}