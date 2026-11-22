package week2.monday.jfp;

/*
 * Điều kiện mua vé: tuổi >= 18 VÀ số dư >= 100000.
 * Thử cả 4 tổ hợp đúng/sai bằng các giá trị sát ngưỡng.
 * Với &&, nếu vế trái false thì Java không đánh giá vế phải.
 */
public class TicketEligibility {
    public static void main(String[] args) {
        System.out.println("Tuoi | So du  | Tuoi >= 18 | So du >= 100000 | Duoc mua ve");
        printEligibility(17, 99999);  // false && false -> false
        printEligibility(17, 100000); // false && true  -> false
        printEligibility(18, 99999);  // true  && false -> false
        printEligibility(18, 100000); // true  && true  -> true

        System.out.println("\nShort-circuit: denominator != 0 && numerator / denominator > 1");
        printGuardedDivision(10, 0);
        printGuardedDivision(10, 2);
    }

    private static void printEligibility(int age, int balance) {
        boolean canBuyTicket = age >= 18 && balance >= 100000;
        System.out.printf("%4d | %6d | %10b | %15b | %b%n",
                age, balance, age >= 18, balance >= 100000, canBuyTicket);
    }

    private static void printGuardedDivision(int numerator, int denominator) {
        // Khi denominator == 0, vế trái false nên phép chia không được thực hiện.
        // Phải đặt điều kiện bảo vệ TRƯỚC phép chia và dùng &&.
        boolean result = denominator != 0 && numerator / denominator > 1;
        System.out.printf("Tu so = %d, mau so = %d -> %b%n", numerator, denominator, result);
        if (denominator == 0) {
            System.out.println("Ve trai false: bo qua phep chia, khong co ArithmeticException.");
        } else {
            System.out.printf("Ve trai true: tinh %d / %d = %d, roi so sanh > 1.%n",
                    numerator, denominator, numerator / denominator);
        }
    }
}
