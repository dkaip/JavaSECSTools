package com.CIMthetics.JavaSECSTools.SECSItems;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * This class represents/implements a <code>SECSItem</code> with the SECS data type of <code>U2</code>,
 * which is a 2 byte (16-bit) unsigned integer.  From the Java side this data
 * type is handled as a Java <code>int</code>.
 * <p>
 * Due to Java not having unsigned data types a scheme of size promotion is used
 * to deal with the issue.  In this case, this is handled by using a java
 * <code>int</code> integer (32-bits) to contain the value of the unsigned two byte integer (16-bits).  With
 * this method the user of this library is able to use normal unsigned values for
 * computational purposes.  Just make sure the values that are stored in the
 * &quot;long&quot; are no larger than +65536 (<code>0xFFFF</code>) and do not make them negative.
 * 
 * @author Douglas Kaip
 *
 */
public class U2SECSItem extends SECSItem
{
    private int value;
    
    /**
     * This constructor creates a <code>SECSItem</code> that has a type of <code>U2</code> 
     * with the specified value.
     * <br>
     * Note: It will be created with 1 length byte.
     * 
     * @param value - the value to be assigned to this <code>SECSItem</code>
     */
    public U2SECSItem(int value)
    {
        super(SECSItemFormatCode.U2, 2);
        this.value = value;
    }
    
    /**
     * This constructor creates a <code>SECSItem</code> that has a type of <code>U2</code>
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
    public U2SECSItem(int value, SECSItemNumLengthBytes desiredNumberOfLengthBytes)
    {
        super(SECSItemFormatCode.U2, 2, desiredNumberOfLengthBytes);
        this.value = value;
    }
    
    /**
     * This constructor is used to create this <code>SECSItem</code> from
     * data in wire/transmission format.
     * 
     * @param data - The buffer where the wire/transmission format data is contained.
     * @param itemOffset - The offset into the data where the desired item starts.
     */
    public U2SECSItem(byte[] data, int itemOffset)
    {
        super(data, itemOffset);
        int offset = 1 + inboundNumberOfLengthBytes.valueOf() + itemOffset;
        bytesConsumed = 1 + inboundNumberOfLengthBytes.valueOf() + lengthInBytes;
        if (lengthInBytes != 2)
            throw new IllegalArgumentException("Illegal data length of: " + lengthInBytes + 
                    ".  The length of the data independent of the item header must be 2.");

        byte[] tempBuffer = new byte[4];
        tempBuffer[0] = 0;
        tempBuffer[1] = 0;
        tempBuffer[2] = data[offset+0];
        tempBuffer[3] = data[offset+1];
        ByteBuffer buf = ByteBuffer.wrap(tempBuffer);
        buf.order(ByteOrder.BIG_ENDIAN);
        value = buf.getInt();
    }
    
    /**
     * Returns the value of this <code>SECSItem</code> in a Java friendly format.
     * @return - A java <code>int</code> that contains this item's value.
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Creates and returns a <code>byte[]</code> that represents this <code>SECSItem</code> in &quot;wire/transmission format&quot;.
     * @return - A <code>byte[]</code> representation of this <code>SECSItem</code> content that is suitable for transmission.
     */
    public byte[] toRawSECSItem()
    {
        byte[] output = new byte[outputHeaderLength()+2];
        int offset = populateSECSItemHeaderData(output, 2);
        
        byte[] temp = new byte[4];
        ByteBuffer bb = ByteBuffer.wrap(temp);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.putInt(value);
        output[offset]   = temp[2];
        output[offset+1] = temp[3];
        
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
        U2SECSItem other = (U2SECSItem) obj;
        if (value != other.value)
            return false;
        return true;
    }
}
