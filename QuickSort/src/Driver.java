import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Driver class that uses QuickSort Threads
 */
public class Driver {

    // create a thread pool
//    Executor pool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws InterruptedException {

        // get number of cores
        int numberOfCores = Runtime.getRuntime().availableProcessors();

        // create a random Integer array
        int[] arrayMulti = new int[20000];
        int[] arraySingle = new int[20000];
        Random random = new Random();
        for (int i = 0; i < 20000; i++)
        {
            arraySingle[i] = random.nextInt(100);
            arrayMulti[i] = random.nextInt(100);
        }

//        QuickSort quickSort = new QuickSort();
//        threadPool.submit(quickSort);

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
        QuickSort quickSort = new QuickSort(array, 0, array.length-1, count);
        quickSort.start();


        // starts 4 threads and sorts
//        for (int i=0; i<numberOfCores; i++){
//            QuickSort quickSort = new QuickSort(array);
//            quickSort.start(); // checks for the run method and starts a new thread
//
////             System.out.println("Thread name: " + quickSort.getName());
//        }

        // gets time after execution
        long endTime = new Date().getTime();


        // prints the array after sorting it
        /*

        OBS! Problemet är att den hämtar arrayen innan den är klar

         */
        Thread.sleep(1000);
        System.out.println(Arrays.toString(array));

        // return time
        return endTime -startTime;
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
        QuickSortSingleThread quickSortSingleThread = new QuickSortSingleThread(array);


        // gets time after execution
        long endTime = new Date().getTime();

        // prints the array after sorting it
        System.out.println(Arrays.toString(array));

        // return time
        return endTime - startTime;
    }
}
