/*
    On a most basic simple enum (EnumPrinter), toString() and name() instance methods both return
        the same string : the declared name (ex: Toshiba) as was declared;
        without any reference to the declared name of the enum type (EnumPrinter)
    Furthermore, those 2 methods return Strings that are both equals and ==
    Enum<E> implements Comparable<E> and here, calling compareTo seems to have the same effect
        as computing callerEnum.ordinal() - argumentEnum.ordinal()
 */

package tostring;

public enum EnumPrinter {
    Toshiba, Canon, HP;
}


class TestClass{
    public static int counter = 45;

    public static void main(String[] args) {
        for (EnumPrinter value : EnumPrinter.values()) {
            System.out.println(value.toString());//Toshiba
            System.out.println(value);//Toshiba
            System.out.println(value.name());//Toshiba

            System.out.println(value.name()==value.toString());//true
            System.out.println(value.name().equals(value.toString()));//true

        } // etc
        System.out.println(EnumPrinter.Canon.compareTo(EnumPrinter.HP));//-1
        System.out.println(EnumPrinter.Canon.compareTo(EnumPrinter.Toshiba));//1
        System.out.println(EnumPrinter.Toshiba.compareTo(EnumPrinter.HP));//-2

        System.out.println(EnumPrinter.Toshiba.ordinal()-EnumPrinter.HP.ordinal());


    }
}
