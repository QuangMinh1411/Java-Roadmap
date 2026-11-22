package week4.monday;

/**
 * Minh họa giới hạn giá trị trong đoạn [min, max].
 * clamp chỉ tính toán; main và các hàm kiểm tra phụ trách hiển thị kết quả.
 */
public class ClampDemo {
    /**
     * Hợp đồng:
     * Tiền điều kiện: min <= max; value nhận mọi giá trị int.
     * Hậu điều kiện: trả min nếu value < min, max nếu value > max,
     * còn lại trả nguyên value. Kết quả luôn thuộc đoạn [min, max].
     * Khi min == max, kết quả luôn bằng min.
     * Hàm chỉ tính và trả kết quả, không đọc dữ liệu hoặc in thông báo.
     *
     * @param value giá trị cần giới hạn
     * @param min cận dưới (bao gồm)
     * @param max cận trên (bao gồm)
     * @return giá trị sau khi giới hạn trong đoạn [min, max]
     * @throws IllegalArgumentException nếu min > max
     */
    public static int clamp(int value, int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min phải <= max (min=" + min + ", max=" + max + ")");
        }
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    public static void main(String[] args) {
        checkClamp(12, 0, 10, 10);
        checkClamp(-1, 0, 10, 0);
        checkClamp(5, 0, 10, 5);
        checkClamp(0, 0, 10, 0);
        checkClamp(10, 0, 10, 10);
        checkClamp(12, 5, 5, 5);
        checkInvalidRange();
    }

    private static void checkClamp(int value, int min, int max, int expected) {
        int actual = clamp(value, min, max);
        if (actual != expected) {
            throw new AssertionError("clamp(" + value + ", " + min + ", " + max
                    + "): mong đợi " + expected + ", nhận " + actual);
        }
        System.out.printf("clamp(%d, %d, %d) = %d (PASS)%n", value, min, max, actual);
    }

    private static void checkInvalidRange() {
        try {
            clamp(5, 10, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("clamp(5, 10, 0) -> Đã chặn: " + e.getMessage() + " (PASS)");
            return;
        }
        throw new AssertionError("Phải chặn clamp khi min > max");
    }
}
