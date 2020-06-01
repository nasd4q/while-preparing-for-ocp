#### `instanceof` operator (see `git checkout instanceof`)

* `null instanceof Object` ---> `false`
* `objOfClassA instanceof ClassB` won't compile if neither class extends the other.
* `objOfClassA instanceof InterfaceB` always compile. Unless ClassA final and not implementing InterfaceB.


#### `equals()`

* Contract stipulates : reflexive, symmetric, transitive, consistent, and returns false for null.
* So shouldn't throw a NullPointerException in case null is the arg
* "In other words, equals method of array classes returns the same result as ==."

Careful : symmetry and transitivity requirements prevent from subclassing and overriding equals so that equals reflects a finer-grained relationship :
Either respect the contract, or don't override equals in a meaningful way.


#### `switch`

* Every statement in a case block must belong to a case or to the default label.
* No two case labels with same value (even if one is defined with a final var and other with constant expr)
* Can you use vars as case labels ?? - Yes, if compile-time constants (ie final vars). Empty switch block is valid ? - With {}, yes.
* `switch (season) { case WINTER: ...` OK 
* `switch (season) { case Season.WINTER: ...` DOESNT COMPILE, `switch (season) { case 0: ...` DOESNT COMPILE EITHER


#### scopes

* "[...] there is a redeclaration of i in the first for() which is not legal."


#### Primitive data types, and boxing

* `43e1` is a double. `float f = 0x0123;`, `var f = 4f;` are valid declarations and initializations of floats.
* `float val = 3; System.out.println(val);` Since, val is of type float, 3.0 is printed (ie not 3, not 3.0f).
* `anInt == anInteger` This will return true (_in that particular case (!)_) because one operand is a primitive int, so the other will be unboxed and then the value will be compared.
* `Float.NaN` can be assigned to a float (and returned, if float is the return type).
* You cannot box an int into a Double object. Example : with `ArrayList<Double> al = new ArrayList<>();`, `al.add(111);` won't compile. (see `git checkout overloading`.)


#### Overloading, boxing-unboxing, varargs (`git checkout overloading`)

* Widening is preferred to boxing/unboxing (so that old code still works as it used to), which in turn, is preferred over var-args. 
* `probe(anInteger)` is never bound to `probe(Long l)` (different objects with no IS-A relationship). 
* It will be bound to `probe(Integer i)`, then `probe(int i)`, then `probe(long i)`, then `probe(int... iA)`.
* `probe(anInt)` is never bound to `probe(Long l)` neither. 
* It is bound to `probe(int i)`, then `probe(long l)`, then `probe(Integer i)`, `probe(int... iA)`.


#### Enums

* `if ( Season.SUMMER == 2) {}` // DOES NOT COMPILE
* `public enum ExtendedSeason extends Season { }` // DOES NOT COMPILE
* While `Season s1 = Season.valueOf("SUMMER");` compiles, `Season s2 = Season.valueOf("summer");` throws a java.lang.IllegalArgumentException
* Every enum constant is always implicitly public static final. (Yes, like fields of interfaces.)
* Constructor is either private or package-private
* Enum implicitely extends java.lang.Enum class. And implicitly implements both Serializable and Comparable interface. But enum might implement other interfaces. 
* Can also have abstract method overriden for each (enum constant) value. (`git checkout AbsMethInEnum`)
* Nested enum types are implicitly static.


#### Nested class

* Member, local and anonymous (aka "proper") inner classes cannot be static ("Member" !) nor define static fields or methods.
* Local inner classes have no access to local vars, except for those vars which are final or effectively final.
* Static "nested" classes cannot access the enclosing class instance (ie _non static_) variables.
* "A non static inner class may have static members. If you make them final." (Enthuware)

#### static members

* Case of public static field in, say, A hidden by a private static field in subclass B. Any sub-subclass C can't access the A's static field without casting the C to an A - compiler error : trying to access B's private field. (TODO - write a class to show this)
* `System.out.println(getDatabase().url)` where url is a _static_ field of getDatabase() 's return type : will print url value even when getDatabase() returns `null`


#### `final`

* Actually, String class itself is final and so all of its methods are implicitly final.


#### Access modifier

* Top-level types can only be public or package-private. No protected or private on those...


#### Generics

* TODO `<? super String>` - does it match a super _interface_? (i'd say : for sure...)
* TODO `Predicate<? super String> predicate = s -> s.startsWith("g");` // COMPILES ? 
      -----> Generics are a compile-time tool only right? so at runtime s->... just becomes another instance of Predicate.
* "var data = new ArrayList<>(); is same as var data = new ArrayList<Object>(); Therefore, data is a list of Objects." (Enthuware)

