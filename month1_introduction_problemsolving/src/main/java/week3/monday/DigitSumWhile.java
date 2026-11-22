package week3.monday;

/**
 * Tính tổng chữ số của số nguyên không âm bằng while.
 * remaining % 10 lấy chữ số cuối; remaining / 10 bỏ chữ số cuối.
 * Phép chia hai số nguyên chỉ giữ phần nguyên.
 *
 * Biến tiến triển: remaining, ban đầu bằng n. Khi remaining > 0,
 * remaining / 10 luôn nhỏ hơn remaining và không âm, nên cuối cùng
 * remaining về 0 và vòng lặp dừng. Giữ n để không mất đầu vào ban đầu.
 *
 * Trace n = 5070, ban đầu remaining = 5070, sum = 0:
 *   remaining trước | chữ số (% 10) | sum sau cộng | remaining sau / 10
 *              5070 |             0 |            0 | 5070 / 10 = 507
 *               507 |             7 |            7 |  507 / 10 = 50
 *                50 |             0 |            7 |   50 / 10 = 5
 *                 5 |             5 |           12 |    5 / 10 = 0
 * remaining = 0: điều kiện while sai, trả về 12.
 * Với n = 0, không vào vòng lặp, sum giữ nguyên 0.
 * Với n âm, từ chối trước vòng lặp.
 */
public class DigitSumWhile {
    public static int sumDigits(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n phải là số nguyên không âm");
        }

        int remaining = n;
        int sum = 0;
        while (remaining > 0) {
            sum += remaining % 10;
            remaining /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        checkSum(5070, 12);
        checkSum(0, 0);
        checkSum(7, 7);
        checkSum(1000, 1);
        checkSum(Integer.MAX_VALUE, 46);
        checkRejected(-1);
    }

    private static void checkSum(int n, int expected) {
        int actual = sumDigits(n);
        if (actual != expected) {
            throw new AssertionError("n=" + n + ": mong đợi " + expected
                    + ", thực tế " + actual);
        }
        System.out.printf("n=%d -> %d (PASS)%n", n, actual);
    }

    private static void checkRejected(int n) {
        try {
            sumDigits(n);
        } catch (IllegalArgumentException e) {
            System.out.printf("n=%d -> Từ chối: %s (PASS)%n", n, e.getMessage());
            return;
        }
        throw new AssertionError("Phải từ chối n=" + n);
    }
}
