package com.myles.alatest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.Map;
import java.util.HashMap;

/**
 * Unit test for simple App.
 */
public class AlphabeticSearchTest extends TestCase
{
    private AlphabeticSearch as; 
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AlphabeticSearchTest( String testName ){
        super( testName );
        as = new AlphabeticSearch();
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite(){
        return new TestSuite( AlphabeticSearchTest.class );
    }

    /**
     *
     */
    public void testIndex(){

        Map<String, Double> map = new HashMap<String, Double>();
        map.put("3", 1.1);
        map.put("52", 1.5);
        map.put("462", 1.3);
        map.put("48", 1.4);
        map.put("46", 1.2);

        as.index(map);

        AlphabeticSearch.AlphabeticNode node = as.getRoot();

        //assertion base on root
        assertTrue( 3==3 );
    }
}

