/*
    From enthuware :
    Identify correct statements about the following code:
    public class Counter<T> {  //1
        T t;

        public <T> int count(T[] ta, T t) {  //2
            this.t = t;  //3
            int count = 0;
            for (T x : ta) {
                count = x == t ? count + 1 : count;//4
            }
            return count;
        }
    }

    --> can reuse type parameter name but hides the other one
 */

package redefinition;

public class Counter<T> {  //1
    T t;

    public <T> int count(T[] ta, T t) {  //2
        //this.t = t;  //3   NOT COMPILING !!
        int count = 0;
        for (T x : ta) {
            count = x == t ? count + 1 : count;//4
        }
        return count;
    }
}





