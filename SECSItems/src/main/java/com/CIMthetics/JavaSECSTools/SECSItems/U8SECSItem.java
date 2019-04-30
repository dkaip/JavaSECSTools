package com.CIMthetics.JavaSECSTools.SECSItems;

import java.math.BigInteger;

/**
 * This class represents/implements a <code>SECSItem</code> with the SECS data type of <code>U8</code>,
 * which is an 8 byte (64-bit) unsigned integer.  From the Java side this data
 * type is handled as a Java <code>BigInteger</code>.
 * <p>
 * Due to Java not having unsigned data types a scheme of size promotion is used
 * to deal with the issue.  However, in this case, Java does not have a suitable primitive
 * data type to handle unsigned integers of this size.  Thus a Java
 * <code>BigInteger</code> is used to contain the values in the Java world.
 * Just make sure the values that are stored in the
 * &quot;BigInteger&quot; are no larger than +18446744073709551615 (<code>0xFFFFFFFFFFFFFFFF</code>) and do not make them negative.
 * 
 * @author Douglas Kaip
 */

public class U8SECSItem extends SECSItem
{
    private static final BigInteger maxValue = new BigInteger("FFFFFFFFFFFFFFFF", 16);
    private static final BigInteger minValue = new BigInteger("0000000000000000", 16);
    private BigInteger value = minValue;
    
    /**
     * This constructor creates a <code>SECSItem</code> that has a type of <code>U8</code> 
     * with the specified value.
     * <br>
     * Note: It will be created with 1 length byte.
     * <p>
     * Warning!!! Do not submit a BigInteger that will be larger than what a <code>U8</code>(unsigned 64-bit integer) may contain
     * 
     * @param value - the value to be assigned to this <code>SECSItem</code>
     */
    public U8SECSItem(BigInteger value)
    {
        super(SECSItemFormatCode.U8, 8);
        
        if (value.compareTo(maxValue) > 0)
            throw new IllegalArgumentException("The value specified (" + value.toString() + ") is too large.  It must be <= to " + maxValue.toString() + ".");

        if (value.compareTo(minValue) < 0)
            throw new IllegalArgumentException("The value specified (" + value.toString() + ") is too small.  It must be > 0.");

        this.value = value;
        
   }
    
    /**
     * This constructor creates a <code>SECSItem</code> that has a type of <code>U8</code>
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
    public U8SECSItem(BigInteger value, SECSItemNumLengthBytes desiredNumberOfLengthBytes)
    {
        super(SECSItemFormatCode.U8, 8, desiredNumberOfLengthBytes);

        if (value.compareTo(maxValue) > 0)
            throw new IllegalArgumentException("The value specified (" + value.toString() + ") is too large.  It must be <= to " + maxValue.toString() + ".");

        if (value.compareTo(minValue) < 0)
            throw new IllegalArgumentException("The value specified (" + value.toString() + ") is too small.  It must be > 0.");

        this.value = value;
    }
    
    /**
     * This constructor is used to create this <code>SECSItem</code> from
     * data in wire/transmission format.
     * 
     * @param data - The buffer where the wire/transmission format data is contained.
     * @param itemOffset - The offset into the data where the desired item starts.
     */
    public U8SECSItem(byte[] data, int itemOffset)
    {
        super(data, itemOffset);
        int offset = 1 + inboundNumberOfLengthBytes.valueOf() + itemOffset;
        bytesConsumed = 1 + inboundNumberOfLengthBytes.valueOf() + lengthInBytes;
        if (lengthInBytes != 8)
            throw new IllegalArgumentException("Illegal data length of: " + lengthInBytes + 
                    ".  The length of the data independent of the item header must be 8.");
        
        /*
         * An array size of 9 is used so that sign extension
         * issues will hopefully not show up.
         */
        byte[] tempBuffer = new byte[9];
        tempBuffer[0] = 0;
        System.arraycopy(data, offset, tempBuffer, 1, 8);
        value = new BigInteger(tempBuffer);
    }
    
    /**
     * Returns the value of this <code>SECSItem</code> in a Java friendly format.
     * @return - A java <code>BigInteger</code> that contains this item's value.
     */
    public BigInteger getValue()
    {
        return value;
    }

    /**
     * Creates and returns a <code>byte[]</code> that represents this <code>SECSItem</code> in &quot;wire/transmission format&quot;.
     * @return - A <code>byte[]</code> representation of this <code>SECSItem</code> content that is suitable for transmission.
     */
    public byte[] toRawSECSItem()
    {
        byte[] output = new byte[outputHeaderLength()+8];
        int offset = populateSECSItemHeaderData(output, 8);
        
        byte[] buf = value.toByteArray();
        
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
        
        for (int i = buf.length - 1, j = offset + 7; i >= firstByte; i--, j--)
        {
            output[j] = buf[i];
        }

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
        U8SECSItem other = (U8SECSItem) obj;
        if (value.equals(other.value) == false)
            return false;
        return true;
    }
}
