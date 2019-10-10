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

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * This class represents/implements a SECSItem with the SECS data type of <code>A</code>(<code>ASCII</code>),
 * which is a &quot;string&quot; of <code>ASCII</code> characters.  From the Java side this data
 * type is handled as a Java <code>String</code>.  Be careful to only use character sets
 * of <code>StandardCharsets.US_ASCII</code> or standard ASCII characters or undesirable
 * behavior may be manifested.
 * 
 * @author Douglas Kaip
 *
 */
public class ASCIISECSItem extends SECSItem
{
    String value = "";
    
    /**
     * This constructor creates a SECSItem that has a type of <code>A</code> with 
     * the minimum number of length bytes required.
     * <br>
     * Note: It will be created with the number of length bytes required
     * based on the length (in characters) of the <code>String</code> provided.
     * The maximum string length allowed is <code>16777215</code> characters.
     * 
     * @param text the value to be assigned to this SECSItem
     */
    public ASCIISECSItem(String text)
    {
        super(SECSItemFormatCode.A, text == null ? 0 : text.length());
        
        if (text != null)
            value = text;
    }
    
    /**
     * This constructor creates a SECSItem that has a type of <code>A</code> with
     * a specified number of length bytes.
     * <p>
     * This form of the constructor is not needed much nowadays.  In the past
     * there were situations where the equipment required that messages
     * contained SECSItems that had a specified number of length bytes.
     * This form of the constructor is here to handle these problem child cases.
     * <p>
     * Note: It will be created with the number of length bytes set
     * to the greater of the number of length bytes specified or
     * the number required based on the length of the <code>text</code>
     * parameter.
     * 
     * @param text the value to be assigned to this SECSItem
     * @param desiredNumberOfLengthBytes The number of length bytes to be used for this SECSItem.
     */
    public ASCIISECSItem(String text, SECSItemNumLengthBytes desiredNumberOfLengthBytes)
    {
        super(SECSItemFormatCode.A, text == null ? 0 : text.length(), desiredNumberOfLengthBytes);

        if (text != null)
            value = text;
    }
    
    /**
     * This constructor is used to create this SECSItem from
     * data in wire/transmission format.
     * 
     * @param data the buffer where the wire/transmission format data is contained
     * @param itemOffset the offset into the data where the desired item starts
     */
    public ASCIISECSItem(byte[] data, int itemOffset)
    {
        super(data, itemOffset);
        int offset = 1 + inboundNumberOfLengthBytes.valueOf() + itemOffset;
        value = new String(Arrays.copyOfRange(data, offset, offset + lengthInBytes));
        bytesConsumed = 1 + inboundNumberOfLengthBytes.valueOf() + lengthInBytes;
    }
    
    /**
     * Returns the value of this <code>ASCIISECSItem</code>.
     * 
     * @return the value of this <code>ASCIISECSItem</code>
     */
    public String getValue()
    {
        return value;
    }

    /**
     * Creates and returns a <code>byte[]</code> that represents this SECSItem in &quot;wire/transmission format&quot;.
     * @return - A <code>byte[]</code> representation of this SECSItem's content that is suitable for transmission.
     */
    public byte[] toRawSECSItem()
    {
        byte[] output = new byte[outputHeaderLength()+value.length()];
        int offset = populateSECSItemHeaderData(output, value.length());
        
        byte[] buf = value.getBytes(StandardCharsets.US_ASCII);

        System.arraycopy(buf, 0, output, offset, buf.length);
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
        return "Format:" + formatCode.toString() + " Value: " + value.toString();
    }
    
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + value.hashCode();
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
        ASCIISECSItem other = (ASCIISECSItem) obj;
        if (!value.equals(other.value))
            return false;
        return true;
    }
}
