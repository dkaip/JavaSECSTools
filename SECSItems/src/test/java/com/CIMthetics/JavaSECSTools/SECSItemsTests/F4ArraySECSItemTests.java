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

import com.CIMthetics.JavaSECSTools.SECSItems.F4ArraySECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.F8SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemFormatCode;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemNumLengthBytes;

/**
 * 
 * @author Douglas Kaip
 *
 */
public class F4ArraySECSItemTests
{
    @SuppressWarnings("unused")
    @Test
    public void test01()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 21, 
                127, 127, -1, -1,
                0, 0, 0, 1,
                -1, -128, 0, 0,
                127, -128, 0, 0,
                0, 0, 0, 0,
                0 };
        
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {F4ArraySECSItem secsItem = new F4ArraySECSItem(input, 0);});
        
        assertEquals( "Illegal data length of: 21 payload length must be a non-zero multiple of 4.", exception.getMessage());
    }
    
    @Test
    public void test02()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 20, 
                127, 127, -1, -1,
                0, 0, 0, 1,
                -1, -128, 0, 0,
                127, -128, 0, 0,
                0, 0, 0, 0 };
        F4ArraySECSItem secsItem = new F4ArraySECSItem(input, 0);
        assertTrue(secsItem.getValue()[0] == Float.MAX_VALUE);
        assertTrue(secsItem.getValue()[1] == Float.MIN_VALUE);
        assertTrue(secsItem.getValue()[2] == Float.NEGATIVE_INFINITY);
        assertTrue(secsItem.getValue()[3] == Float.POSITIVE_INFINITY);
        assertTrue(secsItem.getValue()[4] == 0.0F);
    }

    @Test
    public void test03()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 20, 
                127, 127, -1, -1,
                0, 0, 0, 1,
                -1, -128, 0, 0,
                127, -128, 0, 0,
                0, 0, 0, 0 };
        F4ArraySECSItem secsItem = new F4ArraySECSItem(input, 0);
        assertTrue(secsItem.getSECSItemFormatCode() == SECSItemFormatCode.F4);
    }

    @Test
    public void test04()
    {
        float[] input = {Float.MAX_VALUE, Float.MIN_VALUE, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 0.0F};
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 20, 
                127, 127, -1, -1,
                0, 0, 0, 1,
                -1, -128, 0, 0,
                127, -128, 0, 0,
                0, 0, 0, 0 };
        
        F4ArraySECSItem secsItem = new F4ArraySECSItem(input);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test05()
    {
        float[] input = {Float.MAX_VALUE, Float.MIN_VALUE, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 0.0F};
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x02), 0, 20, 
                127, 127, -1, -1,
                0, 0, 0, 1,
                -1, -128, 0, 0,
                127, -128, 0, 0,
                0, 0, 0, 0 };
        
        F4ArraySECSItem secsItem = new F4ArraySECSItem(input, SECSItemNumLengthBytes.TWO);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test06()
    {
        float[] input = {Float.MAX_VALUE, Float.MIN_VALUE, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 0.0F};
        byte[] expectedResult = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x03), 0, 0, 20, 
                127, 127, -1, -1,
                0, 0, 0, 1,
                -1, -128, 0, 0,
                127, -128, 0, 0,
                0, 0, 0, 0 };
        
        F4ArraySECSItem secsItem = new F4ArraySECSItem(input, SECSItemNumLengthBytes.THREE);
        assertArrayEquals(secsItem.toRawSECSItem(), expectedResult);
    }

    @Test
    public void test07()
    {
        float[] input = {Float.MAX_VALUE, Float.MIN_VALUE, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 0.0F};

        F4ArraySECSItem secsItem = new F4ArraySECSItem(input, SECSItemNumLengthBytes.ONE);
        
        assertTrue(secsItem.toString().equals("Format:F4 Value: Array"));
    }

    @Test
    public void test08()
    {
        float[] input = {Float.MAX_VALUE, Float.MIN_VALUE, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 0.0F};

        F4ArraySECSItem secsItem = new F4ArraySECSItem(input, SECSItemNumLengthBytes.ONE);

        assertTrue(secsItem.hashCode() == 1361524124);
    }

    @Test
    public void test09()
    {
        float[] input = {Float.MAX_VALUE, Float.MIN_VALUE, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 0.0F};

        F4ArraySECSItem secsItem = new F4ArraySECSItem(input, SECSItemNumLengthBytes.ONE);

        assertTrue(secsItem.equals(secsItem));
    }

    @Test
    public void test10()
    {
        float[] input = {Float.MAX_VALUE, Float.MIN_VALUE, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 0.0F};

        F4ArraySECSItem secsItem = new F4ArraySECSItem(input, SECSItemNumLengthBytes.ONE);

        assertFalse(secsItem.equals(null));
    }

    @Test
    public void test11()
    {
        float[] input = {Float.MAX_VALUE, Float.MIN_VALUE, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 0.0F};

        F4ArraySECSItem secsItem1 = new F4ArraySECSItem(input, SECSItemNumLengthBytes.ONE);
        
        Object secsItem2 = new F8SECSItem(2.141592D);
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    public void test12()
    {
        float[] input1 = {Float.MAX_VALUE, Float.MIN_VALUE, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 0.0F};
        float[] input2 = {Float.MAX_VALUE, Float.MIN_VALUE, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 0.0F};

        F4ArraySECSItem secsItem1 = new F4ArraySECSItem(input1, SECSItemNumLengthBytes.ONE);
        F4ArraySECSItem secsItem2 = new F4ArraySECSItem(input2, SECSItemNumLengthBytes.ONE);
        
        assertTrue(secsItem1.equals(secsItem2));
    }

    @Test
    public void test13()
    {
        float[] input1 = {Float.MAX_VALUE, Float.MIN_VALUE, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 0.0F};
        float[] input2 = {Float.MAX_VALUE, Float.MIN_VALUE, Float.NEGATIVE_INFINITY, 0.0F, 0.0F};

        F4ArraySECSItem secsItem1 = new F4ArraySECSItem(input1, SECSItemNumLengthBytes.ONE);
        F4ArraySECSItem secsItem2 = new F4ArraySECSItem(input2, SECSItemNumLengthBytes.ONE);
        
        assertFalse(secsItem1.equals(secsItem2));
    }

    @Test
    public void test14()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 0x00 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        F4ArraySECSItem secsItem = new F4ArraySECSItem(input, 0);});
        assertEquals("Illegal data length of: 0 payload length must be a non-zero multiple of 4.", exception.getMessage());
    }

    @Test
    public void test15()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 0x03 };

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        F4ArraySECSItem secsItem = new F4ArraySECSItem(input, 0);});
        assertEquals("Illegal data length of: 3 payload length must be a non-zero multiple of 4.", exception.getMessage());
    }
}
