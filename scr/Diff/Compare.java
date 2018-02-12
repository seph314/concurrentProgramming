package Diff;

import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Compare extends Thread {

    private List<String> oneList;
    private List<String> anotherList;
    private int lengthDifference;
    private int stop;
    private int start;
    private int atomic;


    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public Compare(List<String> oneList, List<String> anotherList, int lengthDifference, int start, int stop, int atomic) {
        this.oneList = oneList;
        this.anotherList = anotherList;
        this.lengthDifference = lengthDifference;
        this.stop = stop;
        this.start = start;
        this.atomic = atomic;
    }


    @Override
    public void run() {
        compare();
    }

    private void compare() {
        Diff diff = new Diff();

        long startTime = new Date().getTime();

        lock.lock();
        for (int i = start; i < stop - 1; i++) {
            if (diff.isDiff(anotherList.get(i), oneList.get(i))) {
                System.out.println(anotherList.get(i) + "\n" + oneList.get(i) + " Handled by: " + getName());
            }
        }
        lock.unlock();

        lock.lock();
        // print the remaining rows in the longer list
        if (atomic == 0) {
            System.out.println(getName());
            for (int i = anotherList.size() - lengthDifference; i < anotherList.size(); i++) {
                System.out.println(anotherList.get(i));
            }
        }
        lock.unlock();

        long endTime = new Date().getTime();
        System.out.println("Time: " + (endTime - startTime));
    }

    public void singleThread(){
        long startTime = new Date().getTime();

        System.out.println("Single thread");
        Diff diff = new Diff();
        for (int i=0; i < oneList.size()-1; i++){
            if (diff.isDiff(anotherList.get(i), oneList.get(i))){
                System.out.println(anotherList.get(i) + "\n" + oneList.get(i));
            }
        }
        // print the remaining rows in the longer list
        for (int i=anotherList.size()-lengthDifference; i < anotherList.size(); i++){
            System.out.println(anotherList.get(i));
        }

        long endTime = new Date().getTime();
        System.out.println("Time: " + (endTime - startTime));
    }
}
