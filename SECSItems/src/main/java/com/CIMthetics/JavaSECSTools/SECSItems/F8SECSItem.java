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
 * This class represents/implements a <code>SECSItem</code> with the SECS data type of <code>F8</code>,
 * which is an 8 byte floating point number.  From the Java side this data
 * type is handled as a Java <code>double</code>.
 * 
 * @author Douglas Kaip
 *
 */
public class F8SECSItem extends SECSItem
{
    private double value;
    
    /**
     * This constructor creates a <code>SECSItem</code> that has a type of <code>F8</code>
     * with the specified value.
     * <br>
     * Note: It will be created with 1 length byte.
     * 
     * @param value - the value to be assigned to this <code>SECSItem</code>
     */
    public F8SECSItem(double value)
    {
        super(SECSItemFormatCode.F8, 8);
        this.value = value;
    }
    
    /**
     * This constructor creates a <code>SECSItem</code> that has a type of <code>F8</code> 
     * with the specified value.
     * <br>
     * Note: It will be created with the specified number of length bytes.
     * 
     * @param value - the value to be assigned to this <code>SECSItem</code>
     * @param desiredNumberOfLengthBytes - The number of length bytes to be used for this <code>SECSItem</code>.
     * The value for the number of length bytes must be <code>ONE</code>, <code>TWO</code>, or <code>THREE</code>.
     */
    public F8SECSItem(double value, SECSItemNumLengthBytes desiredNumberOfLengthBytes)
    {
        super(SECSItemFormatCode.F8, 8, desiredNumberOfLengthBytes);
        this.value = value;
    }
    
    /**
     * This constructor is typically used to create this <code>SECSItem</code> from
     * &quot;data off the wire&quot;.
     * 
     * @param data - The buffer where the wire/transmission format data is contained.
     * @param itemOffset - The offset into the data where the desired item starts.
     */
    public F8SECSItem(byte[] data, int itemOffset)
    {
        super(data, itemOffset);
        int offset = 1 + inboundNumberOfLengthBytes.valueOf() + itemOffset;
        bytesConsumed = 1 + inboundNumberOfLengthBytes.valueOf() + lengthInBytes;
        if (lengthInBytes != 8)
            throw new IllegalArgumentException("Illegal data length of: " + lengthInBytes + 
                    ".  The length of the data independent of the item header must be 8.");
        
        ByteBuffer buf = ByteBuffer.wrap(data, offset, 8);
        buf.order(ByteOrder.BIG_ENDIAN);
        value = buf.getDouble();
    }
    
    /**
     * Returns the value of this <code>SECSItem</code> in a Java friendly format.
     * @return - A java <code>double</code> that contains this item's value.
     */
    public double getValue()
    {
        return value;
    }

    /**
     * Creates and returns a <code>byte[]</code> that represents this <code>SECSItem</code> in &quot;wire/transmission format&quot;.
     * @return - A <code>byte[]</code> representation of this <code>SECSItem</code>'s content that is suitable for transmission.
     */
    public byte[] toRawSECSItem()
    {
        byte[] output = new byte[outputHeaderLength()+8];
        int offset = populateSECSItemHeaderData(output, 8);
        
        ByteBuffer bb = ByteBuffer.wrap(output, offset, 8);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.putDouble(value);
        
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
        long temp;
        temp = Double.doubleToLongBits(value);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        F8SECSItem other = (F8SECSItem) obj;
        if (Double.doubleToLongBits(value) != Double
                .doubleToLongBits(other.value))
            return false;
        return true;
    }
}
