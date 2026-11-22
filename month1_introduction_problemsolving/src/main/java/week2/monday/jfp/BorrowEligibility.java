package week2.monday.jfp;

/*
 * Được mượn sách khi: tuổi >= 12 VÀ thẻ còn hạn VÀ đang mượn < 3 cuốn.
 * Bảng gồm 8 tổ hợp boolean, dùng các giá trị sát ngưỡng: tuổi 11/12,
 * số sách đang mượn 3/2. Chỉ true && true && true cho kết quả true.
 */
public class BorrowEligibility {
    public static boolean canBorrow(int age, boolean cardValid, int borrowedBooks) {
        return age >= 12 && cardValid && borrowedBooks < 3;
    }

    public static void main(String[] args) {
        System.out.println("Tuoi | The con han | Dang muon | Tuoi >= 12 | Dang muon < 3 | canBorrow");
        printEligibility(11, false, 3);
        printEligibility(11, false, 2);
        printEligibility(11, true, 3);
        printEligibility(11, true, 2);
        printEligibility(12, false, 3);
        printEligibility(12, false, 2);
        printEligibility(12, true, 3);
        printEligibility(12, true, 2);
    }

    private static void printEligibility(int age, boolean cardValid, int borrowedBooks) {
        System.out.printf("%4d | %11b | %9d | %10b | %13b | %b%n",
                age, cardValid, borrowedBooks, age >= 12, borrowedBooks < 3,
                canBorrow(age, cardValid, borrowedBooks));
    }
}
