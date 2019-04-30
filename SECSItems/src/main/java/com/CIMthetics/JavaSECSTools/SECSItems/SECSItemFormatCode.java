package com.CIMthetics.JavaSECSTools.SECSItems;

/**
 * This enumeration contains items for SECS Item Format Codes that have been
 * defined by the SEMI E5 standard.
 * <ul>
 * <li><code>L</code> - List</li>
 * <li><code>B</code> - Binary</li>
 * <li><code>BO</code> - Boolean</li>
 * <li><code>A</code> - ASCII</li>
 * <li><code>J8</code> - JIS-8 - Japanese Industrial Standard 8</li>
 * <li><code>C2</code> - 2 Byte Character Format</li>
 * <li><code>I1</code> - 1 Byte Signed Integer</li>
 * <li><code>I2</code> - 2 Byte Signed Integer</li>
 * <li><code>I4</code> - 4 Byte Signed Integer</li>
 * <li><code>I8</code> - 8 Byte Signed Integer</li>
 * <li><code>U1</code> - 1 Byte Unsigned Integer</li>
 * <li><code>U2</code> - 2 Byte Unsigned Integer</li>
 * <li><code>U4</code> - 4 Byte Unsigned Integer</li>
 * <li><code>U8</code> - 8 Byte Unsigned Integer</li>
 * <li><code>F4</code> - 4 Byte Floating Point</li>
 * <li><code>F8</code> - 8 Byte Floating Point</li>
 * </ul>
 * 
 * @author Douglas Kaip
 *
 */
public enum SECSItemFormatCode
{
    /*
     * Don't change this list without performing the appropriate
     * corrections in the RawSECSItemFormatCodeSingleton Class.
     */
    /**
     * List
     */
    L,
    /**
     * binary
     */
    B,
    /**
     * boolean
     */
    BO,
    /**
     * ASCII
     */
    A,
    /**
     * JIS-8 Japanese Industrial Standard 8
     */
    J8,
    /**
     * 2 byte character format
     */
    C2,
    /**
     * 64-bit signed integer
     */
    I8,
    /**
     * 8-bit signed integer
     */
    I1,
    /**
     * 16-bit signed integer
     */
    I2,
    /**
     * 32-bit signed integer
     */
    I4,
    /**
     * 64-bit floating point (IEEE 754)
     */
    F8,
    /**
     * 32-bit floating point (IEEE 754)
     */
    F4,
    /**
     * 64-bit unsigned integer
     */
    U8,
    /**
     * 8-bit unsigned integer
     */
    U1,
    /**
     * 16-bit unsigned integer
     */
    U2,
    /**
     * 32-bit unsigned integer
     */
    U4,
    /**
     * This is not part of the standard
     */
    UNDEFINED;
    
    /**
     * This method is typically only used the retrieve the proper numeric value
     * of an Item Format Code during the conversion of a SECSItem into its
     * &quot;wire/transmission format&quot;.
     * 
     * @param formatCode the SECS item format code in the Java friendly enum form.
     * 
     * @return The numerical equivalent of the SECS Item Format Code
     */
    public static int getNumberFromSECSItemFormatCode(SECSItemFormatCode formatCode)
    {
        return RawSECSItemFormatCode.mapFormatCodeToNumber(formatCode);
    }
    
    /**
     * This method is typically only used when converting an Item Format Code
     * from its &quot;wire/transmission format&quot; into the format used 
     * (a type of SECSItem) in the Java environment.
     * 
     * @param number - the numeric value from &quot;off the wire&quot;.
     * 
     * @return - the appropriate SECSItemFormatCode
     */
    public static SECSItemFormatCode getSECSItemFormatCodeFromNumber(byte number)
    {
        return RawSECSItemFormatCode.mapNumberToFormatCode(number);
    }
}
