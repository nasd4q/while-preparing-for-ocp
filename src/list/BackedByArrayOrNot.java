/*
    studies Collection::removeIf() instance method on various Lists...

    java.util.Arrays : public static <T> java.util.List<T> asList(@NotNull T... a)
       "Returns a fixed-size list backed by the specified array. Changes made to the array will be visible
        in the returned list, and changes made to the list will be visible in the array. The returned list
        is Serializable and implements RandomAccess.
        The returned list implements the optional Collection methods, except those that would change the size
        of the returned list. Those methods leave the list unchanged and throw UnsupportedOperationException."

        On returned List, removeIf will throw java.lang.UnsupportedOperationException ... unless
            the removeIf ends up not wanting to remove any element.


    java.util.List<E> : public static <E> List<E> of(@NotNull E e1, @NotNull E e2, @NotNull E e3, etc.)

        On returned List, removeIf will throw UnsupportedOperationException.
            even if the removeIf predicate always return false, ie the List wouldn't have been changed anyway.



    java.util.Collection<E> : public boolean removeIf(@NotNull java.util.function.Predicate<? super E> filter)
        "returns true if any elements were removed"
 */

package list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Data {
    int value;
    Data(int value) { this.value = value; }
    public String toString() { return "" + value; }
}

public class BackedByArrayOrNot {

    public static void main(String[] args) {
        Data[] dataArr = new Data[]{new Data(1), new Data(2), new Data(3), new Data(4)};

        List<Data> asListList = Arrays.asList(dataArr); //1

        List<Data> listOfList = List.of(new Data(1), new Data(2), new Data(3), new Data(4));

        //List<Data> arrayListList = new ArrayList<Data>(dataArr); // DOES NOT COMPILE
        //  Error:(22, 36) java: no suitable constructor found for ArrayList(list.Data[])
        //      [...]
        //      list.Data[] cannot be converted to java.util.Collection<? extends list.Data>

        List<Data> arrayListList = new ArrayList<Data>(asListList);
        List<Data> arrayListList2 = new ArrayList<Data>(listOfList);

        //asListList.removeIf((Data d) -> { return d.value % 2 == 0; }); // java.lang.UnsupportedOperationException
        //listOfList.removeIf((Data d) -> { return d.value % 2 == 0; }); // java.lang.UnsupportedOperationException

        asListList.removeIf((Data d) -> { return false; }); // this is fine though
        asListList.removeIf((Data d) -> { return d.value > 4; }); // still fine

        //listOfList.removeIf((Data d) -> { return false; }); // still throws UnsupportedOperationException !!

        arrayListList.removeIf((Data d) -> { return d.value % 2 == 0; });
        System.out.println(arrayListList); // [1, 3]
        arrayListList2.removeIf((Data d) -> { return d.value % 2 == 0; });
        System.out.println(arrayListList2); // [1, 3]

        // Works also like this ...
        arrayListList2.removeIf(d -> d.value % 3 == 0);
        System.out.println(arrayListList2); // [1]


    }
}
