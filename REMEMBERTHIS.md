#### `instanceof` operator (see `git checkout instanceof`)

* `null instanceof Object` ---> `false`
* `objOfClassA instanceof ClassB` won't compile if neither class extends the other.
* `objOfClassA instanceof InterfaceB` always compile. Unless ClassA final and not implementing InterfaceB.

#### String, StringBuilder, CharSequence etc.

* String does override equals(), StringBuilder does not and inherits that of Object.
* A method of StringBuilder : public StringBuilder delete(int start, int end)

#### `equals()`

* Contract stipulates : reflexive, symmetric, transitive, consistent, and returns false for null.
* So shouldn't throw a NullPointerException in case null is the arg

Careful : symmetry and transitivity requirements prevent from subclassing and overriding equals so that equals reflects a finer-grained relationship :
Either respect the contract, or don't override equals in a meaningful way.

#### Enums

* `if ( Season.SUMMER == 2) {}` // DOES NOT COMPILE
* `public enum ExtendedSeason extends Season { }` // DOES NOT COMPILE
* While `Season s1 = Season.valueOf("SUMMER");` compiles, `Season s2 = Season.valueOf("summer");` throws a java.lang.IllegalArgumentException
* Every enum constant is always implicitly public static final.
* Constructor is either private or package-private
* Enum implicitely extends java.lang.Enum class. But enum may implement an interface. 
* Can also have abstract method overriden for each (enum constant) value. (`git checkout AbsMethInEnum`)
* `switch (season) { case WINTER: ...` OK 
* `switch (season) { case Season.WINTER: ...` DOESNT COMPILE, `switch (season) { case 0: ...` DOESNT COMPILE EITHER

#### Constructors and initialization

* Unlike methods, a constructor cannot be abstract, static, final, native, or synchronized.
* If there are final fields uninitialized, they must be initialized in constructor, and can't be used before (-- unlike non final unitialized fields, right?)
* `One o = null;` won't initialize class One and won't cause class One static blocks to be executed.
* `Two t = new Two();` will cause Class Two (and superclass and so on) to be initialized (after superclass).
* `System.out;println(Sub.ID);`, where ID is a static field on a superclass of Sub, isn't sufficient to cause initialization of Sub class. (It should cause initialization of the class declaring the referenced static field ID though.)


#### `switch`

* Every statement in a case block must belong to a case or to the default label.
* No two case labels with same value
* Can you use var inside case labels ?? empty switch block is valid ?

#### Operator precedence

* Though `b2 = b1 != b2` is valid, `b2 != b1 = !b2` doesn't compile (the = operator has least precedence of all operators)
* `==` has less precedence than `>` so `true == 2 > 10` is same as `true == (2 > 10)`

#### Inheritance and overriding

* Only instance methods may be overriden. The rest (static methods, sttc and inst fields) may only be hidden.
* Given Interface/Implementer situation : `Movable m = new Donkey(); System.out.println(m.location)` : 
* 1. It compiles only if Movable has a (public, static and final) location field
* 2. It will only show the content of Movable's location field content, even if hidden
* Overriding method can declare any RuntimeException (such as NullPointerException) in throws clause even if overriden method doesn't declare a throws clause. (TODO - Check it)

#### Collections

* Map and Collection are unrelated interfaces, Map::values (non-static) returns a Collection
* `public interface Collection<E> extends Iterable<E>` has `boolean add(E e)` method.
* `add` ensures that the specified collection contains the passed element. Returns true if collection changed as a result, false otherwise (in case of a duplicate not allowed by the collection).
* If a collection refuses to add the element for any particular reason other than it already contains it (example : `null`), it _must_ throw an exception. 

#### Try Catch Finally

* If each of try, catch and finally blocks (of same try-catch) contains return statement, the finally return statement is the one executed.
* finally block is always executed (even if exception thrown in try or catch) . But calling `System.exit(int)` method exits the JVM and might prevent the execution of finally block.

#### Lambdas

* `(ArrayList al) -> al.isEmpty()` is not accepted where `Predicate<List>` is expected.

#### Primitive data types

* `43e1` is a double. `float f = 0x0123;`, `var f = 4f;` are valid declarations and initializations of floats.

#### Modules

* A standard module may export a non-standard package but that export must be qualified.

#### Command-line tools

* The jmod tool is used to package module files into jmod archives.
* The javap tool is used to inspect a class file to see its fields and methods.
* The jdeps tool is used to find out all dependencies of a class file or a jar file. It inspects the given class file (or all class files inside a jar files) and finds out all the required modules and packages that are referred to by this class or jar file.  You can add module jars and other jars in its search path using --module-path and --classpath options.
* While `jdeps --module-path out out\moduleA\test\A.class` is correct, `jdeps --module-path out moduleA\test.A` isn't because jdeps needs the path to the file (class file or a jar) that you want to inspect.

#### Exceptions

* From Java official tutorial : If a client (ie the one who calls the method) can reasonably be expected to recover from an exception, make it a checked exception. If a client cannot do anything to recover from the exception, make it an unchecked exception. 
* Errors are never thrown by the programmer explicitly. They are thrown by the JVM automatically upon encountering serious issues (ex : OutOfMemoryError or StackOverflowError). They do not necessarily indicate a programming bug.
* RuntimeExceptions such as NullPointerException, IndexOutOfBoundsException indicate that there is a coding error in the program. Ideally, instead of catching the exception, code should be fixed.