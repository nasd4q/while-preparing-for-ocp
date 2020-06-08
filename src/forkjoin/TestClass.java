package forkjoin;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class SynchronizedFileWriter{
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void writeln(String message) {
        lock.writeLock().lock();
        try (final BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("playground/forkjoin.txt",true));){
            bufferedWriter.write(message);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
}

public class TestClass {
    static SynchronizedFileWriter sfw = new SynchronizedFileWriter();

    private static List<CharSequence> getList(int maxLines) {
        class ListGetter extends RecursiveTask<List<CharSequence>> {
            int noOfLines;
            public ListGetter(int noOfLines) {
                System.out.println("new listGetter of " + noOfLines);
                this.noOfLines = noOfLines;
            }
            @Override
            protected List<CharSequence> compute() {
                if (noOfLines == 0) {
                    return Collections.EMPTY_LIST;
                } else if (noOfLines == 1) {
                    StringBuilder sb = new StringBuilder();
                    final int size = ThreadLocalRandom.current().nextInt(100);
                    for (int i = 0; i < size; i++) {
                        if (ThreadLocalRandom.current().nextInt(0,10) == 9) {
                            sb.append(" ");
                        }
                        sb.append(((char) ThreadLocalRandom.current().nextInt(33, 123)));
                    }
                    return new ArrayList<CharSequence>(List.of(sb.toString()));
                } else {
                    RecursiveTask<List<CharSequence>> other = new ListGetter(noOfLines -1);
                    other.fork();
                    System.out.println("computing 1 line from Thread" + Thread.currentThread().getName());
                    List<CharSequence> computed = new ListGetter(1).compute();
                    sfw.writeln(computed.get(0).toString());
                    System.out.println("Done computing 1 line from Thread" + Thread.currentThread().getName());
                    computed.addAll(other.join());
                    return computed;
                }
            }
        }

        ForkJoinTask<List<CharSequence>> task = new ListGetter(ThreadLocalRandom.current().nextInt(maxLines));
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(task);
    }

    public static void main(String[] args) {
        getList(100).forEach(System.out::println);
    }
}
