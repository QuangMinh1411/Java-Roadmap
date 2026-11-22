package week4;

import java.util.random.RandomGenerator;

/**
 * Bài 2 — Sinh số thuộc tập {5, 10, 15, 20, 25, 30}.
 * Kiến thức: trường static, RandomGenerator và phép dịch/co giãn.
 * Cần Java 17 trở lên; mỗi lần chạy main() có thể cho chuỗi khác nhau.
 */
public class RandomStepNumber {
    private static final RandomGenerator random = RandomGenerator.getDefault();

    public static int getRandomStepNumber() {
        // nextInt(6) trả về 0..5: nhân 5 được 0..25, cộng 5 được 5..30.
        // Sáu giá trị có cùng xác suất được chọn.
        return 5 + 5 * random.nextInt(6);
    }

    public static void main(String[] args) {
        System.out.println("20 số ngẫu nhiên theo bước nhảy 5:");
        for (int i = 1; i <= 20; i++) {
            System.out.printf("%d ", getRandomStepNumber());
        }
        System.out.println();
        // Mọi số đều thuộc tập đã cho; không bắt buộc xuất hiện đủ cả sáu số.
    }
}
