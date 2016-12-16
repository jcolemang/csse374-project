
# CSSE374 Final Project

## Introduction

Our CSSE374 project is a UML generation tool meant to be **fully featured** and
**open to extension**. It was written using the Java ASM library and generates
Graphviz Dot files to be compiled and viewed with an external editor. This
project was written by Wenkang Dang, Sabrina Wicker, and Coleman Gibson.


## Using Our Project

### Adding Your Project to the Build Path
1. Open the CSSE374 project in Eclipse
2. Click the "Run" menu
3. Select "Run Configurations"
4. Select the "Classpath" tab and select "User Entries"
5. Select "Add Projects"
6. Add your project from the project selection window

### Running the Code
1. Select the "Run" menu and click "Run Configurations"
2. Create a new Java Application run configuration
3. Make sure the "Project" and "Main class" fields are correct
4. Select the "Arguments" tab
5. Enter the class name of each class you would like analyzed under "Program
   arguments"
6. Add "-recursive" to recursively parse superclasses and interfaces and
   "-private", "-public", or "-protected" to limit the fields and methods
   displayed


## Contributions

### Wenkang
- Contributed to design
- Contributed to documentation
- Contributed to

### Sabrina
- Contributed to design
- Debugged and

### Coleman
- Contributed to design
- Helped to write the class -> graph parsing code
- Helped to write the graph -> DOM parsing code
-



## Things to do:
- Redo our design document
- Italicize Interfaces and Abstract classes
- Demo run configurations
- Finish adding privacy level command line argument
- Comment code and add javadocs
