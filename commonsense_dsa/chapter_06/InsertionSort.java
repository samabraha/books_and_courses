package chapter_06;

import java.util.Arrays;

import chapter_01.TimeTask;

public class InsertionSort {

    public static void main(String[] args) {
        int[] array = new int[]{22, 33, 11, 63, 0, 6, 4, -2, 1};
        System.out.println(Arrays.toString(array));

        System.out.println(TimeTask.timeIt(() -> {
            insertSort(array);
        }));
    }

    public static int[] insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int pos = i - 1;
            while(pos >= 0) {
                if (array[pos] > temp) {
                    array[pos + 1] = array[pos];
                } else {
                    break;
                }
            }
            array[pos] = temp;
        }
        return array;
    }
}