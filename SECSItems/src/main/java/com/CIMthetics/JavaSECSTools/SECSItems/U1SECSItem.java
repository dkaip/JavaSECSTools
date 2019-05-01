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
 * This class represents/implements a <code>SECSItem</code> with the SECS data type of <code>U1</code>,
 * which is an unsigned byte.  From the Java side this data
 * type is handled as a Java <code>short</code>.
 * <p>
 * Due to Java not having unsigned data types a scheme of size promotion is used
 * to deal with the issue.  In this case, this is handled by using a signed
 * <code>short</code> integer (16-bits) to contain the value of the unsigned single byte integer (8-bits).  With
 * this method the user of this library is able to use normal unsigned values for
 * computational purposes.  Just make sure the values that are stored in the
 * &quot;shorts&quot; are no larger than +255 and do not make them negative.
 * 
 * @author Douglas Kaip
 */

public class U1SECSItem extends SECSItem
{
    private short value;
    
    /**
     * This constructor creates a <code>SECSItem</code> that has a type of <code>U1</code> with 
     * the minimum number of length bytes required.
     * 
     * @param value a Java <code>short</code> that has a value between 0 and 255
     */
    public U1SECSItem(short value)
    {
        super(SECSItemFormatCode.U1, 1);
        this.value = value;
    }
    
    /**
     * This constructor creates a <code>SECSItem</code> that has a type of <code>U1</code> with
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
     * @param value the value to be assigned to this <code>SECSItem</code>
     * @param desiredNumberOfLengthBytes The number of length bytes to be used for this <code>SECSItem</code>.
     */
    public U1SECSItem(short value, SECSItemNumLengthBytes desiredNumberOfLengthBytes)
    {
        super(SECSItemFormatCode.U1, 1, desiredNumberOfLengthBytes);
        this.value = value;
    }
    
    public U1SECSItem(byte[] data, int itemOffset)
    {
        super(data, itemOffset);
        int offset = 1 + inboundNumberOfLengthBytes.valueOf() + itemOffset;
        bytesConsumed = 1 + inboundNumberOfLengthBytes.valueOf() + lengthInBytes;
        if (lengthInBytes != 1)
            throw new IllegalArgumentException("Illegal data length of: " + lengthInBytes + 
                    ".  The length of the data independent of the item header must be 1.");

        byte[] tempBuffer = new byte[2];
        tempBuffer[0] = 0;
        tempBuffer[1] = data[offset];
        ByteBuffer buf = ByteBuffer.wrap(tempBuffer);
        value = buf.getShort();
    }
    
    public short getValue()
    {
        return value;
    }

    public byte[] toRawSECSItem()
    {
        byte[] output = new byte[outputHeaderLength()+1];
        int offset = populateSECSItemHeaderData(output, 1);
        
        byte[] temp = new byte[]{0, 0};
        ByteBuffer bb = ByteBuffer.wrap(temp);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.putShort(value);
        output[offset] = temp[1];
        
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
        U1SECSItem other = (U1SECSItem) obj;
        if (value != other.value)
            return false;
        return true;
    }
}
