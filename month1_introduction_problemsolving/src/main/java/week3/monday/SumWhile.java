package week3.monday;

/**
 * Tính S = 1 + 2 + ... + n bằng while, với 0 <= n <= 1_000_000.
 *
 * Đối chiếu trên giấy:
 * Với n > 0, viết S xuôi và ngược rồi cộng từng cặp:
 *   S = 1 +   2   + ... + n
 *   S = n + (n-1) + ... + 1
 *  2S = n(n+1), suy ra S = n(n+1)/2.
 * Với n = 0, tổng rỗng bằng 0, cũng khớp công thức.
 * Trong Java, dùng 1L * n * (n + 1) / 2 để nhân bằng long ngay từ đầu.
 * Tổng lớn nhất là 500_000_500_000, vượt giới hạn int nên sum phải là long.
 *
 * Trace n = 3:
 *   Ban đầu: i = 1, sum = 0.
 *   i = 1 <= 3: sum = 0 + 1 = 1; i tăng thành 2.
 *   i = 2 <= 3: sum = 1 + 2 = 3; i tăng thành 3.
 *   i = 3 <= 3: sum = 3 + 3 = 6; i tăng thành 4.
 *   i = 4 > 3: thoát vòng lặp, trả về 6.
 * Với n = 0, điều kiện 1 <= 0 sai ngay nên trả về 0.
 * Với n = -1, ném ngoại lệ trước khi đến vòng lặp.
 */
public class SumWhile {
    public static long sumToN(int n) {
        if (n < 0 || n > 1_000_000) {
            throw new IllegalArgumentException("n phải từ 0 đến 1000000");
        }

        long sum = 0L;
        int i = 1;
        while (i <= n) {
            sum += i;
            i++;
        }
        return sum;
    }

    public static void main(String[] args) {
        checkSum(0, 0L);
        checkSum(1, 1L);
        checkSum(100, 5050L);
        checkSum(3, 6L);
        checkSum(1_000_000, 500_000_500_000L);
        checkRejected(-1);
        checkRejected(1_000_001);
    }

    private static void checkSum(int n, long expected) {
        long actual = sumToN(n);
        long formula = 1L * n * (n + 1) / 2;
        if (actual != expected || actual != formula) {
            throw new AssertionError("n=" + n + ": mong đợi " + expected
                    + ", thực tế " + actual + ", công thức " + formula);
        }
        System.out.printf("n=%d -> %d (PASS)%n", n, actual);
    }

    private static void checkRejected(int n) {
        try {
            sumToN(n);
        } catch (IllegalArgumentException e) {
            System.out.printf("n=%d -> Từ chối: %s (PASS)%n", n, e.getMessage());
            return;
        }
        throw new AssertionError("Phải từ chối n=" + n);
    }
}
