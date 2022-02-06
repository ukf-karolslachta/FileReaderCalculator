package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.testng.AssertJUnit.assertNotNull;

class CalculatorTest {

    Calculator calculator = null;
    String path = "C:\\Users\\karol\\IdeaProjects\\Calculator\\src\\Example1.txt";

    @Test
    void openFile() {
        calculator = new Calculator(path);
        assertNotNull(calculator);
    }

    @Test
    void read() {
        calculator = new Calculator(path);
        assertNotNull(calculator.getScanner());
    }

    @Test
    void lenghtofArrays(){
        calculator = new Calculator(path);
        if(calculator.getArrayLenghtWord() != calculator.getArrayLenghtNumber()){
            fail("Lenght both arraylist must be same");
        }
    }

    @Test
    void notnullLenght(){
        calculator = new Calculator(path);
        assertNotNull(calculator.getArrayLenghtWord());
        assertNotNull(calculator.getArrayLenghtNumber());
    }

    @Test
    void checkingInstructionAndNumbers(){
        calculator = new Calculator(path);
        System.out.println(calculator.getArrayWord());
        System.out.println(calculator.getArrayNumbers());
        if(calculator.checkIaN(calculator.getArrayWord(), calculator.getArrayNumbers())==true){

        } else {
            fail("Warning instructions with numbers and numbers with instructions");
        }
    }

    @Test
    void divideWithZero(){
        calculator = new Calculator(path);
        if(calculator.calculation() == -1){
            fail("Warning dividing with zero");
        }
    }

    @Test
    void divideWithNoNumber(){
        calculator = new Calculator(path);
        if(calculator.chceckDivisor(calculator.getArrayNumbers())==true){

        } else {
            fail("Warning divide with no number");
        }
    }

    @Test
    void ChceckingApply(){
        calculator = new Calculator(path);
        if(!calculator.chcekingApply(calculator.getArrayWord())){
            fail("Warning file doesnt have apply");
        }
    }


    @Test
    void chceckExample3(){
        calculator = new Calculator(path);
        assertEquals(18.0,calculator.calculation());
    }

}