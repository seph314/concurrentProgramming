package Tee;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomic {

    private AtomicInteger atomicInteger = new AtomicInteger();

    public int getAtomicInteger() {
        return atomicInteger.getAndIncrement();
    }
}
