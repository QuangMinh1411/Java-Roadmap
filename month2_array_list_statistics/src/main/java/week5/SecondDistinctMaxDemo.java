package week5;

import java.util.Arrays;

/**
 * Tìm điểm lớn thứ hai phân biệt bằng mảng, vòng lặp và if
 * (Arrays_and_ArrayLists.md: mục 6.3, 6.6 và bài tập Min/Max).
 * Không sắp xếp, không sửa mảng nguồn. Thời gian O(n), bộ nhớ phụ O(1).
 *
 * Chạy từ thư mục gốc dự án:
 * java month2_array_list_statistics/src/main/java/week5/SecondDistinctMaxDemo.java
 */
public class SecondDistinctMaxDemo {
    public static void main(String[] args) {
        printSecondDistinctMax(new int[]{9, 9, 7, 5}); // 7, bỏ qua điểm 9 trùng.
        printSecondDistinctMax(new int[]{9, 9});       // Không đủ hai giá trị.
        printSecondDistinctMax(new int[]{5, 7, 9, 7}); // 7, cập nhật khi gặp max mới.
        printSecondDistinctMax(new int[]{9, 5, 7});    // 7, cập nhật riêng max thứ hai.
        printSecondDistinctMax(new int[]{-3, -1, -1, -2}); // -2, đúng với số âm.
        printSecondDistinctMax(new int[]{0, Integer.MIN_VALUE});
        printSecondDistinctMax(new int[]{7});
        printSecondDistinctMax(new int[]{});
        printSecondDistinctMax(null);
    }

    /**
     * Trả về giá trị lớn nhất trong các phần tử nhỏ hơn max của mảng.
     * @throws IllegalArgumentException nếu mảng null hoặc có ít hơn hai giá trị phân biệt
     */
    public static int secondDistinctMax(int[] grades) {
        if (grades == null) {
            throw new IllegalArgumentException("Mảng điểm không được null.");
        }
        if (grades.length == 0) {
            throw new IllegalArgumentException("Không đủ hai giá trị phân biệt.");
        }

        int largest = grades[0];
        // Chỉ đọc secondLargest làm kết quả khi hasSecond là true.
        // Không dùng 0 hoặc Integer.MIN_VALUE làm dấu hiệu "chưa tìm thấy".
        int secondLargest = grades[0];
        boolean hasSecond = false;

        for (int i = 1; i < grades.length; i++) {
            int grade = grades[i];
            if (grade > largest) {
                // Max cũ trở thành giá trị lớn thứ hai khi có max mới.
                secondLargest = largest;
                largest = grade;
                hasSecond = true;
            } else if (grade < largest && (!hasSecond || grade > secondLargest)) {
                secondLargest = grade;
                hasSecond = true;
            }
            // grade == largest: bỏ qua để hai giá trị luôn phân biệt.
        }

        if (!hasSecond) {
            throw new IllegalArgumentException("Không đủ hai giá trị phân biệt.");
        }
        return secondLargest;
    }

    private static void printSecondDistinctMax(int[] grades) {
        try {
            int result = secondDistinctMax(grades);
            System.out.printf("%s → %d%n", Arrays.toString(grades), result);
        } catch (IllegalArgumentException e) {
            // Báo lỗi cho từng ca rồi tiếp tục chạy các ca sau.
            System.out.printf("%s → %s%n", Arrays.toString(grades), e.getMessage());
        }
    }
}
