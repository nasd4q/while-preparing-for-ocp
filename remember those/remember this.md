#### Constructors and initialization

* Unlike methods, a constructor cannot be abstract, static, final, native, or synchronized.
* Final variables must be explicitly initialized. In case of final fields, this can happen in instance initializer or every constructor. **In case of static final fields though**, this _must happen before_ (at time of declaration or in static initializer).
* `One o = null;` won't initialize class One and won't cause class One static blocks to be executed.
* `Two t = new Two();` will cause Class Two (and superclass and so on) to be initialized (after superclass).
* `System.out;println(Sub.ID);`, where ID is a static field on a superclass of Sub, isn't sufficient to cause initialization of Sub class. (It should cause initialization of the class declaring the referenced static field ID though.)

"First, static statements/blocks are called IN THE ORDER they are defined. 
Next, instance initializer statements/blocks are called IN THE ORDER they are defined. 
Finally, the constructor is called."


#### Operator precedence and order of execution

* Though `b2 = b1 != b2` is valid, `b2 != b1 = !b2` doesn't compile (the = operator has least precedence of all operators)
* `==` has less precedence than `>` so `true == 2 > 10` is same as `true == (2 > 10)`
* `String str1 = "one"; String str2 = "two"; System.out.println( str1.equals(str1=str2) );` prints false.
* The statement `iA[i] = i = 30 ;` will be processed as follows: `iA[i] = i = 30;` => `iA[0] = i = 30 ;`  =>  `i = 30; iA[0] = i ;` =>  ` iA[0] = 30 ;`
* `return (long) by/d*3;` returns a double (here, by is a byte, d a double) because cast has higher precedence. (Of course still below the . operator, or [] or () for that matter.)

Here is what JLS says on this:
1 Evaluate Left-Hand Operand First  
2 Evaluate Operands before Operation  
3 Evaluation Respects Parentheses and Precedence  
4 Argument Lists are Evaluated Left-to-Right 


#### Inheritance and overriding

* Only instance methods may be overriden. The rest (static methods, sttc and inst fields) may only be hidden.
* Given Interface/Implementer situation : `Movable m = new Donkey(); System.out.println(m.location)` : 
* 1. It compiles only if Movable has a (public, static and final) location field
* 2. It will only show the content of Movable's location field content, even if hidden
* Overriding method can declare any _unchecked_ exception (RuntimeException, Error, and subclasses such as NullPointerException, ArithmeticException etc) in throws clause even if overridden method doesn't declare a throws clause.
* Subclass constructor : "[...] the compiler will automatically add super() as the first line [...]" CAREFUL that super() is accessible from the subclass !! If not, won't compile.
* Which variable (or static method) will be used depends on the class that the variable is declared of.


#### Exceptions

* From Java official tutorial : If a client (ie the one who calls the method) can reasonably be expected to recover from an exception, make it a checked exception. If a client cannot do anything to recover from the exception, make it an unchecked exception. 
* Errors are never thrown by the programmer explicitly. They are thrown by the JVM automatically upon encountering serious issues (ex : OutOfMemoryError or StackOverflowError). They do not necessarily indicate a programming bug.
* RuntimeExceptions such as NullPointerException, IndexOutOfBoundsException indicate that there is a coding error in the program. Ideally, instead of catching the exception, code should be fixed.
* A NullPointerException will be thrown if the expression given to the throw statement results in a null pointer.
* "You are throwing a 'checked' exception and there is no try or catch block, or a throws clause. So it will not compile."
* redundant throws clause ? - Compiles and runs fine. (`git checkout exceptions`)


#### Try Catch Finally

* If each of try, catch and finally blocks (of same try-catch) contains return statement, the value from the finally return statement is the one returned. (TODO - will all 3 return statement be evaluated or only the definitive one ?)
* In a similar fashion, an exception thrown in finally will also be the one sent to the caller even if the try has also sent an uncaught exception.
* finally block is always executed (even if exception thrown in try or catch; or one of those returns) . But calling `System.exit(int)` method exits the JVM and might prevent the execution of finally block.
* Multiple catch blocks : "The IndexOutOfBoundsException is handled by the first catch block. Inside this block, a new NullPointerException is thrown. As this exception is not thrown inside the try block, it will not be caught by any of the remaining catch blocks. It will actually be sent to the caller of the main() method after the finally block is executed. (Hence '4' in the output.)"
* Order of the exception classes listed in a multi-catch block (since Java 7) is not important. The only requirement is that they must not have a ancestor/successor relationship.
* If the exceptions are unrelated then the order of the catch blocks doesn't matter. Otherwise, catch block for more specific exception (i.e. subclass) must appear before less specific exception (i.e. super class).


#### Java 7 's "try with resources"

* In Java 7, "try with resources" statement was introduced, which does not require catch or finally blocks. (Else at least one of those is required. (TODO - check this)) 
* catch and finally blocks are executed after the resources are closed.
* "Any object that implements java.lang.AutoCloseable, which includes all objects which implement java.io.Closeable, can be used as a resource."
* It is possible to put some already declared final or effectively final variable (of a type implementing Autocloseable) along with other resource definitions (inside the parenthesis following "try"). 
* "The auto-closeable variables defined in the try-with-resources statement are implicitly final. Thus, they cannot be reassigned." (Enthuware - TODO check this)


#### Lambdas, double-colon operator

* `(ArrayList al) -> al.isEmpty()` is not accepted where `Predicate<List>` is expected.
* `dList.forEach(x->{ x = x + 10; });` doesn't change the elements (of type Double) of dList. (ie - Same behavior as with regular method calls...)
* `slist.forEach(Student::debug);` is a valid way to call non-static (accessible) `void debug()` method on each Student object in slist.
* "However, a lambda expression does not create a new scope for variables. Therefore, you cannot reuse the local variable names that have already been used in the enclosing method to declare the variables in your lambda expression. It would be like declaring the same variable twice."


#### Functional interfaces

* "The interface may have other default or static methods as well but those are not relevant. All that is required is that it must have exactly one abstract method." (And yes, it must be an interface.)



#### Streams and parallel streams

* "Note that even if the limit(9) statement was changed to limit(10), the program could still hang, since the JVM might not allocate 10 threads to the parallel stream." \[OCP - page 564\]


#### Modules

* A standard module may export a non-standard package but that export must be qualified.
* No circular dependencies in modules.
* The command `javac --module-source-path c:\java\a -d c:\java\b -p c:\java\c -m x.y` will create class files under c:\java\b\x.y
* "Each required module must be specified on a separate requires clause."


#### Command-line tools

* The jmod tool is used to package module files into jmod archives.
* The javap tool is used to inspect a class file to see its fields and methods.
* The jdeps tool is used to find out all dependencies of a class file or a jar file. It inspects the given class file (or all class files inside a jar files) and finds out all the required modules and packages that are referred to by this class or jar file.  You can add module jars and other jars in its search path using --module-path and --classpath options.
* While `jdeps --module-path out out\moduleA\test\A.class` is correct, `jdeps --module-path out moduleA\test.A` isn't because jdeps needs the path to the file (class file or a jar) that you want to inspect.


#### `import` 

* Assuming `System.out.println(logger.getMessages().isEmpty());` is referencing logger of imported public class Logger, which defines public method `ArrayList getMessages()`, _no need to import ArrayList, even if using method ArrayList::isEmpty_, importing Logger suffices.
* Wildcards do not look at subdirectories.


#### `var`

* Considering `var i[ ] = new int[2] {1, 2} ;` : 1.Not compiling because var is not allowed as an element type of an array. 2. Not compiling because if you give the elements explicitely, you can't specify the size at the same time.
* You cannot declare multiple variables in the same statement using var.


#### Java 5 vs Java 8 vs Java 9 vs Java 11 etc.

* However, Java 9 onwards, an interface is allowed to have private methods. It still cannot have protected methods though.
* Java 11 has a new feature called the "Single-File Source-Code program". This feature allows executing your Java source code directly using the java interpreter. The source code is compiled in memory and then executed by the interpreter. The limitation is that all the classes have to be defined in the same file. The class to be executed is the first top-level class found in the source file. It must contain a declaration of the standard main method.


#### Unreachable statements

* `while (false) { x=3; }` is a compile-time error because the statement x=3; is not reachable; `do { x=3; } while (false);` is fine though
* Similarly, `for( int i = 0; false; i++) x = 3;` is also a compile time error because x= 3 is unreachable.  
* `if(false){ x=3; }`, JLS says : this as an exception to the rule. (allowing `if (DEBUG) { ...}`. In this case, setting DEBUG to false will make the compiler optimize by removing the whole block from the class file generated.)


#### `java.io.Serializable`

* Is an example of "marker" interface.


#### Serialization and serialVersionUID

* "By changing the serialVersionUID of the class, we modified its version/state. As a result, no compatible classes were found during deserialization, and an InvalidClassException was thrown." (https://www.baeldung.com/java-serial-version-uid)
* `public class InvalidClassException extends ObjectStreamException` - Thrown when the Serialization runtime detects one of the following problems with a Class.
    - The serial version of the class does not match that of the class descriptor read from the stream
    - The class contains unknown datatypes
    - The class does not have an accessible no-arg constructor. (https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/InvalidClassException.html)
* ...thrown by `public final Object readObject() throws IOException, ClassNotFoundException` of `java.io.ObjectInputStream` when "Something is wrong with a class used by serialization." ([Oracle docs](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/ObjectInputStream.html#readObject()))
* "However, it is highly recommended that each class declares its serialVersionUID as the generated one is compiler dependent and thus may result in unexpected InvalidClassExceptions" ([baeldung](https://www.baeldung.com/java-serialization))
* If a class is serializable _and_ a subclass of a non-serializable one with _no no-arg constructor_, what happens during serialization - deserialization ?

#### Localization

* `Locale locale = Locale.getDefault(); System.out.println(locale);`


#### `volatile`

* "Since you are setting this variable from a different thread e.g. main thread, it's important to mark this variable volatile, otherwise, it's possible for the running thread to cache its value and never check back to main memory for updated value and running infinitely." (https://www.java67.com/2015/07/how-to-stop-thread-in-java-example.html)

    
#### miscellaneous

* You can have a method and a field with the same name in a class.
* `getClass()` is a public instance method in Object class. That means it is polymorphic. In other words, this method is bound at run time.
* `String s = 'a';` WON'T COMPILE
* Unreachable statements prevent code from compiling. Numerous cases possible. For example statement after try-catch-finally where either try or catch returns, _for sure_. 
* "JDK 11 does not include a separate JRE."
* "All compound assignment operators internally do an explicit cast." (talking about += etc.)