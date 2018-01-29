import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class Tee extends Thread{

    // input text goes here
    private String text;

    @Override
    public void run() {

    }

    public String readStandarsInput(){
        System.out.println("readStandarsInput: " + getName());
        Scanner scanner = new Scanner(System.in);
        text = scanner.nextLine();
        return text;

    }

    public void writeStandardOutput(String text){
        System.out.println("writeStandardOutput: " + getName());
        System.out.println(text);
    }

    public void writeToFile(String text) throws IOException {
        System.out.println("writeToFile: " + getName());
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("teeText.md"));
        bufferedWriter.write(text);
        bufferedWriter.close();
    }
}
