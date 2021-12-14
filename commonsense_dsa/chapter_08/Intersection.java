import java.util.HashMap;
import java.util.Map;

public class Intersection {
    public static int[] intersection(int[] array1, int[] array2) {
        int[] largerArray;
        int[] smallerArray;
        
        if (array1.length > array2.length) {
            largerArray = array1;
            smallerArray = array2;
        } else {
            largerArray = array2;
            smallerArray = array1;
        }


        int[] intersection = new int[smallerArray.length];

        Map<Integer> map = new HashMap<Integer>();
        map.fill(largerArray);
        return intersection;
    }
}