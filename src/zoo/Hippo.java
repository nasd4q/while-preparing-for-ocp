/*
    compile : javac -cp lib/commons-lang3-3.10.jar -d out src/zoo/Hippo.java
    run : java -cp lib/commons-lang3-3.10.jar src/zoo/Hippo.java
    run compiled version : java -cp lib/commons-lang3-3.10.jar:out/ zoo.Hippo
 */

package zoo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Hippo {

    private String name;
    private int weight;

    public Hippo(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    public static void main(String ... args) {
        Hippo h1  = new Hippo("Albert", 100);
        Hippo h1b = new Hippo("Albert", 100);
        Hippo h2 = new Hippo("albert", 100);
        Hippo h1c = new Hippo("Albert", Integer.valueOf(100));
        Hippo h3 = new Hippo("Albert", 99);

        System.out.println(h1.equals(h1));
        System.out.println(h1.equals(h1b));
        System.out.println(h1.equals(h1c)); //all true

        System.out.println(h1.equals(h2));
        System.out.println(h1.equals(h3));
        System.out.println(h1.equals(null)); //all false

    }
}
