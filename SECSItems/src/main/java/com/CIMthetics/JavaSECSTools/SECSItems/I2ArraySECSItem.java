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
import java.util.Arrays;

/**
 * This class represents/implements a SECSItem with the SECS data type of <code>I2</code>
 * in an array form.
 * In this case it is an array of zero or more 16-bit signed integers.  From the Java side this data
 * type is handled as a Java <code>short[]</code>.
 * <p>
 * In wire/transmission format all SECS items, with the exception of those with an item format code
 * of <code>L</code>(List), are sent in an array form.  For instance if an item is received which has
 * an item format code of I4 (32-bit signed integer) and has a length of 8 you know that is it an array
 * containing 2 4-byte signed integers.  If it has a length of 0 you know it is an array with zero
 * 4-byte signed integers. Likewise, If an item is received which has
 * an item format code of U2 (16-bit unsigned integer) and has a length of 6 you know that is it an array
 * containing 3 2-byte unsigned integers.
 * <p>
 * In practice, when only one item is received in the array (in the I4 case mentioned previously if the 
 * length was 4 instead of 8) it is handled and processed in a non array form.  Hence <code>I4SECSItem</code>
 * vs <code>I4ArraySECSItem</code>, <code>U2SECSItem</code> vs <code>U2ArraySECSItem</code>, etc.
 * 
 * 
 * @author Douglas Kaip
 */

public class I2ArraySECSItem extends SECSItem
{
    public short[] value = null;
    
    /**
     * This constructor creates a SECSItem that has a type of <code>I2</code> with 
     * the minimum number of length bytes required.
     * <br>
     * Note: It will be created with the number of length bytes required
     * based on the length (in elements) of the <code>short[]</code> provided.
     * The maximum array length allowed is <code>16777215</code> bytes(elements).
     * 
     * @param value an array of signed 16-bit integers to be assigned to this SECSItem
     */
    public I2ArraySECSItem(short[] value)
    {
        super(SECSItemFormatCode.I2, value.length * 2);
        this.value = value;
    }
    
    /**
     * This constructor creates a SECSItem that has a type of <code>I2</code>
     * with the specified value.
     * <p>
     * This form of the constructor is not needed much nowadays.  In past
     * there were situations where the equipment required that messages
     * contained SECSItems that had a specified number of length bytes.
     * This form of the constructor is here to handle these problem child cases.
     * <p>
     * Note: It will be created with the number of length bytes set to greater of, 
     * the specified number of length bytes or the number required based on the 
     * length (in elements) of the <code>short[]</code> provided.
     * 
     * @param value the value to be assigned to this <code>SECSItem</code>
     * @param desiredNumberOfLengthBytes The number of length bytes to be used for this <code>SECSItem</code>.
     * The value for the number of length bytes must be <code>ONE</code>, <code>TWO</code>, or <code>THREE</code>.
     */
    public I2ArraySECSItem(short[] value, SECSItemNumLengthBytes desiredNumberOfLengthBytes)
    {
        super(SECSItemFormatCode.I2, value.length * 2, desiredNumberOfLengthBytes);
        this.value = value;
    }
    
    /**
     * This constructor is used to create this <code>SECSItem</code> from
     * data in wire/transmission format.
     * 
     * @param data - The buffer where the wire/transmission format data is contained.
     * @param itemOffset - The offset into the data where the desired item starts.
     */
    public I2ArraySECSItem(byte[] data, int itemOffset)
    {
        super(data, itemOffset);
        int offset = 1 + inboundNumberOfLengthBytes.valueOf() + itemOffset;
        bytesConsumed = 1 + inboundNumberOfLengthBytes.valueOf() + lengthInBytes;
        if ((lengthInBytes == 0) || ((lengthInBytes % 2) != 0))
            throw new IllegalArgumentException("Illegal data length of: " + lengthInBytes + " payload length must be a non-zero multiple of 2.");
        
        ByteBuffer buf = ByteBuffer.wrap(data);
        buf.order(ByteOrder.BIG_ENDIAN);
        value = new short[lengthInBytes / 2];
        for (int i = 0, j = offset; i < value.length; i++, j+= 2)
        {
            value[i] = buf.getShort(j);
        }
    }
    
    /**
     * Returns the value of this <code>SECSItem</code> in a Java friendly format.
     * @return - A java <code>short[]</code> that contains this item's value.
     */
    public short[] getValue()
    {
        return value;
    }

    /**
     * Creates and returns a <code>short[]</code> that represents this <code>SECSItem</code> in &quot;wire/transmission format&quot;.
     * @return - A <code>short[]</code> representation of this <code>SECSItem</code> content that is suitable for transmission.
     */
    public byte[] toRawSECSItem()
    {
        byte[] output = new byte[outputHeaderLength()+(value.length * 2)];
        int offset = populateSECSItemHeaderData(output, (value.length * 2));
        
        for( int i = offset, j = 0; j < value.length; i+= 2, j++ )
        {
            ByteBuffer bb = ByteBuffer.wrap(output, offset, value.length * 2);
            bb.order(ByteOrder.BIG_ENDIAN);
            bb.putShort(i, value[j]);
        }
        
        return output;
    }
    
    public String toString()
    {
        return "Format:" + formatCode.toString() + " Value: Array";
    }
    
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(value);
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
        I2ArraySECSItem other = (I2ArraySECSItem) obj;
        if (!Arrays.equals(value, other.value))
            return false;
        return true;
    }
}
