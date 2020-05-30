#### `java.base/java.lang.Deprecated` Annotation Type

* "The compiler generates a warning whenever a program uses a method, class, or field with the @Deprecated annotation." [Oracle docs](https://docs.oracle.com/javase/tutorial/java/annotations/predefined.html)  
"When an element is deprecated, it should also be documented using the Javadoc @deprecated tag, as shown in the following example. The use of the at sign (@) in both Javadoc comments and in annotations is not coincidental: they are related conceptually. Also, note that the Javadoc tag starts with a lowercase d and the annotation starts with an uppercase D."


#### `java.lang.annotation.Repeatable` [Oracle doc](https://docs.oracle.com/javase/tutorial/java/annotations/repeating.html)
* Step 1 : annotate annotation definition like this : `@Repeatable(Schedules.class)` (here, before `public @interface Schedule {...}`)  
"The value of the @Repeatable meta-annotation, in parentheses, is the type of the container annotation that the Java compiler generates to store repeating annotations."  
"Applying the same annotation to a declaration without first declaring it to be repeatable results in a compile-time error."
* Step 2 : define container annotation. Example : 
```
public @interface Schedules {
       Schedule[] value();
   }
```
"The containing annotation type must have a value element with an array type." (array of the repeatable annotation)