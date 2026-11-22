package week4;

import java.util.random.RandomGenerator;

/**
 * Bài 3 — Mô phỏng tung đồng xu công bằng 1_000_000 lần.
 * Kiến thức: enum, phương thức trả về enum, switch mũi tên và vòng lặp.
 */
public class CoinFlipSimulation {
    private static final RandomGenerator random = RandomGenerator.getDefault();

    public enum CoinFace { HEADS, TAILS }

    public static CoinFace flipCoin() {
        // nextBoolean() cho hai kết quả có cùng xác suất.
        return random.nextBoolean() ? CoinFace.HEADS : CoinFace.TAILS;
    }

    public static void main(String[] args) {
        final int totalFlips = 1_000_000;
        int headsCount = 0;
        int tailsCount = 0;

        for (int i = 0; i < totalFlips; i++) {
            switch (flipCoin()) {
                case HEADS -> headsCount++;
                case TAILS -> tailsCount++;
            }
        }

        // Nhân với 100.0 trước khi chia để giữ phần thập phân.
        double headsPercentage = headsCount * 100.0 / totalFlips;
        double tailsPercentage = tailsCount * 100.0 / totalFlips;

        System.out.printf("Tổng số lần tung: %,d%n", totalFlips);
        System.out.printf("HEADS (ngửa): %,d lần — %.4f%%%n", headsCount, headsPercentage);
        System.out.printf("TAILS (sấp) : %,d lần — %.4f%%%n", tailsCount, tailsPercentage);
        // Hai tỷ lệ thường gần 50%, không nhất thiết bằng đúng 50%.
    }
}
