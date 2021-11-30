import java.util.*;

public class LinearSearch {
    
    public static void main(String[] args) {
        int[] arr = new int[]{5, 7, 24, 30, 41, 76, 108, 210, 214, 307, 499};

        int searchItem = Integer.parseInt(args[0]);
        
        System.out.println(Arrays.toString(arr));
        
        System.out.println(findByLinear(arr, searchItem));
    }

    public static int findByLinear(int[] arr, int searchItem) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print("[" + i + "] ");
            if (arr[i] == searchItem) {
                return i;
            } else if (arr[i] > searchItem) {
                break;
            }
        }

        return -1;
    }
}