import java.util.*;

public class Hello {
    public static void main(String[] args) {
        long result;
        int size = 100_000_000;
        List<Integer> evens1 = new ArrayList<>((size / 2) + 1);
        result = timeIt(() -> {
            for (int i = 1; i <= size; i++) {
                if (i % 2 == 0) {
                    evens1.add(i);
                }
            }
        });
        System.out.println("Even by check: " + result);
        List<Integer> evens2 = new ArrayList<>((size / 2) + 1);
        result = timeIt(() -> {
            int i = 0;
            while (i <= size) {
                evens2.add(i);
                i += 2;
            }
        });
        System.out.println("Even by skip: " + result);
    }

    static long timeIt(Runnable action) {
        final long start = System.nanoTime();
        action.run();
        final long end = System.nanoTime();
        return end - start;
    }
}