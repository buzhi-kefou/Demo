package org.soulmate.virtualMachine;

import java.util.ArrayList;

public class TestOutOfMemoryError {

    public static void main(String[] args) {
        VMStackSOF();
    }

    //-Xms20m -Xmx20m -XX:+HeapDumpOutOfMemoryError
    public static void heapOOM() {
        class OOMObject {
        }

        ArrayList<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }

    private static int stackLength = 1;
    //-Xss128k
    public static void VMStackSOF() {
        try {
            stackLength++;
            VMStackSOF();
        } catch (Throwable e) {
            System.out.printf("stack length: " + stackLength);
            e.printStackTrace();
        }
    }
}
