import java.util.*;

public class StringPerformanceAnalysis {

    public static long[] testStringConcat(int n) {
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < n; i++) s += "A";
        long end = System.currentTimeMillis();
        return new long[]{end - start, s.length()};
    }

    public static long[] testStringBuilder(int n) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append("A");
        long end = System.currentTimeMillis();
        return new long[]{end - start, sb.length()};
    }

    public static long[] testStringBuffer(int n) {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) sb.append("A");
        long end = System.currentTimeMillis();
        return new long[]{end - start, sb.length()};
    }

    public static String getMemoryEfficiency(long time, long length) {
        double efficiency = (double) length / (time + 1);
        if (efficiency > 10000) return "High";
        else if (efficiency > 1000) return "Medium";
        else return "Low";
    }

    public static void displayResults(long[] strResult, long[] sbResult, long[] sbufResult) {
        System.out.println("\n-----------------------------------------------");
        System.out.printf("%-15s | %-15s | %-15s\n", "Method", "Time (ms)", "Memory Efficiency");
        System.out.println("-----------------------------------------------");
        System.out.printf("%-15s | %-15d | %-15s\n", "String", strResult[0], getMemoryEfficiency(strResult[0], strResult[1]));
        System.out.printf("%-15s | %-15d | %-15s\n", "StringBuilder", sbResult[0], getMemoryEfficiency(sbResult[0], sbResult[1]));
        System.out.printf("%-15s | %-15d | %-15s\n", "StringBuffer", sbufResult[0], getMemoryEfficiency(sbufResult[0], sbufResult[1]));
        System.out.println("-----------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of iterations: ");
        int n = sc.nextInt();

        long[] strResult = testStringConcat(n);
        long[] sbResult = testStringBuilder(n);
        long[] sbufResult = testStringBuffer(n);

        displayResults(strResult, sbResult, sbufResult);

        sc.close();
    }
}
