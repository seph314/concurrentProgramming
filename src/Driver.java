/**
 * Driver class that uses QuickSort Threads
 */
public class Driver {

    public static void main(String[] args) {
        startSomeThreads();

        /*
        * QuickSort test
        * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        int[] array = {3,1,4,1,5,9,2,6,5,3,5};

        // prints before
        for (int i :array){
            System.out.println("Unsorted array: " + i);
        }

        // sorts
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(array);

        // prints after
        for (int i :array){
            System.out.println("QuickSorted array: " + i);
        }
        /*
         * END QuickSort test
         * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    }

    /**
     * Starts some threads
     */
    public static void startSomeThreads(){


        // start 10 threads and prints their names
        for (int i=10; i > 0; i--){
            QuickSort qs = new QuickSort();
            qs.start();
            System.out.println("Thread: " + qs.getName() + " up and running");
        }
    }

    /**
     * Shows number of cores on your comuter
     */
    public void showNumberOfCores() {
        System.out.println("Number of cores: " + Runtime.getRuntime().availableProcessors());
    }

}
