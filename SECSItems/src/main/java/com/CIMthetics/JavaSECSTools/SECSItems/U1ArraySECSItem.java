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
 * This class represents/implements a SECSItem with the SECS data type of <code>U1</code>
 * in an array form.
 * In this case it is an array of zero or more 8-bit unsigned integers.  From the Java side this data
 * type is handled as a Java <code>short[]</code>.
 * <p>
 * Due to Java not having unsigned data types a scheme of size promotion is used
 * to deal with the issue.  In this case, this is handled by using an array of signed
 * short integers (16-bits) to contain the values of the unsigned single byte integers (8-bits).  With
 * this method the user of this library is able to use normal unsigned values for
 * computational purposes.  Just make sure the values that are stored in the
 * &quot;shorts&quot; are no larger than +255 and do not make them negative.
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

public class U1ArraySECSItem extends SECSItem
{
    private short[] value = null;
    
    /**
     * This constructor creates a SECSItem that has a type of <code>U1</code> with 
     * the minimum number of length bytes required.
     * <br>
     * Note: It will be created with the number of length bytes required
     * based on the length (in elements) of the <code>short[]</code> provided.
     * The maximum array length allowed is <code>16777215</code> bytes(elements).
     * 
     * @param value an array of <code>short</code> values to be assigned to this SECSItem
     */
    public U1ArraySECSItem(short[] value)
    {
        super(SECSItemFormatCode.U1, value.length);
        this.value = value;
    }
    
    /**
     * This constructor creates a SECSItem that has a type of <code>U1</code>
     * with the specified value.
     * <p>
     * This form of the constructor is not needed much nowadays.  In the past
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
    public U1ArraySECSItem(short[] value, SECSItemNumLengthBytes desiredNumberOfLengthBytes)
    {
        super(SECSItemFormatCode.U1, value.length, desiredNumberOfLengthBytes);
        this.value = value;
    }
    
    /**
     * This constructor is used to create this <code>SECSItem</code> from
     * data in wire/transmission format.
     * 
     * @param data - The buffer where the wire/transmission format data is contained.
     * @param itemOffset - The offset into the data where the desired item starts.
     */
    public U1ArraySECSItem(byte[] data, int itemOffset)
    {
        super(data, itemOffset);
        int offset = 1 + inboundNumberOfLengthBytes.valueOf() + itemOffset;
        bytesConsumed = 1 + inboundNumberOfLengthBytes.valueOf() + lengthInBytes;
        
        value = new short[lengthInBytes];
        for(int i = offset, j = 0; j < lengthInBytes; i++, j++)
        {
            value[j] = (short)(0x00FF & (short)data[i]);
        }
    }    
    
    /*
     * Since Java seems to be a language for bedwetters who cannot
     * deal with unsigned data types we have to promote the size of
     * a U1 up to 16 bits so we do not have any sign extension
     * issues.
     * 
     * @return the value of the SECSItem
     */
    /**
     * Returns the value of this <code>SECSItem</code> in a Java friendly format.
     * @return - A java <code>short[]</code> that contains this item's value.
     */
    public short[] getValue()
    {
        return value;
    }

    /**
     * Creates and returns a <code>byte[]</code> that represents this <code>SECSItem</code> in &quot;wire/transmission format&quot;.
     * @return - A <code>byte[]</code> representation of this <code>SECSItem</code> content that is suitable for transmission.
     */
    public byte[] toRawSECSItem()
    {
        byte[] output = new byte[outputHeaderLength()+value.length];
        int offset = populateSECSItemHeaderData(output, value.length);
        
        byte[] temp = new byte[2];

        for( int i = offset, j = 0; j < value.length; i++, j++ )
        {
            temp[0] = 0;
            temp[1] = 0;
            ByteBuffer bb = ByteBuffer.wrap(temp);
            bb.order(ByteOrder.BIG_ENDIAN);
            bb.putShort(value[j]);
            output[i] = temp[1];
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
        U1ArraySECSItem other = (U1ArraySECSItem) obj;
        if (!Arrays.equals(value, other.value))
            return false;
        return true;
    }
}
