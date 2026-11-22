package week2;

/** Năm nhuận: chia hết cho 400 hoặc chia hết cho 4 nhưng không chia hết cho 100. */
public class LeapYear {
    public static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

    public static void main(String[] args) {
        int[] years = {1900, 2000, 2024, 2025};

        for (int year : years) {
            System.out.println(year + ": " + isLeapYear(year));
        }
    }
}
