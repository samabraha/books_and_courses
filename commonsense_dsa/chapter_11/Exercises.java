public class Exercises {
    public static void main(String[] args) {
        // if (args.length < 1) {
        //     System.err.println("No number provided!");
        //     return;
        // }

        // int val = Integer.parseInt(args[0]);
        // System.out.println(triangular(val));

        // String str = args[0];
        // System.out.println(firstX(str));

        int columns = 3;
        int rows = 4;
        System.out.println(uniquePaths(columns, rows));
    }

    public static int uniquePaths(int columns, int rows) {
        if (columns == 1 || rows == 1) {
            return 1;
        }
        
        return uniquePaths(columns - 1, rows) + uniquePaths(columns, rows - 1);
    }

    public static int firstX(String word) {
        return firstX(word, 0);
    }

    public static int firstX(String word, int xPos) {
        if (word.substring(xPos, xPos + 1).equalsIgnoreCase("x")) {
            return xPos;
        }
        return firstX(word, xPos + 1);
    }

    public static int triangular(int num) {
        if (num == 1) {
            return 1;
        }
        return num + triangular(num - 1);
    }
}