package week3.wednesday;

import java.util.Locale;
import java.util.Scanner;

/**
 * Nhập điểm trong [0, 10] đến khi gặp giá trị báo dừng -1.
 * Tham khảo: Control Statement.md (§3.5–3.8, §4.9–4.10)
 * và Fundamental Programming Structure.md (§3.3–3.5, §3.7–3.8).
 *
 * Áp dụng while, if/else, break, continue, cộng dồn và biến đếm.
 * Kiểm tra -1 trước khi xét miền: -1 chỉ báo dừng, không phải điểm lỗi.
 * Chỉ điểm hợp lệ mới được cộng vào sum và làm tăng count.
 * sum là double nên sum / count là phép chia thực, giữ được phần lẻ.
 * Kiểm tra count > 0 trước khi chia để tránh chia cho 0.
 *
 * Các giá trị nhập cách nhau bằng khoảng trắng hoặc xuống dòng:
 *   8 6 -1  -> count=2, average=7.00
 *   -1      -> count=0, chưa có điểm
 *   11 5 -1 -> báo lỗi cho 11; count=1, average=5.00
 * Điểm thập phân dùng dấu chấm, ví dụ: 7.5.
 */
public class SentinelGradeAverage {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        input.useLocale(Locale.US);

        double sum = 0.0;
        int count = 0;

        while (true) {
            System.out.print("Nhập điểm [0, 10] (-1 để dừng): ");

            // Kết thúc an toàn nếu luồng nhập đã hết dữ liệu.
            if (!input.hasNext()) {
                break;
            }
            if (!input.hasNextDouble()) {
                System.out.println("Lỗi: phải nhập số; bỏ qua: " + input.next());
                continue;
            }

            double grade = input.nextDouble();
            if (grade == -1) {
                break;
            }

            // Điều kiện này cũng loại NaN và các giá trị vô cực.
            if (grade >= 0 && grade <= 10) {
                sum += grade;
                count++;
            } else {
                System.out.println("Lỗi: điểm phải thuộc [0, 10]; bỏ qua: " + grade);
            }
        }

        System.out.println("count=" + count);
        if (count > 0) {
            double average = sum / count;
            System.out.printf(Locale.US, "average=%.2f%n", average);
        } else {
            System.out.println("chưa có điểm");
        }
    }
}
