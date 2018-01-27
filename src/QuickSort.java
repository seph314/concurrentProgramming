import java.util.concurrent.locks.ReentrantLock;

/**
 * Quicksort extends Thread and implements it'own version of the run() function
 */
public class QuickSort extends Thread{

    ReentrantLock reentrantLock = new ReentrantLock();
    int[] array;
    int left;
    int right;

    /**
     * Constructor that thakes a integer array
     */
    QuickSort(int[] array) {
        this.array = array;
        this.left = 0;
        this.right = array.length-1;
    }

    /**
     * Constructor that handes the recursive call for parallelism
     * @param array
     * @param left
     * @param right
     */
    private QuickSort(int[] array, int left, int right){
        this.array = array;
        this.left = left;
        this.right = right;
    }

    /**
     * Overrides run in Threads
     */
    @Override
    public void run() {
        // calls quickSort
        try {
            quickSort(array, left, right);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * This shoule be implemented in the run() function I think...
     * @param array is the array that we want to sort
     * @param left is the leftmost value in the array
     * @param right is the rightmost value in teh array
     */
    void  quickSort(int array[], int left, int right) throws InterruptedException {

//        int length = right - left + 1;
//        reentrantLock.lock();
        //System.out.println(getName());
//        reentrantLock.unlock();

        reentrantLock.lock();
        int index = partition(array, left, right);
        reentrantLock.unlock();
//        System.out.println(getName());

        if (left < index - 1){
            quickSort(array, left, index - 1);
        }

        if (index < right){
            quickSort(array, index, right);
        }

        join();




//        System.out.println("length: " + length);
//        System.out.println("max length: " + arrayLenghtPerCore);

//        QuickSort quickSort1 = new QuickSort(array, left, index-1);
//        quickSort1.start();
//
//        QuickSort quickSort2 = new QuickSort(array, index, right);
//        quickSort2.start();
//
//        quickSort1.join();
//        quickSort2.join();


//        System.out.println("Thread: " + getName());


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
