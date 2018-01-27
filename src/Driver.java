import java.util.Date;
import java.util.Random;

/**
 * Driver class that uses QuickSort Threads
 */
public class Driver {

    public static void main(String[] args) {

        // get number of cores
        int numberOfCores = Runtime.getRuntime().availableProcessors();

        // create a random Integer array
        int[] array = new int[20];
        Random random = new Random();
        for (int i = 0; i < 20; i++)
        {
            array[i] = random.nextInt(100);
        }


        // prints the array before sorting it
        for (int i :array){
            System.out.println("Unsorted array: " + i);
        }

        // gets time before execution
        long startTime = new Date().getTime();
        System.out.println("Starttime: " + startTime);

        // starts 4 threads and sorts
        for (int i=0; i<numberOfCores; i++){
            QuickSort quickSort = new QuickSort(array);
            quickSort.start(); // checks for the run method and starts a new thread
            // System.out.println("Thread name: " + quickSort.getName());
        }

        // gets time after execution
        long endTime = new Date().getTime();
        System.out.println("Endtime: " + endTime);

        // prints the array after sorting it
        for (int i :array){
            System.out.println("QuickSorted array: " + i);
        }

        System.out.println("This QuickSort took: " + (endTime - startTime) + "ms to perform with: " + numberOfCores + " cores");

    }
}
