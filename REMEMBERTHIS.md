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

#### Constructors

* Unlike methods, a constructor cannot be abstract, static, final, native, or synchronized.
* If there are final fields uninitialized, they must be initialized in constructor, and can't be used before (-- unlike non final unitialized fields, right?)

#### `switch`

* Every statement in a case block must belong to a case or to the default label.
* No two case labels with same value
* Can you use var inside case labels ?? empty switch block is valid ?