package com.cyberaka.practice.throttling.logic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Throttler {

    private ReentrantLock lock = new ReentrantLock();
    private volatile AtomicInteger connectionCount = new AtomicInteger();
    private volatile AtomicInteger connectionsPerSecond = new AtomicInteger();

    private volatile AtomicInteger delay = new AtomicInteger();

    private int throttlingStart;
    private int throttlingDelay;

    public Throttler(int throttlingStart, int throttlingDelay) {
        this.throttlingStart = throttlingStart;
        this.throttlingDelay = throttlingDelay;
    }

    public void connectionReceived() {
        lock.lock();
        try {
            connectionCount.incrementAndGet();
            int tps = connectionsPerSecond.incrementAndGet();
            if (tps > throttlingStart) {
                delay.set(throttlingDelay);
            }
        } finally {
            lock.unlock();
        }
    }

    public void connectionFinished() {
        lock.lock();
        try {
            connectionCount.decrementAndGet();
        } finally {
            lock.unlock();
        }
    }

    public void checkTps() {
        lock.lock();
        try {
            int tps = connectionsPerSecond.get();
            connectionsPerSecond.set(0);
            if (tps >= throttlingStart) {
                delay.set(throttlingDelay);
            } else {
                delay.set(0);
            }
        } finally {
            lock.unlock();
        }
    }

    public int getDelay() {
        return delay.get();
    }

    public int getActiveConnections() {
        return connectionCount.get();
    }

    @Override
    public String toString() {
        return "Throttler{" +
                "connectionCount=" + connectionCount +
                ", connectionsPerSecond=" + connectionsPerSecond +
                ", delay=" + delay +
                '}';
    }
}
