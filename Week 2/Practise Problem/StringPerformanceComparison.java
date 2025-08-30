public class StringPerformanceComparison {
    public static void main(String[] args) {
        int iterations = 10000;

        System.out.println("=== STRING PERFORMANCE COMPARISON ===");

        // Test 1: String (slow)
        long start = System.nanoTime();
        String str = "";
        for (int i = 0; i < iterations; i++) {
            str += "Java" + i;
        }
        long end = System.nanoTime();
        System.out.println("String time       : " + (end - start) + " ns");

        // Test 2: StringBuilder (fast)
        start = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("Java").append(i);
        }
        end = System.nanoTime();
        System.out.println("StringBuilder time: " + (end - start) + " ns");

        // Test 3: StringBuffer (thread-safe, slower than StringBuilder)
        start = System.nanoTime();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sbf.append("Java").append(i);
        }
        end = System.nanoTime();
        System.out.println("StringBuffer time : " + (end - start) + " ns");

        System.out.println("\n=== CONCLUSION ===");
        System.out.println("String       → Slow, avoid for many changes.");
        System.out.println("StringBuilder → Fastest, best for single-threaded.");
        System.out.println("StringBuffer → Thread-safe but a bit slower.");
    }
}
