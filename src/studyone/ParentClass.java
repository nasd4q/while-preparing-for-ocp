/*
    Here we define a parent class that is non serializable, and has no no-args constructor
 */

package studyone;

import java.io.Serializable;

//public class ParentClass implements Serializable {
public class ParentClass {

    public String name = "parent's class name";

    public ParentClass(String s) {
        name = s;
    }
    // /*
    public ParentClass() {

    }
    // */
}
