package week1.friday.LJ;

import java.util.Scanner;

public class ScoreClassify {
    static void main() {
        Scanner input = new Scanner(System.in);
        System.out.print("Nhập điểm (0 - 100): ");
        int grade = input.nextInt();
        if (grade < 0 || grade > 100) {
            System.out.println("Lỗi: Điểm phải nằm trong khoảng từ 0 đến 100.");
        } else {
            // Chuỗi phân loại if...else if...else
            if (grade >= 90) {
                System.out.println("Xếp loại: A");
            } else if (grade >= 80) {
                System.out.println("Xếp loại: B");
            } else if (grade >= 70) {
                System.out.println("Xếp loại: C");
            } else if (grade >= 60) {
                System.out.println("Xếp loại: D");
            } else {
                System.out.println("Xếp loại: F");
            }

            // Toán tử điều kiện ?: tạo một giá trị
            String status = (grade >= 60) ? "Đạt" : "Trượt";
            System.out.printf("Trạng thái: %s%n", status);
        }
    }
}
