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

import java.math.BigInteger;
import java.util.Arrays;

/**
 * This class represents/implements a <code>SECSItem</code> with the SECS data type of <code>U8</code>,
 * which is an 8 byte (64-bit) unsigned integer in an array form.  From the Java side this data
 * type is handled as a Java <code>BigInteger[]</code>.
 * <p>
 * Due to Java not having unsigned data types a scheme of size promotion is used
 * to deal with the issue.  However, in this case, Java does not have a suitable primitive
 * data type to handle unsigned integers of this size.  Thus a Java
 * <code>BigInteger[]</code> is used to contain the values in the Java world.
 * Just make sure the values that are stored in the
 * &quot;BigInteger&quot; are no larger than +18446744073709551615 (<code>0xFFFFFFFFFFFFFFFF</code>) and do not make them negative.
 * 
 * @author Douglas Kaip
 */

public class U8ArraySECSItem extends SECSItem
{
    public BigInteger[] value = null;
    
    /**
     * This constructor creates a SECSItem that has a type of <code>U8</code> with 
     * the minimum number of length bytes required.
     * <br>
     * Note: It will be created with the number of length bytes required
     * based on the length (in elements) of the <code>BigInteger[]</code> provided.
     * The maximum array length allowed is <code>16777215</code> bytes(elements).
     * 
     * @param value an array of <code>BigInteger</code> values to be assigned to this SECSItem
     */
    public U8ArraySECSItem(BigInteger[] value)
    {
        super(SECSItemFormatCode.U8, value.length * 8);
        this.value = value;
    }
    
    /**
     * This constructor creates a SECSItem that has a type of <code>U8</code>
     * with the specified value.
     * <p>
     * This form of the constructor is not needed much nowadays.  In the past
     * there were situations where the equipment required that messages
     * contained SECSItems that had a specified number of length bytes.
     * This form of the constructor is here to handle these problem child cases.
     * <p>
     * Note: It will be created with the number of length bytes set to greater of, 
     * the specified number of length bytes or the number required based on the 
     * length (in elements) of the <code>BigInteger[]</code> provided.
     * 
     * @param value the value to be assigned to this <code>SECSItem</code>
     * @param desiredNumberOfLengthBytes The number of length bytes to be used for this <code>SECSItem</code>.
     * The value for the number of length bytes must be <code>ONE</code>, <code>TWO</code>, or <code>THREE</code>.
     */
    public U8ArraySECSItem(BigInteger[] value, SECSItemNumLengthBytes desiredNumberOfLengthBytes)
    {
        super(SECSItemFormatCode.U8, value.length * 8, desiredNumberOfLengthBytes);
        this.value = value;
    }
    
    /**
     * This constructor is used to create this <code>SECSItem</code> from
     * data in wire/transmission format.
     * 
     * @param data - The buffer where the wire/transmission format data is contained.
     * @param itemOffset - The offset into the data where the desired item starts.
     */
    public U8ArraySECSItem(byte[] data, int itemOffset)
    {
        super(data, itemOffset);
        int offset = 1 + inboundNumberOfLengthBytes.valueOf() + itemOffset;
        bytesConsumed = 1 + inboundNumberOfLengthBytes.valueOf() + lengthInBytes;
        if ((lengthInBytes == 0) || ((lengthInBytes % 8) != 0))
            throw new IllegalArgumentException("Illegal data length of: " + lengthInBytes + " payload length must be a non-zero multiple of 8.");
        
        /*
         * An array size of 9 is used so that sign extension
         * issues will hopefully not show up.
         */
        byte[] tempBuffer = new byte[9];
        tempBuffer[0] = 0;
        value = new BigInteger[lengthInBytes / 8];
        for (int i = offset, j = 0; j < value.length; i += 8, j++)
        {
            System.arraycopy(data, i, tempBuffer, 1, 8);
            value[j] = new BigInteger(tempBuffer);
        }
    }
    
    /**
     * Returns the value of this <code>SECSItem</code> in a Java friendly format.
     * @return - A java <code>BigInteger[]</code> that contains this item's value.
     */
    public BigInteger[] getValue()
    {
        return value;
    }

    /**
     * Creates and returns a <code>byte[]</code> that represents this <code>SECSItem</code> in &quot;wire/transmission format&quot;.
     * @return - A <code>byte[]</code> representation of this <code>SECSItem</code> content that is suitable for transmission.
     */
    @Override
    public byte[] toRawSECSItem()
    {
        byte[] output = new byte[outputHeaderLength()+(value.length * 8)];
        int offset = populateSECSItemHeaderData(output, (value.length * 8));
        
        for( int i = offset, j = 0; j < value.length; i+=8, j++ )
        {
            byte[] buf = value[j].toByteArray();
            
            int firstByte = 0;
            if (buf[0] == 0)
            {
                /*
                 * Remember that this is a U8 so the underlying BigInteger should
                 * never have a negative value.
                 * 
                 * The sign bit is in the highest byte.  If the highest order byte
                 * (remember BIG ENDIAN) is zero that means that the byte buffer
                 * had to be enlarged so that the "sign bit" is zero.  In this case
                 * we just ignore this byte.
                 */
                firstByte = 1;
            }
            
            for (int k = buf.length - 1, m = i + 7; k >= firstByte; k--, m--)
            {
                output[m] = buf[k];
            }
        }
        
        return output;
    }
    
    @Override
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
        U8ArraySECSItem other = (U8ArraySECSItem) obj;
        if (!Arrays.equals(value, other.value))
            return false;
        return true;
    }
}
