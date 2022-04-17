package org.soulmate.concurrency;

import java.util.ArrayList;
import java.util.HashSet;

public class Test {
    public static void main(String[] args) {
        ArrayList<Thread> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new Thread() {
                @Override
                public void run() {
                    add();
                }
            });
        }

        for (int i = 0; i < 100; i++) {
            list.get(i).start();
        }
    }

    static int count = 0;
    static HashSet<Integer> set = new HashSet<>();

    public static void add() {
        System.out.println("count" + count++);
        if (!set.add(count))
            System.out.println("exists");
    }
}
