#### String, StringBuilder, CharSequence etc.

* String does override equals(), StringBuilder does not and inherits that of Object.
* A method of StringBuilder : `public StringBuilder delete(int start, int end)`
* StringBuilder defines : `public void ensureCapacity(int minimumCapacity)` ( If the current capacity is less than the argument, the new capacity is the larger of: the minimumCapacity argument - or - twice the old capacity, plus 2. If the minimumCapacity argument is nonpositive, this method takes no action and simply returns.)
* StringBuilder defines : `public StringBuilder(int capacity)` Constructs a string builder with no characters in it and an initial capacity specified by the capacity argument.
* There is a trim() on a String, but not on StringBuilder
* There is no reverse() method in String class. (But yes, there is one in StringBuilder (!))
* isEmpty(), isBlank(), strip() and lines() (which returns a stream of lines (Stream), sep by line terminators) can all be invoked on a String.


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


#### I/O

* "The performance gain from using a Buffered class to access a low-level file stream cannot be overstated. Unless you are doing something very specialized in your application, you should always wrap a file stream with a Buffered class in practice." \[OCP - page 415\]
* Talking about `new File(oneStringArg)`, "Option C is also correct because Java will convert the slashes to the right one when working with paths" \[OCP - page 566\]
* Talking about calling `mark(int)` and `reset()`  on an instance of a [`java.io.InputStream`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/InputStream.html) (abstract class !) "Not all java.io streams support the mark() operation; therefore, without calling markSupported() on the stream, the result is unknown until runtime." \[OCP - page 568\]

#### Java 6's `java.io.Console` retrieved by invoking  `System.console()` ([Oracle doc](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/Console.html))

* Singleton instance returned by `System.console()`, `System.console()` may return `null` in case no I/O console available.
* method `Console format​(String fmt, Object... args)` "Writes a formatted string to this console's output stream using the specified format string and arguments."
* method `Console printf​(String format, Object... args)` "A convenience method to write a formatted string to this console's output stream using the specified format string and arguments."
* ... but no "print" or "println" methods ! (Use `PrintWriter writer()`	which "retrieves the unique PrintWriter object associated with this console".)


#### NIO.2

* Finally, we can also use the toRealPath() method to gain access to the current working directory, such as shown here: `System.out.println(Paths.get(".").toRealPath());` \[OCP - page 471\]
* "The method Files.isSameFile() first checks to see if the Path values are the same in terms of equals(). Since the first path is relative and the second path is absolute, this comparison will return false, forcing isSameFile() to check for the existence of both paths in the file system. Since we know /zoo/turkey does not exist, a NoSuchFileException is thrown" \[OCP - page 569\]
* "First, the resolve() method does not normalize any path symbols, so C and D are not correct. Second, calling resolve() with an absolute path as a parameter returns the absolute path" \[OCP - page 569\]
* "Remember, isSameFile()returns true only if the files pointed to in the file system are the same, without regard to the file contents" \[OCP - page 570\]
* "Eliminating ".." and a preceding name from a path may result in the path that locates a different file than the original path. This can arise when the preceding name is a symbolic link." (`Path normalize()` method of the `java.nio.file.Path` interface, [Oracle doc](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/file/Path.html#normalize()))
* "The normalize() method does not convert a relative path into an absolute path; therefore, the path value after the first line is just the current directory symbol." \[OCP - page 571\]
    * Yeah, about that : `Paths.get("").equals(Paths.get(".").normalize())` returns true, but not without normalize !
    * And `Paths.get(".").getName(0)` returns a path that equals Paths.get(".")


#### JDBC

* Four interfaces in the java.sql/java.sql package : Driver, Connection, Statement and ResultSet. The implementations are in the required driver JAR !
    
    
    