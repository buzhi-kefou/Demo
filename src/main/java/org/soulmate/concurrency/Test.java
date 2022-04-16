package org.soulmate.concurrency;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Test {
    public static void main(String[] args) {
        testCountDownLatch();
    }

    public static void testCountDownLatch() {
        final CountDownLatch downLatch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (downLatch.getCount() != 0l) {
                    //wait
                }
                System.out.println("finish wait");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(8000);
                    downLatch.countDown();
                    System.out.println("finish sleep");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
