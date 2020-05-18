/*
    Possible to extend on the spot an instance of a class just constructed, and no problem using a constructor with
    params for this...

    THE CLASS IN QUESTION MUST NOT BE FINAL THOUGH ... !
 */

package constructorwparamforanon;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class AClassWithParams{
    int param1;
    String mssg1;

    public AClassWithParams(int param1, String mssg1) {
        this.param1 = param1;
        this.mssg1 = mssg1;
    }

    public String getMssg1() {
        return mssg1;
    }
}

public class TestClass {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        final AClassWithParams aClass = new AClassWithParams(10, "hello there") {
            @Override
            public String getMssg1() {
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < this.param1; i++) {
                    result.append(super.getMssg1() + " * ");
                    result.reverse();
                }
                return result.toString();
            }

            private String getMssgOriginal() {
                return super.getMssg1();
            }
        };

        System.out.println(aClass.getClass());
        System.out.println(aClass.getMssg1());
        for (Method declaredMethod : aClass.getClass().getDeclaredMethods()) {
            System.out.println(declaredMethod);
            System.out.println("invoking...");
            System.out.println(declaredMethod.invoke(aClass));
        }
    }
}
