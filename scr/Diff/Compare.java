package Diff;

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

    private Compare() {

    }

    @Override
    public void run() {
        try {
            compare();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void compare() throws InterruptedException {
        Diff diff = new Diff();

//        if (numberOfCores > 0) {
//            numberOfCores--;
//            Compare moreThreads = new Compare();
//            moreThreads.start();
//        }

//        System.out.println("Första halvan: " + getName());
//        for (int i=0; i < oneList.size()/2; i++){
//            if (diff.isDiff(anotherList.get(i), oneList.get(i))){
//                System.out.println(anotherList.get(i) + "\n" + oneList.get(i));
//            }
//        }
//
//        System.out.println("Andra halvan: " + getName());
//        for (int i=oneList.size()/2+1; i < oneList.size()-1; i++){
//            if (diff.isDiff(anotherList.get(i), oneList.get(i))){
//                System.out.println(anotherList.get(i) + "\n" + oneList.get(i));
//            }
//        }
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

// SINGLE THREAD WORKING
//        for (int i=0; i < oneList.size()-1; i++){
//            if (diff.isDiff(anotherList.get(i), oneList.get(i))){
//                System.out.println(anotherList.get(i) + "\n" + oneList.get(i));
//            }
//        }
//        // print the remaining rows in the longer list
//        for (int i=anotherList.size()-lengthDifference; i < anotherList.size(); i++){
//            System.out.println(anotherList.get(i));
//        }
    }

}
