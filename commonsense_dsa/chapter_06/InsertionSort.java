import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;



public class InsertionSort {
    private static ThreadLocalRandom random = ThreadLocalRandom.current();
    public static void main(String[] args) {
        
        int size;

        if (args[0] != null) {
            size = Integer.parseInt(args[0]);
        } else {
            return;
        }

        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10) + 1;
        }

        // int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        // int[] array = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        

        System.out.println(Arrays.toString(array));

        System.out.println(TimeTask.timeIt(() -> {
            insertSort(array);
        }));

        System.out.println(Arrays.toString(array));
    }

    public static int[] insertSort(int[] array) {
        int shift = 0;
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int pos = i - 1;
            while(pos >= 0) {
                if (array[pos] > temp) {
                    array[pos + 1] = array[pos];
                    shift++;
                } else {
                    break;
                }
                pos--;
            }
            array[pos + 1] = temp;
        }
        System.out.printf("Shifts: %d%n", shift);
        return array;
    }
}