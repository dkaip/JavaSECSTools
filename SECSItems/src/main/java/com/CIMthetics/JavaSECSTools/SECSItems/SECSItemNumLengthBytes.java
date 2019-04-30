package com.CIMthetics.JavaSECSTools.SECSItems;

/**
 * This enum represents the valid values for the number of length bytes for a <code>SECSItem</code>.
 * 
 * <ul>
 * <li><code>ONE</code></li>
 * <li><code>TWO</code></li>
 * <li><code>THREE</code></li>
 *</ul>
 * 
 * @author Douglas Kaip
 *
 */
public enum SECSItemNumLengthBytes
{
    ONE(1),
    TWO(2),
    THREE(3),
    NOT_INITIALIZED(-1);
    
    private int value;
    
    private SECSItemNumLengthBytes(int value) { this.value = value; }

    /**
     * Return the number of length bytes in a numerical form.
     * 
     * @return the number of length bytes in numerical form
     */
    int valueOf() { return value; }
}
