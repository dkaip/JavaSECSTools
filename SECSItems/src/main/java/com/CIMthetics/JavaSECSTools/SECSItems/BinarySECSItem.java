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

import java.util.Arrays;

/**
 * This class represents/implements a SECSItem with the SECS data type of <code>B</code>(<code>BINARY</code>),
 * which is an array of unsigned bytes.  From the Java side this data
 * type is handled as a Java <code>short[]</code>.
 * <p>
 * Due to Java not having unsigned data types a scheme of size promotion is used
 * to deal with the issue.  In this case, this is handled by using an array of signed
 * short integers (16-bits) to contain the values of the unsigned single byte integers (8-bits).  With
 * this method the user of this library is able to use normal unsigned values for
 * computational purposes.  Just make sure the values that are stored in the
 * &quot;shorts&quot; are no larger that +255 and do not make them negative.
 * 
 * @author Douglas Kaip
 */

public class BinarySECSItem extends SECSItem
{
    private short[] value = null;
    
    /**
     * This constructor creates a SECSItem that has a type of <code>B</code> with 
     * the minimum number of length bytes required.
     * <br>
     * Note: It will be created with the number of length bytes required
     * based on the length (in elements) of the <code>short[]</code> provided.
     * The maximum array length allowed is <code>16777215</code> bytes(elements).
     * 
     * @param value an array of binary values to be assigned to this SECSItem
     */
    public BinarySECSItem(short[] value)
    {
        super(SECSItemFormatCode.B, value.length);
        this.value = value;
    }
    
    /**
     * This constructor creates a SECSItem that has a type of <code>B</code> with
     * a specified number of length bytes.
     * <p>
     * This form of the constructor is not needed much nowadays.  In past
     * there were situations where the equipment required that messages
     * contained SECSItems that had a specified number of length bytes.
     * This form of the constructor is here to handle these problem child cases.
     * <p>
     * Note: It will be created with the number of length bytes set
     * to the greater of the number of length bytes specified or
     * the number required based on the length of the <code>value</code>
     * parameter.
     * 
     * @param value the value to be assigned to this SECSItem
     * @param desiredNumberOfLengthBytes The number of length bytes to be used for this SECSItem.
     * The value for the number of length bytes must be 1, 2, or 3.
     */
    public BinarySECSItem(short[] value, SECSItemNumLengthBytes desiredNumberOfLengthBytes)
    {
        super(SECSItemFormatCode.B, value.length, desiredNumberOfLengthBytes);
        this.value = value;
    }
    
    /**
     * This constructor is used to create this SECSItem from
     * data in wire/transmission format.
     * 
     * @param data the buffer where the wire/transmission format data is contained
     * @param itemOffset the offset into the data where the desired item starts
     */
    public BinarySECSItem(byte[] data, int itemOffset)
    {
        super(data, itemOffset);
        int offset = 1 + inboundNumberOfLengthBytes.valueOf() + itemOffset;
        bytesConsumed = 1 + inboundNumberOfLengthBytes.valueOf() + lengthInBytes;
        
        value = new short[lengthInBytes];
        for(int i = 0, j = offset; i < value.length; i++, j++)
        {
            value[i] = (short)(0x00FF & (short)data[j]);
        }
    }
    
    /**
     * Return the value of this SECSItem.
     * <p>
     * Since Java seems to be a language for bedwetters who cannot
     * deal with unsigned data types we have to promote the size of
     * a binary up to 16 bits so we do not have any sign extension
     * issues.
     * 
     * @return the value of the SECSItem
     */
    public short[] getValue()
    {
        return value;
    }

    /**
     * Creates and returns a <code>byte[]</code> that represents this SECSItem in &quot;wire/transmission format&quot;.
     * @return - A <code>byte[]</code> representation of this SECSItem's content that is suitable for transmission.
     */
    public byte[] toRawSECSItem()
    {
        byte[] output = new byte[outputHeaderLength()+value.length];
        int offset = populateSECSItemHeaderData(output, value.length);
        
        for( int i = offset, j = 0; j < value.length; i++, j++ )
        {
            output[i] = (byte)value[j];
        }
        
        return output;
    }
    
    /**
     * Returns a String representation of this item in a format
     * suitable for debugging.
     * 
     * @return a String representation of this item in a format
     * suitable for debugging.
     */
    public String toString()
    {
        if (value.length == 1)
            return "Format:" + formatCode.toString() + " Value: " + value[0];
        else
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
        BinarySECSItem other = (BinarySECSItem) obj;
        if (!Arrays.equals(value, other.value))
            return false;
        return true;
    }
}
