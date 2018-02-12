import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Driver class that uses QuickSort Threads
 */
public class Driver {

    public static void main(String[] args) throws InterruptedException {

        // get number of cores
        int numberOfCores = Runtime.getRuntime().availableProcessors();

        // create a random Integer array
        int[] arrayMulti = new int[80000];
        int[] arraySingle = new int[80000];
        Random random = new Random();
        for (int i = 0; i < 80000; i++)
        {
            arraySingle[i] = random.nextInt(100);
            arrayMulti[i] = random.nextInt(100);
        }

        // calls multithread function
        long multiTime = multiThread(arrayMulti, numberOfCores);
        // calls singlethread function
        long singleTime = singleThread(arraySingle);

        // output times
        System.out.println("This QuickSort took: " + multiTime + "ms to perform with: " + numberOfCores + " cores");
        System.out.println("This QuickSortSingleThread took: " + (singleTime) + "ms to perform with: " + 1 + " core");
    }


    /**
     * Does multithreading things
     * @param array
     */
    private static long multiThread(int[] array, int numberOfCores) throws InterruptedException {

        // create a atomic integer and send it to QuickSort
        AtomicInteger count = new AtomicInteger();

        // prints the array before sorting it
        System.out.println("Multithread arrays");
        System.out.println(Arrays.toString(array));

        // gets time before execution
        long startTime = new Date().getTime();

        // starts 1 thread
        QuickSort quickSort = new QuickSort(array, 0, array.length-1, count, numberOfCores);
        quickSort.start();

        // gets time after execution
        long endTime = new Date().getTime();
        long finnishTime = endTime - startTime;

        // prints the array after sorting it
        /* TODO the array prints before the sort is done.
        *  Temporary fix: let the thread sleep for a while
        *  Also, because of the same thing, the time measured is not correct */
        Thread.sleep(2000);
        System.out.println(Arrays.toString(array));

        // return time
        return finnishTime;
    }


    /**
     * Single thread for comparision
     * @param array
     */
    private static long singleThread(int[] array){

        // prints the array before sorting it
        System.out.println("Singlethread arrays");
        System.out.println(Arrays.toString(array));

        // gets time before execution
        long startTime = new Date().getTime();

        // starts QuickSortSingleThread
        new QuickSortSingleThread(array);

        // gets time after execution
        long endTime = new Date().getTime();

        // prints the array after sorting it
        System.out.println(Arrays.toString(array));

        // return time
        return endTime - startTime;
    }
}
