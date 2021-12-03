public class LeapYear {
    public static void main(String[] args) {
        int year = Integer.parseInt(args[0]);
        System.out.printf("The year %s was %sa leap year.%n", 
            year, isLeapYear(year) ? "" : "not ");
    }

    public static boolean isLeapYear(int year) {
        return (year % 100 == 0) ? (year % 400 == 0) : (year % 4 == 0);
    }
}