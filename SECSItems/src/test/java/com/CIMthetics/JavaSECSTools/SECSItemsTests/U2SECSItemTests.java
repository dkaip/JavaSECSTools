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
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemFormatCode;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemNumLengthBytes;
import com.CIMthetics.JavaSECSTools.SECSItems.U2SECSItem;

public class U2SECSItemTests
{
    @Test
    public void test01()
    {
        byte[] value = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x01), 0x02, -1, -1};
        U2SECSItem secsItem = new U2SECSItem(value, 0);
        assertTrue(secsItem.getValue() == 65535);
    }

    @Test
    public void test02()
    {
        byte[] value = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x01), 0x02, -128, 0};
        U2SECSItem secsItem = new U2SECSItem(value, 0);
        assertTrue(secsItem.getValue() == 32768);
    }

    @Test
    public void test03()
    {
        byte[] value = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x01), 0x02, 0, 0};
        U2SECSItem secsItem = new U2SECSItem(value, 0);
        assertTrue(secsItem.getValue() == 0);
    }

    @Test
    public void test04()
    {
        byte[] value = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x01), 0x02, 127, -1};
        U2SECSItem secsItem = new U2SECSItem(value, 0);
        assertTrue(secsItem.getValue() == 32767);
    }

    @Test
    public void test05()
    {
        int expectedOutput = 65535;
        U2SECSItem secsItem = new U2SECSItem(expectedOutput);
        assertTrue(secsItem.getValue() == expectedOutput);
    }
    
    @Test
    public void test06()
    {
        byte[] value = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x01), 0x02, -1, -1};
        U2SECSItem secsItem = new U2SECSItem(value, 0);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.U2);
    }

    @Test
    public void test07()
    {
        int expectedOutput = 65535;
        U2SECSItem secsItem = new U2SECSItem(expectedOutput);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.U2);
    }
    
    @Test
    public void test08()
    {
        byte[] expectedResult = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x01), 0x02, -1, -1};
        
        U2SECSItem secsItem = new U2SECSItem((int)65535);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test09()
    {
        byte[] expectedResult = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x02), 0, 0x02, -1, -1};
        
        U2SECSItem secsItem = new U2SECSItem((int)65535, SECSItemNumLengthBytes.TWO);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test10()
    {
        byte[] expectedResult = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x03), 0, 0, 0x02, -1, -1};
        
        U2SECSItem secsItem = new U2SECSItem((int)65535, SECSItemNumLengthBytes.THREE);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test11()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x01), 0x00 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        U2SECSItem secsItem = new U2SECSItem(input, 0);});
        assertEquals("Illegal data length of: 0.  The length of the data independent of the item header must be 2.", exception.getMessage());
    }

    @Test
    public void test12()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x01), 0x03 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        U2SECSItem secsItem = new U2SECSItem(input, 0);});
        assertEquals("Illegal data length of: 3.  The length of the data independent of the item header must be 2.", exception.getMessage());
    }

    @Test
    @DisplayName("Test toString()")
    public void test13()
    {
        U2SECSItem secsItem = new U2SECSItem(65535);
        assertTrue(secsItem.toString().equals("Format:U2 Value: 65535"));
    }

    @Test
    @DisplayName("Test equals()")
    public void test14()
    {
        U2SECSItem secsItem1 = new U2SECSItem(65535);
        U2SECSItem secsItem2 = new U2SECSItem(65535);
        assertTrue(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test15()
    {
        U2SECSItem secsItem1 = new U2SECSItem(65535);
        U2SECSItem secsItem2 = new U2SECSItem(0);
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test16()
    {
        U2SECSItem secsItem1 = new U2SECSItem(65535);
        U2SECSItem secsItem2 = null;
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test17()
    {
        U2SECSItem secsItem1 = new U2SECSItem(65535);
        assertTrue(secsItem1.equals(secsItem1));
    }

    @Test
    @DisplayName("Test equals()")
    public void test19()
    {
        U2SECSItem secsItem1 = new U2SECSItem(65535);
        Object secsItem2 = new F8SECSItem(2.141592D);
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test hashCode()")
    public void test20()
    {
        U2SECSItem secsItem1 = new U2SECSItem(65535);
        assertTrue(secsItem1.hashCode() == 65566);
    }

    @Test
    @DisplayName("Test hashCode()")
    public void test21()
    {
        U2SECSItem secsItem1 = new U2SECSItem(65535);
        U2SECSItem secsItem2 = new U2SECSItem(65535);
        assertTrue(secsItem1.hashCode() == secsItem2.hashCode());
    }
}
