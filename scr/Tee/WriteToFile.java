package Tee;

import java.io.IOException;

public class WriteToFile extends Thread {
    Model model;

    WriteToFile(Model model){
        this.model = model;
    }

    @Override
    public void run() {
        System.out.println("Write to file: " + getName());
        try {
            try {
                model.writeToFile();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
