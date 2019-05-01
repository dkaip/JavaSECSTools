package com.CIMthetics.JavaSECSTools.SECSItemsTests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.CIMthetics.JavaSECSTools.SECSItems.F8ArraySECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.F8SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemFormatCode;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemNumLengthBytes;

/**
 * 
 * @author Douglas Kaip
 *
 */
public class F8ArraySECSItemTests
{
    @Test
    public void test01()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 40, 
                127, -17, -1, -1, -1, -1, -1, -1,
                0, 0, 0, 0, 0, 0, 0, 1,
                -1, -16, 0, 0, 0, 0, 0, 0,
                127, -16, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0 };
        F8ArraySECSItem secsItem = new F8ArraySECSItem(input, 0);
        assertTrue(secsItem.getValue()[0] == Double.MAX_VALUE);
        assertTrue(secsItem.getValue()[1] == Double.MIN_VALUE);
        assertTrue(secsItem.getValue()[2] == Double.NEGATIVE_INFINITY);
        assertTrue(secsItem.getValue()[3] == Double.POSITIVE_INFINITY);
        assertTrue(secsItem.getValue()[4] == 0.0D);
    }
    
    @Test
    public void test02()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 40, 
                127, -17, -1, -1, -1, -1, -1, -1,
                0, 0, 0, 0, 0, 0, 0, 1,
                -1, -16, 0, 0, 0, 0, 0, 0,
                127, -16, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0 };
        F8ArraySECSItem secsItem = new F8ArraySECSItem(input, 0);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.F8);
    }

    @Test
    public void test03()
    {
        double[] input = {Double.MAX_VALUE, Double.MIN_VALUE, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0D};
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 40, 
                127, -17, -1, -1, -1, -1, -1, -1,
                0, 0, 0, 0, 0, 0, 0, 1,
                -1, -16, 0, 0, 0, 0, 0, 0,
                127, -16, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0 };
        
        F8ArraySECSItem secsItem = new F8ArraySECSItem(input);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test04()
    {
        double[] input = {Double.MAX_VALUE, Double.MIN_VALUE, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0D};
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x02), 0, 40, 
                127, -17, -1, -1, -1, -1, -1, -1,
                0, 0, 0, 0, 0, 0, 0, 1,
                -1, -16, 0, 0, 0, 0, 0, 0,
                127, -16, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0 };
        
        F8ArraySECSItem secsItem = new F8ArraySECSItem(input, SECSItemNumLengthBytes.TWO);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test05()
    {
        double[] input = {Double.MAX_VALUE, Double.MIN_VALUE, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0D};
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x03), 0, 0, 40, 
                127, -17, -1, -1, -1, -1, -1, -1,
                0, 0, 0, 0, 0, 0, 0, 1,
                -1, -16, 0, 0, 0, 0, 0, 0,
                127, -16, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0 };
        
        F8ArraySECSItem secsItem = new F8ArraySECSItem(input, SECSItemNumLengthBytes.THREE);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    @DisplayName("Test toString()")
    public void test06()
    {
        double[] input = {Double.MAX_VALUE, Double.MIN_VALUE, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0D};

        F8ArraySECSItem secsItem = new F8ArraySECSItem(input, SECSItemNumLengthBytes.ONE);
        
        assertTrue(secsItem.toString().equals("Format:F8 Value: Array"));
    }

    @Test
    @DisplayName("Test hashCode()")
    public void test07()
    {
        double[] input = {Double.MAX_VALUE, Double.MIN_VALUE, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0D};

        F8ArraySECSItem secsItem = new F8ArraySECSItem(input, SECSItemNumLengthBytes.ONE);

        assertTrue(secsItem.hashCode() == 1002786077);
    }

    @Test
    @DisplayName("Test equals()")
    public void test08()
    {
        double[] input = {Double.MAX_VALUE, Double.MIN_VALUE, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0D};

        F8ArraySECSItem secsItem = new F8ArraySECSItem(input, SECSItemNumLengthBytes.ONE);

        assertTrue(secsItem.equals(secsItem));
    }

    @Test
    @DisplayName("Test equals()")
    public void test09()
    {
        double[] input = {Double.MAX_VALUE, Double.MIN_VALUE, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0D};

        F8ArraySECSItem secsItem = new F8ArraySECSItem(input, SECSItemNumLengthBytes.ONE);

        assertFalse(secsItem.equals(null));
    }

    @Test
    @DisplayName("Test equals()")
    public void test10()
    {
        double[] input = {Double.MAX_VALUE, Double.MIN_VALUE, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0D};

        F8ArraySECSItem secsItem1 = new F8ArraySECSItem(input, SECSItemNumLengthBytes.ONE);
        
        Object secsItem2 = new F8SECSItem(2.141592D);
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test11()
    {
        double[] input1 = {Double.MAX_VALUE, Double.MIN_VALUE, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0D};
        double[] input2 = {Double.MAX_VALUE, Double.MIN_VALUE, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0D};

        F8ArraySECSItem secsItem1 = new F8ArraySECSItem(input1, SECSItemNumLengthBytes.ONE);
        F8ArraySECSItem secsItem2 = new F8ArraySECSItem(input2, SECSItemNumLengthBytes.ONE);
        
        assertTrue(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test12()
    {
        double[] input1 = {Double.MAX_VALUE, Double.MIN_VALUE, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0D};
        double[] input2 = {Double.MAX_VALUE, Double.MIN_VALUE, Double.NEGATIVE_INFINITY, 0.0D, 0.0D};

        F8ArraySECSItem secsItem1 = new F8ArraySECSItem(input1, SECSItemNumLengthBytes.ONE);
        F8ArraySECSItem secsItem2 = new F8ArraySECSItem(input2, SECSItemNumLengthBytes.ONE);
        
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    public void test13()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 0x00 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        F8ArraySECSItem secsItem = new F8ArraySECSItem(input, 0);});
        assertEquals("Illegal data length of: 0 payload length must be a non-zero multiple of 8.", exception.getMessage());
    }

    @Test
    public void test14()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 0x03 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        F8ArraySECSItem secsItem = new F8ArraySECSItem(input, 0);});
        assertEquals("Illegal data length of: 3 payload length must be a non-zero multiple of 8.", exception.getMessage());
    }
}