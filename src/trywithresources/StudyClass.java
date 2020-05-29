/*
    try-with-resources and exception handling.

    Case 1 : Exception thrown while creating resource, ie inside (...) of try (...) {}
        -> either will be thrown to caller or can be caught in subsequent catch blocks
        -> try block not executed and close() not called
        -> But close() still get called for other resources that indeed got created before !

        if constructor throws checked exception then as usual : catch it or redeclare it
        idem for if close() method of resource throws checked exception : catch or redeclare

    Case 2 : Everything goes smoothly until resources get closed and then one of them throws Exception
        -> the first (resources are closed in reverse order !) get caught, subsequent ones are suppressed
        -> even if one resource throws uncaught runtime exception, then other resources still get closed
            before program terminates with "Exception in thread "main"..."
            ... AND finally clause still get executed so any RuntimeException thrown there
                will be rethrown instead of the Exception inside the close() method.........


    Side notes - try and catch requires to be followed by {}
               - var and regular type declaring can be mixed together while declaring and
                 creating resources
               - if one catch block throws an exception it wont be caught by subsequent catch blocks of
                 same try-catch section (so either catch or declare in throws clause...)
 */

package trywithresources;

import java.io.IOException;
import java.sql.SQLDataException;

class ResourceClosingGracefully implements AutoCloseable{

    public ResourceClosingGracefully(boolean throwIt) {
        if (throwIt)
            throw new RuntimeException("constructing gracefully closing resource");
    }

    public ResourceClosingGracefully(int numbersOfException) throws Exception{
        if (numbersOfException>0)
            throw new Exception("constructing");
    }

    @Override
    public void close() {
        System.out.println("Closing gracefully");
    }
}

class ResourceThrowingREWhenClosing implements AutoCloseable{

    public ResourceThrowingREWhenClosing(boolean throwIt) {
        if (throwIt)
            throw new RuntimeException("constructing resource which throws re while closing");
    }

    public ResourceThrowingREWhenClosing(int numbersOfException) throws Exception{
        if (numbersOfException>0)
            throw new Exception("constructing");
    }

    @Override
    public void close() {
        System.out.println("Closing and throwing new RE...");
        throw new RuntimeException("Closing called");
    }
}

class ResourceThrowingIOExceptionWhenClosing implements AutoCloseable{

    public ResourceThrowingIOExceptionWhenClosing(boolean throwIt) {
        if (throwIt)
            throw new RuntimeException("constructing resource which throws IOException while closing");
    }

    public ResourceThrowingIOExceptionWhenClosing(int numbersOfException) throws Exception{
        if (numbersOfException>0)
            throw new Exception("constructing");
    }

    @Override
    public void close() throws IOException {
        System.out.println("Closing and throwing new IOException...");
        throw new IOException();
    }
}

class ExceptionPrinter{
    public static void print(Exception e){
        System.out.println("Exception caught of class :" + e.getClass() );

        e.printStackTrace();

        System.out.println("# of suppressed exceptions : " + e.getSuppressed().length);

        for (Throwable throwable : e.getSuppressed()) {
            throwable.printStackTrace();
        }
    }
}

class StudyForCase1 {
    public static void main(String[] args) {
        try (ResourceClosingGracefully r = new ResourceClosingGracefully(true)) {
        }
    } // Exception in thread "main" java.lang.RuntimeException: constructing gracefully closing r
}

class StudyForCase1Bis {
    public static void main(String[] args) {
        try (ResourceClosingGracefully r = new ResourceClosingGracefully(true)) {
        } catch (Exception e) {
            ExceptionPrinter.print(e);
        }
    } // Idem but the RuntimeException caught. No suppressed exceptions.
}

class StudyForCase1Ter {
    public static void main(String[] args) {
        try (var r = new ResourceThrowingIOExceptionWhenClosing(true)) {
            throw new SQLDataException();
        } catch (RuntimeException | IOException | SQLDataException e) {
            ExceptionPrinter.print(e);
        }
    } // Idem but the RuntimeException caught. No suppressed exceptions.
}

class StudyForCase1Quattro {
    public static void main(String[] args) {
        try (   ResourceThrowingREWhenClosing r = new ResourceThrowingREWhenClosing(false);
                var r2 = new ResourceThrowingIOExceptionWhenClosing(true)) {
            throw new SQLDataException();
        } catch (RuntimeException | IOException | SQLDataException e) {
            ExceptionPrinter.print(e);
        }
    } // Though failed right at the construction of the 2nd resource, the fir
}

class StudyForCase1Cinco {
    public static void main(String[] args) {
        try (  var r2 = new ResourceThrowingIOExceptionWhenClosing(true);
                ResourceThrowingREWhenClosing r = new ResourceThrowingREWhenClosing(false)) {
            throw new SQLDataException();
        } catch (RuntimeException | IOException | SQLDataException e) {
            ExceptionPrinter.print(e);
        }
    } // failed while creating first resource so no close() methods were called here.
      // and no suppressed nothing, only one simple RuntimeException thrown from var r2 initialization
}

class StudyForCase1Six {
    public static void main(String[] args) {
        try (  var r2 = new ResourceThrowingIOExceptionWhenClosing(1)) {
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // IOException necessary even with other constructor
      // But this constructor throws checked exception so have to add other catch block
}

class StudyForCase2 {
    public static void main(String[] args) {
        try ( var r1 = new ResourceThrowingREWhenClosing(false);
              var r2 = new ResourceThrowingIOExceptionWhenClosing(false)) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // IOException caught and printed. With one suppressed RuntimeException
}

class StudyForCase2Bis {
    public static void main(String[] args) {
        try ( var r1 = new ResourceThrowingREWhenClosing(false))
        {} // WONT COMPILE WITHOUT THIS LINE !
    } // Nothing caught, runtime exception from close() method rethrown
}

class StudyForCase2Ter {
    public static void main(String[] args) {
        try (  var r = new ResourceClosingGracefully(false);
                var r1 = new ResourceThrowingREWhenClosing(false))
        {} // WONT COMPILE WITHOUT THIS LINE !
        finally {
            System.out.println("ok");
        }
    } //Closing and throwing new RE...
      //Closing gracefully
      //ok
      // then "Exception in thread "main" java.lang.RuntimeException: Closing called"
}

class StudyForCase2Quattro {
    public static void main(String[] args) {
        try (  var r = new ResourceClosingGracefully(false);
               var r1 = new ResourceThrowingREWhenClosing(false))
        {} // WONT COMPILE WITHOUT THIS LINE !
        finally {
            System.out.println("ok");
            throw new RuntimeException("Haha");
        }
    } //Closing and throwing new RE...
    //Closing gracefully
    //ok
    // then "Exception in thread "main" java.lang.RuntimeException: Haha"
}
