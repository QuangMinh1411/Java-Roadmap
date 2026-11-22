package week1.wednesday.LJ;

import java.util.Scanner;

/*
 * BÀI 5 — NÂNG CAO: QUẢN LÝ ĐIỂM BẰNG MẢNG KHÔNG ĐỀU
 * Kiến thức: int[][], mảng của mảng, vòng lặp lồng nhau, continue, break có nhãn.
 *
 * ĐỀ BÀI
 * Cho điểm các lớp: {{8, 9}, {}, {5, 10, 7}}, mỗi hàng là một lớp.
 * 1. In số học viên và điểm trung bình của từng lớp. Lớp rỗng phải ghi
 *    "Chưa có học viên", không chia cho 0.
 * 2. Tính tổng số học viên và điểm trung bình của toàn bộ học viên,
 *    không lấy trung bình của các trung bình lớp.
 * 3. Tìm lớp có điểm trung bình cao nhất, dùng continue bỏ qua lớp rỗng.
 *    Nếu bằng nhau, chọn lớp có chỉ số nhỏ hơn.
 * 4. Nhập một điểm cần tìm, trả về vị trí đầu tiên theo thứ tự hàng rồi cột.
 *    Dùng break có nhãn để thoát cả hai vòng lặp khi tìm thấy.
 * 5. Tạo int[][] alias = scores, đổi một điểm qua alias và quan sát scores.
 * Quy ước: điểm từ 0 đến 10; mảng và các hàng không null nhưng có thể rỗng.
 * Nếu mọi lớp đều rỗng hoặc không tìm thấy điểm, in thông báo phù hợp.
 *
 * KẾT QUẢ CHÍNH VỚI ĐIỂM CẦN TÌM LÀ 10 (trước khi đổi điểm)
 * Tổng số học viên: 5
 * Điểm trung bình chung: 7.8
 * Lớp có trung bình cao nhất: chỉ số 0, trung bình 8.5
 * Tìm điểm 10: hàng 2, cột 1
 *
 * TỰ KIỂM TRA: {}, {{}, {}}, {{8}, {8}}, tìm điểm không tồn tại.
 * CHẠY RIÊNG: chạy main() trong IntelliJ rồi nhập điểm cần tìm từ console.
 * Đổi mảng scores trong main() để thử các trường hợp khác.
 * Lưu ý: Java luôn truyền theo giá trị; alias = scores sao chép giá trị
 * tham chiếu, vì vậy hai biến cùng trỏ đến một mảng, không tạo bản sao mảng.
 */
public class Exercise05JaggedScores {
    public static void main(String[] args) {
        int[][] scores = {
                {8, 9},
                {},
                {5, 10, 7}
        };
        printStatistics(scores);

        Scanner input = new Scanner(System.in);
        System.out.print("Nhập điểm cần tìm (0..10): ");
        if (!input.hasNextInt()) {
            System.out.println("Điểm cần tìm phải là số nguyên từ 0 đến 10.");
            return;
        }
        int target = input.nextInt();
        if (target < 0 || target > 10) {
            System.out.println("Điểm cần tìm phải là số nguyên từ 0 đến 10.");
            return;
        }
        findScore(scores, target);
        demonstrateAlias(scores);
    }

    static void printStatistics(int[][] scores) {
        long totalStudents = 0;
        long totalScore = 0;
        int bestClass = -1;
        double bestAverage = -1;
        for (int row = 0; row < scores.length; row++) {
            int studentCount = scores[row].length;
            if (studentCount == 0) {
                System.out.println("Lớp " + row + ": Chưa có học viên (0 học viên)");
                continue;
            }
            long classSum = 0;
            for (int score : scores[row]) {
                classSum += score;
            }
            double average = (double) classSum / studentCount;
            System.out.println("Lớp " + row + ": " + studentCount
                    + " học viên, trung bình " + average);
            totalStudents += studentCount;
            totalScore += classSum;
            // Chỉ thay khi lớn hơn để giữ lớp có chỉ số nhỏ nhất nếu bằng nhau.
            if (average > bestAverage) {
                bestAverage = average;
                bestClass = row;
            }
        }

        System.out.println("Tổng số học viên: " + totalStudents);
        if (totalStudents == 0) {
            System.out.println("Không có học viên để tính trung bình hoặc chọn lớp tốt nhất.");
        } else {
            System.out.println("Điểm trung bình chung: " + (double) totalScore / totalStudents);
            System.out.println("Lớp có trung bình cao nhất: chỉ số " + bestClass
                    + ", trung bình " + bestAverage);
        }
    }

    static void findScore(int[][] scores, int target) {
        int foundRow = -1;
        int foundColumn = -1;
        search:
        for (int row = 0; row < scores.length; row++) {
            for (int column = 0; column < scores[row].length; column++) {
                if (scores[row][column] == target) {
                    foundRow = row;
                    foundColumn = column;
                    break search;
                }
            }
        }
        if (foundRow == -1) {
            System.out.println("Không tìm thấy điểm " + target);
        } else {
            System.out.println("Tìm điểm " + target + ": hàng " + foundRow + ", cột " + foundColumn);
        }
    }

    static void demonstrateAlias(int[][] scores) {
        int[][] alias = scores;
        for (int row = 0; row < alias.length; row++) {
            if (alias[row].length == 0) {
                continue;
            }
            int oldScore = scores[row][0];
            alias[row][0] = (oldScore + 1) % 11;
            System.out.println("Đổi alias[" + row + "][0]: " + oldScore + " -> " + alias[row][0]);
            System.out.println("scores[" + row + "][0] cũng trở thành: " + scores[row][0]);
            return;
        }
        System.out.println("Không có điểm để minh họa alias.");
    }
}