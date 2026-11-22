package week1.friday.LJ;

import java.util.Scanner;

public class ScoreClass {
    static void main() {
        Scanner input = new Scanner(System.in);

        int passes = 0;
        int failures = 0;
        int studentCounter = 1;
        final int TOTAL_STUDENTS = 8;

        while (studentCounter <= TOTAL_STUDENTS) {
            System.out.printf("Nhập kết quả sinh viên %d (1 = Đậu, 2 = Trượt): ", studentCounter);
            int result = input.nextInt();

            if (result == 1) {
                ++passes;
                ++studentCounter; // Chỉ tăng khi kết quả hợp lệ
            } else if (result == 2) {
                ++failures;
                ++studentCounter; // Chỉ tăng khi kết quả hợp lệ
            } else {
                System.out.println("Dữ liệu sai, vui lòng chỉ nhập 1 hoặc 2.");
            }
        }

        double passRate = ((double) passes / TOTAL_STUDENTS) * 100;

        System.out.println("--- KẾT QUẢ TỔNG HỢP ---");
        System.out.printf("Đậu: %d%nTrượt: %d%n", passes, failures);
        System.out.printf("Tỷ lệ đậu: %.1f%%%n", passRate);

        if (passes >= 6) {
            System.out.println("Lớp đạt thành tích tốt!");
        }
    }
}
