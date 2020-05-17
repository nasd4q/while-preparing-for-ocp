#### `instanceof` operator (see `git checkout instanceof`)

* `null instanceof Object` ---> `false`
* `objOfClassA instanceof ClassB` won't compile if neither class extends the other.
* `objOfClassA instanceof InterfaceB` always compile. Unless ClassA final and not implementing InterfaceB.

#### String, StringBuilder, CharSequence etc.

* String does override equals(), StringBuilder does not and inherits that of Object.

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