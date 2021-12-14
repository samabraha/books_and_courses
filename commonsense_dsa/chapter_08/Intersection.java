import java.util.*;

public class Intersection {
    public static List<Integer> intersection(int[] array1, int[] array2) {
        int[] largerArray;
        int[] smallerArray;
        
        if (array1.length > array2.length) {
            largerArray = array1;
            smallerArray = array2;
        } else {
            largerArray = array2;
            smallerArray = array1;
        }

        Map<Integer, Boolean> map = new HashMap<>();
        
        for (int element : largerArray) {
            map.put(element, true);
        }

        List<Integer> intersection = new ArrayList<>(smallerArray.length);
        for (int i = 0; i < smallerArray.length; i++) {
            if (map.get(smallerArray[i]) != null) {
                intersection.add(smallerArray[i]);
            }
        }
        
        return intersection;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 6, 7, 11};
        int[] b = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 11};
        System.out.println(intersection(a, b));
    }
}