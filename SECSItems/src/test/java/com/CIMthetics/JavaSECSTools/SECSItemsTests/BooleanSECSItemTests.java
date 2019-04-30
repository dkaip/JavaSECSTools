package com.CIMthetics.JavaSECSTools.SECSItemsTests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.CIMthetics.JavaSECSTools.SECSItems.BooleanSECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.F8SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemFormatCode;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemNumLengthBytes;

public class BooleanSECSItemTests
{

    @Test
    public void test01()
    {
        BooleanSECSItem secsItem = new BooleanSECSItem(true);
        assertTrue(secsItem.getValue() == true);
    }

    @Test
    public void test02()
    {
        BooleanSECSItem secsItem = new BooleanSECSItem(false);
        assertTrue(secsItem.getValue() == false);
    }

    @Test
    public void test03()
    {
        BooleanSECSItem secsItem = new BooleanSECSItem(true);
        assertFalse(secsItem.getValue() == false);
    }

    @Test
    public void test04()
    {
        byte[] input = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.BO ) << 2) | 0x01), 0x01, 5};
        BooleanSECSItem secsItem = new BooleanSECSItem(input, 0);
        assertTrue(secsItem.getValue() == true);
    }

    @Test
    public void test05()
    {
        byte[] input = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.BO ) << 2) | 0x01), 0x01, 0};
        BooleanSECSItem secsItem = new BooleanSECSItem(input, 0);
        assertTrue(secsItem.getValue() == false);
    }

    @Test
    public void test06()
    {
        BooleanSECSItem secsItem = new BooleanSECSItem(true);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.BO);
    }

    @Test
    public void test0()
    {
        byte[] input = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.BO ) << 2) | 0x01), 0x01, 5};
        BooleanSECSItem secsItem = new BooleanSECSItem(input, 0);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.BO);
    }

    @Test
    public void test08()
    {
        byte[] expectedResult = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.BO ) << 2) | 0x01), 0x01, 1};
        
        BooleanSECSItem secsItem = new BooleanSECSItem(true);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test09()
    {
        byte[] expectedResult = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.BO ) << 2) | 0x02), 0, 1, 1};
        
        BooleanSECSItem secsItem = new BooleanSECSItem(true, SECSItemNumLengthBytes.TWO);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test10()
    {
        byte[] expectedResult = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.BO ) << 2) | 0x03), 0, 0, 1, 0};
        
        BooleanSECSItem secsItem = new BooleanSECSItem(false, SECSItemNumLengthBytes.THREE);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test11()
    {
        BooleanSECSItem secsItem = new BooleanSECSItem(true);
        assertTrue(secsItem.toString().equals("Format:BO Value: true"));
    }

    @Test
    public void test12()
    {
        BooleanSECSItem secsItem = new BooleanSECSItem(false);
        assertTrue(secsItem.toString().equals("Format:BO Value: false"));
    }

    @Test
    public void test13()
    {
        BooleanSECSItem secsItem = new BooleanSECSItem(true);

        assertTrue(secsItem.hashCode() == 1262);
    }

    @Test
    public void test14()
    {
        BooleanSECSItem secsItem = new BooleanSECSItem(false);

        assertTrue(secsItem.hashCode() == 1268);
    }

    @Test
    public void test15()
    {
        BooleanSECSItem secsItem1 = new BooleanSECSItem(true);
        BooleanSECSItem secsItem2 = new BooleanSECSItem(true);

        assertTrue(secsItem1.hashCode() == secsItem2.hashCode());
    }

    @Test
    public void test16()
    {
        BooleanSECSItem secsItem1 = new BooleanSECSItem(true);
        BooleanSECSItem secsItem2 = new BooleanSECSItem(false);

        assertFalse(secsItem1.hashCode() == secsItem2.hashCode());
    }

    @Test
    public void test17()
    {
        BooleanSECSItem secsItem = new BooleanSECSItem(false);

        assertTrue(secsItem.equals(secsItem));
    }

    @Test
    public void test18()
    {
        BooleanSECSItem secsItem = new BooleanSECSItem(false);

        assertFalse(secsItem.equals(null));
    }

    @Test
    public void test19()
    {
        BooleanSECSItem secsItem1 = new BooleanSECSItem(false);
        Object secsItem2 = new F8SECSItem(2.141592D);
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    public void test20()
    {
        BooleanSECSItem secsItem1 = new BooleanSECSItem(true);
        BooleanSECSItem secsItem2 = new BooleanSECSItem(true);

        assertTrue(secsItem1.equals(secsItem2));
    }

    @Test
    public void test21()
    {
        BooleanSECSItem secsItem1 = new BooleanSECSItem(true);
        BooleanSECSItem secsItem2 = new BooleanSECSItem(false);

        assertFalse(secsItem1.equals(secsItem2));
    }
}
