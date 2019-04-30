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
import com.CIMthetics.JavaSECSTools.SECSItems.U2ArraySECSItem;

public class U2ArraySECSItemTests
{
    @Test
    public void test01()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x01), 10,
                -1, -1,
                -128, 0,
                0, 0,
                0, 1,
                127, -1 };
        U2ArraySECSItem secsItem = new U2ArraySECSItem(input, 0);
        assertTrue(secsItem.getValue()[0] == 65535);
        assertTrue(secsItem.getValue()[1] == 32768);
        assertTrue(secsItem.getValue()[2] == 0);
        assertTrue(secsItem.getValue()[3] == 1);
        assertTrue(secsItem.getValue()[4] == 32767);
    }

    @Test
    public void test02()
    {
        int[] input = { 65535, 32768, 0, 1, 32767 };
        U2ArraySECSItem secsItem = new U2ArraySECSItem(input);
        assertArrayEquals(secsItem.getValue(), input);
    }

    @Test
    public void test03()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x01), 10,
                -1, -1,
                -128, 0,
                0, 0,
                0, 1,
                127, -1 };
        U2ArraySECSItem secsItem = new U2ArraySECSItem(input, 0);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.U2);
    }

    @Test
    public void test04()
    {
        int[] input = { 65535, 32768, 0, 1, 32767 };
        U2ArraySECSItem secsItem = new U2ArraySECSItem(input);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.U2);
   }
    
    @Test
    public void test05()
    {
        int[] input = { 65535, 32768, 0, 1, 32767 };
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x01), 10,
                -1, -1,
                -128, 0,
                0, 0,
                0, 1,
                127, -1 };
       
        U2ArraySECSItem secsItem = new U2ArraySECSItem(input);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test06()
    {
        int[] input = { 65535, 32768, 0, 1, 32767 };
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x02), 0, 10,
                -1, -1,
                -128, 0,
                0, 0,
                0, 1,
                127, -1 };
       
        U2ArraySECSItem secsItem = new U2ArraySECSItem(input, SECSItemNumLengthBytes.TWO);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test07()
    {
        int[] input = { 65535, 32768, 0, 1, 32767 };
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x03), 0, 0, 10,
                -1, -1,
                -128, 0,
                0, 0,
                0, 1,
                127, -1 };
       
        U2ArraySECSItem secsItem = new U2ArraySECSItem(input, SECSItemNumLengthBytes.THREE);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    @DisplayName("Testy toString()")
    public void test08()
    {
        int[] input = { 65535, 32768, 0, 1, 32767 };

        U2ArraySECSItem secsItem = new U2ArraySECSItem(input, SECSItemNumLengthBytes.ONE);
        
        assertTrue(secsItem.toString().equals("Format:U2 Value: Array"));
    }

    @Test
    @DisplayName("Test hashCode()")
    public void test09()
    {
        int[] input = { 65535, 32768, 0, 1, 32767 };

        U2ArraySECSItem secsItem = new U2ArraySECSItem(input, SECSItemNumLengthBytes.ONE);

        assertTrue(secsItem.hashCode() == 1398260059);
    }

    @Test
    @DisplayName("Test equals()")
    public void test10()
    {
        int[] input = { 65535, 32768, 0, 1, 32767 };

        U2ArraySECSItem secsItem = new U2ArraySECSItem(input, SECSItemNumLengthBytes.ONE);

        assertTrue(secsItem.equals(secsItem));
    }

    @Test
    @DisplayName("Test equals()")
    public void test11()
    {
        int[] input = { 65535, 32768, 0, 1, 32767 };

        U2ArraySECSItem secsItem = new U2ArraySECSItem(input, SECSItemNumLengthBytes.ONE);

        assertFalse(secsItem.equals(null));
    }

    @Test
    @DisplayName("Test equals()")
    public void test12()
    {
        int[] input = { 65535, 32768, 0, 1, 32767 };

        U2ArraySECSItem secsItem = new U2ArraySECSItem(input, SECSItemNumLengthBytes.ONE);
        
        Object secsItem2 = new F8SECSItem(2.141592D);
        assertFalse(secsItem.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test13()
    {
        int[] input1 = { 65535, 32768, 0, 1, 32767 };
        int[] input2 = { 65535, 32768, 0, 1, 32767 };

        U2ArraySECSItem secsItem1 = new U2ArraySECSItem(input1, SECSItemNumLengthBytes.ONE);
        U2ArraySECSItem secsItem2 = new U2ArraySECSItem(input2, SECSItemNumLengthBytes.ONE);
        
        assertTrue(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test14()
    {
        int[] input1 = { 65535, 32768, 0, 1, 32767 };
        int[] input2 = { 65535, 32768, 0, 1, 0 };

        U2ArraySECSItem secsItem1 = new U2ArraySECSItem(input1, SECSItemNumLengthBytes.ONE);
        U2ArraySECSItem secsItem2 = new U2ArraySECSItem(input2, SECSItemNumLengthBytes.ONE);
        
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    public void test15()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x01), 0x00 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        U2ArraySECSItem secsItem = new U2ArraySECSItem(input, 0);});
        assertEquals("Illegal data length of: 0 payload length must be a non-zero multiple of 2.", exception.getMessage());
    }

    @Test
    public void test16()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x01), 0x03 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        U2ArraySECSItem secsItem = new U2ArraySECSItem(input, 0);});
        assertEquals("Illegal data length of: 3 payload length must be a non-zero multiple of 2.", exception.getMessage());
    }
}
