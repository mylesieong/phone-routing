package com.myles.alatest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class SearchTest extends TestCase
{
    private List<Search> searches; 
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SearchTest( String testName ){
        super( testName );

        // Define stub map object
        Map<String, Double> map = new HashMap<String, Double>();
        map.put("3", 1.1);
        map.put("52", 1.5);
        map.put("462", 1.3);
        map.put("48", 1.4);
        map.put("46", 1.2);
        
        // Setup testees
        searches = new ArrayList<Search>();
        
        Search brutes = new BruteSearch();
        brutes.index(map);
        searches.add(brutes);
        
        Search binarys = new BinarySearch();
        binarys.index(map);
        searches.add(binarys);
        
        Search alphas = new AlphabeticSearch();
        alphas.index(map);
        searches.add(alphas);
        
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite(){
        return new TestSuite(SearchTest.class);
    }

    /**
     *
     */
    public void testSearchNumberNotAvailable(){
        String token = "12496653";    
        for (Search s: searches){
            Double d = (Double)s.search(token);
            assertTrue( d.doubleValue() == -1 );
        }
    }
    
    public void testSearchMatchLongCase(){
        String token = "46259999";
        for (Search s: searches){
            Double d = (Double)s.search(token);
            assertTrue( d.doubleValue() == 1.3 );
        }
    }

    public void testSearchMatchMiddleCase(){
        String token = "46992299";
        for (Search s: searches){
            Double d = (Double)s.search(token);
            assertTrue( d.doubleValue() == 1.2 );
        }
    }
    
    public void testSearchMatchFirstDigitCase(){
        String token = "34992277";
        for (Search s: searches){
            Double d = (Double)s.search(token);
            assertTrue( d.doubleValue() == 1.1 );
        }
    }
    
    
}

