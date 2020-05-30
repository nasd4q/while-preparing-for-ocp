/*
    Confirms that the presence of @java.lang.annotation.Documented over an annotation's declaration
    determines the visibility of the annotation in the JavaDoc of annotated elements.

    -> anotherpackage.MyDefaultModifiedAnnotation is declared without modifier (package-private) so
       can't be accessed from here.
    -> an annotation can only be public or package-private (like any top-level type)
 */

package documented;

@MyAnnotation(theInt = 3)
class TestClass {
}
