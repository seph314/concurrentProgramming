import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The tee command reads the standard  input and writes it to both the standard output and to the file filename.
 * Your parallel tee program should have three threads:
 * one for reading standard input,
 * one for writing to standard output,
 * and one for writing to file filename.
 */
public class Driver {

    public static void main(String[] args) throws IOException {

        ReentrantLock lock = new ReentrantLock();
        String text;

        Tee tee1 = new Tee();
        Tee tee2 = new Tee();
        Tee tee3 = new Tee();

        tee1.start();
        tee2.start();
        tee3.start();

        lock.lock();
        try{
            text = tee1.readStandarsInput();
        }finally {
            lock.unlock();
        }

        try {
            tee1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tee2.writeStandardOutput(text);
        tee3.writeToFile(text);

    }
}
