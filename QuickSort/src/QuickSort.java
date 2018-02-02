import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
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
    Executor pool = Executors.newFixedThreadPool(4);

    // gets values from constructor
    private int[] array;
    private int left;
    private int right;
    private AtomicInteger count;

    public int[] getArray() {
        return array;
    }


    /**
     * Constructor that thakes a integer array
     */
    QuickSort(int[] array, int left, int right, AtomicInteger count) {
        this.array = array;
        this.left = left;
        this.right = right;
        this.count = count;
//        quickSort(array,left,right);
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

        lock.lock();
        count.getAndDecrement();
            if (count.get() == 1) {
                condition.signalAll();
            }
        lock.unlock();

    }


    /**
     * This shoule be implemented in the run() function I think...
     *
     * @param left  is the leftmost value in the array
     * @param right is the rightmost value in teh array
     */
    public void quickSort(int left, int right) throws InterruptedException {

//        int length = right - left + 1;

        int index = partition(left, right);

        if (count.get() < 4) {
            lock.lock();
            count.getAndAdd(2);
            pool.execute(new QuickSort(array, left, index - 1, count));
            pool.execute(new QuickSort(array, index, right, count));
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
