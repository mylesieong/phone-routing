package com.myles.alatest;

/**
 * This is the command line entrance of this project.
 *
 * By adding options to the runnable jar, which use this
 * class as main class, its can perform in 2 mode: service
 * mode and tournament mode. 
 *
 * In service mode, user can input numbers in stdin, and
 * the matched prefix and the cost will be searched and
 * showed onto stdout.
 *
 * In tournament mode, 10,000 random 12-digits number will 
 * be used to test specified search algorithm and result 
 * will be shown onto stdout. 
 *
 * @author  Myles Ieong
 * @see     README.md for available options    
 *
 */
public class PhoneRoutingCommandLineRunner {

    public final static int TOURNAMENT_MODE = 0;
    public final static int SERVICE_MODE = 1;

    public static void main( String[] args ){

        String tableA = "";
        String tableB = "";
        int mode;

        if (args.length < 5){   //There should be 5 input params

            help();      

        }else if (args.length == 5){

            if (args[0].compareTo("-f") == 0){
                tableA = args[1];
            }

            if (args[2].compareTo("-f") == 0){
                tableB = args[3];
            }

            if (args[4].compareTo("-t") == 0){
                mode = PhoneRoutingCommandLineRunner.TOURNAMENT_MODE;
            }

            if (args[4].compareTo("-s") == 0){
                mode = PhoneRoutingCommandLineRunner.SERVICE_MODE;
            }

        }else{

            help();

        }

        PhoneOperatorMap operatorA = new PhoneOperatorMap();
        PhoneOperatorMap operatorB = new PhoneOperatorMap();
        operatorA.load(tableA);
        operatorB.load(tableB);

        BruteSearch searchA = new BruteSearch();
        BruteSearch searchB = new BruteSearch();
        searchA.index(operatorA);
        searchB.index(operatorB);

        String token = "4673212345";
        Double resultA = searchA.search(token);   //should be 1.1
        Double resultB = searchB.search(token);   //should be 1.0

        System.out.println("Resuld A:" + resultA.toString());
        System.out.println("Resuld B:" + resultB.toString());
    }

    /**
     * Show help of Phone Routing Command Line Runner
     */
    private static void help(){
        System.out.println("Help guide.");
    }

}
