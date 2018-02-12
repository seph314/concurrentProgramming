package Diff;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private AtomicInteger atomicInteger = new AtomicInteger();

    public int getAtomicInteger() {
        return atomicInteger.getAndIncrement();
    }
}
