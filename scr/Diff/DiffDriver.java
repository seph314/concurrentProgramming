package Diff;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * The isDiff command compares corresponding lines in the given files and
 * prints both lines to the standard output, if the lines are different.
 *
 * If one of the files is longer then the other,
 * isDiff prints all extra lines in the longer file to the standard output.
 */
public class DiffDriver {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filenameOne = "taoTeChingLong.txt";
        String filenameTwo = "taoTeChingShort.txt";
        int lengthDifference;

        AtomicInteger atomicInteger = new AtomicInteger();

        Reader readerOne = new Reader(filenameOne);
        Reader readerTwo = new Reader(filenameTwo);

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        /* start, join and String Lists
        * * * * * * * * * * * * * * * * */
        readerOne.start();
        readerTwo.start();

        readerOne.join();
        readerTwo.join();

        List<String> listOfLinesOne = readerOne.getListOfLines();
        List<String> listOfLinesTwo = readerTwo.getListOfLines();



        /* start first thread for compare, check which list is biggest
         * * * * * * * * * * * * * * * * */
        if (listOfLinesOne.size() > listOfLinesTwo.size()){
            lengthDifference = listOfLinesOne.size() - listOfLinesTwo.size();
            Compare compare = new Compare(listOfLinesTwo,listOfLinesOne, lengthDifference);
            compare.start();
        }else {
            lengthDifference = listOfLinesTwo.size()-listOfLinesOne.size();
            Compare compare = new Compare(listOfLinesTwo,listOfLinesOne, lengthDifference);
            compare.start();
        }
    }
}
