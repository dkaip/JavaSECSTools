package com.CIMthetics.JavaSECSTools.SECSItems;

import java.nio.ByteBuffer;

/**
 * This "raw" SECS Item layer has been created because the transport layers
 * used (SECS I or HSMS) to transport SECS II messages do not really care 
 * about the payload of the messages they are transporting.
 * 
 * @author Douglas Kaip
 *
 */

public class RawSECSData
{
    private byte[]              data;
    
    /**
     * This constructor will most likely be used just before a SECS II 
     * message is to be sent out on a SECS I serial line or a HSMS connection.
     * 
     * @param secsItem a <code>SECSItem</code> that is intended to later be
     * converted into wire/transmission format.
     */
    public RawSECSData(SECSItem secsItem)
    {
        data = secsItem.toRawSECSItem();
    }
    
    /**
     * This constructor will most likely be used to contain raw SECS II
     * messages that are received from a SECS I serial line or from a
     * HSMS network connection.
     * 
     * @param incomingData a <code>byte[]</code> that contains the SECS message's payload.
     */
    public RawSECSData(byte[] incomingData)
    {
        data = incomingData;
    }
    
    /**
     * 
     * @return Returns the raw payload data
     */
    public byte[] getData()
    {
        return data;
    }
    
    public SECSItem generateSECSItem()
    {
        return generateSECSItem(data, 0);
    }

    /**
     * Convert the supplied wire/transmission format data into its
     * Java friendly <code>SECSItem</code> format.
     * 
     * @param data an array of bytes that is in wire/transmission format
     * @param offset the byte offset into the <code>data</code> argument where
     * @return the resulting <code>SECSItem</code>
     */
    public static SECSItem generateSECSItem(byte[] data, int offset)
    {
        SECSItem result = null;
        
        SECSItemFormatCode formatCode = SECSItemFormatCode.getSECSItemFormatCodeFromNumber((byte)((data[offset] >> 2) & 0x0000003F));
        int numberOfLengthBytes = (data[offset] & 0x03);
        int incomingDataLength = 0;
        
        switch (numberOfLengthBytes)
        {
            case 1:
            {
                byte[] temp = new byte[4];
                temp[0] = 0;
                temp[1] = 0;
                temp[2] = 0;
                temp[3] = data[offset +1];
                ByteBuffer buf = ByteBuffer.wrap(temp);
                incomingDataLength = buf.getInt();
                break;
            }
            case 2:
            {
                byte[] temp = new byte[4];
                temp[0] = 0;
                temp[1] = 0;
                temp[2] = data[offset+1];
                temp[3] = data[offset+2];
                ByteBuffer buf = ByteBuffer.wrap(temp);
                incomingDataLength = buf.getInt();
                break;
            }
            case 3:
            {
                byte[] temp = new byte[4];
                temp[0] = 0;
                temp[1] = data[offset+1];
                temp[2] = data[offset+2];
                temp[3] = data[offset+3];
                ByteBuffer buf = ByteBuffer.wrap(temp);
                incomingDataLength = buf.getInt();
                break;
            }
        }

        switch(formatCode)
        {
            case L:
                result = new ListSECSItem(data, offset);
                break;
            case B:
                result = new BinarySECSItem(data, offset);
                break;
            case BO:
                // FIXME if the length is zero this should also create the array form
                if (incomingDataLength == 1)
                    result = new BooleanSECSItem(data, offset);
                else
                    result = new BooleanArraySECSItem(data, offset);
                break;
            case A:
                result = new ASCIISECSItem(data, offset);
                break;
            case J8:
                break;
            case C2:
                break;
            case I8:
                // FIXME if the length is zero this should also create the array form
                if (incomingDataLength == 8)
                    result = new I8SECSItem(data, offset);
                else
                    result = new I8ArraySECSItem(data, offset);
                break;
            case I1:
                // FIXME if the length is zero this should also create the array form
                if (incomingDataLength == 1)
                    result = new I1SECSItem(data, offset);
                else
                    result = new I1ArraySECSItem(data, offset);
                break;
            case I2:
                // FIXME if the length is zero this should also create the array form
                if (incomingDataLength == 2)
                    result = new I2SECSItem(data, offset);
                else
                    result = new I2ArraySECSItem(data, offset);
                break;
            case I4:
                // FIXME if the length is zero this should also create the array form
                if (incomingDataLength == 4)
                    result = new I4SECSItem(data, offset);
                else
                    result = new I4ArraySECSItem(data, offset);
                break;
            case F8:
                // FIXME if the length is zero this should also create the array form
                if (incomingDataLength == 8)
                    result = new F8SECSItem(data, offset);
                else
                    result = new F8ArraySECSItem(data, offset);
                break;
            case F4:
                // FIXME if the length is zero this should also create the array form
                if (incomingDataLength == 4)
                    result = new F4SECSItem(data, offset);
                else
                    result = new F4ArraySECSItem(data, offset);
                break;
            case U8:
                // FIXME if the length is zero this should also create the array form
                if (incomingDataLength == 8)
                    result = new U8SECSItem(data, offset);
                else
                    result = new U8ArraySECSItem(data, offset);
                break;
            case U1:
                // FIXME if the length is zero this should also create the array form
                if (incomingDataLength == 1)
                    result = new U1SECSItem(data, offset);
                else
                    result = new U1ArraySECSItem(data, offset);
                break;
            case U2:
                // FIXME if the length is zero this should also create the array form
                if (incomingDataLength == 2)
                    result = new U2SECSItem(data, offset);
                else
                    result = new U2ArraySECSItem(data, offset);
                break;
            case U4:
                // FIXME if the length is zero this should also create the array form
                if (incomingDataLength == 4)
                    result = new U4SECSItem(data, offset);
                else
                    result = new U4ArraySECSItem(data, offset);
                break;
            case UNDEFINED:
                break;
            default:
                break;
        }
        
        return result;
    }
}
