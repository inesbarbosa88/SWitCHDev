package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Exercicio13Test {

    @Test
        //arrange
    void should_return_3_22_for_d_equals_positive_numbers() {
        //act
        Double r= Exercicio13.milha(2,3,1,3,1);
        //assert
        assertEquals(3.22,r,0.01); }
    @Test
        //arrange
    void should_return_NaN_for_d1_equals_0() {
        //act
        Double r= Exercicio13.milha(0,3,1,3,1);
        //assert
        assertEquals(2.57,r,0.01); }
    @Test
        //arrange
    void should_return_NaN_for_all_equals_0() {
        //act
        Double r= Exercicio13.milha(0,0,0,0,0);
        //assert
        assertEquals(0,r,0.01); }

    @Test
        //arrange
    void should_return_NaN_for_d1_equals_negative_number() {
        //act
        Double r= Exercicio13.milha(-3,3,1,5,2);
        //assert
        assertEquals(Double.NaN,r); }
    @Test
        //arrange
    void should_return_NaN_for_d1_d3__negative() {
        //act
        Double r= Exercicio13.milha(1,-4,6,5,6);
        //assert
        assertEquals(Double.NaN,r); }
    @Test
        //arrange
    void should_return_NaN_for_d5_d2__negative() {
        //act
        Double r= Exercicio13.milha(1,4,-6,5,6);
        //assert
        assertEquals(Double.NaN,r); }
    @Test
        //arrange
    void should_return_NaN_for_d4__negative() {
        //act
        Double r= Exercicio13.milha(1,4,6,-5,6);
        //assert
        assertEquals(Double.NaN,r); }
    @Test
        //arrange
    void should_return_NaN_for_d5__negative() {
        //act
        Double r= Exercicio13.milha(1,4,6,5,-6);
        //assert
        assertEquals(Double.NaN,r); }
}