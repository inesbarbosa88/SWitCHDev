package org.example;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class Exercicio19Test {

    @ParameterizedTest
    @CsvSource({
            "D,palavra,Não aplicável",
            "C,dia 2, Não aplicável",
            "A,segunda,30 euros",
            "A,sabado,40 euros",
            "B,quinta,50 euros",
            "B,feriado,70 euros",
            "C,quarta,100 euros",
            "C,domingo,140 euros"
    })
    public void testExercicio19_com_parametros(String kit, String dia,String resultadoEsperado) {
        String r = Exercicio19.jardinagem(kit,dia);
        assertEquals(resultadoEsperado, r);
    }
}
