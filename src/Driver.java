/**
 * Driver class that uses QuickSort Threads
 */
public class Driver {

    public static void main(String[] args) {
        startSomeThreads();
    }

    /**
     * Starts some threads
     */
    public static void startSomeThreads(){
        QuickSort quickSort;

        // start 10 threads and prints their names
        for (int i=10; i > 0; i--){
            System.out.println("Thread: " + new QuickSort().getName() + " up and running");
        }
    }

    /**
     * Shows number of cores on your comuter
     */
    public void showNumberOfCores() {
        System.out.println("Number of cores: " + Runtime.getRuntime().availableProcessors());
    }
}
