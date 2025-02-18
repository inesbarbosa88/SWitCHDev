package org.example;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class Exercicio10Test {

    @ParameterizedTest
    @CsvSource({
            "0, NaN",
            "50,40",
            "51,35.7",
            "100,70",
            "49,39.2",
            "99,69.3",
            "-20, NaN",
            "101,60.6",
            "199,119.4",
            "200,120",
            "201,80.4",
            "1,0.80",
            "50.10,35.07"
    })
    public void testExercicio10_com_parametros(double p, double resultadoEsperado) {
        double r = Exercicio10.desconto(p);
        assertEquals(resultadoEsperado, r, 0.01);
    }
}