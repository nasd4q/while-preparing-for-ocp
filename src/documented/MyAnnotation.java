package documented;



@java.lang.annotation.Documented
@interface MyAnnotation {
    int theInt();
}

@interface MyNonDocumentedAnnotation {
    int value();
    String name() default "John";
}