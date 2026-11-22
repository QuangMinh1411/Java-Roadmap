package week1.friday.LJ;

import java.util.Scanner;

public class SumSquare {
    static void main() {
        Scanner input = new Scanner(System.in);

        System.out.print("Nhập N (1 - 100): ");
        int n = input.nextInt();

        int counter = 1;
        int totalSum = 0;

        while (counter <= n) {
            int square = counter * counter;
            totalSum += square; // Gán kết hợp
            ++counter;          // Tăng bộ đếm
        }

        // Ép kiểu trước phép chia để không làm mất phần lẻ
        double average = (double) totalSum / n;

        System.out.printf("Tổng bình phương: %d%n", totalSum);
        System.out.printf("Trung bình: %.2f%n", average);
    }
}
