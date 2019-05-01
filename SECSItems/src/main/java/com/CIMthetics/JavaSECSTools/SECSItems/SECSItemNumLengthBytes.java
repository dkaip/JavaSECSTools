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
