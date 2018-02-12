package Diff;

import java.io.IOException;
import java.util.List;

/**
 * The isDiff command compares corresponding lines in the given files and
 * prints both lines to the standard output, if the lines are different.
 *
 * If one of the files is longer then the other,
 * isDiff prints all extra lines in the longer file to the standard output.
 *
 * A text file with approx 128000 rows takes 20ms with multithreading and 56 with singlethreading
 */
public class DiffDriver {

    public static void main(String[] args) throws IOException, InterruptedException {
//        String filenameOne = "taoTeChingLong.txt";
//        String filenameTwo = "taoTeChingShort.txt";

        String filenameOne = "big.txt";
        String filenameTwo = "big2.txt";


        int lengthDifference;

        Reader readerOne = new Reader(filenameOne);
        Reader readerTwo = new Reader(filenameTwo);

        /* start, join and String Lists
        * * * * * * * * * * * * * * * * */
        readerOne.start();
        readerTwo.start();

        readerOne.join();
        readerTwo.join();

        List<String> listOfLinesOne = readerOne.getListOfLines();
        List<String> listOfLinesTwo = readerTwo.getListOfLines();



        /* start threads for compare, check which list is biggest
         * * * * * * * * * * * * * * * * */

        // only one thread should reach the end
        Counter counter = new Counter();

        if (listOfLinesOne.size() > listOfLinesTwo.size()){
            lengthDifference = listOfLinesOne.size() - listOfLinesTwo.size();
            Compare firstThread = new Compare(listOfLinesTwo,listOfLinesOne, lengthDifference, 0, listOfLinesTwo.size()/2-1, counter.getAtomicInteger());
            Compare secondThread = new Compare(listOfLinesTwo,listOfLinesOne, lengthDifference, listOfLinesTwo.size()/2, listOfLinesTwo.size(), counter.getAtomicInteger());
            firstThread.start();
            secondThread.start();
        }else {
            lengthDifference = listOfLinesTwo.size() - listOfLinesOne.size();
            Compare firstThread = new Compare(listOfLinesTwo,listOfLinesOne, lengthDifference, 0, listOfLinesOne.size()/2-1, counter.getAtomicInteger());
            Compare secondThread = new Compare(listOfLinesTwo,listOfLinesOne, lengthDifference, listOfLinesOne.size()/2, listOfLinesOne.size(), counter.getAtomicInteger());
            firstThread.start();
            secondThread.start();
        }

        // take time for a single thread
//        lengthDifference = listOfLinesOne.size() - listOfLinesTwo.size();
//        Compare compareSingleThread = new Compare(listOfLinesTwo, listOfLinesOne, lengthDifference, 0, listOfLinesTwo.size(), 0);
//        compareSingleThread.singleThread();

    }
}
