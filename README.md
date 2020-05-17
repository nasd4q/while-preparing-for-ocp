Assuming presence of lib folder containing commons-lang3-3.10.jar library :


* compile with 
`javac -cp lib/commons-lang3-3.10.jar -d out src/zoo/Hippo2.java`

* run with 
`java -cp lib/commons-lang3-3.10.jar:out zoo.Hippo2`
(or `java -cp lib/commons-lang3-3.10.jar:out zoo/Hippo2`)