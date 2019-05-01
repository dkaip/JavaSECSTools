/*
 * Copyright 2019 Douglas Kaip
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.CIMthetics.JavaSECSTools.SECSItemsTests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.CIMthetics.JavaSECSTools.SECSItems.F8SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.I1SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemFormatCode;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemNumLengthBytes;

public class I1SECSItemTests
{
    @Test
    public void test01()
    {
        byte value = -1;
        I1SECSItem secsItem = new I1SECSItem(value);
        assertTrue(secsItem.getValue() == value);
    }

    @Test
    public void test02()
    {
        byte value = -128;
        I1SECSItem secsItem = new I1SECSItem(value);
        assertTrue(secsItem.getValue() == value);
    }

    @Test
    public void test03()
    {
        byte value = 0;
        I1SECSItem secsItem = new I1SECSItem(value);
        assertTrue(secsItem.getValue() == value);
    }

    @Test
    public void test04()
    {
        byte value = 127;
        I1SECSItem secsItem = new I1SECSItem(value);
        assertTrue(secsItem.getValue() == value);
    }

    @Test
    public void test05()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1 ) << 2) | 0x01), 0x01, -1 };
        byte expectedOutput = -1;
        I1SECSItem secsItem = new I1SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test06()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1 ) << 2) | 0x01), 0x01, -128 };
        byte expectedOutput = -128;
        I1SECSItem secsItem = new I1SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test07()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1 ) << 2) | 0x01), 0x01, 0 };
        byte expectedOutput = 0;
        I1SECSItem secsItem = new I1SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test08()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1 ) << 2) | 0x01), 0x01, 127 };
        byte expectedOutput = 127;
        I1SECSItem secsItem = new I1SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test09()
    {
        byte value = -1;
        I1SECSItem secsItem = new I1SECSItem(value);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.I1);
    }

    @Test
    public void test10()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1 ) << 2) | 0x01), 0x01, 127 };
        I1SECSItem secsItem = new I1SECSItem(input, 0);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.I1);
    }

    @Test
    public void test11()
    {
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1 ) << 2) | 0x01), 0x01, 127 };
        
        I1SECSItem secsItem = new I1SECSItem((byte)127);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test12()
    {
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1 ) << 2) | 0x02), 0, 0x01, 127 };
        
        I1SECSItem secsItem = new I1SECSItem((byte)127, SECSItemNumLengthBytes.TWO);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test13()
    {
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1 ) << 2) | 0x03), 0, 0, 0x01, 127 };
        
        I1SECSItem secsItem = new I1SECSItem((byte)127, SECSItemNumLengthBytes.THREE);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test14()
    {
        I1SECSItem secsItem = new I1SECSItem((byte)127);
        assertTrue(secsItem.toString().equals("Format:I1 Value: 127"));
    }

    @Test
    public void test15()
    {
        I1SECSItem secsItem1 = new I1SECSItem((byte)127);
        I1SECSItem secsItem2 = new I1SECSItem((byte)127);
        assertTrue(secsItem1.equals(secsItem2));
    }

    @Test
    public void test16()
    {
        I1SECSItem secsItem1 = new I1SECSItem((byte)127);
        I1SECSItem secsItem2 = new I1SECSItem((byte)-128);
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    public void test17()
    {
        I1SECSItem secsItem1 = new I1SECSItem((byte)127);
        I1SECSItem secsItem2 = null;
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    public void test18()
    {
        I1SECSItem secsItem1 = new I1SECSItem((byte)127);
        assertTrue(secsItem1.equals(secsItem1));
    }

    @Test
    public void test19()
    {
        I1SECSItem secsItem1 = new I1SECSItem((byte)127);
        SECSItem secsItem2 = null;
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    public void test20()
    {
        I1SECSItem secsItem = new I1SECSItem((byte)127);

        assertTrue(secsItem.hashCode() == 158);
    }

    @Test
    public void test21()
    {
        I1SECSItem secsItem1 = new I1SECSItem((byte)127);
        I1SECSItem secsItem2 = new I1SECSItem((byte)127);
        assertTrue(secsItem1.hashCode() == secsItem2.hashCode());
    }

    @Test
    public void test22()
    {
        I1SECSItem secsItem1 = new I1SECSItem((byte)127);
        Object secsItem2 = new F8SECSItem(2.141592D);
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    public void test23()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1 ) << 2) | 0x01), 0x00 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        I1SECSItem secsItem = new I1SECSItem(input, 0);});
        assertEquals("Illegal data length of: 0.  The length of the data independent of the item header must be 1.", exception.getMessage());
    }

    @Test
    public void test24()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1 ) << 2) | 0x01), 0x02 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        I1SECSItem secsItem = new I1SECSItem(input, 0);});
        assertEquals("Illegal data length of: 2.  The length of the data independent of the item header must be 1.", exception.getMessage());
    }
}
