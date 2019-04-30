package com.CIMthetics.JavaSECSTools.SECSItemsTests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import com.CIMthetics.JavaSECSTools.SECSItems.ASCIISECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.BinarySECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.BooleanArraySECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.BooleanSECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.F4ArraySECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.F4SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.F8ArraySECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.F8SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.I1ArraySECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.I1SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.I2ArraySECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.I2SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.I4ArraySECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.I4SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.I8ArraySECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.I8SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.ListSECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.RawSECSData;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemFormatCode;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemNumLengthBytes;
import com.CIMthetics.JavaSECSTools.SECSItems.U1ArraySECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.U1SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.U2ArraySECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.U2SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.U4ArraySECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.U4SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.U8ArraySECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.U8SECSItem;

class RawSECSDataTests
{

    @Test
    void test01()
    {
        byte[] input = { 
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.L ) << 2) | 0x01), 25,
                
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.A ) << 2) | 0x02), 0x00, 0x03, 0x41, 0x42, 0x43,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.B ) << 2) | 0x03), 0x00, 0x00, 0x05, -128, -1, 0, 1, 127,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.BO ) << 2) | 0x01), 0x08, 1, 0, 2, 0, -1, 0, 1, 1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.BO ) << 2) | 0x01), 0x01, 5,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 20, 
                127, 127, -1, -1,
                0, 0, 0, 1,
                -1, -128, 0, 0,
                127, -128, 0, 0,
                0, 0, 0, 0,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 0x04, 127, 127, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 40, 
                127, -17, -1, -1, -1, -1, -1, -1,
                0, 0, 0, 0, 0, 0, 0, 1,
                -1, -16, 0, 0, 0, 0, 0, 0,
                127, -16, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 0x08, 127, -17, -1, -1, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1 ) << 2) | 0x01), 0x04, -1, -128, 0, 127,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1 ) << 2) | 0x01), 0x01, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I2 ) << 2) | 0x01), 10,
                -1, -1,
                -128, 0,
                0, 0,
                0, 1,
                127, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I2 ) << 2) | 0x01), 0x02, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I4 ) << 2) | 0x01), 20,
                -1, -1, -1, -1,
                -128, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 1,
                127, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I4 ) << 2) | 0x01), 0x04, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 40,
                -1, -1, -1, -1, -1, -1, -1, -1,
                -128, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1,
                127, -1, -1, -1, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 0x08, -1, -1, -1, -1, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x01), 0x04, -1, -128, 0, 127,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x01), 0x01, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x01), 10,
                -1, -1,
                -128, 0,
                0, 0,
                0, 1,
                127, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x01), 0x02, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x01), 20,
                -1, -1, -1, -1,
                -128, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 1,
                127, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x01), 0x04, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 40,
                -1, -1, -1, -1, -1, -1, -1, -1,
                -128, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1,
                127, -1, -1, -1, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 0x08, -1, -1, -1, -1, -1, -1, -1, -1,
                
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.L ) << 2) | 0x01), 24,
                
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.A ) << 2) | 0x02), 0x00, 0x03, 0x41, 0x42, 0x43,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.B ) << 2) | 0x03), 0x00, 0x00, 0x05, -128, -1, 0, 1, 127,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.BO ) << 2) | 0x01), 0x08, 1, 0, 2, 0, -1, 0, 1, 1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.BO ) << 2) | 0x01), 0x01, 5,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 20, 
                127, 127, -1, -1,
                0, 0, 0, 1,
                -1, -128, 0, 0,
                127, -128, 0, 0,
                0, 0, 0, 0,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 0x04, 127, 127, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 40, 
                127, -17, -1, -1, -1, -1, -1, -1,
                0, 0, 0, 0, 0, 0, 0, 1,
                -1, -16, 0, 0, 0, 0, 0, 0,
                127, -16, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 0x08, 127, -17, -1, -1, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1 ) << 2) | 0x01), 0x04, -1, -128, 0, 127,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1 ) << 2) | 0x01), 0x01, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I2 ) << 2) | 0x01), 10,
                -1, -1,
                -128, 0,
                0, 0,
                0, 1,
                127, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I2 ) << 2) | 0x01), 0x02, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I4 ) << 2) | 0x01), 20,
                -1, -1, -1, -1,
                -128, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 1,
                127, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I4 ) << 2) | 0x01), 0x04, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 40,
                -1, -1, -1, -1, -1, -1, -1, -1,
                -128, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1,
                127, -1, -1, -1, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 0x08, -1, -1, -1, -1, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x01), 0x04, -1, -128, 0, 127,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x01), 0x01, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x01), 10,
                -1, -1,
                -128, 0,
                0, 0,
                0, 1,
                127, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x01), 0x02, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x01), 20,
                -1, -1, -1, -1,
                -128, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 1,
                127, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x01), 0x04, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 40,
                -1, -1, -1, -1, -1, -1, -1, -1,
                -128, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1,
                127, -1, -1, -1, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 0x08, -1, -1, -1, -1, -1, -1, -1, -1

                };
        ListSECSItem secsItem = new ListSECSItem(input, 0);
        
        SECSItem testElement = null;
        LinkedList<SECSItem> expectedData1 = new LinkedList<SECSItem>();
        LinkedList<SECSItem> expectedData2 = new LinkedList<SECSItem>();
        testElement = new ASCIISECSItem("ABC", SECSItemNumLengthBytes.TWO);
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new BinarySECSItem(new short[]{128, 255, 0, 1, 127}, SECSItemNumLengthBytes.THREE);
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new BooleanArraySECSItem(new boolean[]{true, false, true, false, true, false, true, true});
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new BooleanSECSItem(true);
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new F4ArraySECSItem(new float[]{Float.MAX_VALUE, Float.MIN_VALUE, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 0.0F});
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new F4SECSItem(Float.MAX_VALUE);
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new F8ArraySECSItem(new double[]{Double.MAX_VALUE, Double.MIN_VALUE, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0D});
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new F8SECSItem(Double.MAX_VALUE);
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new I1ArraySECSItem(new byte[]{-1, -128, 0, 127});
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new I1SECSItem((byte)-1);
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new I2ArraySECSItem(new short[]{-1, -32768, 0, 1, 32767});
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new I2SECSItem((short)-1);
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new I4ArraySECSItem(new int[]{-1, -2147483648, 0, 1, 2147483647});
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new I4SECSItem((int)-1);
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new I8ArraySECSItem(new long[]{-1, -9223372036854775808L, 0, 1, 9223372036854775807L});
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new I8SECSItem((long)-1);
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new U1ArraySECSItem(new short[]{255, 128, 0, 127});
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new U1SECSItem((short)255);
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new U2ArraySECSItem(new int[]{65535, 32768, 0, 1, 32767});
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new U2SECSItem((int)65535);
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new U4ArraySECSItem(new long[]{0xFFFFFFFFL, 2147483648L, 0, 1, 2147483647L});
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new U4SECSItem(0xFFFFFFFFL);
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new U8ArraySECSItem(new BigInteger[]{new BigInteger("FFFFFFFFFFFFFFFF", 16), new BigInteger("8000000000000000", 16), new BigInteger("0000000000000000", 16), new BigInteger("0000000000000001", 16), new BigInteger("7FFFFFFFFFFFFFFF", 16)});
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new U8SECSItem(new BigInteger("FFFFFFFFFFFFFFFF", 16));
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        
        ListSECSItem innerList = new ListSECSItem(expectedData2);
        expectedData1.add(innerList);
        
        ListSECSItem expectedResult = new ListSECSItem(expectedData1);
        
        RawSECSData rawData1 = new RawSECSData(secsItem);
        RawSECSData rawData2 = new RawSECSData(expectedResult);
        
        assertArrayEquals(rawData1.getData(), rawData2.getData());
    }

    @Test
    void test02()
    {
        byte[] input = { 
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.L ) << 2) | 0x01), 25,
                
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.A ) << 2) | 0x02), 0x00, 0x03, 0x41, 0x42, 0x43,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.B ) << 2) | 0x03), 0x00, 0x00, 0x05, -128, -1, 0, 1, 127,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.BO ) << 2) | 0x01), 0x08, 1, 0, 2, 0, -1, 0, 1, 1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.BO ) << 2) | 0x01), 0x01, 5,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 20, 
                127, 127, -1, -1,
                0, 0, 0, 1,
                -1, -128, 0, 0,
                127, -128, 0, 0,
                0, 0, 0, 0,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 0x04, 127, 127, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 40, 
                127, -17, -1, -1, -1, -1, -1, -1,
                0, 0, 0, 0, 0, 0, 0, 1,
                -1, -16, 0, 0, 0, 0, 0, 0,
                127, -16, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 0x08, 127, -17, -1, -1, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1 ) << 2) | 0x01), 0x04, -1, -128, 0, 127,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1 ) << 2) | 0x01), 0x01, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I2 ) << 2) | 0x01), 10,
                -1, -1,
                -128, 0,
                0, 0,
                0, 1,
                127, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I2 ) << 2) | 0x01), 0x02, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I4 ) << 2) | 0x01), 20,
                -1, -1, -1, -1,
                -128, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 1,
                127, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I4 ) << 2) | 0x01), 0x04, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 40,
                -1, -1, -1, -1, -1, -1, -1, -1,
                -128, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1,
                127, -1, -1, -1, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 0x08, -1, -1, -1, -1, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x01), 0x04, -1, -128, 0, 127,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x01), 0x01, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x01), 10,
                -1, -1,
                -128, 0,
                0, 0,
                0, 1,
                127, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x01), 0x02, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x01), 20,
                -1, -1, -1, -1,
                -128, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 1,
                127, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x01), 0x04, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 40,
                -1, -1, -1, -1, -1, -1, -1, -1,
                -128, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1,
                127, -1, -1, -1, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 0x08, -1, -1, -1, -1, -1, -1, -1, -1,
                
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.L ) << 2) | 0x01), 24,
                
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.A ) << 2) | 0x02), 0x00, 0x03, 0x41, 0x42, 0x43,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.B ) << 2) | 0x03), 0x00, 0x00, 0x05, -128, -1, 0, 1, 127,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.BO ) << 2) | 0x01), 0x08, 1, 0, 2, 0, -1, 0, 1, 1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.BO ) << 2) | 0x01), 0x01, 5,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 20, 
                127, 127, -1, -1,
                0, 0, 0, 1,
                -1, -128, 0, 0,
                127, -128, 0, 0,
                0, 0, 0, 0,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 0x04, 127, 127, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 40, 
                127, -17, -1, -1, -1, -1, -1, -1,
                0, 0, 0, 0, 0, 0, 0, 1,
                -1, -16, 0, 0, 0, 0, 0, 0,
                127, -16, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 0x08, 127, -17, -1, -1, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1 ) << 2) | 0x01), 0x04, -1, -128, 0, 127,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1 ) << 2) | 0x01), 0x01, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I2 ) << 2) | 0x01), 10,
                -1, -1,
                -128, 0,
                0, 0,
                0, 1,
                127, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I2 ) << 2) | 0x01), 0x02, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I4 ) << 2) | 0x01), 20,
                -1, -1, -1, -1,
                -128, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 1,
                127, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I4 ) << 2) | 0x01), 0x04, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 40,
                -1, -1, -1, -1, -1, -1, -1, -1,
                -128, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1,
                127, -1, -1, -1, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 0x08, -1, -1, -1, -1, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x01), 0x04, -1, -128, 0, 127,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x01), 0x01, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x01), 10,
                -1, -1,
                -128, 0,
                0, 0,
                0, 1,
                127, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x01), 0x02, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x01), 20,
                -1, -1, -1, -1,
                -128, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 1,
                127, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x01), 0x04, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 40,
                -1, -1, -1, -1, -1, -1, -1, -1,
                -128, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1,
                127, -1, -1, -1, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 0x08, -1, -1, -1, -1, -1, -1, -1, -1

                };
        
        RawSECSData rawData1 = new RawSECSData(input);
        
        assertArrayEquals(rawData1.getData(), input);
    }

    @Test
    void test03()
    {
        byte[] input = { 
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.L ) << 2) | 0x01), 25,
                
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.A ) << 2) | 0x02), 0x00, 0x03, 0x41, 0x42, 0x43,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.B ) << 2) | 0x03), 0x00, 0x00, 0x05, -128, -1, 0, 1, 127,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.BO ) << 2) | 0x01), 0x08, 1, 0, 2, 0, -1, 0, 1, 1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.BO ) << 2) | 0x01), 0x01, 5,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 20, 
                127, 127, -1, -1,
                0, 0, 0, 1,
                -1, -128, 0, 0,
                127, -128, 0, 0,
                0, 0, 0, 0,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 0x04, 127, 127, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 40, 
                127, -17, -1, -1, -1, -1, -1, -1,
                0, 0, 0, 0, 0, 0, 0, 1,
                -1, -16, 0, 0, 0, 0, 0, 0,
                127, -16, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 0x08, 127, -17, -1, -1, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1 ) << 2) | 0x01), 0x04, -1, -128, 0, 127,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1 ) << 2) | 0x01), 0x01, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I2 ) << 2) | 0x01), 10,
                -1, -1,
                -128, 0,
                0, 0,
                0, 1,
                127, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I2 ) << 2) | 0x01), 0x02, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I4 ) << 2) | 0x01), 20,
                -1, -1, -1, -1,
                -128, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 1,
                127, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I4 ) << 2) | 0x01), 0x04, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 40,
                -1, -1, -1, -1, -1, -1, -1, -1,
                -128, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1,
                127, -1, -1, -1, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 0x08, -1, -1, -1, -1, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x01), 0x04, -1, -128, 0, 127,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x01), 0x01, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x01), 10,
                -1, -1,
                -128, 0,
                0, 0,
                0, 1,
                127, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x01), 0x02, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x01), 20,
                -1, -1, -1, -1,
                -128, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 1,
                127, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x01), 0x04, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 40,
                -1, -1, -1, -1, -1, -1, -1, -1,
                -128, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1,
                127, -1, -1, -1, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 0x08, -1, -1, -1, -1, -1, -1, -1, -1,
                
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.L ) << 2) | 0x01), 24,
                
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.A ) << 2) | 0x02), 0x00, 0x03, 0x41, 0x42, 0x43,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.B ) << 2) | 0x03), 0x00, 0x00, 0x05, -128, -1, 0, 1, 127,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.BO ) << 2) | 0x01), 0x08, 1, 0, 2, 0, -1, 0, 1, 1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.BO ) << 2) | 0x01), 0x01, 5,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 20, 
                127, 127, -1, -1,
                0, 0, 0, 1,
                -1, -128, 0, 0,
                127, -128, 0, 0,
                0, 0, 0, 0,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4 ) << 2) | 0x01), 0x04, 127, 127, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 40, 
                127, -17, -1, -1, -1, -1, -1, -1,
                0, 0, 0, 0, 0, 0, 0, 1,
                -1, -16, 0, 0, 0, 0, 0, 0,
                127, -16, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8 ) << 2) | 0x01), 0x08, 127, -17, -1, -1, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1 ) << 2) | 0x01), 0x04, -1, -128, 0, 127,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1 ) << 2) | 0x01), 0x01, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I2 ) << 2) | 0x01), 10,
                -1, -1,
                -128, 0,
                0, 0,
                0, 1,
                127, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I2 ) << 2) | 0x01), 0x02, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I4 ) << 2) | 0x01), 20,
                -1, -1, -1, -1,
                -128, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 1,
                127, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I4 ) << 2) | 0x01), 0x04, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 40,
                -1, -1, -1, -1, -1, -1, -1, -1,
                -128, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1,
                127, -1, -1, -1, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8 ) << 2) | 0x01), 0x08, -1, -1, -1, -1, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x01), 0x04, -1, -128, 0, 127,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1 ) << 2) | 0x01), 0x01, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x01), 10,
                -1, -1,
                -128, 0,
                0, 0,
                0, 1,
                127, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2 ) << 2) | 0x01), 0x02, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x01), 20,
                -1, -1, -1, -1,
                -128, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 1,
                127, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4 ) << 2) | 0x01), 0x04, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 40,
                -1, -1, -1, -1, -1, -1, -1, -1,
                -128, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1,
                127, -1, -1, -1, -1, -1, -1, -1,
                (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8 ) << 2) | 0x01), 0x08, -1, -1, -1, -1, -1, -1, -1, -1

                };

        SECSItem testElement = null;
        LinkedList<SECSItem> expectedData1 = new LinkedList<SECSItem>();
        LinkedList<SECSItem> expectedData2 = new LinkedList<SECSItem>();
        testElement = new ASCIISECSItem("ABC", SECSItemNumLengthBytes.TWO);
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new BinarySECSItem(new short[]{128, 255, 0, 1, 127}, SECSItemNumLengthBytes.THREE);
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new BooleanArraySECSItem(new boolean[]{true, false, true, false, true, false, true, true});
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new BooleanSECSItem(true);
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new F4ArraySECSItem(new float[]{Float.MAX_VALUE, Float.MIN_VALUE, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 0.0F});
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new F4SECSItem(Float.MAX_VALUE);
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new F8ArraySECSItem(new double[]{Double.MAX_VALUE, Double.MIN_VALUE, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0D});
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new F8SECSItem(Double.MAX_VALUE);
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new I1ArraySECSItem(new byte[]{-1, -128, 0, 127});
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new I1SECSItem((byte)-1);
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new I2ArraySECSItem(new short[]{-1, -32768, 0, 1, 32767});
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new I2SECSItem((short)-1);
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new I4ArraySECSItem(new int[]{-1, -2147483648, 0, 1, 2147483647});
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new I4SECSItem((int)-1);
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new I8ArraySECSItem(new long[]{-1, -9223372036854775808L, 0, 1, 9223372036854775807L});
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new I8SECSItem((long)-1);
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new U1ArraySECSItem(new short[]{255, 128, 0, 127});
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new U1SECSItem((short)255);
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new U2ArraySECSItem(new int[]{65535, 32768, 0, 1, 32767});
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new U2SECSItem((int)65535);
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new U4ArraySECSItem(new long[]{0xFFFFFFFFL, 2147483648L, 0, 1, 2147483647L});
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new U4SECSItem(0xFFFFFFFFL);
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new U8ArraySECSItem(new BigInteger[]{new BigInteger("FFFFFFFFFFFFFFFF", 16), new BigInteger("8000000000000000", 16), new BigInteger("0000000000000000", 16), new BigInteger("0000000000000001", 16), new BigInteger("7FFFFFFFFFFFFFFF", 16)});
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        testElement = new U8SECSItem(new BigInteger("FFFFFFFFFFFFFFFF", 16));
        expectedData1.add(testElement);
        expectedData2.add(testElement);
        
        ListSECSItem innerList = new ListSECSItem(expectedData2);
        expectedData1.add(innerList);
        
        ListSECSItem expectedResult = new ListSECSItem(expectedData1);
        
        RawSECSData rawData1 = new RawSECSData(input);
        
        SECSItem secsItem = rawData1.generateSECSItem();
        
        assertTrue(secsItem.equals(expectedResult));
    }
}
