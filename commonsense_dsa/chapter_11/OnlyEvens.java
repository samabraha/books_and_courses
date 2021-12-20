import java.util.*;
import java.util.stream.IntStream;

public class OnlyEvens {
    public static void main(String[] args) {
        System.out.println("Started recursion...");
        List<Integer> r = new ArrayList<>();
        IntStream.range(1, 21).forEach(r::add);;
        System.out.println(r);
        System.out.println(only_evens(r));
    }

    public static List<Integer> only_evens(List<Integer> arr) {
        // System.out.println(arr);
        List<Integer> res = new ArrayList<>();
        if (arr.size() < 2) {
            if (arr.get(0) % 2 == 0) {
                res.add(arr.get(0));
            }
            return res;
        }

        // res =  only_evens(List.copyOf(arr.subList(1, arr.size() - 1)));
        res =  only_evens(arr.subList(1, arr.size()));
        
        if (arr.get(0) % 2 == 0) {
            res.add(arr.get(0));
        }
        return res;
    }
}