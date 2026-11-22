package week2.monday.jfp;

import java.util.Scanner;

/*
 * BÀI 3 — TRUNG BÌNH: THỐNG KÊ ĐIỂM VỚI SENTINEL
 * Theo Control_Statement.md, mục 8–9, 13: while theo sentinel,
 * xác thực miền giá trị, tránh chia cho 0 và chọn kiểu dữ liệu.
 *
 * YÊU CẦU
 * 1. Nhập các điểm nguyên đến khi gặp -1; chưa biết trước số điểm.
 * 2. Chỉ chấp nhận điểm 0–100. Giá trị sai khác -1 phải báo lỗi
 *    và không được cộng, đếm hoặc dùng làm min/max.
 * 3. Không cộng hay đếm sentinel. Mọi lượt phải đọc được giá trị tiếp.
 * 4. Nếu có điểm: in count, total, average (hai chữ số), min và max.
 *    Khởi tạo min/max từ điểm hợp lệ đầu tiên, không mặc định min = 0.
 * 5. Nếu nhập -1 trước mọi điểm hợp lệ, in "No grades were entered".
 * 6. Dùng long cho tổng và bộ đếm (giả định chúng không vượt long).
 * Chỉ dùng if/else và while, không mảng. Giả định các token là int
 * và đầu vào luôn kết thúc bằng -1; không cần xử lý token sai kiểu.
 *
 * MẪU: 101 97 88 72 -1
 * Diem khong hop le
 * Count: 3
 * Total: 257
 * Average: 85.67
 * Min: 72
 * Max: 97
 *
 * TỰ KIỂM TRA
 * - -1 hoặc 101 -2 -1: không có điểm (chuỗi sau còn báo hai lỗi).
 * - 0 -1: count 1, total 0, average 0.00, min = max = 0.
 * - 100 100 -1: count 2, total 200, average 100.00, min = max = 100.
 */
public class Exercise03SentinelStatistics {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long total = 0;
        long count = 0;
        int minimum = 0;
        int maximum = 0;

        System.out.print("Nhap diem (-1 de dung): ");
        int grade = input.nextInt();
        while (grade != -1) {
            if (grade < 0) {
                System.out.println("Diem khong hop le");
            } else if (grade > 100) {
                System.out.println("Diem khong hop le");
            } else {
                if (count == 0) {
                    minimum = grade;
                    maximum = grade;
                } else {
                    if (grade < minimum) {
                        minimum = grade;
                    }
                    if (grade > maximum) {
                        maximum = grade;
                    }
                }
                total += grade;
                ++count;
            }
            System.out.print("Nhap diem (-1 de dung): ");
            grade = input.nextInt();
        }

        if (count > 0) {
            double average = (double) total / count;
            System.out.printf("Count: %d%nTotal: %d%nAverage: %.2f%nMin: %d%nMax: %d%n",
                    count, total, average, minimum, maximum);
        } else {
            System.out.println("No grades were entered");
        }
    }
}
