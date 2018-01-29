public class ReadStandardInput extends Thread {

    Model model;

    public  ReadStandardInput (Model model){
        this.model = model;
    }

    @Override
    public void run() {
        System.out.println("Read from standard input: " + getName());
        model.readStandardInput();
    }

}
