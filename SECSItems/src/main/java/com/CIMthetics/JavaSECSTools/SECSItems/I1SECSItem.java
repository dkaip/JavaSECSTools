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

/**
 * This class represents/implements a <code>SECSItem</code> with the SECS data type of <code>I1</code>,
 * which is a 1 byte (8-bit) signed integer number.  From the Java side this data
 * type is handled as a Java <code>byte</code>.
 * 
 * @author Douglas Kaip
 *
 */
public class I1SECSItem extends SECSItem
{
    private byte value = 0;
    
    /**
     * This constructor creates a <code>SECSItem</code> that has a type of <code>I1</code> 
     * with the specified value.
     * <br>
     * Note: It will be created with 1 length byte.
     * 
     * @param value - the value to be assigned to this <code>SECSItem</code>
     */
    public I1SECSItem(byte value)
    {
        super(SECSItemFormatCode.I1, 1);
        this.value = value;
    }
    
    /**
     * This constructor creates a SECSItem that has a type of <code>I1</code>
     * with the specified value.
     * <p>
     * This form of the constructor is not needed much nowadays.  In the past
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
    public I1SECSItem(byte value, SECSItemNumLengthBytes desiredNumberOfLengthBytes)
    {
        super(SECSItemFormatCode.I1, 1, desiredNumberOfLengthBytes);
        this.value = value;
    }
    
    /**
     * This constructor is used to create this <code>SECSItem</code> from
     * data in wire/transmission format.
     * 
     * @param data - The buffer where the wire/transmission format data is contained.
     * @param itemOffset - The offset into the data where the desired item starts.
     */
    public I1SECSItem(byte[] data, int itemOffset)
    {
        super(data, itemOffset);
        int offset = 1 + inboundNumberOfLengthBytes.valueOf() + itemOffset;
        bytesConsumed = 1 + inboundNumberOfLengthBytes.valueOf() + lengthInBytes;
        if (lengthInBytes != 1)
            throw new IllegalArgumentException("Illegal data length of: " + lengthInBytes + 
                    ".  The length of the data independent of the item header must be 1.");
        
        value = data[offset];
    }
    
    /**
     * Returns the value of this <code>SECSItem</code> in a Java friendly format.
     * @return - A java <code>byte</code> that contains this item's value.
     */
    public byte getValue()
    {
        return value;
    }

    /**
     * Creates and returns a <code>byte[]</code> that represents this <code>SECSItem</code> in &quot;wire/transmission format&quot;.
     * @return - A <code>byte[]</code> representation of this <code>SECSItem</code> content that is suitable for transmission.
     */
    public byte[] toRawSECSItem()
    {
        byte[] output = new byte[outputHeaderLength()+1];
        int offset = populateSECSItemHeaderData(output, 1);
        
        output[offset] = value;
        
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
        result = prime * result + value;
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
        I1SECSItem other = (I1SECSItem) obj;
        if (value != other.value)
            return false;
        return true;
    }
}
