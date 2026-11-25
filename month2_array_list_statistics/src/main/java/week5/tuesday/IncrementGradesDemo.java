package week5.tuesday;

import java.util.Arrays;

/** So sánh gán vào biến enhanced-for với ghi trực tiếp vào phần tử theo index. */
public class IncrementGradesDemo {
    public static void main(String[] args) {
        int[] grades = {2, 3, 5};
        int[] before = grades.clone();
        System.out.println("Ban đầu: " + Arrays.toString(grades));

        for (int grade : grades) {
            // grade nhận bản sao giá trị int; phép gán chỉ thay đổi biến cục bộ.
            grade = grade + 1;
            System.out.println("Biến grade sau gán: " + grade);
        }
        System.out.println("Sau enhanced-for: " + Arrays.toString(grades));
        System.out.println("Mảng vẫn bằng ban đầu: " + Arrays.equals(grades, before));

        for (int i = 0; i < grades.length; i++) {
            // Ghi kết quả vào chính ô mảng tại vị trí i.
            grades[i] = grades[i] + 1;
        }
        System.out.println("Sau tăng bằng index: " + Arrays.toString(grades));
        System.out.println("Đúng [3, 4, 6]: " + Arrays.equals(grades, new int[]{3, 4, 6}));
    }
}
