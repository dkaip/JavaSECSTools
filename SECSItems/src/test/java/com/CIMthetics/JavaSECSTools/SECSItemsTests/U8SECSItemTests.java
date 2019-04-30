package com.CIMthetics.JavaSECSTools.SECSItemsTests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.CIMthetics.JavaSECSTools.SECSItems.F8SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemFormatCode;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemNumLengthBytes;
import com.CIMthetics.JavaSECSTools.SECSItems.U8SECSItem;

public class U8SECSItemTests
{
    @Test
    public void test01()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 0x08, -1, -1, -1, -1, -1, -1, -1, -1 };
        BigInteger expectedOutput = new BigInteger("FFFFFFFFFFFFFFFF", 16);
        U8SECSItem secsItem = new U8SECSItem(input, 0);
        assertTrue(secsItem.getValue().compareTo(expectedOutput) == 0);
    }

    @Test
    public void test02()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 0x08, -128, 0, 0, 0, 0, 0, 0, 0 };
        BigInteger expectedOutput = new BigInteger("8000000000000000", 16);
        U8SECSItem secsItem = new U8SECSItem(input, 0);
        assertTrue(secsItem.getValue().compareTo(expectedOutput) == 0);
    }

    @Test
    public void test03()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 0x08, 0, 0, 0, 0, 0, 0, 0, 0 };
        BigInteger expectedOutput = new BigInteger("0000000000000000", 16);
        U8SECSItem secsItem = new U8SECSItem(input, 0);
        assertTrue(secsItem.getValue().compareTo(expectedOutput) == 0);
    }

    @Test
    public void test04()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 0x08, 0, 0, 0, 0, 0, 0, 0, 1 };
        BigInteger expectedOutput = new BigInteger("0000000000000001", 16);
        U8SECSItem secsItem = new U8SECSItem(input, 0);
        assertTrue(secsItem.getValue().compareTo(expectedOutput) == 0);
    }

    @Test
    public void test05()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 0x08, 127, -1, -1, -1, -1, -1, -1, -1 };
        BigInteger expectedOutput = new BigInteger("7FFFFFFFFFFFFFFF", 16);
        U8SECSItem secsItem = new U8SECSItem(input, 0);
        assertTrue(secsItem.getValue().compareTo(expectedOutput) == 0);
    }

    @Test
    public void test06()
    {
        BigInteger expectedOutput = new BigInteger("FFFFFFFFFFFFFFFF", 16);
        U8SECSItem secsItem = new U8SECSItem(expectedOutput);
        assertTrue(secsItem.getValue().compareTo(expectedOutput) == 0);
    }

    @Test
    public void test07()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 0x08, -1, -1, -1, -1, -1, -1, -1, -1 };
        U8SECSItem secsItem = new U8SECSItem(input, 0);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.U8);
    }

    @Test
    public void test08()
    {
        BigInteger expectedOutput = new BigInteger("FFFFFFFFFFFFFFFF", 16);
        U8SECSItem secsItem = new U8SECSItem(expectedOutput);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.U8);
    }

    @Test
    public void test09()
    {
         byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 0x08, 127, -1, -1, -1, -1, -1, -1, -1 };
        
         U8SECSItem secsItem = new U8SECSItem(new BigInteger("7FFFFFFFFFFFFFFF", 16));
         assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test10()
    {
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x02), 0, 0x08, -1, -1, -1, -1, -1, -1, -1, -1 };
        
        U8SECSItem secsItem = new U8SECSItem(new BigInteger("FFFFFFFFFFFFFFFF", 16), SECSItemNumLengthBytes.TWO);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test11()
    {
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x03), 0, 0, 0x08, 127, 1, 0, 0, 0, 0, -1, -1 };
        
        U8SECSItem secsItem = new U8SECSItem(new BigInteger("7F0100000000FFFF", 16), SECSItemNumLengthBytes.THREE);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test12()
    {
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x03), 0, 0, 0x08, 0, 0, 0, 0, 0, 0, 0, 1 };
        
        U8SECSItem secsItem = new U8SECSItem(new BigInteger("0000000000000001", 16), SECSItemNumLengthBytes.THREE);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    @DisplayName("Test toString()")
    public void test13()
    {
        BigInteger bigInt = new BigInteger("FFFFFFFFFFFFFFFF", 16);
        U8SECSItem secsItem = new U8SECSItem(bigInt);
        
        assertTrue(secsItem.toString().equals("Format:U8 Value: 18446744073709551615"));
    }

    @Test
    @DisplayName("Test toString()")
    public void test14()
    {
        BigInteger bigInt = new BigInteger("0000000000000000", 16);
        U8SECSItem secsItem = new U8SECSItem(bigInt);
        
        assertTrue(secsItem.toString().equals("Format:U8 Value: 0"));
    }

    @Test
    public void test15()
    {
        BigInteger bigInt = new BigInteger("10000000000000000", 16);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        U8SECSItem secsItem = new U8SECSItem(bigInt);});
        
        assertEquals( "The value specified (18446744073709551616) is too large.  It must be <= to 18446744073709551615.", exception.getMessage());
    }

    @Test
    public void test16()
    {
        BigInteger bigInt = new BigInteger("-1", 10);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        U8SECSItem secsItem = new U8SECSItem(bigInt);});

        assertEquals( "The value specified (-1) is too small.  It must be > 0.", exception.getMessage());
    }

    @Test
    public void test17()
    {
        BigInteger bigInt = new BigInteger("10000000000000000", 16);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        U8SECSItem secsItem = new U8SECSItem(bigInt, SECSItemNumLengthBytes.ONE);});
        
        assertEquals( "The value specified (18446744073709551616) is too large.  It must be <= to 18446744073709551615.", exception.getMessage());
    }

    @Test
    public void test18()
    {
        BigInteger bigInt = new BigInteger("-1", 10);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        U8SECSItem secsItem = new U8SECSItem(bigInt, SECSItemNumLengthBytes.ONE);});

        assertEquals( "The value specified (-1) is too small.  It must be > 0.", exception.getMessage());
    }

    @Test
    @DisplayName("Test equals()")
    public void test19()
    {
        U8SECSItem secsItem1 = new U8SECSItem(new BigInteger("3141592", 10));
        U8SECSItem secsItem2 = new U8SECSItem(new BigInteger("3141592", 10));
        assertTrue(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test20()
    {
        U8SECSItem secsItem1 = new U8SECSItem(new BigInteger("3141592", 10));
        U8SECSItem secsItem2 = new U8SECSItem(new BigInteger("2141592", 10));
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test21()
    {
        U8SECSItem secsItem1 = new U8SECSItem(new BigInteger("3141592", 10));
        U8SECSItem secsItem2 = null;
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test22()
    {
        U8SECSItem secsItem1 = new U8SECSItem(new BigInteger("3141592", 10));
        assertTrue(secsItem1.equals(secsItem1));
    }

    @Test
    @DisplayName("Test equals()")
    public void test23()
    {
        U8SECSItem secsItem1 = new U8SECSItem(new BigInteger("3141592", 10));
        SECSItem secsItem2 = null;
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test24()
    {
        U8SECSItem secsItem1 = new U8SECSItem(new BigInteger("3141592", 10));
        Object secsItem2 = new F8SECSItem(2.141592D);
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test hashCode()")
    public void test25()
    {
        U8SECSItem secsItem1 = new U8SECSItem(new BigInteger("FFFFFFFFFFFFFFFF", 16));
        assertTrue(secsItem1.hashCode() == -1);
    }

    @Test
    @DisplayName("Test hashCode()")
    public void test26()
    {
        U8SECSItem secsItem1 = new U8SECSItem(new BigInteger("FFFFFFFFFFFFFFFF", 16));
        U8SECSItem secsItem2 = new U8SECSItem(new BigInteger("FFFFFFFFFFFFFFFF", 16));
        assertTrue(secsItem1.hashCode() == secsItem2.hashCode());
    }

    @Test
    public void test27()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 0x00 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        U8SECSItem secsItem = new U8SECSItem(input, 0);});
        assertEquals("Illegal data length of: 0.  The length of the data independent of the item header must be 8.", exception.getMessage());
    }

    @Test
    public void test28()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 0x09 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        U8SECSItem secsItem = new U8SECSItem(input, 0);});
        assertEquals("Illegal data length of: 9.  The length of the data independent of the item header must be 8.", exception.getMessage());
    }

}
