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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.CIMthetics.JavaSECSTools.SECSItems.F8SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemFormatCode;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemNumLengthBytes;
import com.CIMthetics.JavaSECSTools.SECSItems.U1ArraySECSItem;

public class U1ArraySECSItemTests
{
    @Test
    public void test01()
    {
        byte[] input = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x01), 0x04, -1, -128, 0, 127};
        U1ArraySECSItem secsItem = new U1ArraySECSItem(input, 0);
        assertTrue(secsItem.getValue()[0] == 255);
        assertTrue(secsItem.getValue()[1] == 128);
        assertTrue(secsItem.getValue()[2] == 0);
        assertTrue(secsItem.getValue()[3] == 127);
    }

    @Test
    public void test02()
    {
        short[] input = {255, 128, 0, 127};
        U1ArraySECSItem secsItem = new U1ArraySECSItem(input);
        assertArrayEquals(secsItem.getValue(), input);
    }

    @Test
    public void test03()
    {
        byte[] input = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x01), 0x04, -1, -128, 0, 127};
        U1ArraySECSItem secsItem = new U1ArraySECSItem(input, 0);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.U1);
    }

    @Test
    public void test04()
    {
        short[] input = {255, 128, 0, 127};
        U1ArraySECSItem secsItem = new U1ArraySECSItem(input);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.U1);
    }

    @Test
    public void test05()
    {
        short[] input = { 255, 128, 0, 1, 127 };
        byte[] expectedResult = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x01), 0x05, -1, -128, 0, 1, 127};
       
        U1ArraySECSItem secsItem = new U1ArraySECSItem(input);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test06()
    {
        short[] input = { 255, 128, 0, 1, 127 };
        byte[] expectedResult = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x02), 0, 0x05, -1, -128, 0, 1, 127};
       
        U1ArraySECSItem secsItem = new U1ArraySECSItem(input, SECSItemNumLengthBytes.TWO);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test07()
    {
        short[] input = { 255, 128, 0, 1, 127 };
        byte[] expectedResult = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x03), 0, 0, 0x05, -1, -128, 0, 1, 127};
       
        U1ArraySECSItem secsItem = new U1ArraySECSItem(input, SECSItemNumLengthBytes.THREE);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    @DisplayName("Test empty array creation.")
    public void test08()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x01), 0x00};
        
        U1ArraySECSItem secsItem = new U1ArraySECSItem(input, 0);
        
        assertTrue(secsItem.getValue().length == 0);
    }

    @Test
    @DisplayName("Test toString()")
    public void test09()
    {
        short[] input = { 255, 128, 0, 1, 127 };

        U1ArraySECSItem secsItem = new U1ArraySECSItem(input, SECSItemNumLengthBytes.ONE);
        
        assertTrue(secsItem.toString().equals("Format:U1 Value: Array"));
    }

    @Test
    @DisplayName("Test hashCode()")
    public void test10()
    {
        short[] input = { 255, 128, 0, 1, 127 };

        U1ArraySECSItem secsItem = new U1ArraySECSItem(input, SECSItemNumLengthBytes.ONE);

        assertTrue(secsItem.hashCode() == 267940443);
    }

    @Test
    @DisplayName("Test equals()")
    public void test11()
    {
        short[] input = { 255, 128, 0, 1, 127 };

        U1ArraySECSItem secsItem = new U1ArraySECSItem(input, SECSItemNumLengthBytes.ONE);

        assertTrue(secsItem.equals(secsItem));
    }

    @Test
    @DisplayName("Test equals()")
    public void test12()
    {
        short[] input = { 255, 128, 0, 1, 127 };

        U1ArraySECSItem secsItem = new U1ArraySECSItem(input, SECSItemNumLengthBytes.ONE);

        assertFalse(secsItem.equals(null));
    }

    @Test
    @DisplayName("Test equals()")
    public void test13()
    {
        short[] input = { 255, 128, 0, 1, 127 };

        U1ArraySECSItem secsItem = new U1ArraySECSItem(input, SECSItemNumLengthBytes.ONE);
        
        Object secsItem2 = new F8SECSItem(2.141592D);
        assertFalse(secsItem.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test14()
    {
        short[] input1 = { 255, 128, 0, 1, 127 };
        short[] input2 = { 255, 128, 0, 1, 127 };

        U1ArraySECSItem secsItem1 = new U1ArraySECSItem(input1, SECSItemNumLengthBytes.ONE);
        U1ArraySECSItem secsItem2 = new U1ArraySECSItem(input2, SECSItemNumLengthBytes.ONE);
        
        assertTrue(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Test equals()")
    public void test15()
    {
        short[] input1 = { 255, 128, 0, 1, 127 };
        short[] input2 = { 255, 128, 0, 1, 0 };

        U1ArraySECSItem secsItem1 = new U1ArraySECSItem(input1, SECSItemNumLengthBytes.ONE);
        U1ArraySECSItem secsItem2 = new U1ArraySECSItem(input2, SECSItemNumLengthBytes.ONE);
        
        assertFalse(secsItem1.equals(secsItem2));
    }
}
