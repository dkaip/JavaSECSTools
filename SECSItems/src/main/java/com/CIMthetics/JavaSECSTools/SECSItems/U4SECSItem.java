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
package com.CIMthetics.JavaSECSTools.SECSItems;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * This class represents/implements a <code>SECSItem</code> with the SECS data type of <code>U4</code>,
 * which is a 4 byte (32-bit) unsigned integer.  From the Java side this data
 * type is handled as a Java <code>long</code>.
 * <p>
 * Due to Java not having unsigned data types a scheme of size promotion is used
 * to deal with the issue.  In this case, this is handled by using a java
 * <code>long</code> integer (64-bits) to contain the value of the unsigned four byte integer (32-bits).  With
 * this method the user of this library is able to use normal unsigned values for
 * computational purposes.  Just make sure the values that are stored in the
 * &quot;long&quot; are no larger than +4294967295 (<code>0xFFFFFFFF</code>) and do not make them negative.
 * 
 * @author Douglas Kaip
 */

public class U4SECSItem extends SECSItem
{
    private long value;
    
    /**
     * This constructor creates a <code>SECSItem</code> that has a type of <code>U4</code> 
     * with the specified value.
     * <br>
     * Note: It will be created with 1 length byte.
     * 
     * @param value - the value to be assigned to this <code>SECSItem</code>
     */
    public U4SECSItem(long value)
    {
        super(SECSItemFormatCode.U4, 4);
        this.value = value;
    }
    
    /**
     * This constructor creates a <code>SECSItem</code> that has a type of <code>U4</code>
     * with the specified value.
     * <p>
     * This form of the constructor is not needed much nowadays.  In past
     * there were situations where the equipment required that messages
     * contained SECSItems that had a specified number of length bytes.
     * This form of the constructor is here to handle these problem child cases.
     * <br>
     * Note: It will be created with the specified number of length bytes.
     * 
     * @param value the value to be assigned to this <code>SECSItem</code>
     * @param desiredNumberOfLengthBytes The number of length bytes to be used for this <code>SECSItem</code>.
     * The value for the number of length bytes must be <code>ONE</code>, <code>TWO</code>, or <code>THREE</code>.
     */
    public U4SECSItem(long value, SECSItemNumLengthBytes desiredNumberOfLengthBytes)
    {
        super(SECSItemFormatCode.U4, 4, desiredNumberOfLengthBytes);
        this.value = value;
    }
    
    /**
     * This constructor is used to create this <code>SECSItem</code> from
     * data in wire/transmission format.
     * 
     * @param data - The buffer where the wire/transmission format data is contained.
     * @param itemOffset - The offset into the data where the desired item starts.
     */
    public U4SECSItem(byte[] data, int itemOffset)
    {
        super(data, itemOffset);
        int offset = 1 + inboundNumberOfLengthBytes.valueOf() + itemOffset;
        bytesConsumed = 1 + inboundNumberOfLengthBytes.valueOf() + lengthInBytes;
        if (lengthInBytes != 4)
            throw new IllegalArgumentException("Illegal data length of: " + lengthInBytes + 
                    ".  The length of the data independent of the item header must be 4.");
        
        byte[] tempBuffer = new byte[8];
        tempBuffer[0] = 0;
        tempBuffer[1] = 0;
        tempBuffer[2] = 0;
        tempBuffer[3] = 0;
        tempBuffer[4] = data[offset + 0];
        tempBuffer[5] = data[offset + 1];
        tempBuffer[6] = data[offset + 2];
        tempBuffer[7] = data[offset + 3];
        ByteBuffer buf = ByteBuffer.wrap(tempBuffer);
        buf.order(ByteOrder.BIG_ENDIAN);
        value = buf.getLong();
    }
    
    /**
     * Returns the value of this <code>SECSItem</code> in a Java friendly format.
     * @return - A java <code>long</code> that contains this item's value.
     */
    public long getValue()
    {
        return value;
    }

    /**
     * Creates and returns a <code>byte[]</code> that represents this <code>SECSItem</code> in &quot;wire/transmission format&quot;.
     * @return - A <code>byte[]</code> representation of this <code>SECSItem</code> content that is suitable for transmission.
     */
    public byte[] toRawSECSItem()
    {
        byte[] output = new byte[outputHeaderLength()+4];
        int offset = populateSECSItemHeaderData(output, 4);
        
        byte[] temp = new byte[8];
        ByteBuffer bb = ByteBuffer.wrap(temp);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.putLong(value);
        output[offset]   = temp[4];
        output[offset+1] = temp[5];
        output[offset+2] = temp[6];
        output[offset+3] = temp[7];
        
        return output;
    }
    
    public String toString()
    {
        return "Format:" + formatCode.toString() + " Value: " + value;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (value ^ (value >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        U4SECSItem other = (U4SECSItem) obj;
        if (value != other.value)
            return false;
        return true;
    }
}
