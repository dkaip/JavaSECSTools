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
 * This class represents/implements a <code>SECSItem</code> with the SECS data type of <code>BO</code>,
 * which is a boolean.  From the Java side this data
 * type is handled as a Java <code>boolean</code>.
 * 
 * @author Douglas Kaip
 *
 */
public class BooleanSECSItem extends SECSItem
{
    private boolean value;
    
    /**
     * This constructor creates a <code>SECSItem</code> that has a type of <code>BO</code> 
     * with the specified value.
     * <br>
     * Note: It will be created with 1 length byte.
     * 
     * @param value - the value to be assigned to this <code>SECSItem</code>
     */
    public BooleanSECSItem(boolean value)
    {
        super(SECSItemFormatCode.BO, 1);
        this.value = value;
    }
    
    /**
     * This constructor creates a SECSItem that has a type of <code>BO</code>
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
    public BooleanSECSItem(boolean value, SECSItemNumLengthBytes desiredNumberOfLengthBytes)
    {
        super(SECSItemFormatCode.BO, 1, desiredNumberOfLengthBytes);
        this.value = value;
    }
    
    /**
     * This constructor is used to create this <code>SECSItem</code> from
     * data in wire/transmission format.
     * 
     * @param data - The buffer where the wire/transmission format data is contained.
     * @param itemOffset - The offset into the data where the desired item starts.
     */
    public BooleanSECSItem(byte[] data, int itemOffset)
    {
        super(data, itemOffset);
        int offset = 1 + inboundNumberOfLengthBytes.valueOf() + itemOffset;
        bytesConsumed = 1 + inboundNumberOfLengthBytes.valueOf() + lengthInBytes;
        if (data[offset] == 0)
            this.value = false;
        else
            this.value = true;
    }
    
    /**
     * Returns the value of this <code>SECSItem</code> in a Java friendly format.
     * @return - A java <code>boolean</code> that contains this item's value.
     */
    public boolean getValue()
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
        
        if (value == true)
            output[offset] = 1;
        else
            output[offset] = 0;
        
        return output;
    }
    
    public String toString()
    {
        if (value == true)
            return "Format:" + formatCode.toString() + " Value: true";
        else
            return "Format:" + formatCode.toString() + " Value: false";
    }
    
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (value ? 1231 : 1237);
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
        BooleanSECSItem other = (BooleanSECSItem) obj;
        if (value != other.value)
            return false;
        return true;
    }
}
