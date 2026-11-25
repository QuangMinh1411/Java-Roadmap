package week5;

import java.util.Arrays;

/** Bài ôn 1: histogram điểm; báo và bỏ qua từng điểm không hợp lệ. */
public class GradeHistogramDemo {
    public static void main(String[] args) {
        double[] grades = {0, 4.9, 5, 6.5, 8, 10, -1, 11};
        String[] intervals = {"[0,5)", "[5,6.5)", "[6.5,8)", "[8,10]"};
        int[] counts = new int[intervals.length];
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
            counts[bucket]++;
            validCount++;
        }

        int total = 0;
        for (int i = 0; i < counts.length; i++) {
            System.out.println(intervals[i] + ": " + counts[i]);
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
