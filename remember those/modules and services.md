### An example from openjdk docs...

A module requires a "service" : 

```
module java.sql {
    requires public java.logging;
    requires public java.xml;
    exports java.sql;
    exports javax.sql;
    exports javax.transaction.xa;
    uses java.sql.Driver;
}
```

A service is provided by a module :

```
module com.mysql.jdbc {
    requires java.sql;
    requires org.slf4j;
    exports com.mysql.jdbc;
    provides java.sql.Driver with com.mysql.jdbc.Driver;
}
```

* "For migration purposes, if a JAR file that defines an automatic module contains META-INF/services resource entries then each such entry is treated as if it were a corresponding provides clause in a hypothetical declaration of that module. An automatic module is considered to use every available service."


Source : [openjdk migration guide](https://openjdk.java.net/projects/jigsaw/spec/sotms/#compatibility--migration) part 4)


