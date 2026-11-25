package week5.tuesday;

import java.util.Arrays;

/** Đếm điểm dưới trung bình bằng hai lượt duyệt, không sửa mảng đầu vào. */
public class CountBelowAverageDemo {
    public static void main(String[] args) {
        int[] grades = {2, 3, 7};
        int[] before = grades.clone();

        // Lượt 1: tính tổng bằng double để tránh phép chia số nguyên.
        double sum = 0.0;
        for (int grade : grades) {
            sum += grade;
        }
        double average = sum / grades.length;

        // Lượt 2: chỉ đọc và đếm điểm nhỏ hơn trung bình; không tính điểm bằng nó.
        int count = 0;
        for (int grade : grades) {
            if (grade < average) {
                count++;
            }
        }

        System.out.println("Điểm: " + Arrays.toString(grades));
        System.out.println("Trung bình: " + average);
        System.out.println("Số điểm dưới trung bình: " + count);
        System.out.println("Mảng đầu vào không đổi: " + Arrays.equals(grades, before));
    }
}
