
# CSSE374 Final Project

# Milestone 4
- Dependency Inversion

  If A has a B and B has an interface and B is concrete then there is a
  violation of the dependency inversion principle

- Decorators

  If you extend something and have an instance of the something then you have
  decorated the something

- Adapter

  If you extend A and have an instance of B and you override all of A's methods.

- Abstract Decorator

  If A is abstract and has an instance of the class it extends

Notes:
  String is a violation? Ignore string
  Implement code analysis

  If it extends abstract then it is a decorator


# Added
- make an analyzer remove certain relationships
- add hashmap to DOM
- getopt
- Add classes for association, dependency, bidirection
- Make sure arrows arent overwriting eachother
- Add cardinality setter
- Add cardinalities when we go from data structure to DOM
- fix the "if it fails it is primitive" problem

## Introduction

Our CSSE374 project is a UML generation tool meant to be **fully featured** and
**open to extension**. It was written using the Java ASM library and generates
Graphviz-compatible .DOT files to be compiled and viewed with an external editor. This
project was written by Wenkang Dang, Sabrina Wicker, and Coleman Gibson.

only association, not


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
6. Add `-recursive` to recursively parse superclasses and interfaces and
   `-private`, `-public`, or `-protected` to limit the fields and methods
   displayed


## Contributions

### Wenkang
- Update the uml design for our milestone 1 at end
- Do some start for command line arguments


### Sabrina
- Contributed to overall design and many design refinement debates
- Edge implementation
- DOT code debugging
- Code design improvements via abstraction of related classes
- Interface cleanup

### Coleman
- Contributed to design
- Helped to write the class -> graph parsing code
- Helped to write the graph -> DOM parsing code
