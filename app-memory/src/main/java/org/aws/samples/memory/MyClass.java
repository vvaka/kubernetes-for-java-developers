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
        
        byte[] array;
        byte[] array2;
        byte[] array3;

        if (args.length == 1) {
            byte bytes = Byte.parseByte(args[0]);
            if (bytes >= 1) {
                array = new byte[GIGABYTE];
                Arrays.fill(array, (byte)1);
                System.out.println("==> Array1 initialized");
            }

            if (bytes >= 2) {
                array2 = new byte[GIGABYTE];
                Arrays.fill(array2, (byte)2);
                System.out.println("==> Array2 initialized");
            }

            if (bytes >= 3) {
                array3 = new byte[GIGABYTE];
                Arrays.fill(array3, (byte)3);
                System.out.println("==> Array3 initialized");
            }
        } else {
            System.out.println("==> No arrays initialized");
        }

        printStats();

        array = null;
        array2 = null;
        array3 = null;
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
