package Tee;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
    private Condition condition = lock.newCondition();

    public void readStandardInput() {
        lock.lock();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write a filename");
        filename = scanner.nextLine();
        System.out.println("Write something to save in the file");
        text = scanner.nextLine();
        condition.signalAll();
        lock.unlock();
    }

    public void writeStandardOutput() {
        lock.lock();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(getText());
        }
        lock.unlock();
    }

    public void writeToFile() throws IOException {
        lock.lock();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename + ".md"));
        bufferedWriter.write(getText());
        bufferedWriter.close();
        lock.unlock();
    }
}