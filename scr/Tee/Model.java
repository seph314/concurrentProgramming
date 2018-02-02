package Tee;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Model {

    // getter, är inte alltis sparat
    public String getText() {
        return text;
    }

    private String filename;
    private String text;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition(); // you get the condition object from the lock you are locking on

    public void readStandardInput() throws InterruptedException {
        Thread.sleep(100); // makes shore the other threads have time to lock their locks
        lock.lock();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write a filename");
        filename = scanner.nextLine();
        System.out.println("Write something to save in the file");
        text = scanner.nextLine();
        condition.signalAll();
        lock.unlock();
    }


    public void writeStandardOutput() throws InterruptedException {

        lock.lock();
        System.out.println("Waiting...");
        condition.await();
        System.out.println("Woken up! Writing to standard output, time: " + new Date().getTime());
        try {
            System.out.println(getText());
        } finally {
            lock.unlock();
        }

    }

    public void writeToFile() throws IOException, InterruptedException {
        lock.lock();
        System.out.println("Waiting...");
        condition.await();
        System.out.println("Woken up! Writing to file, time: " + new Date().getTime());
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename + ".md"));
            bufferedWriter.write(getText());
            bufferedWriter.close();
        }finally {
            lock.unlock();
        }
    }
}







//package Tee;
//
//        import java.io.BufferedWriter;
//        import java.io.FileWriter;
//        import java.io.IOException;
//        import java.util.Date;
//        import java.util.Scanner;
//        import java.util.concurrent.locks.Condition;
//        import java.util.concurrent.locks.Lock;
//        import java.util.concurrent.locks.ReentrantLock;
//
//
//public class Model {
//
//    // getter, är inte alltis sparat
//    public String getText() {
//        return text;
//    }
//
//    private String filename;
//    private String text;
//
//    private Lock lock = new ReentrantLock();
//    private Condition condition = lock.newCondition(); // you get the condition object from the lock you are locking on
//
//    public void readStandardInput() throws InterruptedException {
//        Thread.sleep(100); // makes shore the other threads have time to lock their locks
//        lock.lock();
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Write a filename");
//        filename = scanner.nextLine();
//        System.out.println("Write something to save in the file");
//        text = scanner.nextLine();
//        condition.signalAll();
//        lock.unlock();
//    }
//
//    public void writeStandardOutput() throws InterruptedException {
//        lock.lock();
//        System.out.println("Waiting...");
//        condition.await();
//        System.out.println("Woken up!" + new Date().getTime());
//        try {
//            System.out.println(getText());
//        } finally {
//            lock.unlock();
//        }
//
//    }
//
//    public void writeToFile() throws IOException, InterruptedException {
//        lock.lock();
//        System.out.println("Waiting...");
//        condition.await();
//        System.out.println("Woken up!" + new Date().getTime());
//        try{
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename + ".md"));
//            bufferedWriter.write(getText());
//            bufferedWriter.close();
//        }finally {
//            lock.unlock();
//        }
//    }
//}