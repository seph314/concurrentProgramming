public class WriteStandardOutput extends Thread{

    Model model;

    public  WriteStandardOutput (Model model){
        this.model = model;
    }

    @Override
    public void run() {
        System.out.println("Write to Standard Output: " + getName());
        model.writeStandardOutput();
    }
}
