package com.myles.alatest;

/**
 * 
 *
 */
public class PhoneRoutingCommandLineRunner {
    public static void main( String[] args ){
        PhoneOperatorTable operatorA = new PhoneOperatorTable();
        PhoneOperatorTable operatorB = new PhoneOperatorTable();
        operatorA.loadTable("operator_a.txt");
        operatorB.loadTable("operator_b.txt");
        String token = "4673212345";
        double resultA = operatorA.search(token);   //should be 1.1
        double resultB = operatorB.search(token);   //should be 1.0
        System.out.println("Resuld A:" + resultA);
        System.out.println("Resuld B:" + resultB);
    }
}
