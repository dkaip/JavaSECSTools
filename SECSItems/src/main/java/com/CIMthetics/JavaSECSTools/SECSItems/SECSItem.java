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
 * This is the base class for all of the SECSItems that are implemented in this
 * library.  Its purpose is to conceal the tedium of managing the information
 * that will be needed for encoding/decoding the various SECSItems to/from
 * the wire/transmission format that the transport layer(s) use.
 * 
 * @author Douglas Kaip
 *
 */
public abstract class SECSItem
{
    /**
     * The <code>SECSItem</code>'s Format Code
     */
    protected SECSItemFormatCode  formatCode                  = SECSItemFormatCode.UNDEFINED;
    
    /**
     * This is the number of length bytes that the wire/transmission format
     * data had when it was converted to this SECSItem.
     */
    SECSItemNumLengthBytes                 inboundNumberOfLengthBytes = SECSItemNumLengthBytes.NOT_INITIALIZED;
    /**
     * This attribute will contain the number of length bytes to be
     * used when the wire/transmission form of this SECSItem is generated.
     */
    SECSItemNumLengthBytes                 outboundNumberOfLengthBytes = SECSItemNumLengthBytes.NOT_INITIALIZED;
    
    /**
     * This is the length in bytes that this SECSItem requires, 
     * in addition to the length of its item header, when
     * it is converted into its wire/transmission format.
     */
    protected int                 lengthInBytes               = 0;

    /**
     * This contains the number of bytes that have been consumed when
     * a SECSItem is created from a SECS message that is in 
     * wire/transmission format.
     */
    protected int                 bytesConsumed               = 0;
    
    /**
     * This is a base class constructor for a SECSItem.  When using this constructor, the
     * outbound number of length bytes will be set to the minimum number required to
     * handle an item with the length that is specified by the value of the
     * <code>length</code> parameter.
     * <p>
     * This constructor sets the following base class attributes from the provided data:
     * <ul>
     * <li><code>formatCode</code></li>
     * <li><code>lengthInBytes</code></li>
     * <li><code>outboundNumberOfLengthBytes</code></li>
     * </ul>
     * 
     * 
     * @param formatCode the format code for this item
     * @param lengthInBytes The length in bytes of the item, or in the case of a <code>ListSECSItem</code> 
     * the number of items it contains.  It must be between 0 and 16777215 inclusive.
     */
    protected SECSItem(SECSItemFormatCode formatCode, int lengthInBytes)
    {
        if (lengthInBytes < 0 || lengthInBytes > (int)0x00FFFFFF)
        {
            throw new IllegalArgumentException(
                    "The value for the length argument must be between 0 and 16777215 inclusive.");
        }
        else
        {
            this.formatCode = formatCode;
            this.lengthInBytes = lengthInBytes;
            outboundNumberOfLengthBytes = calculateMinimumNumberOfLengthBytes(lengthInBytes);
        }
    }
    
    /**
     * This is a base class constructor for a SECSItem.  When using this constructor, the
     * number of length bytes will be set to the greater of, the specified number of 
     * length bytes, or the minimum number required to
     * handle an item with the length that is specified by the value of the
     * <code>length</code> parameter.
     * <p>
     * This constructor sets the following base class attributes from the provided data:
     * <ul>
     * <li><code>formatCode</code></li>
     * <li><code>lengthInBytes</code></li>
     * <li><code>outboundNumberOfLengthBytes</code></li>
     * </ul>
     * 
     * <p>
     * Note: An <code>IllegalArgumentException</code> will be thrown if the value of the
     * <code>length</code> parameter is outside the range of 0 - 16777215
     * inclusive.
     * 
     * @param formatCode - The SECS Item Format code for this SECSItem.
     * @param length - The length in bytes that this SECSItem will take up in its 
     * wire/transmission format.
     * @param desiredNumberOfLengthBytes - This parameter expresses the desired
     * number or length bytes to be used for this SECSItem when it is converted
     * to its wire/transmission format.

     */
    protected SECSItem(SECSItemFormatCode formatCode, int length, SECSItemNumLengthBytes desiredNumberOfLengthBytes)
    {
        this.formatCode = formatCode;
        lengthInBytes = length;
        setOutboundNumberOfLengthBytes(length, desiredNumberOfLengthBytes);
    }

    /**
     * This is a base class constructor for a SECSItem.  This form of the constructor
     * is used when parsing wire/transmission format data and converting it into
     * its "Java form".
     * <p>
     * This constructor sets the following base class attributes from the provided data:
     * <ul>
     * <li><code>formatCode</code></li>
     * <li><code>inboundNumberOfLengthBytes</code></li>
     * <li><code>outboundNumberOfLengthBytes</code></li>
     * <li><code>lengthInBytes</code> or if the <code>formatCode</code> is of type <code>L</code>(List) <code>lengthInBytes</code>
     * will be the number of items in the list</li>
     * </ul>
     * 
     * @param data the raw data that the <code>SECSItem</code> is to be created from.  The value of this
     * argument must not be <code>null</code>.
     * @param itemOffset the offset into the data where to begin extracting information
     * 
     * @exception IllegalArgumentException if the data argument is null
     * @exception IllegalArgumentException if the data argument has a length of zero
     * @exception IllegalArgumentException if the number of length bytes parsed out is zero.
     * <p>
     * In normal use the only time the <code>IllegalArgumentException</code> 
     * exception should be caught is if you are reading data from a piece of
     * equipment that does not properly speak SECS and you want to be able to
     * recover from the error gracefully.  Typically the ACM process will have
     * detected this equipment and it will not be allowed into the factory in
     * the first place.
     */
    protected SECSItem(byte[] data, int itemOffset)
    {
        if (data == null)
        {
            throw new IllegalArgumentException("\"data\" argument must not be null.");
        }
        
        if (data.length < 2)
        {
            throw new IllegalArgumentException("\"data\" argument must have a length >= 2.");
        }

        formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)((data[itemOffset] >> 2) & 0x0000003F));
        
        byte[] temp1 = new byte[4];
        switch(data[itemOffset] & 0x03) // Extract the number of length bytes from the message
        {
            case 0:
                throw new IllegalArgumentException("The number of length bytes is not allowed to be ZERO.");
            case 1:
                inboundNumberOfLengthBytes = SECSItemNumLengthBytes.ONE;
                outboundNumberOfLengthBytes = inboundNumberOfLengthBytes;
                
                temp1[0] = 0;
                temp1[1] = 0;
                temp1[2] = 0;
                temp1[3] = data[itemOffset+1];
                break;
            case 2:
                inboundNumberOfLengthBytes = SECSItemNumLengthBytes.TWO;
                outboundNumberOfLengthBytes = inboundNumberOfLengthBytes;
                if (data.length < 3)
                {
                    throw new IllegalArgumentException("With two length bytes the minimum length for the \"data\" argument is 3.");
                }

                temp1[0] = 0;
                temp1[1] = 0;
                temp1[2] = data[itemOffset+1];
                temp1[3] = data[itemOffset+2];
                break;
            case 3:
                inboundNumberOfLengthBytes = SECSItemNumLengthBytes.THREE;
                outboundNumberOfLengthBytes = inboundNumberOfLengthBytes;
                if (data.length < 4)
                {
                    throw new IllegalArgumentException("With three length bytes the minimum length for the \"data\" argument is 4.");
                }

                temp1[0] = 0;
                temp1[1] = data[itemOffset+1];
                temp1[2] = data[itemOffset+2];
                temp1[3] = data[itemOffset+3];
                break;
        }

        ByteBuffer buf = ByteBuffer.wrap(temp1);
        buf.order(ByteOrder.BIG_ENDIAN);
        lengthInBytes = buf.getInt();
    }
    
    /**
     * Return the minimum number of length bytes required based on the
     * specified length.
     * 
     * The maximum length of a SECSItem in its &quot;wire/transmission format&quot;
     * is 16777215 (stored in 24 bits).
     * 
     * @param length - the number of bytes this SECSItem will need in
     * &quot;wire/transmission format&quot;.
     */
    private SECSItemNumLengthBytes calculateMinimumNumberOfLengthBytes(int length)
    {
        SECSItemNumLengthBytes result = SECSItemNumLengthBytes.ONE;
        if (length > 255)
        {
            if (length < 65536)
                result = SECSItemNumLengthBytes.TWO;
            else
                result = SECSItemNumLengthBytes.THREE;
        }
        
        return result;
    }

    /**
     * Returns the length in bytes of what the outbound item header will require. 
     * @return length in bytes
     */
    protected final int outputHeaderLength()
    {
        return outboundNumberOfLengthBytes.valueOf() + 1;
    }
    
    /**
     * This method fills in the first 2 to 4 bytes of a <code>SECSItem</code>'s 
     * value (the <code>SECSItem</code>'s header) in its raw wire/transmission format.  The first byte contains value
     * of the item's format code and number of length bytes and the next 1 to 3
     * bytes contain the value of the item's length in bytes.
     * <p>
     * Make sure <code>buffer</code> is large enough!!!
     * 
     * @param buffer  The buffer that will be used to contain an outgoing message item
     * @param numberOfBytes number of bytes in the payload...get this right!
     * 
     * @return the offset to where the payload data may loaded into the buffer
     */
    protected final int populateSECSItemHeaderData(byte[] buffer, int numberOfBytes)
    {
        int offset = 0;
        byte[] outputLengthBytes = new byte[]{0, 0, 0, 0};
        buffer[0] = (byte)((SECSItemFormatCode.getNumberFromSECSItemFormatCode(formatCode) << 2) | outboundNumberOfLengthBytes.valueOf());
        
        ByteBuffer bb = ByteBuffer.wrap(outputLengthBytes);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.putInt(numberOfBytes);

        switch(outboundNumberOfLengthBytes)
        {
            case ONE:
                buffer[1] = outputLengthBytes[3];
                offset = 2;
                break;
            case TWO:
                buffer[1] = outputLengthBytes[2];
                buffer[2] = outputLengthBytes[3];
                offset = 3;
                break;
            case THREE:
                buffer[1] = outputLengthBytes[1];
                buffer[2] = outputLengthBytes[2];
                buffer[3] = outputLengthBytes[3];
                offset = 4;
                break;
            case NOT_INITIALIZED:
                System.err.println("The case where outboundNumberOfLengthBytes is still in its NOT_INITIALIZED state should never happen.");
                break;
        }
        
        return offset;
    }
    
    /**
     * Return the length in bytes of the actual "data" portion of this <code>SECSItem</code>.
     * @return the length in bytes of the "payload" portion of this <code>SECSItem</code>
     */
    public int getLengthInBytes()
    {
        return lengthInBytes;
    }
    
    /**
     * Returns an <code>enum</code> indicating the number of length bytes used
     * in the wire/transmission format data this <code>SECSItem</code> was
     * constructed from.
     * 
     * @return the number of length bytes that were in the wire/transmission
     * format data that this <code>SECSItem</code> was created from
     */
    public SECSItemNumLengthBytes getInboundNumberOfLengthBytes()
    {
        return inboundNumberOfLengthBytes;
    }
    
    /**
     * Returns an <code>enum</code> indicating the number of length bytes
     * that will be used when creating the wire/transmission format data
     * from this <code>SECSItem</code>.
     * 
     * @return The number of length bytes that will be used when this
     * <code>SECSItem</code> is converted to wire/transmission format.
     */
    public SECSItemNumLengthBytes getOutboundNumberOfLengthBytes()
    {
        return outboundNumberOfLengthBytes;
    }
    
    /**
     * This method is used to change the number of length bytes used when this
     * <code>SECSItem</code> is converted to its wire/transmission format.  The
     * value the number of length bytes will actually set to is the greater of
     * the minimum required or the number desired.
     * 
     * @param length length in bytes of the <code>SECSItem</code>
     * @param desiredNumberOfLengthBytes The number of length bytes to be used 
     * for this SECSItem. The value for the number of length bytes must be <code>ONE</code>, 
     * <code>TWO</code>, or <code>THREE</code>.
     */
    public void setOutboundNumberOfLengthBytes(int length, SECSItemNumLengthBytes desiredNumberOfLengthBytes)
    {
        if (length < 0 || length > (int)0x00FFFFFF)
        {
            throw new IllegalArgumentException(
                    "The value for the length argument must be between 0 and 16777215 inclusive.");
        }

        outboundNumberOfLengthBytes = calculateMinimumNumberOfLengthBytes(length);
        if (outboundNumberOfLengthBytes.valueOf() < desiredNumberOfLengthBytes.valueOf())
        {
            outboundNumberOfLengthBytes = desiredNumberOfLengthBytes;
        }
    }
    
    /**
     * Returns an <code>enum</code> version of this <code>SECSItem</code>'s
     * Format Code.
     * 
     * @return this <code>SECSItem</code>'s Format Code.
     */
    public SECSItemFormatCode getSECSItemFormatCode()
    {
        return getFormatCode();
    }
    
    /**
     * Returns an <code>enum</code> version of this <code>SECSItem</code>'s
     * Format Code.
     * 
     * @return - The <code>SECSItemFormatCode</code> for this <code>SECSItem</code>.
     */
    public SECSItemFormatCode getFormatCode()
    {
        return formatCode;
    }
    
    /*
     * This will need to be implemented by the Classes that extend this one.
     */
    public abstract boolean equals(Object abc);

    /*
     * This will need to be implemented by the Classes that extend this one.
     */
    public abstract int hashCode();
    
    /**
     * Creates and returns a <code>byte[]</code> that represents this SECSItem in its &quot;wire/transmission format&quot;.
     * <p>
     * Each object that extends this class will need to correctly implement this method.
     * 
     * @return - A <code>byte[]</code> representation of this SECSItem's content that is suitable for transmission.
     */
    public abstract byte[] toRawSECSItem();
}
