package Tee;

public class ReadStandardInput extends Thread {

    Tee.Model model;

    ReadStandardInput(Tee.Model model){
        this.model = model;
    }

    @Override
    public void run() {
        System.out.println("Read from standard input: " + getName());
        model.readStandardInput();
    }

}
