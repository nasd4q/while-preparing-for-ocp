/*
    java.io.InvalidClassException: studyone.SerializableSubclass; no valid constructor
    thrown from line containing : "final Object o = ois.readObject();"
        when ParentClass doesn't implement Serializable, and doesn't have a no-args constructor

    However, declaring ParentClass implement Serializable suffices to make everything work fine
    that is both parents and child fields get restored to their original state

    Other simple solution :  (1)  add a no-arg constructor on ParentClass
        In that case the deserialized object retrieves its right subclass "forename" field
        BUT will get the ParentClass 's "name" field set to "parent's class name"
        as per the instance initialization of ParentClass...

    AND   (2)   define these 2 methods on the serializable subclass where you take care of parent's fields:
    private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
    private void writeObject(java.io.ObjectOutputStream stream) throws IOException {

        Those permit to customize the serialization/deserialization process on a Serializable class
            (won't work with enums though... according to the official oracle docs
                see : https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/ObjectOutputStream.html#defaultWriteObject())
 */

package studyone;

import java.io.*;
import java.nio.file.Path;

public class SerializationTest {
    public static void main(String[] args) {
        SerializableSubclass ssc = new SerializableSubclass("John 5");
        ssc.forename = "modified forename 3";
        System.out.println(ssc.name); // John
        System.out.println(ssc.forename);// modified forename

        serialize(ssc); // prints out : oos just wrote Object
    }

    public static boolean serialize(Object o){
        final Path playground = Path.of("playground", "serialized.txt");
        try (final FileOutputStream fos = new FileOutputStream(playground.toFile());
             final BufferedOutputStream bos = new BufferedOutputStream(fos);
             final ObjectOutputStream oos = new ObjectOutputStream(bos))
        {
            oos.writeObject(o);
            System.out.println("oos just wrote Object");
            return true;
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Object deserialize(){
        final Path playground = Path.of("playground", "serialized.txt");
        try (final FileInputStream fos = new FileInputStream(playground.toFile());
             final BufferedInputStream bos = new BufferedInputStream(fos);
             final ObjectInputStream ois = new ObjectInputStream(bos))
        {
            final Object o = ois.readObject();
            System.out.println("ois just read an object");
            return o;
        } catch (final IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

class DeserializationTest{
    public static void main(String[] args) {
        final Object deserialize = SerializationTest.deserialize();
        SerializableSubclass ssc = (SerializableSubclass) deserialize;
        System.out.println(ssc.forename);
        System.out.println(ssc.name);
    }
}
