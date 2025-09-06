public class Counter {
    static int count = 0;

    Counter() {
        count++;
    }

    // Static method getCount()
    public static int getCount() {
        return count;
    }

    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        Counter c3 = new Counter();
        Counter c4 = new Counter();

        System.out.println("Number of objects created: " + Counter.getCount());
    }
}


