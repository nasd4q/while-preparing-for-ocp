/*
 * Now Using Apache Commons Lang ToStringBuilder
 */
package zoo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Hippo2 {
    private String name;
    private double weight;

    public Hippo2(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(
                this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static void main(String[] args) {
        Hippo2 h1 = new Hippo2("Harry", 3100);
        System.out.println(h1); // Hippo2[name=Harry,weight=3100.0]
    }
}