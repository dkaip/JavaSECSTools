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

import com.CIMthetics.JavaSECSTools.SECSItems.ASCIISECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.F8SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemFormatCode;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemNumLengthBytes;

public class ASCIISECSItemTests
{
    @Test
    @DisplayName("Testing constructor public ASCIISECSItem(String text) with null as text argument")
    public void test01()
    {
        ASCIISECSItem secsItem = new ASCIISECSItem(null);
        
        assertTrue(secsItem.getValue().equals(""));
    }

    @Test
    @DisplayName("Testing constructor public ASCIISECSItem(String text, SECSItemNumLengthBytes desiredNumberOfLengthBytes) with null as text argument")
    public void test02()
    {
        ASCIISECSItem secsItem = new ASCIISECSItem(null, SECSItemNumLengthBytes.ONE);
        
        assertTrue(secsItem.getValue().equals(""));
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.A);
    }

    @Test
    @DisplayName("Testing constructor public ASCIISECSItem(String text) in the normal case")
    public void test03()
    {
        ASCIISECSItem secsItem = new ASCIISECSItem("DEF");
        assertTrue(secsItem.getValue().equals("DEF"));
    }

    @Test
    @DisplayName("Testing constructor public ASCIISECSItem(byte[] data, int itemOffset)")
    public void test04()
    {
        byte[] input = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.A ) << 2) | 0x01), 0x03, 0x41, 0x42, 0x43};                  // 'A', 'B', 'C'
        ASCIISECSItem secsItem = new ASCIISECSItem(input, 0);
        assertTrue(secsItem.getValue().equals("ABC"));
    }

    @Test
    @DisplayName("Testing constructor public ASCIISECSItem(byte[] data, int itemOffset)")
    public void test05()
    {
        byte[] input = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.L ) << 2) | 0x01), 0x00,
                        (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.A ) << 2) | 0x01), 0x03, 0x41, 0x42, 0x43};                  // 'A', 'B', 'C'
        ASCIISECSItem secsItem = new ASCIISECSItem(input, 2);
        assertTrue(secsItem.getValue().equals("ABC"));
    }

    @Test
    @DisplayName("Testing getSECSItemFormatCode method")
    public void test06()
    {
        ASCIISECSItem secsItem = new ASCIISECSItem("DEF");
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.A);
    }

    @Test
    @DisplayName("Testing constructor public ASCIISECSItem(byte[] data, int itemOffset)")
    public void test07()
    {
        byte[] input = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.A ) << 2) | 0x01), 0x03, 0x41, 0x42, 0x43};                  // 'A', 'B', 'C'
        ASCIISECSItem secsItem = new ASCIISECSItem(input, 0);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.A);
    }

    @Test
    @DisplayName("Testing toRawSECSItem method")
    public void test08()
    {
        byte[] expectedResult = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.A ) << 2) | 0x01), 0x03, 0x41, 0x42, 0x43};                  // 'A', 'B', 'C'
        
        ASCIISECSItem secsItem = new ASCIISECSItem("ABC");
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    @DisplayName("Testing constructor public ASCIISECSItem(byte[] data, int itemOffset)")
    public void test09()
    {
        byte[] expectedResult = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.A ) << 2) | 0x02), 0, 0x03, 0x41, 0x42, 0x43};                  // 'A', 'B', 'C'
        
        ASCIISECSItem secsItem = new ASCIISECSItem("ABC", SECSItemNumLengthBytes.TWO);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    @DisplayName("Testing constructor public ASCIISECSItem(byte[] data, int itemOffset)")
    public void test10()
    {
        byte[] expectedResult = {(byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.A ) << 2) | 0x03), 0, 0, 0x03, 0x41, 0x42, 0x43};                  // 'A', 'B', 'C'
        
        ASCIISECSItem secsItem = new ASCIISECSItem("ABC", SECSItemNumLengthBytes.THREE);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    @DisplayName("Testing toString method")
    public void test11()
    {
        ASCIISECSItem secsItem = new ASCIISECSItem("3.141592F");
        assertTrue(secsItem.toString().equals("Format:A Value: 3.141592F"));
    }

    @Test
    @DisplayName("Testing equals method")
    public void test12()
    {
        ASCIISECSItem secsItem1 = new ASCIISECSItem("3.141592F");
        ASCIISECSItem secsItem2 = new ASCIISECSItem("3.141592F");
        assertTrue(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Testing equals method")
    public void test13()
    {
        ASCIISECSItem secsItem1 = new ASCIISECSItem("3.141592F");
        ASCIISECSItem secsItem2 = new ASCIISECSItem("2.141592F");
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Testing equals method")
    public void test14()
    {
        ASCIISECSItem secsItem1 = new ASCIISECSItem("3.141592F");
        ASCIISECSItem secsItem2 = null;
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Testing equals method")
    public void test15()
    {
        ASCIISECSItem secsItem1 = new ASCIISECSItem("3.141592F");
        assertTrue(secsItem1.equals(secsItem1));
    }

    @Test
    @DisplayName("Testing equals method")
    public void test16()
    {
        ASCIISECSItem secsItem1 = new ASCIISECSItem("3.141592F");
        SECSItem secsItem2 = null;
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Testing equals method")
    public void test17()
    {
        ASCIISECSItem secsItem1 = new ASCIISECSItem(null);
        SECSItem secsItem2 = new ASCIISECSItem("3.141592F");
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Testing equals method")
    public void test18()
    {
        ASCIISECSItem secsItem1 = new ASCIISECSItem(null);
        SECSItem secsItem2 = new ASCIISECSItem(null);;
        assertTrue(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Testing equals method")
    public void test19()
    {
        ASCIISECSItem secsItem1 = new ASCIISECSItem("3.141592F");
        Object secsItem2 = new F8SECSItem(2.141592D);
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    @DisplayName("Testing hashCode method")
    public void test20()
    {
        ASCIISECSItem secsItem1 = new ASCIISECSItem("3.141592F");
        assertTrue(secsItem1.hashCode() == -1347356470);
    }

    @Test
    @DisplayName("Testing hashCode method")
    public void test21()
    {
        ASCIISECSItem secsItem1 = new ASCIISECSItem(null);
        assertTrue(secsItem1.hashCode() == 31);
    }

    @Test
    @DisplayName("Testing hashCode method")
    public void test22()
    {
        ASCIISECSItem secsItem1 = new ASCIISECSItem("3.141592F");
        ASCIISECSItem secsItem2 = new ASCIISECSItem("3.141592F");
        assertTrue(secsItem1.hashCode() == secsItem2.hashCode());
    }
}
