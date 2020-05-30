* review System.console Console class and java.io.File class and all their respective methods


* what about serialization of collections ?


* practice deques etc.


* practice concurrency and concurrency collections (concurrentskiplistset / map and so on)


* (final i) -> sout(i); ok as a lambda ?


* variables that can be used in local inner class are the same than those that can be used in a lambda ?
	that is everything which is visible plus final local vars - excluding non effectively final locals ?


try (InputStream is = new FileInputStream("source-data.txt"); 
OutputStream out = new FileOutputStream("output-data.txt")) { // Copy stream data to file 
Files.copy(is, Paths.get("c:\\mammals\\wolf.txt")); 
// Copy file data to stream 
Files.copy(Paths.get("c:\\fish\\clown.xsl"), out); 
} catch (IOException e) { 
// Handle file I/O exception... } 

* the streams creation - can throw exceptions ?
* if they do throw some IO Exception, those will be catched or transmitted to caller? 
study which exceptions are catched and how with try with resources.



* Files.lines(Path) can modify the file itself ?? (I guess not). ReadAllLines() ? (I’d say neither)
  Does those method « block » access to the file?


Path path = ____________________; 
if(Files.isDirectory(path))  
System.out.println(Files.deleteIfExists(path) ? "Success": "Try Again"); 
* ~~QUESTION : if path refers to a symbolic link, deleteIfExists follows it or not ?~~
    "In this case, the symbolic link, not the directory, would be deleted" \[OCP - page 568\]
* ~~QUESTION 2 : deleteIfExists throws checked exception right?~~
    
    "the method deleteIfExists() would throw a DirectoryNotEmptyException if it had contents"
    
    java.lang.Object>java.lang.Throwable>
    java.lang.Exception>java.io.IOException>
    java.nio.file.FileSystemException>java.nio.file.DirectoryNotEmptyException


* relativePath.resolve(relativePath) - what happens ?

* aPath.normalize() ? does it always output absolute path ?? (i guess not)

* Establish truth about wether `Paths.getPath("ocelot.txt")` compiles and runs fine or not


* "DriverManager knows that a JAR is a driver because it contains a file called java.sql.Driver in the directory META-INF/services" \[OCP - page 516 \]  
    ---> What it that META-INF/ directory ??? 