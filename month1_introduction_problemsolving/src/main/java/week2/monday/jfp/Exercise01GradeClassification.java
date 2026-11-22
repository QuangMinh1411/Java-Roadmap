package week2.monday.jfp;

import java.util.Scanner;

/*
 * BÀI 1 — CƠ BẢN: PHÂN LOẠI ĐIỂM
 * Theo Control_Statement.md, mục 3–5: if, if…else, else if và ?:.
 *
 * YÊU CẦU
 * 1. Nhập một điểm nguyên. Nếu ngoài 0–100, in "Diem khong hop le"
 *    và không phân loại. Không cần yêu cầu nhập lại.
 * 2. Điểm hợp lệ: A từ 90, B từ 80, C từ 70, D từ 60, còn lại F.
 *    Dùng chuỗi if…else if xét các ngưỡng từ cao xuống thấp.
 * 3. Dùng ?: để chọn "Passed" nếu điểm >= 60, ngược lại "Failed".
 * 4. Dùng một if độc lập để in "Excellent!" khi điểm >= 90.
 * Giả định token nhập vào đọc được bằng Scanner.nextInt().
 *
 * MẪU: nhập 90, kết quả (không tính lời nhắc):
 * Grade: A
 * Result: Passed
 * Excellent!
 *
 * TỰ KIỂM TRA
 * - 0, 59: F, Failed; 60, 69: D, Passed; 70, 79: C, Passed.
 * - 80, 89: B, Passed; 90, 100: A, Passed, có Excellent!.
 * - -1 hoặc 101: chỉ báo điểm không hợp lệ.
 */
public class Exercise01GradeClassification {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Nhap diem: ");
        int grade = input.nextInt();

        if (grade < 0) {
            System.out.println("Diem khong hop le");
        } else if (grade > 100) {
            System.out.println("Diem khong hop le");
        } else {
            String letter;
            if (grade >= 90) {
                letter = "A";
            } else if (grade >= 80) {
                letter = "B";
            } else if (grade >= 70) {
                letter = "C";
            } else if (grade >= 60) {
                letter = "D";
            } else {
                letter = "F";
            }

            System.out.printf("Grade: %s%n", letter);
            System.out.printf("Result: %s%n", grade >= 60 ? "Passed" : "Failed");
            if (grade >= 90) {
                System.out.println("Excellent!");
            }
        }
    }
}
