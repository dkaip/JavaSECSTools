# JavaSECSTools

**JavaSECSTools** is a project for those that are working primarily in th realm of shop floor automation in the semiconductor industry. 
If you do not work in that industry or you are not familiar with the [SEMI Standards](www.semi.org/en/standards) 
**E4**, **E5**, **E30**, **E37**, el al, this project is probably not for you.  

This project provides some tools for problem identification and monitoring as well as API libraries that will help 
in writing software for equipment interfaces and / or equipment simulators.

## Get the code
Use the <code>git clone</code> command to get the code. 

<code>git clone https://github.com/dkaip/JavaSECSTools JavaSECSTools</code> 

This will create a <code>JavaSECSTools</code> directory in your current directory

## Building
Change your current directory (<code>cd</code>) to the project root directory.  If you used the 
command above it would be <code>cd jvulkan</code>.  

Enter the following<code>gradle</code> command:

<code>gradle assemble test</code>

This compiles and creates a jar file with the appropriate files and runs the unit tests.

The result of a successful build is file <code>jvulkan.jar</code> found in the <code>build/libs</code> directory.

## Installation

In addition to this library you will need to retrieve and build the 
[jvulkan-natives-Linux-x86_64](https://github.com/dkaip/jvulkan-natives-Linux-x86_64) project.  The **jvulkan-natives-Linux-x86_64** 
library provides the linkage between code written in Java using this library and 
the Vulkan® SDK provided by LUNARG. 

##Documentation

At the current time, refer to the documentation provided by the 
[KRONOS® GROUP](https://www.khronos.org/). It may be found here 
[Vulkan® Documentation](https://www.khronos.org/registry/vulkan/specs/1.1-extensions/html/). 

This documentation is written with c++ in mind, but, currently the specification is 
updated rather frequently and because of this it is probably the best source for 
accuracy. **jvulkan** is implemented in a systematic way so that the 
&quot;conversion&quot; between the c++ and Java form of the functions(methods) 
should happen in a consistent manner.  Refer to the **Usage Notes** section below 
for more information.

In the future, I may work on adding javadoc so that working in an IDE might be easier, 
but, that is way down the road.

## Usage Notes
The [Vulkan® SDK](https://www.lunarg.com/vulkan-sdk/) from LUNARG is written in c++ 
and as you might imagine there are some challenges when "wrapping" c++ code with Java. 
As a result there are some systematic differences between using **jvulkan** and Java 
versus using c++ and the LUNARG Vulkan® SDK directly.

When the documentation ([Vulkan® Specification](https://www.khronos.org/registry/vulkan/specs/1.1-extensions/html/)) indicates that an array of <code>SomeObjectType</code> is to be passed as an argument there is always an additional argument indicating the number of elements contained within the array.  (There is an exception to this in the cases where there are &quot;parallel&quot; 
arrays that are directly associated with one another.  In these cases sometimes there is only 
one &quot;number of elements&quot; argument.) When using **jvulkan** array arguments will be passed as a <code>Collection&lt;SomeObjectType&gt;</code>.  Additionally, the argument indicating the number of elements contained within the array will not be present since a Java collection 
knows its size. 

The same applies for sending arrays of primitive types.  Java arrays know their size so the 
&quot;number of elements in the array&quot; argument is not needed and thus not present in 
the Java version of the function(method). 

In Java you cannot pass a pointer to a pointer as an argument to a function (method) as you 
can in c++.  Because of this, in the cases where data (objects) are returned in this manner in the c++ functions the Java objects must be created first in the Java environment.  Here is an example, 
first is the c++ function followed by the Java version provided by **jvulkan**:  

**c++**  
<code>VkResult vkEnumeratePhysicalDevices(
VkInstance                                  instance, 
uint32_t*                                   pPhysicalDeviceCount, 
VkPhysicalDevice*                           pPhysicalDevices);</code> 

**Java**  
<code>LinkedList&lt;VkPhysicalDevice&gt; physicalDeviceList = new LinkedList&lt;VkPhysicalDevice&gt;();<br> 
VkResult result = vkEnumeratePhysicalDevices(vulkanInstance, physicalDeviceList);</code>  

Notice how, in the Java environment the argument <code>pPhysicalDeviceCount</code> is not 
present, again because it is not needed since a Java <code>Collection</code> knows its size. 
In addition, notice how the Collection, in this case a <code>LinkedList&lt;VkPhysicalDevice&gt;</code> is created 
before the call to <code>vkEnumeratePhysicalDevices</code>.  This is because Java cannot return 
a created object in this manner, but, it can populate it. The elements that are added to 
the Collection, if any, are in the Java world meaning they are normal Java objects that will 
be garbage collected in the normal manner. In most cases in this situation the Collection should be empty.  Creating the object first is necessary when anything is returned via the arguments themselves in the c++ environment.

In the case where an object is the return value of the function(method) it does not have to be 
created ahead of time.  The is evident on the example above where the return code 
<code>VkResult result</code> is only declared and not initially created.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

The native method definitions in the file <code>NativeProxies.java</code> are in 
alphabetical order for the Vulkan® functions.  Please keep them in alphabetical order if you 
are adding native methods.  The ones defined in <code>VulkanFunctions.java</code> are not 
in alphabetical order and I do not currently feel the need to have them that way.

Please do not reformat the text in existing code.  If you create new classes, etc. please format 
things as you normally would.

Please make sure to update tests as appropriate.

In the event you are upgrading or adding to this software the command to recreate the 
JNI header file for the native functions is:

<code>javah -classpath my-jvulkan-project-path/src/main/java com.CIMthetics.jvulkan.VulkanCore.VK11.NativeProxies</code> 

This will create the file <code>com&lowbar;CIMthetics&lowbar;jvulkan&lowbar;VulkanCore&lowbar;VK11&lowbar;NativeProxies.h</code> that will need to be placed in the <code>headers</code> directory of 
the **jvulkan-natives-Linux-x86_64** project on your machine.  You will then need to implement 
any new functions you have added here in that project as well.

## License
[Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0)