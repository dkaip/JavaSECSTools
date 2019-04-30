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
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemFormatCode;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemNumLengthBytes;
import com.CIMthetics.JavaSECSTools.SECSItems.U8ArraySECSItem;

public class U8ArraySECSItemTests
{
    @Test
    public void test01()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 40,
                -1, -1, -1, -1, -1, -1, -1, -1,
                -128, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1,
                127, -1, -1, -1, -1, -1, -1, -1 };
        U8ArraySECSItem secsItem = new U8ArraySECSItem(input, 0);
        assertTrue(secsItem.getValue()[0].compareTo(new BigInteger("FFFFFFFFFFFFFFFF", 16)) == 0);
        assertTrue(secsItem.getValue()[1].compareTo(new BigInteger("8000000000000000", 16)) == 0);
        assertTrue(secsItem.getValue()[2].compareTo(new BigInteger("0000000000000000", 16)) == 0);
        assertTrue(secsItem.getValue()[3].compareTo(new BigInteger("0000000000000001", 16)) == 0);
        assertTrue(secsItem.getValue()[4].compareTo(new BigInteger("7FFFFFFFFFFFFFFF", 16)) == 0);
    }

    @Test
    public void test02()
    {
        BigInteger input[] = new BigInteger[5];
        input[0] = new BigInteger("FFFFFFFFFFFFFFFF", 16);
        input[1] = new BigInteger("8000000000000000", 16);
        input[2] = new BigInteger("0000000000000000", 16);
        input[3] = new BigInteger("0000000000000001", 16);
        input[4] = new BigInteger("7FFFFFFFFFFFFFFF", 16);
        U8ArraySECSItem secsItem = new U8ArraySECSItem(input);
        assertArrayEquals(secsItem.getValue(), input);
    }

    @Test
    public void test03()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 40,
                -1, -1, -1, -1, -1, -1, -1, -1,
                -128, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1,
                127, -1, -1, -1, -1, -1, -1, -1 };
        U8ArraySECSItem secsItem = new U8ArraySECSItem(input, 0);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.U8);
    }

    @Test
    public void test04()
    {
        BigInteger input[] = new BigInteger[5];
        input[0] = new BigInteger("FFFFFFFFFFFFFFFF", 16);
        input[1] = new BigInteger("8000000000000000", 16);
        input[2] = new BigInteger("0000000000000000", 16);
        input[3] = new BigInteger("0000000000000001", 16);
        input[4] = new BigInteger("7FFFFFFFFFFFFFFF", 16);
        U8ArraySECSItem secsItem = new U8ArraySECSItem(input);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.U8);
    }

    @Test
    public void test05()
    {
        BigInteger[] input = new BigInteger[]{ new BigInteger("FFFFFFFFFFFFFFFF", 16), new BigInteger("8000000000000000", 16), new BigInteger("0000000000000000", 16), new BigInteger("0000000000000001", 16), new BigInteger("7FFFFFFFFFFFFFFF", 16) };
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 40,
                -1, -1, -1, -1, -1, -1, -1, -1,
                -128, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1,
                127, -1, -1, -1, -1, -1, -1, -1 };
       
        U8ArraySECSItem secsItem = new U8ArraySECSItem(input);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test06()
    {
        BigInteger[] input = new BigInteger[]{ new BigInteger("FFFFFFFFFFFFFFFF", 16), new BigInteger("8000000000000000", 16), new BigInteger("0000000000000000", 16), new BigInteger("0000000000000001", 16), new BigInteger("7FFFFFFFFFFFFFFF", 16) };
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x02), 0, 40,
                -1, -1, -1, -1, -1, -1, -1, -1,
                -128, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1,
                127, -1, -1, -1, -1, -1, -1, -1 };
       
        U8ArraySECSItem secsItem = new U8ArraySECSItem(input, SECSItemNumLengthBytes.TWO);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test07()
    {
        BigInteger[] input = new BigInteger[]{ new BigInteger("FFFFFFFFFFFFFFFF", 16), new BigInteger("8000000000000000", 16), new BigInteger("0000000000000000", 16), new BigInteger("0000000000000001", 16), new BigInteger("7FFFFFFFFFFFFFFF", 16) };
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x03), 0, 0, 40,
                -1, -1, -1, -1, -1, -1, -1, -1,
                -128, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1,
                127, -1, -1, -1, -1, -1, -1, -1 };
       
        U8ArraySECSItem secsItem = new U8ArraySECSItem(input, SECSItemNumLengthBytes.THREE);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    @DisplayName("Test toString()")
    public void test08()
    {
        BigInteger[] input = new BigInteger[]{ new BigInteger("FFFFFFFFFFFFFFFF", 16), new BigInteger("8000000000000000", 16), new BigInteger("0000000000000000", 16), new BigInteger("0000000000000001", 16), new BigInteger("7FFFFFFFFFFFFFFF", 16) };

        U8ArraySECSItem secsItem = new U8ArraySECSItem(input, SECSItemNumLengthBytes.ONE);
        
        assertTrue(secsItem.toString().equals("Format:U8 Value: Array"));
    }

    @Test
    @DisplayName("Test hashCode()")
    public void test09()
    {
        BigInteger[] input = new BigInteger[]{ new BigInteger("FFFFFFFFFFFFFFFF", 16), new BigInteger("8000000000000000", 16), new BigInteger("0000000000000000", 16), new BigInteger("0000000000000001", 16), new BigInteger("7FFFFFFFFFFFFFFF", 16) };

        U8ArraySECSItem secsItem = new U8ArraySECSItem(input, SECSItemNumLengthBytes.ONE);

        assertTrue(secsItem.hashCode() == -923491);
    }

    @Test
    @DisplayName("Test equals()")
    public void test10()
    {
        BigInteger[] input = new BigInteger[]{ new BigInteger("FFFFFFFFFFFFFFFF", 16), new BigInteger("8000000000000000", 16), new BigInteger("0000000000000000", 16), new BigInteger("0000000000000001", 16), new BigInteger("7FFFFFFFFFFFFFFF", 16) };

        U8ArraySECSItem secsItem = new U8ArraySECSItem(input, SECSItemNumLengthBytes.ONE);

        assertTrue(secsItem.equals(secsItem));
    }

    @Test
    @DisplayName("Test equals()")
    public void test11()
    {
        BigInteger[] input = new BigInteger[]{ new BigInteger("FFFFFFFFFFFFFFFF", 16), new BigInteger("8000000000000000", 16), new BigInteger("0000000000000000", 16), new BigInteger("0000000000000001", 16), new BigInteger("7FFFFFFFFFFFFFFF", 16) };

        U8ArraySECSItem secsItem = new U8ArraySECSItem(input, SECSItemNumLengthBytes.ONE);

        assertFalse(secsItem.equals(null));
    }

    @Test
    @DisplayName("Test equals()")
    public void test12()
    {
        BigInteger[] input = new BigInteger[]{ new BigInteger("FFFFFFFFFFFFFFFF", 16), new BigInteger("8000000000000000", 16), new BigInteger("0000000000000000", 16), new BigInteger("0000000000000001", 16), new BigInteger("7FFFFFFFFFFFFFFF", 16) };

        U8ArraySECSItem secsItem = new U8ArraySECSItem(input, SECSItemNumLengthBytes.ONE);
        
        Object secsItem2 = new F8SECSItem(2.141592D);
        assertFalse(secsItem.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test13()
    {
        BigInteger[] input1 = new BigInteger[]{ new BigInteger("FFFFFFFFFFFFFFFF", 16), new BigInteger("8000000000000000", 16), new BigInteger("0000000000000000", 16), new BigInteger("0000000000000001", 16), new BigInteger("7FFFFFFFFFFFFFFF", 16) };
        BigInteger[] input2 = new BigInteger[]{ new BigInteger("FFFFFFFFFFFFFFFF", 16), new BigInteger("8000000000000000", 16), new BigInteger("0000000000000000", 16), new BigInteger("0000000000000001", 16), new BigInteger("7FFFFFFFFFFFFFFF", 16) };

        U8ArraySECSItem secsItem1 = new U8ArraySECSItem(input1, SECSItemNumLengthBytes.ONE);
        U8ArraySECSItem secsItem2 = new U8ArraySECSItem(input2, SECSItemNumLengthBytes.ONE);
        
        assertTrue(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test14()
    {
        BigInteger[] input1 = new BigInteger[]{ new BigInteger("FFFFFFFFFFFFFFFF", 16), new BigInteger("8000000000000000", 16), new BigInteger("0000000000000000", 16), new BigInteger("0000000000000001", 16), new BigInteger("7FFFFFFFFFFFFFFF", 16) };
        BigInteger[] input2 = new BigInteger[]{ new BigInteger("FFFFFFFFFFFFFFFF", 16), new BigInteger("8000000000000000", 16), new BigInteger("0000000000000000", 16), new BigInteger("0000000000000001", 16), new BigInteger("0000000000000000", 16) };

        U8ArraySECSItem secsItem1 = new U8ArraySECSItem(input1, SECSItemNumLengthBytes.ONE);
        U8ArraySECSItem secsItem2 = new U8ArraySECSItem(input2, SECSItemNumLengthBytes.ONE);
        
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    public void test15()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 0x00 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        U8ArraySECSItem secsItem = new U8ArraySECSItem(input, 0);});
        assertEquals("Illegal data length of: 0 payload length must be a non-zero multiple of 8.", exception.getMessage());
    }

    @Test
    public void test16()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 0x03 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        U8ArraySECSItem secsItem = new U8ArraySECSItem(input, 0);});
        assertEquals("Illegal data length of: 3 payload length must be a non-zero multiple of 8.", exception.getMessage());
    }
}
