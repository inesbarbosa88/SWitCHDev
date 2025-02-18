package org.example;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class Exercicio14Test {

    @ParameterizedTest
    @CsvSource({
            "2,2,1,Isósceles",
            "-1,3,2,NaN",
            "1,-2,3,NaN",
            "1,2,-3,NaN",
            "0,20,30,NaN",
            "20,0,30,NaN",
            "20,30,0,NaN",
            "2,2,2,Equilátero",
            "3,5,3,Isósceles",
            "1,3,3,Isósceles",
            "1,4,3,Escaleno"

    })
    public void testExercicio14_com_parametros(Double a,Double b,Double c, String resultadoEsperado) {
        String r = Exercicio14.triangulo(a,b,c);
        assertEquals(resultadoEsperado, r);
    }
}