import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Quicksort extends Thread and implements it'own version of the run() function
 */
public class QuickSort extends Thread {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    AtomicInteger count;

    private int[] array;
    private int left;
    private int right;


    /**
     * Constructor that thakes a integer array
     */
    QuickSort(int[] array) {
        this.array = array;
        this.left = 0;
        this.right = array.length - 1;
        this.count = new AtomicInteger();
        quickSort(array,left,right,count);
    }

    /**
     * Constructor that handes the recursive call for parallelism
     *  @param array
     * @param left
     * @param right
     * @param count
     */
    private QuickSort(int[] array, int left, int right, AtomicInteger count) {
        this.array = array;
        this.left = left;
        this.right = right;
        this.count = count;
    }

    /**
     * Overrides run in Threads
     */
    @Override
    public void run() {
        quickSort(array, left, right, count);

    }


    /**
     * This shoule be implemented in the run() function I think...
     *
     * @param array is the array that we want to sort
     * @param left  is the leftmost value in the array
     * @param right is the rightmost value in teh array
     */
    public void quickSort(int array[], int left, int right, AtomicInteger count) {

//        int length = right - left + 1;
        int index = partition(array, left, right);
//        System.out.println(getName());


        if (count.get() > 4){
            if (left < index - 1) {
                quickSort(array, left, index - 1, count);
            }


            if (index < right) {
                quickSort(array, index, right, count);
            }
        }
        else {
            count.getAndAdd(2);
            new QuickSort(array,left,index-1, count).start();
            new QuickSort(array,index,right, count).start();
        }



    }

    private int partition(int array[], int left, int right) {
        int i = left, j = right;
        int tmp;
        int pivot = array[(left + right) / 2];

        while (i <= j) {
            while (array[i] < pivot)
                i++;

            while (array[j] > pivot)
                j--;

            if (i <= j) {
                tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i++;
                j--;
            }
        }

        return i;
    }
}
