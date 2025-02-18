package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Exercicio9Test {

    @Test
        //arrange
    void should_return_NaN_for_x_equals_99() {
        //act
        String r= Exercicio9.crescente(99);
        //assert
        assertEquals("NaN",r); }

    @Test
        //arrange
    void should_return_decrescente_for_x_equals_100() {
        //act
        String r= Exercicio9.crescente(100);
        //assert
        assertEquals("nao crescente",r); }

    @Test
        //arrange
    void should_return_crescente_for_x_equals_123() {
        //act
        String r= Exercicio9.crescente(123);
        //assert
        assertEquals("crescente",r); }

    @Test
        //arrange
    void should_return_nao_crescente_for_x_equals_321() {
        //act
        String r= Exercicio9.crescente(321);
        //assert
        assertEquals("nao crescente",r); }

    @Test
        //arrange
    void should_return_NaN_for_x_equals_1000() {
        //act
        String r= Exercicio9.crescente(1000);
        //assert
        assertEquals("NaN",r); }

    @Test
        //arrange
    void should_return_nao_crescente_for_x_equals_999() {
        //act
        String r= Exercicio9.crescente(999);
        //assert
        assertEquals("nao crescente",r); }

    @Test
        //arrange
    void should_return_nao_crescente_for_x_equals_333() {
        //act
        String r= Exercicio9.crescente(333);
        //assert
        assertEquals("nao crescente",r); }

    @Test
        //arrange
    void should_return_nao_crescente_for_x_equals_132() {
        //act
        String r= Exercicio9.crescente(132);
        //assert
        assertEquals("nao crescente",r); }
    @Test
        //arrange
    void should_return_nao_crescente_for_x_equals_312() {
        //act
        String r= Exercicio9.crescente(312);
        //assert
        assertEquals("nao crescente",r); }
    @Test
        //arrange
    void should_return_nao_crescente_for_x_equals_213() {
        //act
        String r= Exercicio9.crescente(213);
        //assert
        assertEquals("nao crescente",r); }
    @Test
        //arrange
    void should_return_nao_crescente_for_x_equals_223() {
        //act
        String r= Exercicio9.crescente(223);
        //assert
        assertEquals("nao crescente",r); }
    @Test
        //arrange
    void should_return_nao_crescente_for_x_equals_233() {
        //act
        String r= Exercicio9.crescente(233);
        //assert
        assertEquals("nao crescente",r); }
}
