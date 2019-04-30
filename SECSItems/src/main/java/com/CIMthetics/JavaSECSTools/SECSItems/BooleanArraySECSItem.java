package com.CIMthetics.JavaSECSTools.SECSItems;

import java.util.Arrays;

/**
 * This class represents/implements a SECSItem with the SECS data type of <code>BO</code>
 * in an array form.
 * In this case it is an array of zero or more 8-bit boolean values.  From the Java side this data
 * type is handled as a Java <code>boolean[]</code>.
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

public class BooleanArraySECSItem extends SECSItem
{

    private boolean[] value = null;
    
    /**
     * This constructor creates a SECSItem that has a type of <code>BO</code> with 
     * the minimum number of length bytes required.
     * <br>
     * Note: It will be created with the number of length bytes required
     * based on the length (in elements) of the <code>boolean[]</code> provided.
     * The maximum array length allowed is <code>16777215</code> bytes(elements).
     * 
     * @param value an array of <code>boolean</code> values to be assigned to this SECSItem
     */
    public BooleanArraySECSItem(boolean[] value)
    {
        super(SECSItemFormatCode.BO, value.length);
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
     * <p>
     * Note: It will be created with the number of length bytes set to greater of, 
     * the specified number of length bytes or the number required based on the 
     * length (in elements) of the <code>boolean[]</code> provided.
     * 
     * @param value the value to be assigned to this <code>SECSItem</code>
     * @param desiredNumberOfLengthBytes The number of length bytes to be used for this <code>SECSItem</code>.
     * The value for the number of length bytes must be <code>ONE</code>, <code>TWO</code>, or <code>THREE</code>.
     */
    public BooleanArraySECSItem(boolean[] value, SECSItemNumLengthBytes desiredNumberOfLengthBytes)
    {
        super(SECSItemFormatCode.BO, value.length, desiredNumberOfLengthBytes);
        this.value = value;
    }
    
    /**
     * This constructor is used to create this <code>SECSItem</code> from
     * data in wire/transmission format.
     * <br>
     * Note: Zero values are interpreted as <code>false</code> and non-zero values
     * are interpreted as <code>true</code>.
     * 
     * @param data - The buffer where the wire/transmission format data is contained.
     * @param itemOffset - The offset into the data where the desired item starts.
     */
    public BooleanArraySECSItem(byte[] data, int itemOffset)
    {
        super(data, itemOffset);
        int offset = 1 + inboundNumberOfLengthBytes.valueOf() + itemOffset;
        bytesConsumed = 1 + inboundNumberOfLengthBytes.valueOf() + lengthInBytes;
        value = new boolean[lengthInBytes];
        for(int i = 0; i< lengthInBytes; i++)
        {
            if (data[offset+i] == 0)
                value[i] = false;
            else
                value[i] = true;
                
        }
    }
    
    /**
     * Returns the value of this <code>SECSItem</code> in a Java friendly format.
     * @return - A java <code>boolean[]</code> that contains this item's value.
     */
    public boolean[] getValue()
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
        
        for( int i = offset, j = 0; j < value.length; i++, j++ )
        {
            if (value[j] == true)
                output[i] = 1;
            else
                output[i] = 0;
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
        BooleanArraySECSItem other = (BooleanArraySECSItem) obj;
        if (!Arrays.equals(value, other.value))
            return false;
        return true;
    }
}
