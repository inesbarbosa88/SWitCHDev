package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Exercicio12Test {

    @Test
    void should_return_nao_aplicavel_for_quantidade_equals_zero() {
        String r=Exercicio12.jardinagem(0,2,1);
        assertEquals("Nao aplicavel",r);
    }
    @Test
    void should_return_nao_aplicavel_for_quantidade_equals_negative_1() {
        String r=Exercicio12.jardinagem(-1,1,2);
        assertEquals("Nao aplicavel",r);
    }
    @Test
    void should_return_price_and_time_for_quantidade_equals_200() {
        String r=Exercicio12.jardinagem(200,200,200);
        assertEquals("Grama:602000.0euros,1000.0minutos.Árvores:1204000euros,2000minutos.Arbustos:771000.0euros,1280.0minutos.",r);

    }
}