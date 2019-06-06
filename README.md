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
command above it would be <code>cd JavaSECSTools</code>.  

Enter the following <code>gradle</code> command:

<code>gradle assemble test javadoc</code>

This compiles and creates a jar file with the appropriate files and runs the unit tests.

The result of a successful build produces several files:
* The file <code>SECSItems.jar</code> found in the <code>SECSItems/build/libs</code> directory.
* The file uhhh that contains the javadoc for the classes contained in the file <code>SECSItems.jar</code>.
* The file <code>SECSSpy.jar</code> found in the <code>SECSSpy/build/libs</code> directory.

##Documentation


## Usage Notes

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0)