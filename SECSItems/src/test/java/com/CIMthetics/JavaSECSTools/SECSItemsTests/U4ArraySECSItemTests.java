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
import com.CIMthetics.JavaSECSTools.SECSItems.U4ArraySECSItem;

public class U4ArraySECSItemTests
{
    @Test
    public void test01()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x01), 20,
                -1, -1, -1, -1,
                -128, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 1,
                127, -1, -1, -1 };
        U4ArraySECSItem secsItem = new U4ArraySECSItem(input, 0);
        assertTrue(secsItem.getValue()[0] == 0xFFFFFFFFL);
        assertTrue(secsItem.getValue()[1] == 2147483648L);
        assertTrue(secsItem.getValue()[2] == 0);
        assertTrue(secsItem.getValue()[3] == 1);
        assertTrue(secsItem.getValue()[4] == 2147483647L);
    }

    @Test
    public void test02()
    {
        long[] input = { 0xFFFFFFFFL, 2147483648L, 0, 1, 2147483647L };
        U4ArraySECSItem secsItem = new U4ArraySECSItem(input);
        assertArrayEquals(secsItem.getValue(), input);
    }

    @Test
    public void test03()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x01), 20,
                -1, -1, -1, -1,
                -128, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 1,
                127, -1, -1, -1 };
        U4ArraySECSItem secsItem = new U4ArraySECSItem(input, 0);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.U4);
    }

    @Test
    public void test04()
    {
        long[] input = { 0xFFFFFFFFL, 2147483648L, 0, 1, 2147483647L };
        U4ArraySECSItem secsItem = new U4ArraySECSItem(input);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.U4);
    }

    @Test
    public void test05()
    {
        long[] input = { 0xFFFFFFFFL, 2147483648L, 0, 1, 2147483647L };
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x01), 20,
                -1, -1, -1, -1,
                -128, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 1,
                127, -1, -1, -1 };
       
        U4ArraySECSItem secsItem = new U4ArraySECSItem(input);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test06()
    {
        long[] input = { 0xFFFFFFFFL, 2147483648L, 0, 1, 2147483647L };
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x02), 0, 20,
                -1, -1, -1, -1,
                -128, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 1,
                127, -1, -1, -1 };
       
        U4ArraySECSItem secsItem = new U4ArraySECSItem(input, SECSItemNumLengthBytes.TWO);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test07()
    {
        long[] input = { 0xFFFFFFFFL, 2147483648L, 0, 1, 2147483647L };
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x03), 0, 0, 20,
                -1, -1, -1, -1,
                -128, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 1,
                127, -1, -1, -1 };
       
        U4ArraySECSItem secsItem = new U4ArraySECSItem(input, SECSItemNumLengthBytes.THREE);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    @DisplayName("Test toString()")
    public void test08()
    {
        long[] input = { 0xFFFFFFFFL, 2147483648L, 0, 1, 2147483647L };

        U4ArraySECSItem secsItem = new U4ArraySECSItem(input, SECSItemNumLengthBytes.ONE);
        
        assertTrue(secsItem.toString().equals("Format:U4 Value: Array"));
    }

    @Test
    @DisplayName("Test hashCode()")
    public void test09()
    {
        long[] input = { 0xFFFFFFFFL, 2147483648L, 0, 1, 2147483647L };

        U4ArraySECSItem secsItem = new U4ArraySECSItem(input, SECSItemNumLengthBytes.ONE);

        assertTrue(secsItem.hashCode() == 27705691);
    }

    @Test
    @DisplayName("Test equals()")
    public void test10()
    {
        long[] input = { 0xFFFFFFFFL, 2147483648L, 0, 1, 2147483647L };

        U4ArraySECSItem secsItem = new U4ArraySECSItem(input, SECSItemNumLengthBytes.ONE);

        assertTrue(secsItem.equals(secsItem));
    }

    @Test
    @DisplayName("Test equals()")
    public void test11()
    {
        long[] input = { 0xFFFFFFFFL, 2147483648L, 0, 1, 2147483647L };

        U4ArraySECSItem secsItem = new U4ArraySECSItem(input, SECSItemNumLengthBytes.ONE);

        assertFalse(secsItem.equals(null));
    }

    @Test
    @DisplayName("Test equals()")
    public void test12()
    {
        long[] input = { 0xFFFFFFFFL, 2147483648L, 0, 1, 2147483647L };

        U4ArraySECSItem secsItem = new U4ArraySECSItem(input, SECSItemNumLengthBytes.ONE);
        
        Object secsItem2 = new F8SECSItem(2.141592D);
        assertFalse(secsItem.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test13()
    {
        long[] input1 = { 0xFFFFFFFFL, 2147483648L, 0, 1, 2147483647L };
        long[] input2 = { 0xFFFFFFFFL, 2147483648L, 0, 1, 2147483647L };

        U4ArraySECSItem secsItem1 = new U4ArraySECSItem(input1, SECSItemNumLengthBytes.ONE);
        U4ArraySECSItem secsItem2 = new U4ArraySECSItem(input2, SECSItemNumLengthBytes.ONE);
        
        assertTrue(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test14()
    {
        long[] input1 = { 0xFFFFFFFFL, 2147483648L, 0, 1, 2147483647L };
        long[] input2 = { 0xFFFFFFFFL, 2147483648L, 0, 1, 0 };

        U4ArraySECSItem secsItem1 = new U4ArraySECSItem(input1, SECSItemNumLengthBytes.ONE);
        U4ArraySECSItem secsItem2 = new U4ArraySECSItem(input2, SECSItemNumLengthBytes.ONE);
        
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    public void test15()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x01), 0x00 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        U4ArraySECSItem secsItem = new U4ArraySECSItem(input, 0);});
        assertEquals("Illegal data length of: 0 payload length must be a non-zero multiple of 4.", exception.getMessage());
    }

    @Test
    public void test16()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x01), 0x03 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        U4ArraySECSItem secsItem = new U4ArraySECSItem(input, 0);});
        assertEquals("Illegal data length of: 3 payload length must be a non-zero multiple of 4.", exception.getMessage());
    }
}
