package com.CIMthetics.JavaSECSTools.SECSItems;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * This class represents/implements a SECSItem with the SECS data type of <code>U4</code>
 * in an array form.
 * In this case it is an array of zero or more 32-bit unsigned integers.  From the Java side this data
 * type is handled as a Java <code>long[]</code>.
 * <p>
 * Due to Java not having unsigned data types a scheme of size promotion is used
 * to deal with the issue.  In this case, this is handled by using an array of signed
 * long integers (64-bits) to contain the values of the unsigned four byte integers (32-bits).  With
 * this method the user of this library is able to use normal unsigned values for
 * computational purposes.  Just make sure the values that are stored in the
 * &quot;longs&quot; are no larger than +4294967295 (0xFFFFFFFF) and do not make them negative.
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

public class U4ArraySECSItem extends SECSItem
{
    public long[] value = null;
    
    /**
     * This constructor creates a SECSItem that has a type of <code>U4</code> with 
     * the minimum number of length bytes required.
     * <br>
     * Note: It will be created with the number of length bytes required
     * based on the length (in elements) of the <code>long[]</code> provided.
     * The maximum array length allowed is <code>16777215</code> bytes(elements).
     * 
     * @param value an array of <code>long</code> values to be assigned to this SECSItem
     */
    public U4ArraySECSItem(long[] value)
    {
        super(SECSItemFormatCode.U4, value.length * 4);
        this.value = value;
    }
    
    /**
     * This constructor creates a SECSItem that has a type of <code>U4</code>
     * with the specified value.
     * <p>
     * This form of the constructor is not needed much nowadays.  In past
     * there were situations where the equipment required that messages
     * contained SECSItems that had a specified number of length bytes.
     * This form of the constructor is here to handle these problem child cases.
     * <p>
     * Note: It will be created with the number of length bytes set to greater of, 
     * the specified number of length bytes or the number required based on the 
     * length (in elements) of the <code>long[]</code> provided.
     * 
     * @param value the value to be assigned to this <code>SECSItem</code>
     * @param desiredNumberOfLengthBytes The number of length bytes to be used for this <code>SECSItem</code>.
     * The value for the number of length bytes must be <code>ONE</code>, <code>TWO</code>, or <code>THREE</code>.
     */
    public U4ArraySECSItem(long[] value, SECSItemNumLengthBytes desiredNumberOfLengthBytes)
    {
        super(SECSItemFormatCode.U4, value.length * 4, desiredNumberOfLengthBytes);
        this.value = value;
    }
    
    /**
     * This constructor is used to create this <code>SECSItem</code> from
     * data in wire/transmission format.
     * 
     * @param data - The buffer where the wire/transmission format data is contained.
     * @param itemOffset - The offset into the data where the desired item starts.
     */
    public U4ArraySECSItem(byte[] data, int itemOffset)
    {
        super(data, itemOffset);
        int offset = 1 + inboundNumberOfLengthBytes.valueOf() + itemOffset;
        bytesConsumed = 1 + inboundNumberOfLengthBytes.valueOf() + lengthInBytes;
        if ((lengthInBytes == 0) || ((lengthInBytes % 4) != 0))
            throw new IllegalArgumentException("Illegal data length of: " + lengthInBytes + " payload length must be a non-zero multiple of 4.");
        
        byte[] tempBuffer = new byte[8];
        tempBuffer[0] = 0;
        tempBuffer[1] = 0;
        tempBuffer[2] = 0;
        tempBuffer[3] = 0;
        value = new long[lengthInBytes / 4];
        for (int i = offset, j = 0; j < value.length; i += 4, j++)
        {
            tempBuffer[4] = data[i];
            tempBuffer[5] = data[i+1];
            tempBuffer[6] = data[i+2];
            tempBuffer[7] = data[i+3];
            ByteBuffer buf = ByteBuffer.wrap(tempBuffer);
            buf.order(ByteOrder.BIG_ENDIAN);
            value[j] = buf.getLong();
        }
    }
    
    /*
     * Since Java seems to be a language for bedwetters who cannot
     * deal with unsigned data types we have to promote the size of
     * a U4 up to 64 bits so we do not have any sign extension
     * issues.
     * 
     * @return the value of the SECSItem
     */
    /**
     * Returns the value of this <code>SECSItem</code> in a Java friendly format.
     * @return - A java <code>long[]</code> that contains this item's value.
     */
    public long[] getValue()
    {
        return value;
    }

    /**
     * Creates and returns a <code>byte[]</code> that represents this <code>SECSItem</code> in &quot;wire/transmission format&quot;.
     * @return - A <code>byte[]</code> representation of this <code>SECSItem</code> content that is suitable for transmission.
     */
    public byte[] toRawSECSItem()
    {
        byte[] output = new byte[outputHeaderLength()+(value.length * 4)];
        int offset = populateSECSItemHeaderData(output, (value.length * 4));
        
        byte[] temp = new byte[8];

        for( int i = offset, j = 0; j < value.length; i+=4, j++ )
        {
            temp[0] = 0;
            temp[1] = 0;
            temp[2] = 0;
            temp[3] = 0;
            temp[4] = 0;
            temp[5] = 0;
            temp[6] = 0;
            temp[7] = 0;
            ByteBuffer bb = ByteBuffer.wrap(temp);
            bb.order(ByteOrder.BIG_ENDIAN);
            bb.putLong(value[j]);
            output[i]   = temp[4];
            output[i+1] = temp[5];
            output[i+2] = temp[6];
            output[i+3] = temp[7];
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
        U4ArraySECSItem other = (U4ArraySECSItem) obj;
        if (!Arrays.equals(value, other.value))
            return false;
        return true;
    }
}
