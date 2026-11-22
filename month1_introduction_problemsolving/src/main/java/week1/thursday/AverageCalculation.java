package week1.thursday;

import java.util.Locale;

/*
 * Tính trung bình của 7, 8, 8: tổng 23, trung bình gần 7.6667.
 * So sánh chia nguyên với ép kiểu trước phép chia.
 */
public class AverageCalculation {
    public static void main(String[] args) {
        int first = 7;
        int second = 8;
        int third = 8;
        int sum = first + second + third;
        int count = 3;

        // Sai: int / int cho 7, sau đó mới chuyển thành double 7.0.
        double wrongAverage = sum / count;
        // Đúng: ép tổng sang double trước khi chia để giữ phần lẻ.
        double correctAverage = (double) sum / count;
        // (double)(sum / count) vẫn sai vì phép chia nguyên đã xảy ra.

        System.out.println("Trung bình sai: " + wrongAverage);
        System.out.println("Trung bình đúng: " + correctAverage);
        System.out.printf(Locale.ROOT, "Làm tròn 4 chữ số thập phân: %.4f%n", correctAverage);
    }
}
