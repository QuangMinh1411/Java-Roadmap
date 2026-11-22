package week1;

import java.util.Locale;
import java.util.Scanner;

/*
 * Nhập cân nặng (kg) và chiều cao (m), tính weight / (height * height).
 * TIỀN ĐIỀU KIỆN: nhập đúng hai số thực hữu hạn, cân nặng > 0 và chiều cao > 0.
 * Giả sử đầu vào đáp ứng tiền điều kiện; chiều cao phải khác 0 để tránh chia cho 0.
 * Chỉ tính và in chỉ số BMI với 2 chữ số thập phân.
 *
 * DỮ LIỆU THỬ (dùng dấu chấm thập phân)
 * 70 1.75 -> BMI: 22.86
 * 50 1.60 -> BMI: 19.53
 * 80 1.80 -> BMI: 24.69
 */
public class BMI {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in).useLocale(Locale.ROOT);

        System.out.print("Nhập cân nặng (kg): ");
        double weight = input.nextDouble();
        System.out.print("Nhập chiều cao (m): ");
        double height = input.nextDouble();
        double bmi = weight / (height * height);

        System.out.printf(Locale.ROOT, "%nBMI: %.2f%n", bmi);
    }
}
