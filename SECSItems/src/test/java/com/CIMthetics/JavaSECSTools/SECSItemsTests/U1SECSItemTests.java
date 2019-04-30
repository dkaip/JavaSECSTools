package com.CIMthetics.JavaSECSTools.SECSItemsTests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.CIMthetics.JavaSECSTools.SECSItems.F8SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemFormatCode;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemNumLengthBytes;
import com.CIMthetics.JavaSECSTools.SECSItems.U1SECSItem;

public class U1SECSItemTests
{
    @Test
    public void test01()
    {
        short value = 255;
        U1SECSItem secsItem = new U1SECSItem(value);
        assertTrue(secsItem.getValue() == 255);
    }

    @Test
    public void test02()
    {
        short value = 128;
        U1SECSItem secsItem = new U1SECSItem(value);
        assertTrue(secsItem.getValue() == 128);
    }

    @Test
    public void test03()
    {
        short value = 0;
        U1SECSItem secsItem = new U1SECSItem(value);
        assertTrue(secsItem.getValue() == 0);
    }

    @Test
    public void test04()
    {
        short value = 127;
        U1SECSItem secsItem = new U1SECSItem(value);
        assertTrue(secsItem.getValue() == 127);
    }

    @Test
    public void test05()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x01), 0x01, -1 };
        short expectedOutput = 255;
        U1SECSItem secsItem = new U1SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test06()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x01), 0x01, -128 };
        short expectedOutput = 128;
        U1SECSItem secsItem = new U1SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test07()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x01), 0x01, 0 };
        short expectedOutput = 0;
        U1SECSItem secsItem = new U1SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test08()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x01), 0x01, 127 };
        short expectedOutput = 127;
        U1SECSItem secsItem = new U1SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test09()
    {
        short value = 255;
        U1SECSItem secsItem = new U1SECSItem(value);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.U1);
    }

    @Test
    public void test10()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x01), 0x01, -1 };
        U1SECSItem secsItem = new U1SECSItem(input, 0);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.U1);
    }

    @Test
    public void test11()
    {
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x01), 0x01, -1 };
        
        U1SECSItem secsItem = new U1SECSItem((short)255);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test12()
    {
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x02), 0, 0x01, -1 };
        
        U1SECSItem secsItem = new U1SECSItem((short)255, SECSItemNumLengthBytes.TWO);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test13()
    {
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x03), 0, 0, 0x01, -1 };
        
        U1SECSItem secsItem = new U1SECSItem((short)255, SECSItemNumLengthBytes.THREE);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test14()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x01), 0x00 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        U1SECSItem secsItem = new U1SECSItem(input, 0);});
        assertEquals("Illegal data length of: 0.  The length of the data independent of the item header must be 1.", exception.getMessage());
    }

    @Test
    public void test15()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x01), 0x02 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        U1SECSItem secsItem = new U1SECSItem(input, 0);});
        assertEquals("Illegal data length of: 2.  The length of the data independent of the item header must be 1.", exception.getMessage());
    }

    @Test
    @DisplayName("Test toString()")
    public void test16()
    {
        U1SECSItem secsItem = new U1SECSItem((short)255);
        assertTrue(secsItem.toString().equals("Format:U1 Value: 255"));
    }

    @Test
    @DisplayName("Test equals()")
    public void test17()
    {
        U1SECSItem secsItem1 = new U1SECSItem((short)255);
        U1SECSItem secsItem2 = new U1SECSItem((short)255);
        assertTrue(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test18()
    {
        U1SECSItem secsItem1 = new U1SECSItem((short)255);
        U1SECSItem secsItem2 = new U1SECSItem((short)127);
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test19()
    {
        U1SECSItem secsItem1 = new U1SECSItem((short)255);
        U1SECSItem secsItem2 = null;
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test20()
    {
        U1SECSItem secsItem1 = new U1SECSItem((short)255);
        assertTrue(secsItem1.equals(secsItem1));
    }

    @Test
    @DisplayName("Test equals()")
    public void test21()
    {
        U1SECSItem secsItem1 = new U1SECSItem((short)255);
        Object secsItem2 = new F8SECSItem(2.141592D);
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test hashCode()")
    public void test22()
    {
        U1SECSItem secsItem1 = new U1SECSItem((short)255);
        assertTrue(secsItem1.hashCode() == 286);
    }

    @Test
    @DisplayName("Test hashCode()")
    public void test23()
    {
        U1SECSItem secsItem1 = new U1SECSItem((short)255);
        U1SECSItem secsItem2 = new U1SECSItem((short)255);
        assertTrue(secsItem1.hashCode() == secsItem2.hashCode());
    }
}
