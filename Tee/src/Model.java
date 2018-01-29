import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Model {

    private String text;

    Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void readStandardInput(){
        lock.lock();
        Scanner scanner = new Scanner(System.in);
        text = scanner.nextLine();
        condition.signalAll();
        lock.unlock();
    }

    public void writeStandardOutput(){
        lock.lock();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(text);
        lock.unlock();
    }

    public void writeToFile() throws IOException {
        lock.lock();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("teeText.md"));
        bufferedWriter.write(text);
        bufferedWriter.close();
        lock.unlock();
    }
}