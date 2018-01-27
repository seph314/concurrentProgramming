import java.util.concurrent.locks.ReentrantLock;

/**
 * Quicksort extends Thread and implements it'own version of the run() function
 */
public class QuickSort extends Thread {

    private ReentrantLock reentrantLock = new ReentrantLock();

    /**
     * Constructor that thakes a integer array
     */
    int[] array;
    QuickSort(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {

        // example to illustrate multithreading
        for (int i=0; i<10; i++){
            System.out.println("Thread name " + getName() + " loop " + i);

            // sleeps for a while (1/10s second)
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // calls quickSort
        quickSort(array);
    }

    /*
    * Creates left and right value and calls the correct method with these
    * */
    void quickSort(int[] array){

        quickSort(array, 0, array.length-1);

    }

    /**
     * This shoule be implemented in the run() function I think...
     * @param array is the array that we want to sort
     * @param left is the leftmost value in the array
     * @param right is the rightmost value in teh array
     */
    void quickSort(int array[], int left, int right) {

//        reentrantLock.lock();
        //System.out.println(getName());
//        reentrantLock.unlock();


        int index = partition(array, left, right);

        if (left < index - 1)
            quickSort(array, left, index - 1);

        if (index < right)
            quickSort(array, index, right);


    }

    private int partition(int array[], int left, int right)

    {
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
