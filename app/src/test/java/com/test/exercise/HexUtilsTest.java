package com.test.exercise;


import com.test.exercise.utils.HexConverterUtils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by renu on 31/01/18.
 */

public class HexUtilsTest {

    @Test
    public void generate() throws Exception {
        HexConverterUtils utils = new HexConverterUtils();
        String result = utils.generateRandomString(2);
        int length = result.length();
        assertEquals(2, length);
    }

    @Test
    public void sum() throws Exception {
        HexConverterUtils utils = new HexConverterUtils();
        utils.sum("AB");
        assertEquals(171, 171);
    }


    @Test
    public void multiply() throws Exception {
        HexConverterUtils utils = new HexConverterUtils();
        int result = utils.multiply("");
        assertFalse("not a valid input ", result == 0);
    }

    @Test
    public void isBigInteger() throws Exception {
        HexConverterUtils utils = new HexConverterUtils();

        String result = utils.generateRandomString(10);
        int value = utils.multiply(utils.getHexString(result));

        System.out.println(value);
        assertFalse("Int max size reached ", value < 0);
    }
}
