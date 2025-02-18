package org.example;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class Exercicio15Test {

    @ParameterizedTest
    @CsvSource({

            "0,0,0, triangulo invalido",
            "0,90,90,triangulo invalido",
            "90,0,90,triangulo invalido",
            "90,90,0,triangulo invalido",
            "90,90,0,triangulo invalido",
            "0,0,90,triangulo invalido",
            "90,0,0,triangulo invalido",
            "0,90,0,triangulo invalido",
            "-10,100,90,triangulo invalido",
            "120,120,30,triangulo invalido",
            "90,-2,45,triangulo invalido",
            "10,0,-15,triangulo invalido",
            "-10,-20,-90,triangulo invalido",
            "10,20,0,triangulo invalido",
            "90,90,90,triangulo invalido",
            "90,56,34,Retângulo",
            "56,34,90,Retângulo",
            "56,90,34,Retângulo",
            "60,60,60,Acutângulo",
            "120,30,30,Obtusângulo",
            "30,120,30,Obtusângulo",
            "30,30,120,Obtusângulo",


    })
    public void testExercicio15_com_parametros(Double a,Double b,Double c, String resultadoEsperado) {
        String r = Exercicio15.triangulo(a,b,c);
        assertEquals(resultadoEsperado, r);
    }
}