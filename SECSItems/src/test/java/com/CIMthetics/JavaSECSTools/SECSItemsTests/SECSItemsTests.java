package com.CIMthetics.JavaSECSTools.SECSItemsTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.CIMthetics.JavaSECSTools.SECSItems.BinarySECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItem;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemFormatCode;
import com.CIMthetics.JavaSECSTools.SECSItems.SECSItemNumLengthBytes;

class SECSItemsTests
{
    class SECSItemTest extends SECSItem
    {
        SECSItemTest(SECSItemFormatCode formatCode, int length)
        {
            super(formatCode, length);
        }

        SECSItemTest(SECSItemFormatCode formatCode, int length, SECSItemNumLengthBytes desiredNumberOfLengthBytes)
        {
            super(formatCode, length, desiredNumberOfLengthBytes);
        }

        SECSItemTest(byte[] data, int itemOffset)
        {
            super(data, itemOffset);
        }

        int testPopulateSECSItemHeaderData(byte[] buffer, int numberOfBytes)
        {
            return populateSECSItemHeaderData(buffer, numberOfBytes);
        }

        @Override
        public boolean equals(Object abc)
        {
            return false;
        }

        @Override
        public int hashCode()
        {
            return 0;
        }

        @Override
        public byte[] toRawSECSItem()
        {
            return null;
        }
    }

    @Test
    @DisplayName("Test invalid length in Constructor SECSItem(SECSItemFormatCode formatCode, int lengthInBytes)...length < 0.")
    void test01()
    {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        SECSItemTest secsItem = new SECSItemTest(SECSItemFormatCode.U1, -1);});
        
        assertEquals( "The value for the length argument must be between 0 and 16777215 inclusive.", exception.getMessage());
    }

    @Test
    @DisplayName("Test invalid length in Constructor SECSItem(SECSItemFormatCode formatCode, int lengthInBytes)...length > 16777215.")
    void test02()
    {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        SECSItemTest secsItem = new SECSItemTest(SECSItemFormatCode.U1, 16777216);});
        
        assertEquals( "The value for the length argument must be between 0 and 16777215 inclusive.", exception.getMessage());
    }

    @Test
    @DisplayName("Test proper format code assignement in Constructor SECSItem(SECSItemFormatCode formatCode, int lengthInBytes).")
    void test03()
    {
        for (SECSItemFormatCode formatCode : SECSItemFormatCode.values())
        {
            SECSItemTest secsItem = new SECSItemTest(formatCode, 0);
            
            assertTrue(secsItem.getSECSItemFormatCode() == formatCode);
            assertTrue(secsItem.getFormatCode() == formatCode);
        }
    }

    @Test
    @DisplayName("Test proper length byte calculation in Constructor SECSItem(SECSItemFormatCode formatCode, int lengthInBytes).")
    void test04()
    {
        SECSItemTest secsItem = new SECSItemTest(SECSItemFormatCode.U1, 0);
        
        assertTrue(secsItem.getOutboundNumberOfLengthBytes() == SECSItemNumLengthBytes.ONE);
    }

    @Test
    @DisplayName("Test proper length byte calculation in Constructor SECSItem(SECSItemFormatCode formatCode, int lengthInBytes).")
    void test05()
    {
        SECSItemTest secsItem = new SECSItemTest(SECSItemFormatCode.U1, 255);
        
        assertTrue(secsItem.getOutboundNumberOfLengthBytes() == SECSItemNumLengthBytes.ONE);
    }

    @Test
    @DisplayName("Test proper length byte calculation in Constructor SECSItem(SECSItemFormatCode formatCode, int lengthInBytes).")
    void test06()
    {
        SECSItemTest secsItem = new SECSItemTest(SECSItemFormatCode.U1, 256);
        
        assertTrue(secsItem.getOutboundNumberOfLengthBytes() == SECSItemNumLengthBytes.TWO);
    }

    @Test
    @DisplayName("Test proper length byte calculation in Constructor SECSItem(SECSItemFormatCode formatCode, int lengthInBytes).")
    void test07()
    {
        SECSItemTest secsItem = new SECSItemTest(SECSItemFormatCode.U1, 65535);
        
        assertTrue(secsItem.getOutboundNumberOfLengthBytes() == SECSItemNumLengthBytes.TWO);
    }

    @Test
    @DisplayName("Test proper length byte calculation in Constructor SECSItem(SECSItemFormatCode formatCode, int lengthInBytes).")
    void test08()
    {
        SECSItemTest secsItem = new SECSItemTest(SECSItemFormatCode.U1, 65536);
        
        assertTrue(secsItem.getOutboundNumberOfLengthBytes() == SECSItemNumLengthBytes.THREE);
    }

    @Test
    @DisplayName("Test proper length byte calculation in Constructor SECSItem(SECSItemFormatCode formatCode, int lengthInBytes).")
    void test09()
    {
        SECSItemTest secsItem = new SECSItemTest(SECSItemFormatCode.U1, 16777215);
        
        assertTrue(secsItem.getOutboundNumberOfLengthBytes() == SECSItemNumLengthBytes.THREE);
    }

    @Test
    @DisplayName("Test invalid length in Constructor SECSItem(SECSItemFormatCode formatCode, int length, SECSItemNumLengthBytes desiredNumberOfLengthBytes)...length < 0.")
    void test10()
    {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        SECSItemTest secsItem = new SECSItemTest(SECSItemFormatCode.U1, -1, SECSItemNumLengthBytes.ONE);});
        
        assertEquals( "The value for the length argument must be between 0 and 16777215 inclusive.", exception.getMessage());
    }

    @Test
    @DisplayName("Test invalid length in Constructor SECSItem(SECSItemFormatCode formatCode, int length, SECSItemNumLengthBytes desiredNumberOfLengthBytes)...length > 16777215.")
    void test11()
    {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        SECSItemTest secsItem = new SECSItemTest(SECSItemFormatCode.U1, 16777216, SECSItemNumLengthBytes.ONE);});
        
        assertEquals( "The value for the length argument must be between 0 and 16777215 inclusive.", exception.getMessage());
    }

    @Test
    @DisplayName("Test valid number of length bytes... number of length bytes = 1, length <= 255.")
    void test12()
    {
        SECSItemTest secsItem = new SECSItemTest(SECSItemFormatCode.U1, 1, SECSItemNumLengthBytes.ONE);
        
        assertTrue(secsItem.getOutboundNumberOfLengthBytes() == SECSItemNumLengthBytes.ONE);
    }

    @Test
    @DisplayName("Test valid number of length bytes... number of length bytes = 1, length > 255 && < 65536.")
    void test13()
    {
        SECSItemTest secsItem = new SECSItemTest(SECSItemFormatCode.U1, 256, SECSItemNumLengthBytes.ONE);
        
        assertTrue(secsItem.getOutboundNumberOfLengthBytes() == SECSItemNumLengthBytes.TWO);
    }

    @Test
    @DisplayName("Test valid number of length bytes... number of length bytes = 1, length >= 65536.")
    void test14()
    {
        SECSItemTest secsItem = new SECSItemTest(SECSItemFormatCode.U1, 65536, SECSItemNumLengthBytes.ONE);
        
        assertTrue(secsItem.getOutboundNumberOfLengthBytes() == SECSItemNumLengthBytes.THREE);
    }

    @Test
    @DisplayName("Test valid number of length bytes... number of length bytes = 2, length <= 255.")
    void test15()
    {
        SECSItemTest secsItem = new SECSItemTest(SECSItemFormatCode.U1, 1, SECSItemNumLengthBytes.TWO);
        
        assertTrue(secsItem.getOutboundNumberOfLengthBytes() == SECSItemNumLengthBytes.TWO);
    }

    @Test
    @DisplayName("Test valid number of length bytes... number of length bytes = 2, length > 255 && < 65536.")
    void test16()
    {
        SECSItemTest secsItem = new SECSItemTest(SECSItemFormatCode.U1, 256, SECSItemNumLengthBytes.TWO);
        
        assertTrue(secsItem.getOutboundNumberOfLengthBytes() == SECSItemNumLengthBytes.TWO);
    }

    @Test
    @DisplayName("Test valid number of length bytes... number of length bytes = 2, length >= 65536.")
    void test17()
    {
        SECSItemTest secsItem = new SECSItemTest(SECSItemFormatCode.U1, 65536, SECSItemNumLengthBytes.TWO);
        
        assertTrue(secsItem.getOutboundNumberOfLengthBytes() == SECSItemNumLengthBytes.THREE);
    }

    @Test
    @DisplayName("Test valid number of length bytes... number of length bytes = 3, length <= 255.")
    void test18()
    {
        SECSItemTest secsItem = new SECSItemTest(SECSItemFormatCode.U1, 1, SECSItemNumLengthBytes.THREE);
        
        assertTrue(secsItem.getOutboundNumberOfLengthBytes() == SECSItemNumLengthBytes.THREE);
    }

    @Test
    @DisplayName("Test valid number of length bytes... number of length bytes = 3, length > 255 && < 65536.")
    void test19()
    {
        SECSItemTest secsItem = new SECSItemTest(SECSItemFormatCode.U1, 256, SECSItemNumLengthBytes.THREE);
        
        assertTrue(secsItem.getOutboundNumberOfLengthBytes() == SECSItemNumLengthBytes.THREE);
    }

    @Test
    @DisplayName("Test valid number of length bytes... number of length bytes = 3, length >= 65536.")
    void test20()
    {
        SECSItemTest secsItem = new SECSItemTest(SECSItemFormatCode.U1, 65536, SECSItemNumLengthBytes.THREE);
        
        assertTrue(secsItem.getOutboundNumberOfLengthBytes() == SECSItemNumLengthBytes.THREE);
    }

    @Test
    @DisplayName("Test Constructor protected SECSItem(byte[] data, int itemOffset) for getting length byte correct...1 length bytes.")
    void test21()
    {
        short[] input = {128, 255, 0, 1, 127};
        
        // This is just a lazy way to get a known SECSItem in "wire format"
        BinarySECSItem secsItem = new BinarySECSItem(input, SECSItemNumLengthBytes.ONE);
        
        SECSItemTest secsItemA = new SECSItemTest(secsItem.toRawSECSItem(), 0);
        
        assertTrue(secsItemA.getInboundNumberOfLengthBytes() == SECSItemNumLengthBytes.ONE);
        assertTrue(secsItemA.getSECSItemFormatCode() == SECSItemFormatCode.B );
        assertTrue(secsItemA.getLengthInBytes() == 5);
    }
    
    @Test
    @DisplayName("Test Constructor protected SECSItem(byte[] data, int itemOffset) for getting length byte correct...2 length bytes.")
    void test22()
    {
        short[] input = {128, 255, 0, 1, 127};
        
        // This is just a lazy way to get a known SECSItem in "wire format"
        BinarySECSItem secsItem = new BinarySECSItem(input, SECSItemNumLengthBytes.TWO);
        
        SECSItemTest secsItemA = new SECSItemTest(secsItem.toRawSECSItem(), 0);
        
        assertTrue(secsItemA.getInboundNumberOfLengthBytes() == SECSItemNumLengthBytes.TWO);
        assertTrue(secsItemA.getSECSItemFormatCode() == SECSItemFormatCode.B );
        assertTrue(secsItemA.getLengthInBytes() == 5);
    }
    
    @Test
    @DisplayName("Test Constructor protected SECSItem(byte[] data, int itemOffset) for getting length byte correct...3 length bytes.")
    void test23()
    {
        short[] input = {128, 255, 0, 1, 127};
        
        // This is just a lazy way to get a known SECSItem in "wire format"
        BinarySECSItem secsItem = new BinarySECSItem(input, SECSItemNumLengthBytes.THREE);
        
        SECSItemTest secsItemA = new SECSItemTest(secsItem.toRawSECSItem(), 0);
        
        assertTrue(secsItemA.getInboundNumberOfLengthBytes() == SECSItemNumLengthBytes.THREE);
        assertTrue(secsItemA.getSECSItemFormatCode() == SECSItemFormatCode.B );
        assertTrue(secsItemA.getLengthInBytes() == 5);
    }
    
    @Test
    @DisplayName("Test Constructor protected SECSItem(byte[] data, int itemOffset) for data argument for being null.")
    void test24()
    {
        byte[] input = null;
        
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        SECSItemTest secsItem = new SECSItemTest(input, 0);});

        assertEquals("\"data\" argument must not be null.", exception.getMessage());
    }
    
    @Test
    @DisplayName("Test Constructor protected SECSItem(byte[] data, int itemOffset) for data being too short case 1.")
    void test25()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.L ) << 2) | 0x01) };
        
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        SECSItemTest secsItem = new SECSItemTest(input, 0);});

        assertEquals("\"data\" argument must have a length >= 2.", exception.getMessage());
    }
    
    @Test
    @DisplayName("Test Constructor protected SECSItem(byte[] data, int itemOffset) for data being too short case 2.")
    void test26()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.L ) << 2) | 0x02), 0x00 };
        
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        SECSItemTest secsItem = new SECSItemTest(input, 0);});

        assertEquals("With two length bytes the minimum length for the \"data\" argument is 3.", exception.getMessage());
    }
    
    @Test
    @DisplayName("Test Constructor protected SECSItem(byte[] data, int itemOffset) for data being too short case 3.")
    void test27()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.L ) << 2) | 0x03), 0x00, 0x00 };
        
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        SECSItemTest secsItem = new SECSItemTest(input, 0);});

        assertEquals("With three length bytes the minimum length for the \"data\" argument is 4.", exception.getMessage());
    }
    
    @Test
    @DisplayName("Test Constructor protected SECSItem(byte[] data, int itemOffset) for data argument for having zero length.")
    void test28()
    {
        byte[] input = {};
        
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        SECSItemTest secsItem = new SECSItemTest(input, 0);});

        assertEquals("\"data\" argument must have a length >= 2.", exception.getMessage());
    }
    
    @Test
    @DisplayName("Test Constructor List case 0 length.")
    void test29()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.L ) << 2) | 0x01), 0x00 };
        
        SECSItemTest secsItem = new SECSItemTest(input, 0);
        
        assertTrue(secsItem.getLengthInBytes() == 0);
    }
    
    @Test
    @DisplayName("Test Constructor List case 255 length.")
    void test30()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.L ) << 2) | 0x01), (byte)0xFF };
        
        SECSItemTest secsItem = new SECSItemTest(input, 0);
        
        assertTrue(secsItem.getLengthInBytes() == 255);
    }
    
    @Test
    @DisplayName("Test Constructor List case 256 length.")
    void test31()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.L ) << 2) | 0x02), 0x01, 0x00 };
        
        SECSItemTest secsItem = new SECSItemTest(input, 0);
        
        assertTrue(secsItem.getLengthInBytes() == 256);
    }
    
    @Test
    @DisplayName("Test Constructor List case 65535 length.")
    void test32()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.L ) << 2) | 0x02), (byte)0xFF, (byte)0xFF };
        
        SECSItemTest secsItem = new SECSItemTest(input, 0);
        
        assertTrue(secsItem.getLengthInBytes() == 65535);
    }
    
    @Test
    @DisplayName("Test Constructor List case 65536 length.")
    void test33()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.L ) << 2) | 0x03), 0x01, 0x00, 0x00 };
        
        SECSItemTest secsItem = new SECSItemTest(input, 0);
        
        assertTrue(secsItem.getLengthInBytes() == 65536);
    }
    
    @Test
    @DisplayName("Test Constructor List case 16777215 length.")
    void test34()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.L ) << 2) | 0x03), (byte)0xFF, (byte)0xFF, (byte)0xFF };
        
        SECSItemTest secsItem = new SECSItemTest(input, 0);
        
        assertTrue(secsItem.getLengthInBytes() == 16777215);
    }
    
    @Test
    @DisplayName("Test Constructor zero length bytes case")
    void test35()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.L ) << 2) | 0x00), 0x00 };
        
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> 
        {@SuppressWarnings("unused")
        SECSItemTest secsItem = new SECSItemTest(input, 0);});

        assertEquals("The number of length bytes is not allowed to be ZERO.", exception.getMessage());
    }
    
    /*
     * Just to stop the coverage checker from complaining.
     */
    @Test
    @DisplayName("A test just to stop the converage checker from complaining.")
    void test36()
    {
        SECSItemTest secsItem = new SECSItemTest(SECSItemFormatCode.U1, 1, SECSItemNumLengthBytes.ONE);

        assertTrue(secsItem.equals(secsItem) == false);
    }
    
    /*
     * Just to stop the coverage checker from complaining.
     */
    @Test
    @DisplayName("A test just to stop the converage checker from complaining.")
    void test37()
    {
        SECSItemTest secsItem = new SECSItemTest(SECSItemFormatCode.U1, 1, SECSItemNumLengthBytes.ONE);

        assertTrue(secsItem.toRawSECSItem() == null);
    }
    
    /*
     * Just to stop the coverage checker from complaining.
     */
    @Test
    @DisplayName("A test just to stop the converage checker from complaining.")
    void test38()
    {
        SECSItemTest secsItem = new SECSItemTest(SECSItemFormatCode.U1, 1, SECSItemNumLengthBytes.ONE);

        assertTrue(secsItem.hashCode() == 0);
    }
    
    @Test
    @DisplayName("A test of populateSECSItemHeaderData for the one length byte case.")
    void test39()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1 ) << 2) | 0x01), 0x01, 0x7F };
        
        SECSItemTest secsItem = new SECSItemTest(input, 0);
        
        byte[] itemHeader = new byte[2];
        
        int offset = secsItem.testPopulateSECSItemHeaderData(itemHeader, 1);
        assertTrue(offset == 2);
        assertTrue(itemHeader[0] == 0x65);
        assertTrue(itemHeader[1] == 1);
    }
    
    @Test
    @DisplayName("A test of populateSECSItemHeaderData for the two length bytes case.")
    void test40()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1 ) << 2) | 0x02), 0x00, 0x01, 0x7F };
        
        SECSItemTest secsItem = new SECSItemTest(input, 0);
        
        byte[] itemHeader = new byte[3];
        
        int offset = secsItem.testPopulateSECSItemHeaderData(itemHeader, 1);
        assertTrue(offset == 3);
        assertTrue(itemHeader[0] == 0x66);
        assertTrue(itemHeader[1] == 0);
        assertTrue(itemHeader[2] == 1);
    }
    
    @Test
    @DisplayName("A test of populateSECSItemHeaderData for the three length bytes case.")
    void test41()
    {
        byte[] input = { (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(SECSItemFormatCode.I1 ) << 2) | 0x03), 0x00, 0x00, 0x01, 0x7F };
        
        SECSItemTest secsItem = new SECSItemTest(input, 0);
        
        byte[] itemHeader = new byte[4];
        
        int offset = secsItem.testPopulateSECSItemHeaderData(itemHeader, 1);
        assertTrue(offset == 4);
        assertTrue(itemHeader[0] == 0x67);
        assertTrue(itemHeader[1] == 0);
        assertTrue(itemHeader[2] == 0);
        assertTrue(itemHeader[3] == 1);
    }
}
