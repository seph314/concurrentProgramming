import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  Quicksort extends Thread and implements it'own version of the run() function
 *  Choose a pivot element
 *  Compare all elements VS pivot
 *  Split array <pivot =pivot >pivot
 *  Recurse on each array
 */
public class QuickSort extends Thread {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private Executor pool = Executors.newFixedThreadPool(4);

    // gets values from constructor
    private int[] array;
    private int left;
    private int right;
    private AtomicInteger count;
    private int numberOfCores;


    /**
     * Constructor that thakes a integer array
     */
    QuickSort(int[] array, int left, int right, AtomicInteger count, int numberOfCores) {
        this.array = array;
        this.left = left;
        this.right = right;
        this.count = count;
        this.numberOfCores = numberOfCores;
    }


    /**
     * Overrides run in Threads
     */
    @Override
    public void run() {
        System.out.println("THREAD: " + getName() + " COUNT: " + count.get());
        try {
            quickSort(left, right);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // this should signal for a thread to unlock the lock inside quickSort
        // this should create two new threads
        lock.lock();
        count.getAndDecrement();
        condition.signal();
        lock.lock();

    }

    /**
     * Uses the QuickSort algorithm to sort an array
     * Uses multiple threads
     * @param left  is the leftmost value in the array
     * @param right is the rightmost value in teh array
     */
    private void quickSort(int left, int right) throws InterruptedException {

        int index = partition(left, right);

        if (count.get() < numberOfCores*2) {
            lock.lock();
            count.getAndAdd(2);
            pool.execute(new QuickSort(array, left, index - 1, count, numberOfCores));
            pool.execute(new QuickSort(array, index, right, count, numberOfCores));
            condition.await();
            lock.unlock();
        }
        else {
            if (left < index - 1)
                quickSort(left, index - 1);

            if (index < right)
                quickSort(index, right);
        }
    }

    private int partition(int left, int right) {
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
