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

import com.CIMthetics.JavaSECSTools.SECSItems.F4SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.F8SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemFormatCode;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemNumLengthBytes;

public class F4SECSItemTests
{
    @Test
    public void test01()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 0x04, 127, 127, -1, -1 };
        float expectedOutput = Float.MAX_VALUE;
        F4SECSItem secsItem = new F4SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test02()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 0x04, 0, 0, 0, 1 };
        float expectedOutput = Float.MIN_VALUE;
        F4SECSItem secsItem = new F4SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test02a()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 0x04, -1, 127, -1, -1 };
        float expectedOutput = -Float.MAX_VALUE;
        F4SECSItem secsItem = new F4SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test03()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 0x04, -1, -128, 0, 0 };
        float expectedOutput = Float.NEGATIVE_INFINITY;
        F4SECSItem secsItem = new F4SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test04()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 0x04, 127, -128, 0, 0 };
        float expectedOutput = Float.POSITIVE_INFINITY;
        F4SECSItem secsItem = new F4SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test05()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 0x04, 0, 0, 0, 0 };
        float expectedOutput = 0.0F;
        F4SECSItem secsItem = new F4SECSItem(input, 0);
        assertTrue(secsItem.getValue() == expectedOutput);
    }

    @Test
    public void test06()
    {
        float expectedOutput = 3.141592F;
        F4SECSItem secsItem = new F4SECSItem(expectedOutput);
        assertTrue(secsItem.getValue() == expectedOutput);
    }
    
    @Test
    public void test07()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 0x04, 127, 127, -1, -1 };
        F4SECSItem secsItem = new F4SECSItem(input, 0);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.F4);
    }

    @Test
    public void test08()
    {
        float expectedOutput = 3.141592F;
        F4SECSItem secsItem = new F4SECSItem(expectedOutput);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.F4);
    }
    
    @Test
    public void test09()
    {
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 0x04, 127, -128, 0, 0 };
        
        F4SECSItem secsItem = new F4SECSItem(Float.POSITIVE_INFINITY);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test10()
    {
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x02), 0, 0x04, 127, -128, 0, 0 };
        
        F4SECSItem secsItem = new F4SECSItem(Float.POSITIVE_INFINITY, SECSItemNumLengthBytes.TWO);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test11()
    {
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x03), 0, 0, 0x04, 127, -128, 0, 0 };
        
        F4SECSItem secsItem = new F4SECSItem(Float.POSITIVE_INFINITY, SECSItemNumLengthBytes.THREE);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test12()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 0x00 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        F4SECSItem secsItem = new F4SECSItem(input, 0);});
        assertEquals("Illegal data length of: 0.  The length of the data independent of the item header must be 4.", exception.getMessage());
    }

    @Test
    public void test13()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 0x05 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        F4SECSItem secsItem = new F4SECSItem(input, 0);});
        assertEquals("Illegal data length of: 5.  The length of the data independent of the item header must be 4.", exception.getMessage());
    }

    @Test
    @DisplayName("Test toString()")
    public void test14()
    {
        F4SECSItem secsItem = new F4SECSItem(3.141592F);
        assertTrue(secsItem.toString().equals("Format:F4 Value: 3.141592"));
    }

    @Test
    @DisplayName("Test equals()")
    public void test15()
    {
        F4SECSItem secsItem1 = new F4SECSItem(3.141592F);
        F4SECSItem secsItem2 = new F4SECSItem(3.141592F);
        assertTrue(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test16()
    {
        F4SECSItem secsItem1 = new F4SECSItem(3.141592F);
        F4SECSItem secsItem2 = new F4SECSItem(2.141592F);
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test17()
    {
        F4SECSItem secsItem1 = new F4SECSItem(3.141592F);
        F4SECSItem secsItem2 = null;
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test18()
    {
        F4SECSItem secsItem1 = new F4SECSItem(3.141592F);
        assertTrue(secsItem1.equals(secsItem1));
    }

    @Test
    @DisplayName("Test equals()")
    public void test19()
    {
        F4SECSItem secsItem1 = new F4SECSItem(3.141592F);
        SECSItem secsItem2 = null;
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test20()
    {
        F4SECSItem secsItem1 = new F4SECSItem(3.141592F);
        Object secsItem2 = new F8SECSItem(2.141592D);
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test hashCode()")
    public void test21()
    {
        F4SECSItem secsItem1 = new F4SECSItem(3.141592F);
        assertTrue(secsItem1.hashCode() == 1078530039);
    }

    @Test
    @DisplayName("Test hashCode()")
    public void test22()
    {
        F4SECSItem secsItem1 = new F4SECSItem(3.141592F);
        F4SECSItem secsItem2 = new F4SECSItem(3.141592F);
        assertTrue(secsItem1.hashCode() == secsItem2.hashCode());
    }
}