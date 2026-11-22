package week1.wednesday.LJ;

import java.util.Locale;
import java.util.Scanner;

/*
 * BÀI 2 — CƠ BẢN MỞ RỘNG: MÁY TÍNH BỐN PHÉP TOÁN
 * Kiến thức: double, if/else, switch, toán tử số học và so sánh.
 *
 * ĐỀ BÀI
 * 1. Nhập hai số double và một phép toán +, -, *, /.
 * 2. Dùng switch chọn phép tính, dùng if/else ngăn phép chia cho 0.
 * 3. Báo lỗi khi phép toán không được hỗ trợ.
 * Thử thách tùy chọn: viết lại bằng switch expression (Java 14 trở lên).
 *
 * DỮ LIỆU VÀ KẾT QUẢ MẪU (không tính lời nhắc)
 * 12 / 5  -> Kết quả: 2.4
 * 12 / 0  -> Không thể chia cho 0
 * 12 ^ 2  -> Phép toán không hợp lệ
 *
 * CHẠY RIÊNG: chạy main() trong IntelliJ và nhập theo dạng: số phép-toán số.
 * Dùng dấu chấm cho phần thập phân, ví dụ: 1.5 + 2.5.
 */
public class Exercise02Calculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in).useLocale(Locale.ROOT);
        System.out.print("Nhập phép tính (ví dụ 12 / 5): ");
        if (!input.hasNextDouble()) {
            System.out.println("Dữ liệu không hợp lệ: cần nhập số thứ nhất.");
            return;
        }
        double firstNumber = input.nextDouble();
        if (!input.hasNext()) {
            System.out.println("Dữ liệu không hợp lệ: thiếu phép toán.");
            return;
        }
        String operator = input.next();
        if (!input.hasNextDouble()) {
            System.out.println("Dữ liệu không hợp lệ: cần nhập số thứ hai.");
            return;
        }
        double secondNumber = input.nextDouble();

        if (operator.equals("/") && secondNumber == 0) {
            System.out.println("Không thể chia cho 0");
            return;
        }

        String result = switch (operator) {
            case "+" -> "Kết quả: " + (firstNumber + secondNumber);
            case "-" -> "Kết quả: " + (firstNumber - secondNumber);
            case "*" -> "Kết quả: " + (firstNumber * secondNumber);
            case "/" -> "Kết quả: " + (firstNumber / secondNumber);
            default -> "Phép toán không hợp lệ";
        };
        System.out.println(result);
    }
}