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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.CIMthetics.JavaSECSTools.SECSItems.F8SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.I8SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemFormatCode;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemNumLengthBytes;

public class I8SECSItemTests
{
    @Test
    public void test01()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 0x08, -1, -1, -1, -1, -1, -1, -1, -1 };
        long expectedOutput = -1;
        I8SECSItem secsItem = new I8SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test02()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 0x08, -128, 0, 0, 0, 0, 0, 0, 0 };
        long expectedOutput = -9223372036854775808L;
        I8SECSItem secsItem = new I8SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test03()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 0x08, 0, 0, 0, 0, 0, 0, 0, 0 };
        long expectedOutput = 0;
        I8SECSItem secsItem = new I8SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test04()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 0x08, 0, 0, 0, 0, 0, 0, 0, 1 };
        long expectedOutput = 1;
        I8SECSItem secsItem = new I8SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test05()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 0x08, 127, -1, -1, -1, -1, -1, -1, -1 };
        long expectedOutput = 9223372036854775807L;
        I8SECSItem secsItem = new I8SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test06()
    {
        long expectedOutput = 9223372036854775807L;
        I8SECSItem secsItem = new I8SECSItem(expectedOutput);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test07()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 0x08, -1, -1, -1, -1, -1, -1, -1, -1 };
        I8SECSItem secsItem = new I8SECSItem(input, 0);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.I8);
    }

    @Test
    public void test08()
    {
        long expectedOutput = 9223372036854775807L;
        I8SECSItem secsItem = new I8SECSItem(expectedOutput);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.I8);
    }

    @Test
    public void test09()
    {
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 0x08, 127, -1, -1, -1, -1, -1, -1, -1 };
        
        I8SECSItem secsItem = new I8SECSItem(9223372036854775807L);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test10()
    {
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x02), 0, 0x08, 127, -1, -1, -1, -1, -1, -1, -1 };
        
        I8SECSItem secsItem = new I8SECSItem(9223372036854775807L, SECSItemNumLengthBytes.TWO);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test11()
    {
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x03), 0, 0, 0x08, 127, -1, -1, -1, -1, -1, -1, -1 };
        
        I8SECSItem secsItem = new I8SECSItem(9223372036854775807L, SECSItemNumLengthBytes.THREE);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test12()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 0x00 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        I8SECSItem secsItem = new I8SECSItem(input, 0);});
        assertEquals("Illegal data length of: 0.  The length of the data independent of the item header must be 8.", exception.getMessage());
    }

    @Test
    public void test13()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 0x09 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        I8SECSItem secsItem = new I8SECSItem(input, 0);});
        assertEquals("Illegal data length of: 9.  The length of the data independent of the item header must be 8.", exception.getMessage());
    }

    @Test
    @DisplayName("Test toString()")
    public void test14()
    {
        I8SECSItem secsItem = new I8SECSItem(9223372036854775807L);
        assertTrue(secsItem.toString().equals("Format:I8 Value: 9223372036854775807"));
    }

    @Test
    @DisplayName("Test equals()")
    public void test15()
    {
        I8SECSItem secsItem1 = new I8SECSItem(9223372036854775807L);
        I8SECSItem secsItem2 = new I8SECSItem(9223372036854775807L);
        assertTrue(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test16()
    {
        I8SECSItem secsItem1 = new I8SECSItem(9223372036854775807L);
        I8SECSItem secsItem2 = new I8SECSItem(-9223372036854775808L);
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test17()
    {
        I8SECSItem secsItem1 = new I8SECSItem(9223372036854775807L);
        I8SECSItem secsItem2 = null;
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test18()
    {
        I8SECSItem secsItem1 = new I8SECSItem(9223372036854775807L);
        assertTrue(secsItem1.equals(secsItem1));
    }

    @Test
    @DisplayName("Test equals()")
    public void test19()
    {
        I8SECSItem secsItem1 = new I8SECSItem(9223372036854775807L);
        SECSItem secsItem2 = null;
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test20()
    {
        I8SECSItem secsItem1 = new I8SECSItem(9223372036854775807L);
        Object secsItem2 = new F8SECSItem(2.141592D);
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test hashCode()")
    public void test21()
    {
        I8SECSItem secsItem1 = new I8SECSItem(9223372036854775807L);
        assertTrue(secsItem1.hashCode() == -2147483617);
    }

    @Test
    @DisplayName("Test hashCode()")
    public void test22()
    {
        I8SECSItem secsItem1 = new I8SECSItem(9223372036854775807L);
        I8SECSItem secsItem2 = new I8SECSItem(9223372036854775807L);
        assertTrue(secsItem1.hashCode() == secsItem2.hashCode());
    }
}