package com.myles.alatest;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import org.joda.time.DateTime;

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

    public final static String TOURNAMENT_MODE = "tournament";
    public final static String SEARCH_MODE = "search";
    
    public static void main( String[] args ){

        List<String> files = new ArrayList<String>();
        List<String> queries = new ArrayList<String>();
        String mode = "";

        // Parse in param: wrong input
        if (args.length == 0) {
            echo("Input wrong arguments");
            help();
            return;
        }

        // Parse in param: read mode 
        mode = args[0];

        // Parse in param: read the rest of param, index starts from 1 
        int i = 1;
        while (i < args.length){

            if (args[i].compareTo("-f") == 0){
                files.add(args[i+1]);
                i = i + 2;
            }else if (args[i].compareTo("-q") == 0){
                queries.add(args[i+1]);
                i = i + 2;
            }else{
                echo("Input wrong arguments");
                help();
                return;
            }

        }

        // Invoke method according to input
        if (mode.compareTo(TOURNAMENT_MODE) == 0){
            if (files.size() != 1 || queries.size() != 0 ){

                echo("Input wrong arguments");
                help();

            }else{

                String file = files.get(0);
                List<Search> searchs = new ArrayList<Search>();

                searchs.add(new BruteSearch());
                searchs.add(new AlphabeticSearch());

                tournament(file, searchs);

            }
        }

        if (mode.compareTo(SEARCH_MODE) == 0){
            if (files.size() == 0 || queries.size() == 0){

                echo("Input wrong arguments");
                help();

            }else{

                // use AlphabeticSearch as default
                search(files, queries, new AlphabeticSearch());

            }
        }

    }

    /**
     * Tournament method generate 1,000,000 random 20-digit number and 
     * test the performance of specified search agents.
     * @param String the phone operator file name
     * @param List<Search> a list of search agents that compete each others
     */
    private static void tournament(String file, List<Search> competitors){

        // Generate the list of samples
        int sampleSize = 1000000; 
        int sampleLengh = 20;
        String[] tokens = new String[sampleSize];
        Random r = new Random();
        for ( int i = 0 ; i < sampleSize; i++){
            tokens[i] = String.valueOf(r.nextInt((int)Math.pow(10, sampleLengh)));
        }

        // Search all and print the start and stop time
        DateTime start;
        DateTime stop;
        long duration;
        String searchName;
        PhoneOperatorMap operator = new PhoneOperatorMap();
        operator.load(file);
        for (Search s : competitors){
            searchName = s.getClass().getName();
            s.index(operator);
            start = DateTime.now();
            for ( int i = 0; i < sampleSize; i++){
                s.search(tokens[i]);
            }
            stop = DateTime.now();
            duration = stop.getMillis() - start.getMillis();
            echo(searchName + " starts at: " + start);
            echo(searchName + " stops at:  " + stop);
            echo(searchName + " comsumes:  " + duration);
            echo("---------------------------------");
        }

    }

    /**
     * Search method accept a list of files and a list of queries, and
     * search for result from every operator files for each queries and
     * return the phone cost onto screen.
     * @param List<String> list of files
     * @param List<String> list of queries 
     * @param Search selected search algorithm 
     */
    private static void search(List<String> files, List<String> queries, Search search){

        // Prepare operators
        List<PhoneOperatorMap> operators = new ArrayList<PhoneOperatorMap>();
        for (String f: files){
            PhoneOperatorMap op = new PhoneOperatorMap();
            op.load(f);
            op.setName(f);
            operators.add(op);
        }

        for (String q: queries){
            Double result;
            echo("Search " + q + " in input operators:");
            for (PhoneOperatorMap op: operators){
                search.reset();
                search.index(op);
                result = (Double)search.search(q);
                echo("  In operator " + op.getName() + ", cost is " + result.toString());
            }
            echo("------------------------------------");
        }

    }

    /**
     * Helper function: print string to stdout
     * @param String string to be printed
     */
    private static void echo(String s){
        System.out.println(s);
    }

    /**
     * Show help of Phone Routing Command Line Runner
     */
    private static void help(){
        String help =  
"NAME:       Phone Routing Problem Command Line Runner" +"\n" +
"" +"\n" +
"SYNOPSIS:   java com.myles.alatest.PhoneRoutingCommandLineRunner <mode> <file-args>" +"\n" +
"            [<file-args>] [<query-args>]" +"\n" +
"" +"\n" +
"MODE:       Two working mode is available. They are: tournament and search." +"\n" +
"" +"\n" +
"            Tournament mode will launch a performance comparison among 2 search" +"\n" +
"            algorithm implementation based on same operator file and 1,000,000" +"\n" +
"            random generated numbers. Note that tournament mode only accepts one" +"\n" +
"            set of <file-args>." +"\n" +
"" +"\n" +
"            Search mode will search through a given list of operators for each" +"\n" +
"            queries. Note that search mode accepts at least one set of <file-args>" +"\n" +
"            and at least one set of <query-args>." +"\n" +
"" +"\n" +
"ARGGS:      <file-args> composes an identifier '-f' followed by a path to an operator" +"\n" +
"            file. The operator file should contains a list of lines of prefix-cost" +"\n" +
"            pairs by which should be separated by a <tab> character between the prefix" +"\n" +
"            and the cost." +"\n" +
"" +"\n" +
"            <query-args> composes an identifier '-q' followed by a number to be" +"\n" +
"            searched." +"\n" +
"" +"\n" +
"EXAMPLE:" +"\n" +
"" +"\n" +
"*   To search the cost of number 4656311335 in operator_a and operator_b:" +"\n" +
"    java com.myles.alatest.PhoneRoutingCommandLineRunner search -f operator_a.txt -f operator_b.txt -q 4656311335" +"\n" +
"" +"\n" +
"*   To launch an tournament among 3 search algorithms based on operator_x:" +"\n" +
"    java com.myles.alatest.PhoneRoutingCommandLineRunner tournament -f operator_x.txt";
        System.out.println(help);
    }

}
