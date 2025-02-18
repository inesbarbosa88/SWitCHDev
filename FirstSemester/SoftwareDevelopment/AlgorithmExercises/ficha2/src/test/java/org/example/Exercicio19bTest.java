package org.example;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class Exercicio19bTest {

    @ParameterizedTest
    @CsvSource({
            "D,palavra,20,-1.0",
            "A,segunda,-20,-1.0",
            "A,sabado,0,-1.0",
            "A,sabado,30,100.0",
            "A,segunda,30,90.0",
            "B,quinta,2,54.0",
            "B,feriado,5,80.0",
            "C,quarta,30,160.0",
            "C,domingo,4,148.0",
            "D,domingo,4,-1"
    })
    public void testExercicio19b_com_parametros(String kit, String dia,double distancia,double resultadoEsperado) {
        double r = Exercicio19b.jardinagemb(kit,dia,distancia);
        assertEquals(resultadoEsperado, r);
    }
}