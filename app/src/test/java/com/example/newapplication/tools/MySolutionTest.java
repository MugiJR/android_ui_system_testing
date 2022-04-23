package com.example.newapplication.tools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class MySolutionTest {

    @Test
    public void findSuffix_withValidInput1() {

        MySolution mySol = new MySolution("Traveltime", 5);
        try {
            assertEquals("ltime",mySol.findSuffix());
        }
        catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void findSuffix_withValidInput2() {

        MySolution mySol = new MySolution("Traveltime", 10);
        try {
            assertEquals("Traveltime",mySol.findSuffix());
        }
        catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void findSuffix_withValidInput3() {

        MySolution mySol = new MySolution("Traveltime", 1);
        try {
            assertEquals("e",mySol.findSuffix());
        }
        catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void findSuffix_withZeroCharIndex() {

        MySolution mySol = new MySolution("Traveltime", 0);
        try {
            mySol.findSuffix();
            fail();
        }
        catch (Exception ex) {
            assertEquals("Character index must be greater than 0", ex.getMessage() );
        }
    }

    @Test
    public void findSuffix_withNegativeCharIndex() {

        MySolution mySol = new MySolution("Traveltime", -5);
        try {
            mySol.findSuffix();
            fail();
        }
        catch (Exception ex) {
            assertEquals("Character index must be greater than 0", ex.getMessage() );
        }
    }

    @Test
    public void findSuffix_withGreaterCharIndex_thanInput1() {

        MySolution mySol = new MySolution("Traveltime", 11);
        try {
            mySol.findSuffix();
            fail();
        }
        catch (Exception ex) {
            assertEquals("Character index can't be greater than input1 length", ex.getMessage() );
        }
    }

}
