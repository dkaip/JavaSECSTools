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
import com.CIMthetics.JavaSECSTools.SECSItems.I4SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemFormatCode;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemNumLengthBytes;

public class I4SECSItemTests
{
    @Test
    public void test01()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I4 ) << 2) | 0x01), 0x04, -1, -1, -1, -1 };
        int expectedOutput = -1;
        I4SECSItem secsItem = new I4SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test02()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I4 ) << 2) | 0x01), 0x04, -128, 0, 0, 0 };
        int expectedOutput = -2147483648;
        I4SECSItem secsItem = new I4SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test03()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I4 ) << 2) | 0x01), 0x04, 0, 0, 0, 0 };
        int expectedOutput = 0;
        I4SECSItem secsItem = new I4SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test04()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I4 ) << 2) | 0x01), 0x04, 0, 0, 0, 1 };
        int expectedOutput = 1;
        I4SECSItem secsItem = new I4SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test05()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I4 ) << 2) | 0x01), 0x04, 127, -1, -1, -1 };
        int expectedOutput = 2147483647;
        I4SECSItem secsItem = new I4SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test06()
    {
        int expectedOutput = 2147483647;
        I4SECSItem secsItem = new I4SECSItem(expectedOutput);
        assertTrue(secsItem.getValue() == expectedOutput);
    }
    
    @Test
    public void test07()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I4 ) << 2) | 0x01), 0x04, -1, -1, -1, -1 };
        I4SECSItem secsItem = new I4SECSItem(input, 0);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.I4);
    }

    @Test
    public void test08()
    {
        int expectedOutput = 2147483647;
        I4SECSItem secsItem = new I4SECSItem(expectedOutput);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.I4);
    }
    
    @Test
    public void test09()
    {
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I4 ) << 2) | 0x01), 0x04, 127, -1, -1, -1 };
        
        I4SECSItem secsItem = new I4SECSItem(2147483647);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test10()
    {
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I4 ) << 2) | 0x02), 0, 0x04, 127, -1, -1, -1 };
        
        I4SECSItem secsItem = new I4SECSItem(2147483647, SECSItemNumLengthBytes.TWO);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test11()
    {
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I4 ) << 2) | 0x03), 0, 0, 0x04, 127, -1, -1, -1 };
        
        I4SECSItem secsItem = new I4SECSItem(2147483647, SECSItemNumLengthBytes.THREE);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test12()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I4 ) << 2) | 0x01), 0x00 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        I4SECSItem secsItem = new I4SECSItem(input, 0);});
        assertEquals("Illegal data length of: 0.  The length of the data independent of the item header must be 4.", exception.getMessage());
    }

    @Test
    public void test13()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I4 ) << 2) | 0x01), 0x05 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        I4SECSItem secsItem = new I4SECSItem(input, 0);});
        assertEquals("Illegal data length of: 5.  The length of the data independent of the item header must be 4.", exception.getMessage());
    }

    @Test
    @DisplayName("Test toString()")
    public void test14()
    {
        I4SECSItem secsItem = new I4SECSItem(2147483647);
        assertTrue(secsItem.toString().equals("Format:I4 Value: 2147483647"));
    }

    @Test
    @DisplayName("Test equals()")
    public void test15()
    {
        I4SECSItem secsItem1 = new I4SECSItem(2147483647);
        I4SECSItem secsItem2 = new I4SECSItem(2147483647);
        assertTrue(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test16()
    {
        I4SECSItem secsItem1 = new I4SECSItem(2147483647);
        I4SECSItem secsItem2 = new I4SECSItem(-2147483648);
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test17()
    {
        I4SECSItem secsItem1 = new I4SECSItem(2147483647);
        I4SECSItem secsItem2 = null;
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test18()
    {
        I4SECSItem secsItem1 = new I4SECSItem(2147483647);
        assertTrue(secsItem1.equals(secsItem1));
    }

    @Test
    @DisplayName("Test equals()")
    public void test19()
    {
        I4SECSItem secsItem1 = new I4SECSItem(2147483647);
        SECSItem secsItem2 = null;
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test20()
    {
        I4SECSItem secsItem1 = new I4SECSItem(2147483647);
        Object secsItem2 = new F8SECSItem(2.141592D);
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test hashCode()")
    public void test21()
    {
        I4SECSItem secsItem1 = new I4SECSItem(2147483647);
        assertTrue(secsItem1.hashCode() == -2147483618);
    }

    @Test
    @DisplayName("Test hashCode()")
    public void test22()
    {
        I4SECSItem secsItem1 = new I4SECSItem(2147483647);
        I4SECSItem secsItem2 = new I4SECSItem(2147483647);
        assertTrue(secsItem1.hashCode() == secsItem2.hashCode());
    }
}
