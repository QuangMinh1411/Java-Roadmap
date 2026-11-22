package week4.tests;

import week4.GcdLcm;

/** Kiểm tra ví dụ, số 0, miền không hợp lệ và biên chống tràn số. */
public class GcdLcmTest {
    public static void main(String[] args) {
        check(12, 18, 6, 36);
        check(18, 12, 6, 36);
        check(0, 18, 18, 0);
        check(12, 0, 12, 0);
        check(17, 13, 1, 221);
        check(12, 12, 12, 12);
        check(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        check(Integer.MAX_VALUE, Integer.MAX_VALUE - 1, 1, 4_611_686_011_984_936_962L);

        rejects(() -> GcdLcm.gcd(0, 0));
        if (GcdLcm.lcm(0, 0) != 0) {
            throw new AssertionError("lcm(0, 0) phải bằng 0.");
        }
        System.out.println("PASS (0, 0): gcd từ chối; lcm = 0");

        int[][] negativePairs = {{-1, 18}, {12, -1}, {Integer.MIN_VALUE, 0}};
        for (int[] pair : negativePairs) {
            rejects(() -> GcdLcm.gcd(pair[0], pair[1]));
            rejects(() -> GcdLcm.lcm(pair[0], pair[1]));
            System.out.printf("PASS (%d, %d): cả hai hàm từ chối số âm%n", pair[0], pair[1]);
        }
        System.out.println("Tất cả kiểm thử GCD/LCM đạt.");
    }

    private static void check(int a, int b, int expectedGcd, long expectedLcm) {
        int actualGcd = GcdLcm.gcd(a, b);
        long actualLcm = GcdLcm.lcm(a, b);
        if (actualGcd != expectedGcd || actualLcm != expectedLcm) {
            throw new AssertionError("Sai kết quả tại (" + a + ", " + b + "): "
                    + actualGcd + ", " + actualLcm);
        }
        System.out.printf("PASS (%d, %d): gcd=%d, lcm=%d%n", a, b, actualGcd, actualLcm);
    }

    private static void rejects(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException expected) {
            return;
        }
        throw new AssertionError("Phải từ chối đối số ngoài miền xác định.");
    }
}
