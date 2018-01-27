import java.util.Arrays;
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
        int[] arrayMulti = new int[50000];
        int[] arraySingle = new int[50000];
        Random random = new Random();
        for (int i = 0; i < 50000; i++)
        {
            arraySingle[i] = arrayMulti[i] = random.nextInt(100);
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
    private static long multiThread(int[] array, int numberOfCores){


        // get the length of the array split by numberOfCores
        int arrayLenghPerCore = array.length/numberOfCores;

        // prints the array before sorting it
//        for (int i :array){
//            System.out.println("Unsorted array: " + i);
//        }
        System.out.println("Multithread arrays");
        System.out.println(Arrays.toString(array));

        // gets time before execution
        long startTime = new Date().getTime();
        //System.out.println("Starttime: " + startTime);

        // starts 4 threads and sorts
        for (int i=0; i<numberOfCores; i++){
            QuickSort quickSort = new QuickSort(array);
            quickSort.start(); // checks for the run method and starts a new thread
            // System.out.println("Thread name: " + quickSort.getName());
        }

        // gets time after execution
        long endTime = new Date().getTime();
      //  System.out.println("Endtime: " + endTime);

        // prints the array after sorting it
//        for (int i :array){
//            System.out.println("QuickSorted array: " + i);
//        }
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
//        for (int i :array){
//            System.out.println("Single thread unsorted array: " + i);
//        }
        System.out.println("Singlethread arrays");
        System.out.println(Arrays.toString(array));


        // gets time before execution
        long startTime = new Date().getTime();
//        System.out.println("Starttime: " + startTime);

        // starts QuickSortSingleThread
        QuickSortSingleThread quickSortSingleThread = new QuickSortSingleThread(array);


        // gets time after execution
        long endTime = new Date().getTime();
       // System.out.println("Endtime: " + endTime);

        // prints the array after sorting it
//        for (int i :array){
//            System.out.println("Single thread QuickSorted array: " + i);
//        }
        System.out.println(Arrays.toString(array));


        // return time
        return endTime - startTime;

    }
}
