package week5;

import java.util.Arrays;

/**
 * Minh hoạ mảng đếm tần suất, enhanced for và biểu đồ bằng String.repeat()
 * theo ghi chú Arrays_and_ArrayLists.md (mục 6.10, 6.13 và mini project).
 *
 * <p>Các khoảng: [0,5), [5,6.5), [6.5,8), [8,10]. Dấu [ nhận mốc trái;
 * dấu ) loại mốc phải, riêng khoảng cuối nhận cả điểm 10.
 * Với {0, 4.9, 5, 6.5, 8, 10}, kết quả là {2, 1, 1, 2}.
 * Điểm -1 và 11 bị từ chối, không làm tăng tổng các cột.
 */
public class GradeHistogramDemo {
    public static void main(String[] args) {
        double[] grades = {0, 4.9, 5, 6.5, 8, 10, -1, 11};
        String[] intervals = {"[0,5)", "[5,6.5)", "[6.5,8)", "[8,10]"};
        int[] counts = new int[intervals.length]; // Bốn biến đếm ban đầu đều bằng 0.
        int validCount = 0;
        int rejectedCount = 0;

        System.out.println("Điểm đầu vào: " + Arrays.toString(grades));
        for (double grade : grades) {
            int bucket;
            try {
                bucket = bucketIndex(grade);
            } catch (IllegalArgumentException exception) {
                rejectedCount++;
                System.out.println("Từ chối: " + exception.getMessage());
                continue; // Điểm sai không được tính vào bất kỳ cột nào.
            }
            // Mỗi điểm hợp lệ chỉ thuộc một khoảng, nên chỉ tăng một cột.
            counts[bucket]++;
            validCount++;
        }

        int total = 0;
        for (int i = 0; i < counts.length; i++) {
            System.out.printf("%-8s: %d %s%n", intervals[i], counts[i], "*".repeat(counts[i]));
            total += counts[i];
        }
        System.out.println("Histogram: " + Arrays.toString(counts));
        System.out.println("Số điểm hợp lệ: " + validCount);
        System.out.println("Số điểm bị từ chối: " + rejectedCount);
        System.out.println("Tổng các cột: " + total);
        System.out.println("Tổng các cột bằng số điểm hợp lệ: " + (total == validCount));
    }

    /**
     * Trả về chỉ số cột từ 0 đến 3; khoảng cuối bao gồm điểm 10.
     * @throws IllegalArgumentException nếu điểm không hữu hạn hoặc ngoài [0,10]
     */
    public static int bucketIndex(double grade) {
        if (!Double.isFinite(grade) || grade < 0 || grade > 10) {
            throw new IllegalArgumentException("Điểm " + grade + " phải hữu hạn và thuộc [0,10].");
        }
        // Kiểm tra lần lượt cận trên: 5, 6.5 và 8 thuộc khoảng kế tiếp.
        // Không ép điểm sang int vì sẽ làm mất phần thập phân.
        if (grade < 5) {
            return 0;
        } else if (grade < 6.5) {
            return 1;
        } else if (grade < 8) {
            return 2;
        } else {
            return 3;
        }
    }
}
