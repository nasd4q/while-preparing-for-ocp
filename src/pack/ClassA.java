package pack;

interface InterfaceB {}

class ClassB{}

final class FinalClass{}

class SubClassOfA extends ClassA{}

public class ClassA{
    public static void main (String ... args) {
        ClassA classA = new ClassA();
        FinalClass finalClass = new FinalClass();
        ClassB classB = new ClassB();

        //System.out.println(classA instanceof ClassB); //Not compiling : "error: incompatible types: ClassA cannot be converted to ClassB"
        System.out.println(classA instanceof SubClassOfA); //false
        System.out.println(classA instanceof InterfaceB); //false
        //System.out.println(finalClass instanceof InterfaceB); //Not compiling, unless FinalClass implements InterfaceB



        int i = 90;
        Integer integer = Integer.valueOf(91);

        //System.out.println(i instanceof Integer); // Not compiling : "unexpected type - required : reference, found: int"
        System.out.println(integer instanceof Integer); //true
        //System.out.println(integer instanceof Double); //Not compiling - incompatible types


        Double d = null;
        System.out.println(d instanceof Object); //false
    }
}
