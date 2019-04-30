package com.CIMthetics.JavaSECSTools.SECSItemsTests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.CIMthetics.JavaSECSTools.SECSItems.F4SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.F8SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemFormatCode;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemNumLengthBytes;

public class F8SECSItemTests
{
    @Test
    public void test01()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 0x08, 127, -17, -1, -1, -1, -1, -1, -1 };
        double expectedOutput = Double.MAX_VALUE;
        F8SECSItem secsItem = new F8SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test02()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 0x08, 0, 0, 0, 0, 0, 0, 0, 1 };
        double expectedOutput = Double.MIN_VALUE;
        F8SECSItem secsItem = new F8SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test03()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 0x08, -1, -16, 0, 0, 0, 0, 0, 0 };
        double expectedOutput = Double.NEGATIVE_INFINITY;
        F8SECSItem secsItem = new F8SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test04()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 0x08, 127, -16, 0, 0, 0, 0, 0, 0 };
        double expectedOutput = Double.POSITIVE_INFINITY;
        F8SECSItem secsItem = new F8SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test05()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 0x08, 0, 0, 0, 0, 0, 0, 0, 0 };
        double expectedOutput = 0.0D;
        F8SECSItem secsItem = new F8SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test06()
    {
        double expectedOutput = 3.141592D;
        F8SECSItem secsItem = new F8SECSItem(expectedOutput);
        assertTrue(secsItem.getValue() == expectedOutput);
    }
    
    @Test
    public void test07()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 0x08, 127, -17, -1, -1, -1, -1, -1, -1 };
        F8SECSItem secsItem = new F8SECSItem(input, 0);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.F8);
    }

    @Test
    public void test08()
    {
        double expectedOutput = 3.141592D;
        F8SECSItem secsItem = new F8SECSItem(expectedOutput);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.F8);
    }
    
    @Test
    public void test09()
    {
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 0x08, 127, -16, 0, 0, 0, 0, 0, 0 };
        
        F8SECSItem secsItem = new F8SECSItem(Double.POSITIVE_INFINITY);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test10()
    {
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x02), 0, 0x08, 127, -16, 0, 0, 0, 0, 0, 0 };
        
        F8SECSItem secsItem = new F8SECSItem(Double.POSITIVE_INFINITY, SECSItemNumLengthBytes.TWO);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test11()
    {
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x03), 0, 0, 0x08, 127, -16, 0, 0, 0, 0, 0, 0 };
        
        F8SECSItem secsItem = new F8SECSItem(Double.POSITIVE_INFINITY, SECSItemNumLengthBytes.THREE);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test12()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 0x00 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        F8SECSItem secsItem = new F8SECSItem(input, 0);});
        assertEquals("Illegal data length of: 0.  The length of the data independent of the item header must be 8.", exception.getMessage());
    }

    @Test
    public void test13()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 0x09 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        F8SECSItem secsItem = new F8SECSItem(input, 0);});
        assertEquals("Illegal data length of: 9.  The length of the data independent of the item header must be 8.", exception.getMessage());
    }

    @Test
    @DisplayName("Test toString()")
    public void test14()
    {
        F8SECSItem secsItem = new F8SECSItem(3.141592D);
        assertTrue(secsItem.toString().equals("Format:F8 Value: 3.141592"));
    }

    @Test
    @DisplayName("Test equals()")
    public void test15()
    {
        F8SECSItem secsItem1 = new F8SECSItem(3.141592D);
        F8SECSItem secsItem2 = new F8SECSItem(3.141592D);
        assertTrue(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test16()
    {
        F8SECSItem secsItem1 = new F8SECSItem(3.141592D);
        F8SECSItem secsItem2 = new F8SECSItem(2.141592D);
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test17()
    {
        F8SECSItem secsItem1 = new F8SECSItem(3.141592D);
        F8SECSItem secsItem2 = null;
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test18()
    {
        F8SECSItem secsItem1 = new F8SECSItem(3.141592D);
        assertTrue(secsItem1.equals(secsItem1));
    }

    @Test
    @DisplayName("Test equals()")
    public void test19()
    {
        F8SECSItem secsItem1 = new F8SECSItem(3.141592D);
        SECSItem secsItem2 = null;
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test20()
    {
        F8SECSItem secsItem1 = new F8SECSItem(3.141592D);
        Object secsItem2 = new F4SECSItem(2.141592F);
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test hashCode()")
    public void test21()
    {
        F8SECSItem secsItem1 = new F8SECSItem(3.141592D);
        assertTrue(secsItem1.hashCode() == -1132322401);
    }

    @Test
    @DisplayName("Test hashCode()")
    public void test22()
    {
        F8SECSItem secsItem1 = new F8SECSItem(3.141592D);
        F8SECSItem secsItem2 = new F8SECSItem(3.141592D);
        assertTrue(secsItem1.hashCode() == secsItem2.hashCode());
    }
}
