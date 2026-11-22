package week2.monday.jfp;

import java.util.Scanner;

/*
 * BÀI 4 — KHÁ: BÁO CÁO THI NHIỀU LỚP
 * Theo Control_Statement.md, mục 6–10: lồng while với while,
 * phân loại bằng if/else và tổng hợp sau vòng lặp.
 *
 * YÊU CẦU
 * 1. Nhập số lớp trong 1–20; nếu sai miền, yêu cầu nhập lại bằng while.
 * 2. Mỗi lớp nhận đúng 10 kết quả hợp lệ: 1 = đậu, 2 = rớt.
 *    Giá trị khác phải báo lỗi, nhập lại cho cùng sinh viên và không đếm.
 * 3. Sau mỗi lớp, in số đậu/rớt. Nếu hơn 8 người đậu, in
 *    "Bonus to instructor!" và tăng số lớp được thưởng.
 * 4. Sau tất cả các lớp, in tổng đậu/rớt, tỷ lệ đậu (%) với hai chữ số
 *    thập phân và số lớp được thưởng. Tính tỷ lệ bằng phép chia số thực.
 * 5. Đặt lại số đậu/rớt từng lớp ở đầu vòng lặp ngoài;
 *    tổng toàn bộ phải giữ lại giữa các lớp. Không dùng mảng.
 * Giả định mọi token là int, có đủ dữ liệu để nhận đủ các kết quả hợp lệ.
 *
 * MẪU: 1 1 1 1 1 1 1 1 1 1 2
 * Class 1: passed 9, failed 1
 * Bonus to instructor!
 * Total passed: 9
 * Total failed: 1
 * Pass rate: 90.00%
 * Bonus classes: 1
 *
 * TỰ KIỂM TRA
 * - 8 đậu, 2 rớt: tỷ lệ 80.00%, không thưởng (điều kiện > 8).
 * - Chèn 0 hoặc 3 trước một kết quả: báo lỗi, vẫn cần đủ 10 kết quả.
 * - 2 lớp: lớp đầu 10 đậu, lớp sau 10 rớt: tổng 10/10,
 *   tỷ lệ 50.00%, thưởng 1 lớp; lớp sau phải báo 0 đậu, 10 rớt.
 */
public class Exercise04ExamReport {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Nhap so lop (1..20): ");
        int classes = input.nextInt();
        boolean validCount = classes >= 1;
        if (classes > 20) {
            validCount = false;
        }
        while (!validCount) {
            System.out.print("So lop khong hop le. Nhap lai (1..20): ");
            classes = input.nextInt();
            validCount = classes >= 1;
            if (classes > 20) {
                validCount = false;
            }
        }

        int classCounter = 1;
        int totalPasses = 0;
        int totalFailures = 0;
        int bonusClasses = 0;
        while (classCounter <= classes) {
            int passes = 0;
            int failures = 0;
            int studentCounter = 1;
            while (studentCounter <= 10) {
                System.out.printf("Lop %d, sinh vien %d (1 = dau, 2 = rot): ",
                        classCounter, studentCounter);
                int result = input.nextInt();
                if (result == 1) {
                    ++passes;
                    ++studentCounter;
                } else if (result == 2) {
                    ++failures;
                    ++studentCounter;
                } else {
                    System.out.println("Ket qua khong hop le");
                }
            }
            System.out.printf("Class %d: passed %d, failed %d%n",
                    classCounter, passes, failures);
            if (passes > 8) {
                System.out.println("Bonus to instructor!");
                ++bonusClasses;
            }
            totalPasses += passes;
            totalFailures += failures;
            ++classCounter;
        }

        double passRate = 100.0 * totalPasses / (totalPasses + totalFailures);
        System.out.printf("Total passed: %d%nTotal failed: %d%n", totalPasses, totalFailures);
        System.out.printf("Pass rate: %.2f%%%nBonus classes: %d%n", passRate, bonusClasses);
    }
}
