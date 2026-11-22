package week3;

/**
 * Tính UCLN bằng vòng lặp Euclid với phép chia lấy dư.
 *
 * Hợp đồng:
 * - a, b là số nguyên không âm; ít nhất một số khác 0.
 * - UCLN(a, 0) = a với a > 0; UCLN(0, b) = b với b > 0.
 * - (0, 0) không xác định: ném IllegalArgumentException.
 * - Đầu vào âm cũng bị từ chối bằng IllegalArgumentException.
 *
 * Trace (a, b) = (48, 18):
 *   Bước | a trước | b trước | r = a % b | a sau | b sau
 *      1 |      48 |      18 |          12 |    18 |    12
 *      2 |      18 |      12 |           6 |    12 |     6
 *      3 |      12 |       6 |           0 |     6 |     0
 *   b = 0: dừng, trả về a = 6.
 *
 * Trace (a, b) = (0, 5):
 *   Bước | a trước | b trước | r = a % b | a sau | b sau
 *      1 |       0 |       5 |           0 |     5 |     0
 *   b = 0: dừng, trả về a = 5.
 *
 * Trace (0, 0): vi phạm hợp đồng, báo không xác định trước vòng lặp.
 * Không thực hiện phép chia lấy dư cho 0.
 */
public class EuclidGcd {
    public static int gcd(int a, int b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("a và b phải là số nguyên không âm.");
        }
        if (a == 0 && b == 0) {
            throw new IllegalArgumentException("UCLN(0, 0) không xác định.");
        }

        // UCLN(a, b) = UCLN(b, a % b).
        // Khi b > 0, 0 <= remainder < b nên b giảm dần tới 0.
        while (b != 0) {
            int remainder = a % b; // Lưu số dư trước khi thay đổi a.
            a = b;
            b = remainder;
        }
        return a;
    }

    public static void main(String[] args) {
        checkGcd(48, 18, 6);
        checkGcd(0, 5, 5);
        checkGcd(5, 0, 5);
        checkGcd(17, 13, 1);
        checkGcd(12, 12, 12);

        try {
            gcd(0, 0);
            throw new AssertionError("Phải từ chối UCLN(0, 0).");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + " (PASS)");
        }
    }

    private static void checkGcd(int a, int b, int expected) {
        int actual = gcd(a, b);
        if (actual != expected) {
            throw new AssertionError("UCLN(" + a + ", " + b + "): mong đợi "
                    + expected + ", thực tế " + actual);
        }
        System.out.printf("UCLN(%d, %d) = %d (PASS)%n", a, b, actual);
    }
}
