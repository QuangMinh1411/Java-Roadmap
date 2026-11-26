package week5;

import java.util.Arrays;

/**
 * Minh họa mảng đếm tần suất theo Arrays_and_ArrayLists.md (mục 6.10, 6.13).
 * Điểm nguyên từ 0 đến 10 được dùng trực tiếp làm chỉ số trong mảng 11 ô.
 *
 * Chính sách: báo và bỏ qua từng điểm ngoài [0,10], tiếp tục đếm điểm hợp lệ.
 * Không ép điểm sai về biên, không sửa mảng nguồn. Mảng rỗng có 11 ô đếm bằng 0.
 * Thời gian O(n + 11), bộ nhớ cho mảng đếm cố định O(1).
 *
 * Chạy từ thư mục gốc dự án:
 * java month2_array_list_statistics/src/main/java/week5/GradeFrequencyDemo.java
 */
public class GradeFrequencyDemo {
    public static void main(String[] args) {
        printFrequencies(new int[]{0, 10, 10, 5});
        printFrequencies(new int[]{0, 10, 10, 5, 11, -1});
        printFrequencies(new int[]{});
    }

    /**
     * Trả về mảng mới: counts[grade] là số lần xuất hiện của điểm hợp lệ grade.
     * Điểm ngoài [0,10] được báo kèm chỉ số đầu vào rồi bỏ qua.
     * @throws IllegalArgumentException nếu mảng điểm null
     */
    public static int[] countFrequencies(int[] grades) {
        if (grades == null) {
            throw new IllegalArgumentException("Mảng điểm không được null.");
        }

        int[] counts = new int[11]; // Chỉ số 0..10; các ô tự động khởi tạo bằng 0.
        for (int i = 0; i < grades.length; i++) {
            int grade = grades[i];
            // Kiểm tra TRƯỚC counts[grade] để tránh truy cập ngoài miền mảng.
            if (grade < 0 || grade >= counts.length) {
                System.out.printf("Từ chối grades[%d]=%d: điểm phải thuộc [0,10].%n",
                        i, grade);
                continue;
            }
            counts[grade]++; // Mỗi điểm hợp lệ chỉ tăng đúng một ô.
        }
        return counts;
    }

    private static void printFrequencies(int[] grades) {
        System.out.println("\nĐiểm đầu vào: " + Arrays.toString(grades));
        int[] counts = countFrequencies(grades);
        int validCount = 0;

        for (int grade = 0; grade < counts.length; grade++) {
            System.out.printf("count%d = %d%n", grade, counts[grade]);
            validCount += counts[grade];
        }

        System.out.println("Mảng tần suất: " + Arrays.toString(counts));
        System.out.println("Tổng tần suất / số điểm hợp lệ: " + validCount);
        System.out.println("Số điểm bị từ chối: " + (grades.length - validCount));
    }
}
