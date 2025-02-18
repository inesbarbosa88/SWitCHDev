package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Exercicio11Test {

    @Test
        //arrange
    void should_return_aceitavel_for_i_equals_0() {
        //act
        String r= Exercicio11.notificacao(0);
        //assert
        assertEquals("Indice Poluição Aceitável",r); }

     @Test
    //arrange
     void should_return_grupo1_for_i_equals_0_3() {
    //act
    String r= Exercicio11.notificacao(0.3);
    //assert
    assertEquals("Indice Poluição Aceitável",r); }

@Test
    //arrange
void should_return_grupo1_for_i_equals_0_35 (){
    //act
    String r= Exercicio11.notificacao(0.35);
    //assert
    assertEquals("Indice Poluição Não Aceitável: Grupo 1 deverá suspender atividade",r); }

    @Test
    //arrange
    void should_return_grupo1_for_i_equals_0_4() {
        //act
        String r= Exercicio11.notificacao(0.4);
        //assert
        assertEquals("Indice Poluição Não Aceitável: Grupo 1 deverá suspender atividade",r); }

    @Test
        //arrange
    void should_return_grupo1e2_for_i_equals_0_45() {
        //act
        String r= Exercicio11.notificacao(0.45);
        //assert
        assertEquals("Indice Poluição Não Aceitável: Grupo 1 e Grupo 2 deverão suspender atividade",r); }

    @Test
        //arrange
    void should_return_grupo12_for_i_equals_0_5() {
        //act
        String r= Exercicio11.notificacao(0.5);
        //assert
        assertEquals("Indice Poluição Não Aceitável: Grupo 1 e Grupo 2 deverão suspender atividade",r); }

    @Test
        //arrange
    void should_return_grupo12e3_for_i_equals_0_6() {
        //act
        String r= Exercicio11.notificacao(0.6);
        //assert
        assertEquals("Indice Poluição Não Aceitável: Grupo 1, 2 e 3 deverão suspender atividade",r); }

    @Test
        //arrange
    void should_return_grupo_naoaplicavel_for_i_equals_negative_number() {
        //act
        String r= Exercicio11.notificacao(-2);
        //assert
        assertEquals("Não aplicável",r); }
}