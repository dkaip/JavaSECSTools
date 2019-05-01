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

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemFormatCode;

/**
 * 
 * @author Douglas Kaip
 *
 */
public class SECSItemFormatCodeTests
{

    @Test
    public void testConvertingToNumber()
    {
        int result = 0;
        
        result = SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.L);
        assertTrue( result == 0x00 );
        
        result = SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.B);
        assertTrue( result == 0x08 );
        
        result = SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.BO);
        assertTrue( result == 0x09 );
        
        result = SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.A);
        assertTrue( result == 0x10 );
        
        result = SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.J8);
        assertTrue( result == 0x11 );
        
        result = SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.C2);
        assertTrue( result == 0x12 );
        
        result = SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I8);
        assertTrue( result == 0x18 );
        
        result = SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1);
        assertTrue( result == 0x19 );
        
        result = SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I2);
        assertTrue( result == 0x1A );
        
        result = SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I4);
        assertTrue( result == 0x1C );
        
        result = SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F8);
        assertTrue( result == 0x20 );
        
        result = SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.F4);
        assertTrue( result == 0x24 );
        
        result = SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U8);
        assertTrue( result == 0x28 );
        
        result = SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U1);
        assertTrue( result == 0x29 );
        
        result = SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U2);
        assertTrue( result == 0x2A );
        
        result = SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.U4);
        assertTrue( result == 0x2C );
        
        result = SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.UNDEFINED);
        assertTrue( result == 0x3E );
    }

    @Test
    public void testConvertingFromANumber()
    {
        SECSItemFormatCode formatCode = null;
        
        /*
         * Test the codes that are supposed to be there.
         */
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)0x00);
        assertTrue(formatCode == SECSItemFormatCode.L);
        
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)0x08);
        assertTrue(formatCode == SECSItemFormatCode.B);
        
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)0x09);
        assertTrue(formatCode == SECSItemFormatCode.BO);
        
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)0x10);
        assertTrue(formatCode == SECSItemFormatCode.A);
        
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)0x11);
        assertTrue(formatCode == SECSItemFormatCode.J8);
        
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)0x12);
        assertTrue(formatCode == SECSItemFormatCode.C2);
        
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)0x18);
        assertTrue(formatCode == SECSItemFormatCode.I8);
        
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)0x19);
        assertTrue(formatCode == SECSItemFormatCode.I1);
        
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)0x1A);
        assertTrue(formatCode == SECSItemFormatCode.I2);
        
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)0x1C);
        assertTrue(formatCode == SECSItemFormatCode.I4);
        
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)0x20);
        assertTrue(formatCode == SECSItemFormatCode.F8);
        
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)0x24);
        assertTrue(formatCode == SECSItemFormatCode.F4);
        
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)0x28);
        assertTrue(formatCode == SECSItemFormatCode.U8);
        
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)0x29);
        assertTrue(formatCode == SECSItemFormatCode.U1);
        
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)0x2A);
        assertTrue(formatCode == SECSItemFormatCode.U2);
        
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)0x2C);
        assertTrue(formatCode == SECSItemFormatCode.U4);
        
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)0x3E);
        assertTrue(formatCode == SECSItemFormatCode.UNDEFINED);
        
        /*
         * Now we need to verify that we do not get a legitimate code from empty
         * elements in the table.
         * 
         * Yes, yes, it will blow up if a number is specified that is out of 
         * bounds, but, deal with it.
         */
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)1);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)2);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)3);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)4);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)5);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)6);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)7);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)10);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)11);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)12);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)13);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)14);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)15);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)19);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)20);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)21);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)22);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)23);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)27);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)29);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)30);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)31);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)33);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)34);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)35);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)37);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)38);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)39);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)43);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)45);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)46);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)47);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)48);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)49);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)50);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)51);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)52);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)53);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)54);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)55);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)56);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)57);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)58);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)59);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)60);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)61);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)62);
        assertTrue( formatCode == SECSItemFormatCode.UNDEFINED);
    }
    
    @Test
    public void testConvertingToString()
    {
        /*
         * This may seem like a silly test, but, hopefully it will prevent
         * the situation where some silly twit decides to override the
         * toString method.  If this happens it will most likely break
         * quite a bit of code "way up" the food chain.
         */
        assertTrue( SECSItemFormatCode.L.toString().equals("L") );
        assertTrue( SECSItemFormatCode.B.toString().equals("B") );
        assertTrue( SECSItemFormatCode.BO.toString().equals("BO") );
        assertTrue( SECSItemFormatCode.A.toString().equals("A") );
        assertTrue( SECSItemFormatCode.J8.toString().equals("J8") );
        assertTrue( SECSItemFormatCode.C2.toString().equals("C2") );
        assertTrue( SECSItemFormatCode.I8.toString().equals("I8") );
        assertTrue( SECSItemFormatCode.I1.toString().equals("I1") );
        assertTrue( SECSItemFormatCode.I2.toString().equals("I2") );
        assertTrue( SECSItemFormatCode.I4.toString().equals("I4") );
        assertTrue( SECSItemFormatCode.F8.toString().equals("F8") );
        assertTrue( SECSItemFormatCode.F4.toString().equals("F4") );
        assertTrue( SECSItemFormatCode.U8.toString().equals("U8") );
        assertTrue( SECSItemFormatCode.U1.toString().equals("U1") );
        assertTrue( SECSItemFormatCode.U2.toString().equals("U2") );
        assertTrue( SECSItemFormatCode.U4.toString().equals("U4") );
        assertTrue( SECSItemFormatCode.UNDEFINED.toString().equals("UNDEFINED") );
    }


}
