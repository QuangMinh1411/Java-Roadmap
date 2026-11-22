package week4.thursday;

import java.util.Objects;
import java.util.Random;

/**
 * Minh họa sinh số trong một đoạn đóng dựa trên §5.4 của Methods.md.
 * API sinh số theo [origin, bound): lấy origin, không lấy bound.
 * Muốn lấy cả max, đặt bound = max + 1.
 * Seed cố định giúp tái hiện kết quả khi giữ nguyên thứ tự các lời gọi.
 */
public class RandomBetweenDemo {
    private static final int TRIALS = 100;

    /**
     * Hợp đồng:
     * Tiền điều kiện: min <= max và random khác null.
     * Hậu điều kiện: trả một int trong [min, max], bao gồm cả hai đầu.
     * Khi min == max, kết quả luôn bằng min.
     * Hàm dùng và cập nhật trạng thái bộ sinh được truyền vào, không nhập/xuất.
     *
     * @param min cận dưới, được lấy
     * @param max cận trên, được lấy
     * @param random bộ sinh đã được khởi tạo ở nơi gọi
     * @return số nguyên ngẫu nhiên trong đoạn đóng [min, max]
     * @throws IllegalArgumentException nếu min > max
     * @throws NullPointerException nếu random là null
     */
    public static int randomBetween(int min, int max, Random random) {
        if (min > max) {
            throw new IllegalArgumentException("min phải <= max (min=" + min + ", max=" + max + ")");
        }
        Objects.requireNonNull(random, "random không được null");

        // Dùng long trước khi cộng 1 để không tràn khi max = Integer.MAX_VALUE.
        // Ví dụ [3, 3] -> nextLong(3, 4), [1, 6] -> nextLong(1, 7).
        // Kết quả nằm trong miền int nên ép về int không mất giá trị.
        return (int) random.nextLong(min, (long) max + 1);
    }

    public static void main(String[] args) {
        Random random = new Random(20261117L);
        checkRange(3, 3, random); // 100 lần đều phải là 3.
        checkRange(1, 6, random); // 100 lần không được ra 0, 7 hoặc số ngoài miền khác.

        // Kiểm tra thêm biên int để minh họa lý do dùng long trong công thức.
        checkRange(Integer.MAX_VALUE, Integer.MAX_VALUE, random);
        checkInvalidRange(random);
    }

    private static void checkRange(int min, int max, Random random) {
        int outsideRange = 0;
        int zeros = 0;
        int sevens = 0;

        for (int i = 0; i < TRIALS; i++) {
            int value = randomBetween(min, max, random);
            if (value < min || value > max) {
                outsideRange++;
            }
            if (value == 0) {
                zeros++;
            }
            if (value == 7) {
                sevens++;
            }
        }

        if (outsideRange != 0) {
            throw new AssertionError("Miền [" + min + "," + max + "]: có "
                    + outsideRange + " kết quả ngoài miền");
        }
        System.out.printf("Miền [%d,%d], %d lần: ngoài miền = %d (PASS)%n",
                min, max, TRIALS, outsideRange);
        if (min == max) {
            System.out.println("Cả " + TRIALS + " kết quả đều bằng " + min);
        }
        if (min == 1 && max == 6) {
            System.out.printf("Số lần ra 0 = %d; số lần ra 7 = %d%n", zeros, sevens);
        }
    }

    private static void checkInvalidRange(Random random) {
        try {
            randomBetween(6, 1, random);
        } catch (IllegalArgumentException e) {
            System.out.println("Đã chặn miền đảo: " + e.getMessage() + " (PASS)");
            return;
        }
        throw new AssertionError("Phải từ chối min > max");
    }
}
