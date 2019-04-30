package com.CIMthetics.JavaSECSTools.SECSItems;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * This class represents/implements a <code>SECSItem</code> with the SECS data type of <code>L</code>,
 * which is a list of <code>SECSItem</code>s.  From the Java side this data
 * type is handled as a Java <code>LinkedList&lt;SECSItem&gt;</code>.
 * 
 * @author Douglas Kaip
 *
 */
public class ListSECSItem extends SECSItem
{
    private LinkedList<SECSItem> value = null;
    
    /**
     * This constructor creates a <code>SECSItem</code> that has a type of <code>L</code>
     * with the specified value.
     * <br>
     * Note: It will be created with the minimum number of length bytes required to
     * accommodate the size of the provided list of <code>SECSItem</code>s.
     * 
     * @param value - the value to be assigned to this <code>SECSItem</code>
     */
    public ListSECSItem(LinkedList<SECSItem> value)
    {
        super(SECSItemFormatCode.L, value == null ? 0 : value.size());
        if (value == null)
            this.value = new LinkedList<SECSItem>();
        else
            this.value = value;
    }
    
    /**
     * This constructor creates a <code>SECSItem</code> that has a type of <code>L</code>
     * with the specified value and specified number of length bytes.
     * <p>
     * This form of the constructor is not needed much nowadays.  In past
     * there were situations where the equipment required that messages
     * contained SECSItems that had a specified number of length bytes.
     * This form of the constructor is here to handle these problem child cases.
     * <br>
     * Note: It will be created with the greater of the specified number of length bytes 
     * or the number of length bytes required to
     * accommodate the size of the provided list of <code>SECSItem</code>s.
     * 
     * @param value the value to be assigned to this <code>SECSItem</code>
     * @param desiredNumberOfLengthBytes The number of length bytes to be used for this <code>SECSItem</code>.
     * The value for the number of length bytes must be <code>ONE</code>, <code>TWO</code>, or <code>THREE</code>.
     */
    public ListSECSItem(LinkedList<SECSItem> value, SECSItemNumLengthBytes desiredNumberOfLengthBytes)
    {
        super(SECSItemFormatCode.L, value == null ? 0 : value.size(), desiredNumberOfLengthBytes);
        if (value == null)
            this.value = new LinkedList<SECSItem>();
        else
            this.value = value;
    }

    /**
     * This constructor is used to create this <code>SECSItem</code> from
     * data in wire/transmission format.
     * 
     * @param data - The buffer where the wire/transmission format data is contained.
     * @param itemOffset - The offset into the data where the desired item starts.
     */
    public ListSECSItem(byte[] data, int itemOffset)
    {
        super(data, itemOffset);
        int offset = 1 + inboundNumberOfLengthBytes.valueOf() + itemOffset;
        bytesConsumed = offset + lengthInBytes;
        value = new LinkedList<SECSItem>();
        
        /*
         * This is a special case where lengthInBytes actually
         * means number of elements.  It only means that for
         * a List item and only during this constructor.  Once
         * this constructor has completed the number of elements
         * will need to be accessed directly from the LinkedList
         * value attribute.
         */
        for( int i = 0; i < lengthInBytes; i++)
        {
            SECSItem temp = RawSECSData.generateSECSItem(data, offset);
            offset += temp.bytesConsumed;
            this.bytesConsumed += temp.bytesConsumed;
            value.add(temp);
        }
    }
    
    /**
     * Returns the value of this <code>SECSItem</code> in a Java friendly format.
     * @return - A java <code>LinkedList&lt;SECSItem&gt;</code> that contains this item's value.
     */
    public LinkedList<SECSItem> getValue()
    {
        return value;
    }
    

    /**
     * This method returns a SECSItem contained in this list based on its 
     * "address" or null if the item does not exist.  
     * 
     * In the example below a specified address of "3.2" would return the
     * element with the value 'Answer' and type of ASCII.
     * <br>
     * <ol>
     * <li>A 'ABC' </li>
     * <li>A 'DEF' </li>
     * <li>L 3 </li>
     * <ol>
     * <li>I2 -32768</li>
     * <li>A 'Answer'</li>
     * <li>U1 255</li>
     * </ol>
     * <li>F4 3.141592</li>
     * </ol>
     * 
     * @param address - The &quot;address&quot; of the element to be retrieved.
     * Refer to the description for more details.
     * @return The SECSItem at the provided address or null if not found.
     */
    public SECSItem getElementAt(String address)
    {
        SECSItem result = null;
        
        String[] addressArray = address.split("\\.", 2);
        int elementNumber = Integer.parseInt(addressArray[0]);
        
        if (elementNumber < 1 || elementNumber > value.size())
            return null;
        
        result = value.get(elementNumber-1);
        if (addressArray.length > 1)
        {
            if (result.formatCode != SECSItemFormatCode.L)
            {
                /*
                 * If we are here the address has more levels, but, this 
                 * element is not a list so we cannot proceed.
                 */
                return null;
            }
            else
            {
                return getElementAt(addressArray[1]);
            }
        }
        
        return result;
    }

    /**
     * Creates and returns a <code>byte[]</code> that represents this <code>SECSItem</code> in &quot;wire/transmission format&quot;.
     * @return - A <code>byte[]</code> representation of this <code>SECSItem</code>'s content that is suitable for transmission.
     */
    public byte[] toRawSECSItem()
    {
        
        LinkedList<byte[]> outputStorage = new LinkedList<byte[]>();

        int outputBufferSize = 0;
        for(SECSItem item : value)
        {
            byte[] itemBytes = item.toRawSECSItem();
            outputBufferSize += itemBytes.length;
            outputStorage.add(itemBytes);
        }

        
        byte[] output = new byte[outputHeaderLength()+outputBufferSize];
        int offset = populateSECSItemHeaderData(output, value.size());

        for(byte[] rawData : outputStorage)
        {
            System.arraycopy(rawData, 0, output, offset, rawData.length);
            offset += rawData.length;
        }
        
        return output;
    }
    
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        
        result.append("Format:" + formatCode.toString() + " Value: " + value.size());
        for (SECSItem item : value)
        {
            String temp = item.toString();
            result.append("\n" + temp);
        }
        
        return result.toString();
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        
        for(SECSItem secsItem : value)
        {
            result += prime * result + secsItem.hashCode();
        }
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        // Do the less expensive tests first
        if (this == obj)
            return true;
        
        if (obj == null)
            return false;
        
        if (getClass() != obj.getClass())
            return false;
        
        ListSECSItem other = (ListSECSItem) obj;
        
        if (value.size() != other.getValue().size())
            return false;
        
        // If we are here the two list have the same number of elements
        ListIterator<SECSItem> otherIterator = other.getValue().listIterator(0);
        for(SECSItem secsItem : value)
        {
            if (secsItem.equals(otherIterator.next()) == false)
                return false;
        }

        return true;
    }
}
