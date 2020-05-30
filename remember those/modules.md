* "The unnamed module reads every other module. Code in any type loaded from the class path will thus be able to access the exported types of all other readable modules, which by default will include all of the named, built-in platform modules." ([openjdk doc](https://openjdk.java.net/projects/jigsaw/spec/sotms/#compatibility--migration))

* "the module system still guarantees that every named module reads at most one named module defining a given package and that named modules defining identically-named packages do not interfere with each other" ([openjdk doc](https://openjdk.java.net/projects/jigsaw/spec/sotms/#compatibility--migration) part 3.4)
* "If, however, multiple JAR files on the class path intentionally contain types in the same package then on the class path they must remain." ([openjdk doc](https://openjdk.java.net/projects/jigsaw/spec/sotms/#compatibility--migration) part 3.4)

