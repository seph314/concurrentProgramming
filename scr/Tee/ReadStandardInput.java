package Tee;

public class ReadStandardInput extends Thread {

    Model model;

    ReadStandardInput(Model model){
        this.model = model;
    }

    @Override
    public void run() {
        System.out.println("Read from standard input: " + getName());
        try {
            model.readStandardInput();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
