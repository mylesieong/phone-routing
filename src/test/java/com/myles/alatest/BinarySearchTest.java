package com.myles.alatest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class BinarySearchTest extends TestCase
{
    private BinarySearch bs; 
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public BinarySearchTest( String testName ){
        super( testName );
        bs = new BinarySearch();
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite(){
        return new TestSuite( BinarySearchTest.class );
    }

    /**
     *
     */
    public void testCompareDifferentInitialPrefix(){
        String tee = "1";
        String ter = "46";
        assertTrue( bs.comparePrefix(tee, ter) == -1 );
    }
    public void testCompareDifferentInitialPrefix2(){
        String tee = "46";
        String ter = "1";
        assertTrue( bs.comparePrefix(tee, ter) == 1 );
    }
    public void testCompareSameInitialPrefix(){
        String tee = "462";
        String ter = "46";
        assertTrue( bs.comparePrefix(tee, ter) == 1 );
    }
    public void testCompareSameInitialPrefix2(){
        String tee = "46";
        String ter = "462";
        assertTrue( bs.comparePrefix(tee, ter) == -1 );
    }
    public void testCompareEqualPrefix(){
        String tee = "46";
        String ter = "46";
        assertTrue( bs.comparePrefix(tee, ter) == 0 );
    }
}
