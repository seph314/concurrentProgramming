import java.io.IOException;

public class WriteToFile extends Thread {
    Model model;

    public  WriteToFile (Model model){
        this.model = model;
    }

    @Override
    public void run() {
        System.out.println("Write to file: " + getName());
        try {
            model.writeToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
