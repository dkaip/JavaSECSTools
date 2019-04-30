package com.CIMthetics.JavaSECSTools.SECSItemsTests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.CIMthetics.JavaSECSTools.SECSItems.F8SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemFormatCode;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemNumLengthBytes;
import com.CIMthetics.JavaSECSTools.SECSItems.U4SECSItem;

public class U4SECSItemTests
{
    @Test
    public void test01()
    {
        byte[] value = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x01), 0x04, -1, -1, -1, -1};
        U4SECSItem secsItem = new U4SECSItem(value, 0);
        assertTrue(secsItem.getValue() == 0xFFFFFFFFL);
    }

    @Test
    public void test02()
    {
        byte[] value = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x01), 0x04, -128, 0, 0, 0};
        U4SECSItem secsItem = new U4SECSItem(value, 0);
        assertTrue(secsItem.getValue() == 2147483648L);
    }

    @Test
    public void test03()
    {
        byte[] value = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x01), 0x04, 0, 0, 0, 0};
        U4SECSItem secsItem = new U4SECSItem(value, 0);
        assertTrue(secsItem.getValue() == 0);
    }

    @Test
    public void test04()
    {
        byte[] value = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x01), 0x04, 127, -1, -1, -1};
        U4SECSItem secsItem = new U4SECSItem(value, 0);
        assertTrue(secsItem.getValue() == 2147483647L);
    }

    @Test
    public void test05()
    {
        long expectedOutput = 0xFFFFFFFFL;
        U4SECSItem secsItem = new U4SECSItem(expectedOutput);
        assertTrue(secsItem.getValue() == expectedOutput);
    }
    
    @Test
    public void test06()
    {
        byte[] value = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x01), 0x04, -1, -1, -1, -1};
        U4SECSItem secsItem = new U4SECSItem(value, 0);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.U4);
    }

    @Test
    public void test07()
    {
        long expectedOutput = 0xFFFFFFFFL;
        U4SECSItem secsItem = new U4SECSItem(expectedOutput);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.U4);
    }
    
    @Test
    public void test08()
    {
        byte[] expectedResult = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x01), 0x04, -1, -1, -1, -1};
        
        U4SECSItem secsItem = new U4SECSItem(0xFFFFFFFFL);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test09()
    {
        byte[] expectedResult = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x02), 0, 0x04, -1, -1, -1, -1};
        
        U4SECSItem secsItem = new U4SECSItem(0xFFFFFFFFL, SECSItemNumLengthBytes.TWO);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test10()
    {
        byte[] expectedResult = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x03), 0, 0, 0x04, -1, -1, -1, -1};
        
        U4SECSItem secsItem = new U4SECSItem(0xFFFFFFFFL, SECSItemNumLengthBytes.THREE);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test12()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x01), 0x00 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        U4SECSItem secsItem = new U4SECSItem(input, 0);});
        assertEquals("Illegal data length of: 0.  The length of the data independent of the item header must be 4.", exception.getMessage());
    }

    @Test
    public void test13()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x01), 0x05 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        U4SECSItem secsItem = new U4SECSItem(input, 0);});
        assertEquals("Illegal data length of: 5.  The length of the data independent of the item header must be 4.", exception.getMessage());
    }

    @Test
    @DisplayName("Test toString()")
    public void test14()
    {
        U4SECSItem secsItem = new U4SECSItem(4294967295L);
        assertTrue(secsItem.toString().equals("Format:U4 Value: 4294967295"));
    }

    @Test
    @DisplayName("Test equals()")
    public void test15()
    {
        U4SECSItem secsItem1 = new U4SECSItem(4294967295L);
        U4SECSItem secsItem2 = new U4SECSItem(4294967295L);
        assertTrue(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test16()
    {
        U4SECSItem secsItem1 = new U4SECSItem(4294967295L);
        U4SECSItem secsItem2 = new U4SECSItem(0);
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test17()
    {
        U4SECSItem secsItem1 = new U4SECSItem(4294967295L);
        U4SECSItem secsItem2 = null;
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test18()
    {
        U4SECSItem secsItem1 = new U4SECSItem(4294967295L);
        assertTrue(secsItem1.equals(secsItem1));
    }

    @Test
    @DisplayName("Test equals()")
    public void test19()
    {
        U4SECSItem secsItem1 = new U4SECSItem(4294967295L);
        SECSItem secsItem2 = null;
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test20()
    {
        U4SECSItem secsItem1 = new U4SECSItem(4294967295L);
        Object secsItem2 = new F8SECSItem(2.141592D);
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test hashCode()")
    public void test21()
    {
        U4SECSItem secsItem1 = new U4SECSItem(4294967295L);

        assertTrue(secsItem1.hashCode() == 30);
    }

    @Test
    @DisplayName("Test hashCode()")
    public void test22()
    {
        U4SECSItem secsItem1 = new U4SECSItem(4294967295L);
        U4SECSItem secsItem2 = new U4SECSItem(4294967295L);
        assertTrue(secsItem1.hashCode() == secsItem2.hashCode());
    }
}
