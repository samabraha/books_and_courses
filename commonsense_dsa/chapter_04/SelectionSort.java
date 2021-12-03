import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SelectionSort {

    public static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
    public static void sort(int array[]) {
        
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

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Wrong number of arguments: ");
            return;
        } 

        Random random = ThreadLocalRandom.current();

        final int SIZE = Integer.parseInt(args[0]);
        final int MAX = Integer.parseInt(args[1]);


        int[] array = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            array[i] = random.nextInt(MAX);
        }

        System.out.println(Arrays.toString(array));

        sort(array);
        System.out.println(Arrays.toString(array));
    }
}