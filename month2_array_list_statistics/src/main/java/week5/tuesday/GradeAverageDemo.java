package week5.tuesday;

import java.util.Arrays;

/** Hai cách tính trung bình bằng double cho mảng điểm [2, 3, 5]. */
public class GradeAverageDemo {
    public static void main(String[] args) {
        int[] grades = {2, 3, 5};

        // Cách 1: duyệt theo index, cộng bằng int rồi ép kiểu TRƯỚC khi chia.
        int sum = 0;
        for (int i = 0; i < grades.length; i++) {
            sum += grades[i];
        }
        double averageByIndex = (double) sum / grades.length;

        // Cách 2: enhanced-for, cộng bằng double nên phép chia cũng là double.
        double doubleSum = 0.0;
        for (int grade : grades) {
            doubleSum += grade;
        }
        double averageByForEach = doubleSum / grades.length;

        System.out.println("Điểm: " + Arrays.toString(grades));
        System.out.println("Cách 1 (index): 10/3 = " + averageByIndex);
        System.out.println("Cách 2 (enhanced-for): 10/3 = " + averageByForEach);
        // Chia hai int trước sẽ mất phần lẻ; gán sang double không khôi phục được.
        double wrongAverage = sum / grades.length;
        System.out.println("Chia int rồi gán sang double (sai): " + wrongAverage);
    }
}
