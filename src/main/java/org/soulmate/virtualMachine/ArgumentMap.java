package org.soulmate.virtualMachine;

import java.util.HashMap;
import java.util.HashSet;

public class ArgumentMap {

    public static void main(String[] args) {

    }

    public static HashSet<String> put(){
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("-XX:+UnlockExperimentalVMOptions");
        hashSet.add("-XX:+UseJVMCICompiler");
        hashSet.add("-XX:+TraceBytecodes");
        hashSet.add("-XX:StopInterpreterAt=<n>");
        hashSet.add("-Xmx");
        hashSet.add("-Xms");
        hashSet.add("-Xss");
        hashSet.add("-Xoss");
        hashSet.add("-XX:+HeapDumpOnOutOfMemoryError");
        hashSet.add("-XX:MaxPermSize");
        hashSet.add("-XX:+/-UseTLAB");
        hashSet.add("-XX:FieldsAllocationStyle");
        hashSet.add("-XX:CompactFields");

        return hashSet;
    }
}
