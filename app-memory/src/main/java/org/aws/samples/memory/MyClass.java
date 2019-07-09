package org.aws.samples.memory;

import java.util.Arrays;

/**
 * @author argu
 */
public class MyClass {
    private static final int KILOBYTE = 1024;
    private static final int MEGABYTE = KILOBYTE * KILOBYTE;
    private static final int GIGABYTE = MEGABYTE * KILOBYTE;

    public static void main(String[] args) {
        printStats();

        byte[] array = new byte[GIGABYTE];        
        Arrays.fill(array, (byte)1);
        
        System.out.println("==> Array initialized: " + printUnits(GIGABYTE));

        printStats();
        
        array = null;
        System.out.println("==> Array nullified");        
        Runtime.getRuntime().gc();
        System.out.println("==> GC done");        
        
        printStats();

    }

    static private void printStats() {
        System.out.println("** PROCESSOR AND MEMORY STATS **");
        Runtime rt = Runtime.getRuntime();
        System.out.println("processors: " + rt.availableProcessors());
        System.out.println("max memory: " + printUnits(rt.maxMemory()));
        System.out.println("free memory: " + printUnits(rt.freeMemory()));
        System.out.println("total memory: " + printUnits(rt.totalMemory()));
        System.out.println("** PROCESSOR AND MEMORY STATS **");
    }

    static private String printUnits(long bytes) {
        int unit = 1024;
        if (bytes < unit) {
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = ("KMGTPE").charAt(exp - 1) + "i";
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}
