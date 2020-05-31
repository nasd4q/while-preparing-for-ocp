package studyone;

import java.io.IOException;
import java.io.Serializable;

public class SerializableSubclass extends ParentClass implements Serializable {
    public String forename = "Subclass's forename";

    public SerializableSubclass(String s) {
        super(s);
    }

    // /*
    private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
        System.out.println("ssc readObject() method for custom deserialization called");
        stream.defaultReadObject();
        this.name = (String) stream.readObject();
    }

    private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
        System.out.println("ssc writeObject() method for custom serialization called");
        stream.defaultWriteObject();
        stream.writeObject(this.name);
    }
    // */
}
