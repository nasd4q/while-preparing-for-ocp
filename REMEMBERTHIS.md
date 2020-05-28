#### `instanceof` operator (see `git checkout instanceof`)

* `null instanceof Object` ---> `false`
* `objOfClassA instanceof ClassB` won't compile if neither class extends the other.
* `objOfClassA instanceof InterfaceB` always compile. Unless ClassA final and not implementing InterfaceB.


#### String, StringBuilder, CharSequence etc.

* String does override equals(), StringBuilder does not and inherits that of Object.
* A method of StringBuilder : `public StringBuilder delete(int start, int end)`
* StringBuilder defines : `public void ensureCapacity(int minimumCapacity)` ( If the current capacity is less than the argument, the new capacity is the larger of: the minimumCapacity argument - or - twice the old capacity, plus 2. If the minimumCapacity argument is nonpositive, this method takes no action and simply returns.)
* StringBuilder defines : `public StringBuilder(int capacity)` Constructs a string builder with no characters in it and an initial capacity specified by the capacity argument.
* There is a trim() on a String, but not on StringBuilder
* There is no reverse() method in String class. (But yes, there is one in StringBuilder (!))
* isEmpty(), isBlank(), strip() and lines() (which returns a stream of lines (Stream), sep by line terminators) can all be invoked on a String.


#### `equals()`

* Contract stipulates : reflexive, symmetric, transitive, consistent, and returns false for null.
* So shouldn't throw a NullPointerException in case null is the arg
* "In other words, equals method of array classes returns the same result as ==."

Careful : symmetry and transitivity requirements prevent from subclassing and overriding equals so that equals reflects a finer-grained relationship :
Either respect the contract, or don't override equals in a meaningful way.


#### Enums

* `if ( Season.SUMMER == 2) {}` // DOES NOT COMPILE
* `public enum ExtendedSeason extends Season { }` // DOES NOT COMPILE
* While `Season s1 = Season.valueOf("SUMMER");` compiles, `Season s2 = Season.valueOf("summer");` throws a java.lang.IllegalArgumentException
* Every enum constant is always implicitly public static final. (Yes, like fields of interfaces.)
* Constructor is either private or package-private
* Enum implicitely extends java.lang.Enum class. And implicitly implements both Serializable and Comparable interface. But enum might implement other interfaces. 
* Can also have abstract method overriden for each (enum constant) value. (`git checkout AbsMethInEnum`)
* Nested enum types are implicitly static. 


#### Constructors and initialization

* Unlike methods, a constructor cannot be abstract, static, final, native, or synchronized.
* Final variables must be explicitly initialized. In case of final fields, this can happen in instance initializer or every constructor. **In case of static final fields though**, this _must happen before_ (at time of declaration or in static initializer).
* `One o = null;` won't initialize class One and won't cause class One static blocks to be executed.
* `Two t = new Two();` will cause Class Two (and superclass and so on) to be initialized (after superclass).
* `System.out;println(Sub.ID);`, where ID is a static field on a superclass of Sub, isn't sufficient to cause initialization of Sub class. (It should cause initialization of the class declaring the referenced static field ID though.)

"First, static statements/blocks are called IN THE ORDER they are defined. 
Next, instance initializer statements/blocks are called IN THE ORDER they are defined. 
Finally, the constructor is called."


#### `switch`

* Every statement in a case block must belong to a case or to the default label.
* No two case labels with same value (even if one is defined with a final var and other with constant expr)
* Can you use vars as case labels ?? - Yes, if compile-time constants (ie final vars). Empty switch block is valid ? - With {}, yes.
* `switch (season) { case WINTER: ...` OK 
* `switch (season) { case Season.WINTER: ...` DOESNT COMPILE, `switch (season) { case 0: ...` DOESNT COMPILE EITHER


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


#### `java.util.Collection<E>`

* Map and Collection are unrelated interfaces, Map::values (non-static) returns a Collection
* `public interface Collection<E> extends Iterable<E>` has `boolean add(E e)` method.
* `add` ensures that the specified collection contains the passed element. Returns true if collection changed as a result, false otherwise (in case of a duplicate not allowed by the collection).
* If a collection refuses to add the element for any particular reason other than it already contains it (example : `null`), it _must_ throw an exception. 
* With `ArrayList<Double> al = new ArrayList<>();`, `System.out.println(al.indexOf(1.0));` and `System.out.println(al.contains("string"));` both compile fine. In fact, those methods expect an argument of type Object... 
* `java.util.Collection<E>` interface defines `boolean contains(Object o)`, not with the generic E... Same with `boolean containsAll(Collection<?> c)`. In fact, it seems like E is only used for `boolean add(E e)` and `boolean addAll(Collection<? extends E> c`) (speaking of the interface _Collection_). **And in** `Iterator<E> iterator()`.
* In `ArrayList<E>`, `E set(int index, E element)` and others (`E remove(int index)`, `E get(int index)`, `List<E> subList(int fromIndex, int toIndex)` ) making use of compile-time type-checking...
* ArrayList<E> constructor which takes Collection takes a Collection<? extends E>. So for example `List<Object> objects = new ArrayList<Object>(strings);` where `strings` is a `List<String>` compiles.
* `java.util.Collection<E>` defines `default boolean removeIf(Predicate<? super E> filter)`. (Returns true if the collection was changed in the process...) 
* `java.util.List<E>` defines `default void replaceAll(UnaryOperator<E> operator)`
* `java.util.Vector<E>` implements `java.util.List<E>`, `java.util.ArrayDeque<E>` doesn't.
* The Map interface uses put() rather than add() !
* `Arrays.asList(nullValuedArg);` throws a `java.lang.NullPointerException`.


#### `java.lang.Iterable<T>`

* `java.lang.Iterable<T>` defines `default void forEach(Consumer<? super T> action)`
* "Enhanced" for loops can be used with any Iterable.


#### `java.util.Iterator<E>` (interface)

* "...they \[iterators returned by the synchronized collections\] are fail-fast—meaning that if they detect that the collection has changed since iteration began, they throw the unchecked ConcurrentModificationException." \[Java Concurrency In Practice - Brian Goetz, page 82\]


#### `java.lang.Comparable<T>`, `java.util.Comparator<T>`

* `java.lang.String` implements `java.lang.Comparable<String>`


#### Generics

* TODO `<? super String>` - does it match a super _interface_?
* TODO `Predicate<? super String> predicate = s -> s.startsWith("g");` // COMPILES ? If yes as what?


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


#### Lambdas, double-colon operator

* `(ArrayList al) -> al.isEmpty()` is not accepted where `Predicate<List>` is expected.
* `dList.forEach(x->{ x = x + 10; });` doesn't change the elements (of type Double) of dList. (ie - Same behavior as with regular method calls...)
* `slist.forEach(Student::debug);` is a valid way to call non-static (accessible) `void debug()` method on each Student object in slist.
* "However, a lambda expression does not create a new scope for variables. Therefore, you cannot reuse the local variable names that have already been used in the enclosing method to declare the variables in your lambda expression. It would be like declaring the same variable twice."


#### Functional interfaces

* "The interface may have other default or static methods as well but those are not relevant. All that is required is that it must have exactly one abstract method." (And yes, it must be an interface.)



#### Streams and parallel streams

* "Note that even if the limit(9) statement was changed to limit(10), the program could still hang, since the JVM might not allocate 10 threads to the parallel stream." \[OCP - page 564\]


#### Primitive data types, and boxing

* `43e1` is a double. `float f = 0x0123;`, `var f = 4f;` are valid declarations and initializations of floats.
* `float val = 3; System.out.println(val);` Since, val is of type float, 3.0 is printed (ie not 3, not 3.0f).
* `anInt == anInteger` This will return true (_in that particular case (!)_) because one operand is a primitive int, so the other will be unboxed and then the value will be compared.
* `Float.NaN` can be assigned to a float (and returned, if float is the return type).
* You cannot box an int into a Double object. Example : with `ArrayList<Double> al = new ArrayList<>();`, `al.add(111);` won't compile. (see `git checkout overloading`.)


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


#### Exceptions

* From Java official tutorial : If a client (ie the one who calls the method) can reasonably be expected to recover from an exception, make it a checked exception. If a client cannot do anything to recover from the exception, make it an unchecked exception. 
* Errors are never thrown by the programmer explicitly. They are thrown by the JVM automatically upon encountering serious issues (ex : OutOfMemoryError or StackOverflowError). They do not necessarily indicate a programming bug.
* RuntimeExceptions such as NullPointerException, IndexOutOfBoundsException indicate that there is a coding error in the program. Ideally, instead of catching the exception, code should be fixed.
* A NullPointerException will be thrown if the expression given to the throw statement results in a null pointer.
* "You are throwing a 'checked' exception and there is no try or catch block, or a throws clause. So it will not compile."
* redundant throws clause ? - Compiles and runs fine. (`git checkout exceptions`)

#### `import` 

* Assuming `System.out.println(logger.getMessages().isEmpty());` is referencing logger of imported public class Logger, which defines public method `ArrayList getMessages()`, _no need to import ArrayList, even if using method ArrayList::isEmpty_, importing Logger suffices.
* Wildcards do not look at subdirectories.


#### `var`

* Considering `var i[ ] = new int[2] {1, 2} ;` : 1.Not compiling because var is not allowed as an element type of an array. 2. Not compiling because if you give the elements explicitely, you can't specify the size at the same time.
* You cannot declare multiple variables in the same statement using var.


#### Nested class

* Member, local and anonymous (aka "proper") inner classes cannot be static ("Member" !) nor define static fields or methods.
* Local inner classes have no access to local vars, except for those vars which are final or effectively final.
* Static "nested" classes cannot access the enclosing class instance (ie _non static_) variables.


#### Overloading, boxing-unboxing, varargs (`git checkout overloading`)

* Widening is preferred to boxing/unboxing (so that old code still works as it used to), which in turn, is preferred over var-args. 
* `probe(anInteger)` is never bound to `probe(Long l)` (different objects with no IS-A relationship). 
* It will be bound to `probe(Integer i)`, then `probe(int i)`, then `probe(long i)`, then `probe(int... iA)`.
* `probe(anInt)` is never bound to `probe(Long l)` neither. 
* It is bound to `probe(int i)`, then `probe(long l)`, then `probe(Integer i)`, `probe(int... iA)`.


#### Java 5 vs Java 8 vs Java 9 vs Java 11 etc.

* However, Java 9 onwards, an interface is allowed to have private methods. It still cannot have protected methods though.
* Java 11 has a new feature called the "Single-File Source-Code program". This feature allows executing your Java source code directly using the java interpreter. The source code is compiled in memory and then executed by the interpreter. The limitation is that all the classes have to be defined in the same file. The class to be executed is the first top-level class found in the source file. It must contain a declaration of the standard main method.


#### scopes

* "[...] there is a redeclaration of i in the first for() which is not legal."


#### static members

* Case of public static field in, say, A hidden by a private static field in subclass B. Any sub-subclass C can't access the A's static field without casting the C to an A - compiler error : trying to access B's private field. (TODO - write a class to show this)
* `System.out.println(getDatabase().url)` where url is a _static_ field of getDatabase() 's return type : will print url value even when getDatabase() returns `null`


#### `final`

* Actually, String class itself is final and so all of its methods are implicitly final.


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
* ...thrown by `public final Object readObject() throws IOException, ClassNotFoundException` of `java.io.ObjectInputStream` when "Something is wrong with a class used by serialization." [Oracle doc](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/ObjectInputStream.html#readObject())


#### I/O

* "The performance gain from using a Buffered class to access a low-level file stream cannot be overstated. Unless you are doing something very specialized in your application, you should always wrap a file stream with a Buffered class in practice." \[OCP - page 415\]
* Talking about `new File(oneStringArg)`, "Option C is also correct because Java will convert the slashes to the right one when working with paths" \[OCP - page 566\]
* Talking about calling `mark(int)` and `reset()`  on an instance of a [`java.io.InputStream`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/InputStream.html) (abstract class !) "Not all java.io streams support the mark() operation; therefore, without calling markSupported() on the stream, the result is unknown until runtime." \[OCP - page 568\]

#### Java 6's `java.io.Console` retrieved by invoking  `System.console()` [Oracle doc](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/Console.html)

* Singleton instance returned by `System.console()`, `System.console()` may return `null` in case no I/O console available.
* method `Console format​(String fmt, Object... args)` "Writes a formatted string to this console's output stream using the specified format string and arguments."
* method `Console printf​(String format, Object... args)` "A convenience method to write a formatted string to this console's output stream using the specified format string and arguments."
* ... but no "print" or "println" methods ! (Use `PrintWriter writer()`	which "retrieves the unique PrintWriter object associated with this console".)


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




##### Sources

\[OCP\] : OCP Java SE 8 Programmer II - Study Guide (1Z0-809) - Jeanne Boyarsky and Scott Selikoff