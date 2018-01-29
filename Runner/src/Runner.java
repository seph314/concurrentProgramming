public class Runner extends Thread{

    int count = 0;

    private void increment(){
        for (int i=0; i<10000; i++){
            count++;
        }
    }

    // thread 1
    public void firstThread() throws InterruptedException{
        increment();

    }

    // thread 2
    public void secondThread() throws InterruptedException{
        increment();

    }

    public void finnished(){
        System.out.println("Count is: " + count);
    }

}
