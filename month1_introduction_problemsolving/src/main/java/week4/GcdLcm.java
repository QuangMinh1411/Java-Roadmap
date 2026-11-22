package week4;

/**
 * Ước chung lớn nhất (GCD) và bội chung nhỏ nhất (LCM).
 * Miền đầu vào: 0 <= a, b <= Integer.MAX_VALUE (2_147_483_647).
 * gcd(0, 0) không xác định: ném IllegalArgumentException, giống week3.EuclidGcd.
 * Quy ước lcm(a, 0) = lcm(0, b) = 0, kể cả lcm(0, 0).
 * gcd trả int; lcm trả long để chứa mọi kết quả trong miền đã công bố.
 */
public class GcdLcm {
    public static int gcd(int a, int b) {
        requireNonNegative(a, b);
        if (a == 0 && b == 0) {
            throw new IllegalArgumentException("gcd(0, 0) không xác định.");
        }

        // Euclid: gcd(a, b) = gcd(b, a % b). Chỉ chia khi b khác 0.
        while (b != 0) {
            int remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }

    public static long lcm(int a, int b) {
        requireNonNegative(a, b);
        if (a == 0 || b == 0) {
            return 0L; // Xử lý trước để không gọi gcd(0, 0) hoặc chia cho 0.
        }

        // Gọi lại gcd; chia trước, ép long TRƯỚC phép nhân để không tràn int.
        // Kết quả <= a*b <= (2^31-1)^2 < Long.MAX_VALUE.
        return (long) (a / gcd(a, b)) * b;
    }

    private static void requireNonNegative(int a, int b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("a và b phải là số nguyên không âm.");
        }
    }

    public static void main(String[] args) {
        System.out.println("gcd(12, 18) = " + gcd(12, 18)); // 6
        System.out.println("lcm(12, 18) = " + lcm(12, 18)); // 36
        System.out.println("gcd(0, 18) = " + gcd(0, 18));   // 18
        System.out.println("gcd(12, 0) = " + gcd(12, 0));   // 12
        System.out.println("lcm(0, 18) = " + lcm(0, 18));   // 0
        System.out.println("lcm(12, 0) = " + lcm(12, 0));   // 0
        System.out.println("lcm(0, 0) = " + lcm(0, 0));     // 0
    }
}
