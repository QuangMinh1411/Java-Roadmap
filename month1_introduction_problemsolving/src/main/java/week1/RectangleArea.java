package week1;

import java.util.Locale;
import java.util.Scanner;

/*
 * Nhập hai cạnh hình chữ nhật bằng mét, giả sử là các số thực dương.
 * Kiểu dữ liệu: double cho hai cạnh và diện tích để nhận được số thập phân.
 * Phép tính: area = length * width (diện tích tính bằng mét vuông).
 * Dữ liệu thử: 2.5 4 -> Diện tích: 10.00
 * %.2f in đúng 2 chữ số sau dấu thập phân.
 *
 * Biên dịch và chạy từ thư mục gốc JavaRoadmap:
 * mkdir -p out
 * javac -encoding UTF-8 -d out month1_introduction_problemsolving/src/main/java/week1/RectangleArea.java
 * java -cp out week1.RectangleArea
 */
public class RectangleArea {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in).useLocale(Locale.ROOT);

        System.out.print("Nhập chiều dài: ");
        double length = input.nextDouble();
        System.out.print("Nhập chiều rộng: ");
        double width = input.nextDouble();

        double area = length * width;

        System.out.printf(Locale.ROOT, "%nDiện tích: %.2f%n", area);
    }
}
