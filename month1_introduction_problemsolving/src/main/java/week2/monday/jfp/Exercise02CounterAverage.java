package week2.monday.jfp;

import java.util.Scanner;

/*
 * BÀI 2 — CƠ BẢN MỞ RỘNG: TRUNG BÌNH ĐỦ N ĐIỂM HỢP LỆ
 * Theo Control_Statement.md, mục 6–7, 9, 11–12: while theo bộ đếm,
 * tổng tích luỹ, ép kiểu trước phép chia, += và ++.
 *
 * YÊU CẦU
 * 1. Nhập n trong 1–100; dùng while yêu cầu nhập lại nếu n sai miền.
 * 2. Nhập đủ n điểm nguyên hợp lệ trong 0–100.
 *    Điểm sai miền phải báo "Diem khong hop le", không cộng tổng,
 *    không tăng bộ đếm; tiếp tục nhập cho sinh viên hiện tại.
 * 3. In số điểm, tổng, trung bình với hai chữ số thập phân.
 *    Dùng += để cộng tổng và ++ để tăng bộ đếm.
 * 4. Chỉ dùng while và if/else; không dùng mảng, for, do…while.
 * Giả định mọi token là int và có đủ dữ liệu hợp lệ để hoàn tất.
 *
 * MẪU: 3 97 88 72
 * Count: 3
 * Total: 257
 * Average: 85.67
 *
 * TỰ KIỂM TRA (kết quả không tính lời nhắc)
 * - 0 101 1 100: nhập lại n hai lần; count 1, total 100, average 100.00.
 * - 2 -1 0 101 100: loại -1 và 101; count 2, total 100, average 50.00.
 * - 1 0: count 1, total 0, average 0.00.
 */
public class Exercise02CounterAverage {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Nhap so sinh vien (1..100): ");
        int n = input.nextInt();
        boolean validCount = n >= 1;
        if (n > 100) {
            validCount = false;
        }
        while (!validCount) {
            System.out.print("So luong khong hop le. Nhap lai (1..100): ");
            n = input.nextInt();
            validCount = n >= 1;
            if (n > 100) {
                validCount = false;
            }
        }

        int total = 0;
        int counter = 0;
        while (counter < n) {
            System.out.printf("Nhap diem sinh vien %d: ", counter + 1);
            int grade = input.nextInt();
            if (grade < 0) {
                System.out.println("Diem khong hop le");
            } else if (grade > 100) {
                System.out.println("Diem khong hop le");
            } else {
                total += grade;
                ++counter;
            }
        }

        double average = (double) total / counter;
        System.out.printf("Count: %d%nTotal: %d%nAverage: %.2f%n",
                counter, total, average);
    }
}
