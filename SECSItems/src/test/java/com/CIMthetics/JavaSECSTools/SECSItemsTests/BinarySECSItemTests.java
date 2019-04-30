package com.CIMthetics.JavaSECSTools.SECSItemsTests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.CIMthetics.JavaSECSTools.SECSItems.BinarySECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.F8SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemFormatCode;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemNumLengthBytes;

public class BinarySECSItemTests
{

    @Test
    public void test01()
    {
        byte[] input = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.B ) << 2) | 0x01), 0x05, -128, -1, 0, 1, 127};
        short[] expectedResult = {128, 255, 0, 1, 127};
        
        BinarySECSItem secsItem = new BinarySECSItem(input, 0);
        assertArrayEquals(secsItem.getValue(), expectedResult);
    }

    @Test
    public void test02()
    {
        byte[] input = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.B ) << 2) | 0x01), 0x05, -128, -1, 0, 1, 127};
        
        BinarySECSItem secsItem = new BinarySECSItem(input, 0);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.B);
    }

    @Test
    public void test03()
    {
        short[] input = {128, 255, 0, 1, 127};
        byte[] expectedResult = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.B ) << 2) | 0x01), 0x05, -128, -1, 0, 1, 127};
        
        BinarySECSItem secsItem = new BinarySECSItem(input);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test04()
    {
        short[] input = {128, 255, 0, 1, 127};
        byte[] expectedResult = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.B ) << 2) | 0x02), 0, 5, -128, -1, 0, 1, 127};
        
        BinarySECSItem secsItem = new BinarySECSItem(input, SECSItemNumLengthBytes.TWO);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test05()
    {
        short[] input = {128, 255, 0, 1, 127};
        byte[] expectedResult = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.B ) << 2) | 0x03), 0, 0, 5, -128, -1, 0, 1, 127};
        
        BinarySECSItem secsItem = new BinarySECSItem(input, SECSItemNumLengthBytes.THREE);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test06()
    {
        short[] input = {128};

        BinarySECSItem secsItem = new BinarySECSItem(input, SECSItemNumLengthBytes.THREE);

        assertTrue(secsItem.toString().equals("Format:B Value: 128"));
    }

    @Test
    public void test07()
    {
        short[] input = {128, 255, 0, 1, 127};
        
        BinarySECSItem secsItem = new BinarySECSItem(input, SECSItemNumLengthBytes.THREE);

        assertTrue(secsItem.toString().equals("Format:B Value: Array"));
    }

    @Test
    public void test08()
    {
        short[] input = {128, 255, 0, 1, 127};
        
        BinarySECSItem secsItem = new BinarySECSItem(input, SECSItemNumLengthBytes.ONE);

        assertTrue(secsItem.hashCode() == 154436733);
    }

    @Test
    public void test09()
    {
        short[] input = {128, 255, 0, 1, 127};
        
        BinarySECSItem secsItem = new BinarySECSItem(input, SECSItemNumLengthBytes.ONE);
        
        assertTrue(secsItem.equals(secsItem));
    }

    @Test
    public void test10()
    {
        short[] input = {128, 255, 0, 1, 127};
        
        BinarySECSItem secsItem = new BinarySECSItem(input, SECSItemNumLengthBytes.ONE);
        
        assertFalse(secsItem.equals(null));
    }
    
    @Test
    public void test11()
    {
        short[] input = {128, 255, 0, 1, 127};
        
        BinarySECSItem secsItem1 = new BinarySECSItem(input, SECSItemNumLengthBytes.ONE);
        Object secsItem2 = new F8SECSItem(2.141592D);
        assertFalse(secsItem1.equals(secsItem2));
    }
    
    @Test
    public void test12()
    {
        short[] input1 = {128, 255, 0, 1, 127};
        short[] input2 = {128, 255, 0, 1, 127};
        
        BinarySECSItem secsItem1 = new BinarySECSItem(input1, SECSItemNumLengthBytes.ONE);
        BinarySECSItem secsItem2 = new BinarySECSItem(input2, SECSItemNumLengthBytes.ONE);

        assertTrue(secsItem1.equals(secsItem2));
    }
    
    @Test
    public void test13()
    {
        short[] input1 = {128, 255, 0, 1, 127};
        short[] input2 = {128, 255, 0, 1, 126};
        
        BinarySECSItem secsItem1 = new BinarySECSItem(input1, SECSItemNumLengthBytes.ONE);
        BinarySECSItem secsItem2 = new BinarySECSItem(input2, SECSItemNumLengthBytes.ONE);

        assertFalse(secsItem1.equals(secsItem2));
    }
}
