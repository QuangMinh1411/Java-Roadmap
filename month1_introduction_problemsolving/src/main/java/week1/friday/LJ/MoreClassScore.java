package week1.friday.LJ;

import java.util.Scanner;

public class MoreClassScore {
    static void main() {
        Scanner input = new Scanner(System.in);

        int total = 0;
        int count = 0;
        int max = -1;
        int min = 101;

        System.out.print("Nhập điểm (0 - 100) hoặc -1 để kết thúc: ");
        int grade = input.nextInt();

        // Vòng lặp dừng khi gặp sentinel
        while (grade != -1) {
            if (grade < 0 || grade > 100) {
                System.out.printf("Bỏ qua điểm %d không hợp lệ.%n", grade);
            } else {
                total += grade;
                ++count;

                if (grade > max) {
                    max = grade;
                }
                if (grade < min) {
                    min = grade;
                }
            }

            System.out.print("Nhập điểm (0 - 100) hoặc -1 để kết thúc: ");
            grade = input.nextInt();
        }

        // Kiểm tra trường hợp biên: không có dữ liệu
        if (count > 0) {
            double average = (double) total / count;
            System.out.println("--- BÁO CÁO THỐNG KÊ ---");
            System.out.printf("Số lượng điểm hợp lệ: %d%n", count);
            System.out.printf("Điểm cao nhất: %d%n", max);
            System.out.printf("Điểm thấp nhất: %d%n", min);
            System.out.printf("Điểm trung bình: %.2f%n", average);
        } else {
            System.out.println("Không có điểm hợp lệ nào được nhập.");
        }
    }
}
