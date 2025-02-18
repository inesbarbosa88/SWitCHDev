package org.example;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class Exercicio3Test {

@ParameterizedTest
@CsvSource({

        "3,2,3,5,2.24",
        "4,5,4,6,2.24",
        "3,-5,3,2,8.06",
        "3,4,2,-5,7.07",
        "2,2,3,3,0",
        "1,2,1,2,1.41"

})
public void testExercicio3_com_parametros (int x1, int y1, int x2, int y2, double ResultEsperado) {
    double r = Exercicio3.distancia(x1, y1, x2, y2);
    assertEquals(ResultEsperado, r);
}
}