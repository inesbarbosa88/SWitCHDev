package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Exercicio7Test {

    @Test
    //arrange
    void should_return_bom_dia_for_s_equals_25200() {
        //act
        String r= Exercicio7.tempo(25200);
        //assert
        assertEquals("Bom dia",r);

    }
    @Test
        //arrange
    void should_return_bom_dia_for_s_equals_43201() {
        //act
        String r= Exercicio7.tempo(43201);
        //assert
        assertEquals("Boa tarde",r);

    }

    @Test
        //arrange
    void should_return_negative_1_for_s_equals_negative_6() {
        //act
        String r= Exercicio7.tempo(-21600);
        //assert
        assertEquals("-1",r); }

    @Test
        //arrange
    void should_return_bomdia_for_s_equals_21600() {
        //act
        String r= Exercicio7.tempo(21600);
        //assert
        assertEquals("Bom dia",r); }

    @Test
        //arrange
    void should_return_boatarde_for_s_equals_43200() {
        //act
        String r= Exercicio7.tempo(43200);
        //assert
        assertEquals("Bom dia",r); }

    @Test
        //arrange
    void should_return_boatarde_for_s_equals_72000() {
        //act
        String r= Exercicio7.tempo(72000);
        //assert
        assertEquals("Boa tarde",r); }

    @Test
        //arrange
    void should_return_boanoite_for_s_equals_72001() {
        //act
        String r= Exercicio7.tempo(72001);
        //assert
        assertEquals("Boa noite",r); }

    @Test
        //arrange
    void should_return_boa_noite_for_s_equals_21599() {
        //act
        String r= Exercicio7.tempo(21599);
        //assert
        assertEquals("Boa noite",r); }

    @Test
        //arrange
    void should_return_boanoite_for_s_equals_0() {
        //act
        String r= Exercicio7.tempo(0);
        //assert
        assertEquals("Boa noite",r); }

}
