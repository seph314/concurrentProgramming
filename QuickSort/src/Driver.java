import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * Driver class that uses QuickSort Threads
 */
public class Driver {

    public static void main(String[] args) throws InterruptedException {

        // get number of cores
        int numberOfCores = Runtime.getRuntime().availableProcessors();

        // create a random Integer array
        int[] arrayMulti = new int[20];
        int[] arraySingle = new int[20];
        Random random = new Random();
        for (int i = 0; i < 20; i++)
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

        // get the length of the array split by numberOfCores
        int arrayLenghPerCore = array.length/numberOfCores;

        // prints the array before sorting it
        System.out.println("Multithread arrays");
        System.out.println(Arrays.toString(array));

        // gets time before execution
        long startTime = new Date().getTime();

        // starts 1 thread
//        QuickSort quickSort = new QuickSort(array);
//        quickSort.start();
        QuickSort quickSort = new QuickSort(array);

        // starts 4 threads and sorts
//        for (int i=0; i<numberOfCores; i++){
//            QuickSort quickSort = new QuickSort(array, numberOfCores);
//            quickSort.start(); // checks for the run method and starts a new thread
////             System.out.println("Thread name: " + quickSort.getName());
//        }

        // gets time after execution
        long endTime = new Date().getTime();

        // prints the array after sorting it
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
