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

    protected String getText() {
        return text;
    }

    private String filename;
    private String text;

    private Lock lock = new ReentrantLock();
    private Condition readInput = lock.newCondition();
    private Condition writeToStandardOutput = lock.newCondition();
    private Condition writeToFile = lock.newCondition();


    public void readStandardInput() throws InterruptedException {
//        Thread.sleep(100); // makes shore the other threads have time to lock their locks
        lock.lock();
        try{
            writeToStandardOutput.await(); /* wait for StandardOutPut thread*/
            writeToFile.await(); /* wait for File thread */
            Scanner scanner = new Scanner(System.in);
            System.out.println("Write a filename");
            filename = scanner.nextLine();
            System.out.println("Write something to save in the file");
            text = scanner.nextLine();
            readInput.signalAll(); // signal to all threads that the we read the input
        } finally {
            lock.unlock();
        }
    }

    /*  feedback: remove sleep and use two other signal backs
        don't call await unconditionally, some if condition */
    public void writeStandardOutput() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("Waiting...");
            writeToStandardOutput.signalAll(); /* signal to all thread that writeToStandardOutput is waiting */
            readInput.await();
            System.out.println("Woken up! Writing to standard output, time: " + new Date().getTime());
            System.out.println(getText());
        } finally {
            lock.unlock();
        }
    }

    public void writeToFile() throws IOException, InterruptedException {
        lock.lock();
        try {
            System.out.println("Waiting...");
            writeToFile.signalAll(); /* signal to all threads that writeToFile is waiting */
            readInput.await();
            System.out.println("Woken up! Writing to file, time: " + new Date().getTime());
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename + ".md"));
            bufferedWriter.write(getText());
            bufferedWriter.close();
        }finally {
            lock.unlock();
        }
    }
}