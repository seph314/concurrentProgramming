
/**
 * Quicksort extends Thread and implements it'own version of the run() function
 */
public class QuickSortSingleThread{


    /**
     * Constructor that thakes a integer array
     */
    int[] array;
    QuickSortSingleThread(int[] array) {
        this.array = array;
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
