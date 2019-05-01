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
import com.CIMthetics.JavaSECSTools.SECSItems.I8ArraySECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemFormatCode;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemNumLengthBytes;

public class I8ArraySECSItemTests
{
    @Test
    public void test01()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 40,
                -1, -1, -1, -1, -1, -1, -1, -1,
                -128, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1,
                127, -1, -1, -1, -1, -1, -1, -1 };
        I8ArraySECSItem secsItem = new I8ArraySECSItem(input, 0);
        assertTrue(secsItem.getValue()[0] == -1);
        assertTrue(secsItem.getValue()[1] == -9223372036854775808L);
        assertTrue(secsItem.getValue()[2] == 0);
        assertTrue(secsItem.getValue()[3] == 1);
        assertTrue(secsItem.getValue()[4] == 9223372036854775807L);
    }

    @Test
    public void test02()
    {
        long[] input = { -1, -9223372036854775808L, 0, 1, 9223372036854775807L };
        I8ArraySECSItem secsItem = new I8ArraySECSItem(input);
        assertArrayEquals(secsItem.getValue(), input);
    }

    @Test
    public void test03()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 40,
                -1, -1, -1, -1, -1, -1, -1, -1,
                -128, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1,
                127, -1, -1, -1, -1, -1, -1, -1 };
        I8ArraySECSItem secsItem = new I8ArraySECSItem(input, 0);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.I8);
    }

    @Test
    public void test04()
    {
        long[] input = { -1, -9223372036854775808L, 0, 1, 9223372036854775807L };
        I8ArraySECSItem secsItem = new I8ArraySECSItem(input);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.I8);
    }

    @Test
    public void test05()
    {
        long[] input = { -1, -9223372036854775808L, 0, 1, 9223372036854775807L };
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 40,
                -1, -1, -1, -1, -1, -1, -1, -1,
                -128, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1,
                127, -1, -1, -1, -1, -1, -1, -1 };
        
        I8ArraySECSItem secsItem = new I8ArraySECSItem(input);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test06()
    {
        long[] input = { -1, -9223372036854775808L, 0, 1, 9223372036854775807L };
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x02), 0, 40,
                -1, -1, -1, -1, -1, -1, -1, -1,
                -128, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1,
                127, -1, -1, -1, -1, -1, -1, -1 };
        
        I8ArraySECSItem secsItem = new I8ArraySECSItem(input, SECSItemNumLengthBytes.TWO);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test07()
    {
        long[] input = { -1, -9223372036854775808L, 0, 1, 9223372036854775807L };
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x03), 0, 0, 40,
                -1, -1, -1, -1, -1, -1, -1, -1,
                -128, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1,
                127, -1, -1, -1, -1, -1, -1, -1 };
        
        I8ArraySECSItem secsItem = new I8ArraySECSItem(input, SECSItemNumLengthBytes.THREE);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    @DisplayName("Test toString()")
    public void test08()
    {
        long[] input = { -1, -9223372036854775808L, 0, 1, 9223372036854775807L };

        I8ArraySECSItem secsItem = new I8ArraySECSItem(input, SECSItemNumLengthBytes.ONE);
        
        assertTrue(secsItem.toString().equals("Format:I8 Value: Array"));
    }

    @Test
    @DisplayName("Test hashCode()")
    public void test09()
    {
        long[] input = { -1, -9223372036854775808L, 0, 1, 9223372036854775807L };

        I8ArraySECSItem secsItem = new I8ArraySECSItem(input, SECSItemNumLengthBytes.ONE);

        assertTrue(secsItem.hashCode() == 28629213);
    }

    @Test
    @DisplayName("Test equals()")
    public void test10()
    {
        long[] input = { -1, -9223372036854775808L, 0, 1, 9223372036854775807L };

        I8ArraySECSItem secsItem = new I8ArraySECSItem(input, SECSItemNumLengthBytes.ONE);

        assertTrue(secsItem.equals(secsItem));
    }

    @Test
    @DisplayName("Test equals()")
    public void test11()
    {
        long[] input = { -1, -9223372036854775808L, 0, 1, 9223372036854775807L };

        I8ArraySECSItem secsItem = new I8ArraySECSItem(input, SECSItemNumLengthBytes.ONE);

        assertFalse(secsItem.equals(null));
    }

    @Test
    @DisplayName("Test equals()")
    public void test12()
    {
        long[] input = { -1, -9223372036854775808L, 0, 1, 9223372036854775807L };

        I8ArraySECSItem secsItem = new I8ArraySECSItem(input, SECSItemNumLengthBytes.ONE);
        
        Object secsItem2 = new F8SECSItem(2.141592D);
        assertFalse(secsItem.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test13()
    {
        long[] input1 = { -1, -9223372036854775808L, 0, 1, 9223372036854775807L };
        long[] input2 = { -1, -9223372036854775808L, 0, 1, 9223372036854775807L };

        I8ArraySECSItem secsItem1 = new I8ArraySECSItem(input1, SECSItemNumLengthBytes.ONE);
        I8ArraySECSItem secsItem2 = new I8ArraySECSItem(input2, SECSItemNumLengthBytes.ONE);
        
        assertTrue(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test14()
    {
        long[] input1 = { -1, -9223372036854775808L, 0, 1, 9223372036854775807L };
        long[] input2 = { -1, -9223372036854775808L, 0, 1, 0 };

        I8ArraySECSItem secsItem1 = new I8ArraySECSItem(input1, SECSItemNumLengthBytes.ONE);
        I8ArraySECSItem secsItem2 = new I8ArraySECSItem(input2, SECSItemNumLengthBytes.ONE);
        
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    public void test15()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 0x00 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        I8ArraySECSItem secsItem = new I8ArraySECSItem(input, 0);});
        assertEquals("Illegal data length of: 0 payload length must be a non-zero multiple of 8.", exception.getMessage());
    }

    @Test
    public void test16()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 0x03 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        I8ArraySECSItem secsItem = new I8ArraySECSItem(input, 0);});
        assertEquals("Illegal data length of: 3 payload length must be a non-zero multiple of 8.", exception.getMessage());
    }
}
