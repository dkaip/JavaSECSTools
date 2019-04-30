package com.CIMthetics.JavaSECSTools.SECSItemsTests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.CIMthetics.JavaSECSTools.SECSItems.BooleanArraySECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.F8SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemFormatCode;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemNumLengthBytes;

public class BooleanArraySECSItemTests
{

    @Test
    public void test01()
    {
        byte[] input = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.BO ) << 2) | 0x01), 0x08, 1, 0, 2, 0, -1, 0, 1, 1};
        boolean[] expectedResult = {true, false, true, false, true, false, true, true};
        BooleanArraySECSItem secsItem = new BooleanArraySECSItem(input, 0);
        assertArrayEquals(secsItem.getValue(), expectedResult);
    }

    @Test
    public void test02()
    {
        byte[] input = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.BO ) << 2) | 0x01), 0x08, 1, 0, 2, 0, -1, 0, 1, 1};
        BooleanArraySECSItem secsItem = new BooleanArraySECSItem(input, 0);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.BO);
    }

    @Test
    public void test03()
    {
        boolean[] input = {true, false, true, false, true, false, true, true};
        byte[] expectedResult = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.BO ) << 2) | 0x01), 8, 1, 0, 1, 0, 1, 0, 1, 1};
        
        BooleanArraySECSItem secsItem = new BooleanArraySECSItem(input);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test04()
    {
        boolean[] input = {true, false, true, false, true, false, true, true};
        byte[] expectedResult = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.BO ) << 2) | 0x02), 0, 8, 1, 0, 1, 0, 1, 0, 1, 1};
        
        BooleanArraySECSItem secsItem = new BooleanArraySECSItem(input, SECSItemNumLengthBytes.TWO);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test05()
    {
        boolean[] input = {true, false, true, false, true, false, true, true};
        byte[] expectedResult = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.BO ) << 2) | 0x03), 0, 0, 8, 1, 0, 1, 0, 1, 0, 1, 1};
        
        BooleanArraySECSItem secsItem = new BooleanArraySECSItem(input, SECSItemNumLengthBytes.THREE);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    @DisplayName("Test toString method")
    public void test06()
    {
        boolean[] input = {true, false, true, false, true, false, true, true};

        BooleanArraySECSItem secsItem = new BooleanArraySECSItem(input, SECSItemNumLengthBytes.ONE);
        
        assertTrue(secsItem.toString().equals("Format:BO Value: Array"));
    }

    @Test
    @DisplayName("Test hashCode method")
    public void test07()
    {
        boolean[] input = {true, false, true, false, true, false, true, true};

        BooleanArraySECSItem secsItem = new BooleanArraySECSItem(input, SECSItemNumLengthBytes.ONE);

        assertTrue(secsItem.hashCode() == 796855730);
    }

    @Test
    @DisplayName("Test equals method")
    public void test08()
    {
        boolean[] input = {true, false, true, false, true, false, true, true};

        BooleanArraySECSItem secsItem = new BooleanArraySECSItem(input, SECSItemNumLengthBytes.ONE);

        assertTrue(secsItem.equals(secsItem));
    }

    @Test
    @DisplayName("Test equals method")
    public void test09()
    {
        boolean[] input = {true, false, true, false, true, false, true, true};

        BooleanArraySECSItem secsItem = new BooleanArraySECSItem(input, SECSItemNumLengthBytes.ONE);

        assertFalse(secsItem.equals(null));
    }

    @Test
    @DisplayName("Test equals method")
    public void test10()
    {
        boolean[] input1 = {true, false, true, false, true, false, true, true};
        boolean[] input2 = {true, false, true, false, true, false, true, true};

        BooleanArraySECSItem secsItem1 = new BooleanArraySECSItem(input1, SECSItemNumLengthBytes.ONE);
        BooleanArraySECSItem secsItem2 = new BooleanArraySECSItem(input2, SECSItemNumLengthBytes.ONE);

        assertTrue(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals method")
    public void test11()
    {
        boolean[] input1 = {true, false, true, false, true, false, true, true};
        boolean[] input2 = {true, false, true, false, true, false, true, false};

        BooleanArraySECSItem secsItem1 = new BooleanArraySECSItem(input1, SECSItemNumLengthBytes.ONE);
        BooleanArraySECSItem secsItem2 = new BooleanArraySECSItem(input2, SECSItemNumLengthBytes.ONE);

        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals method")
    public void test12()
    {
        boolean[] input1 = {true, false, true, false, true, false, true, true};
        BooleanArraySECSItem secsItem1 = new BooleanArraySECSItem(input1, SECSItemNumLengthBytes.ONE);
        Object secsItem2 = new F8SECSItem(2.141592D);
        assertFalse(secsItem1.equals(secsItem2));
    }
}
