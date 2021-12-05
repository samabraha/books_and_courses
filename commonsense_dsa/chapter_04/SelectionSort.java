import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import chapter_01.TimeTask;

public class SelectionSort {
    private static final Random random = ThreadLocalRandom.current();

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Wrong number of arguments: ");
            return;
        } 

        final int SIZE = Integer.parseInt(args[0]);
        final int MAX = Integer.parseInt(args[1]);

        // System.out.println(Arrays.toString(array));

        // System.out.printf("Beffore: %,d nanoseconds%n", TimeTask.timeIt(() -> selectionSort(array)));        

        final int[] array = getRandomNumbers(SIZE, MAX);

        System.out.printf("Time taken: %,d nanoseconds%n", TimeTask.timeIt(() -> modifiedSelectSort(array)));
        System.out.println(Arrays.toString(array));

        final int[] array2 = getRandomNumbers(SIZE, MAX);
        System.out.printf("Time taken: %,d nanoseconds%n", TimeTask.timeIt(() -> selectionSort(array2)));

    }

    public static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void selectionSort(int array[]) {
        
        for (int i = 0; i < array.length; i++) {
            int lowestItem = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[lowestItem]) {
                    lowestItem = j;
                }
            }
            swap(array, i, lowestItem);
        }
    }

    public static void modifiedSelectSort(int array[]) {
        int valley = 0;
        int crest = array.length - 1;
        boolean swapped = true;
        for (int i = valley; swapped;) {
            swapped = false;
        }
    }

    public static int[] getRandomNumbers(int size, int maxValue) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(maxValue + 1);
        }

        return array;
    }
}