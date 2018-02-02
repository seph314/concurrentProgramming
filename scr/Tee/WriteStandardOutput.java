package Tee;

public class WriteStandardOutput extends Thread{

    Model model;

    WriteStandardOutput(Model model){
        this.model = model;
    }

    @Override
    public void run() {
        System.out.println("Write to Standard Output: " + getName());
        try {
            model.writeStandardOutput();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
