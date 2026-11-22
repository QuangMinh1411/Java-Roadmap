package week2.wednesday.jfp;

import java.util.Locale;
import java.util.Scanner;

/*
 * BÀI 4 — KHÁ: THỐNG KÊ ĐIỂM ĐẾN EOF
 * Tham khảo More_Control_Statement.md, mục 7–10 và 13.
 *
 * ĐỀ BÀI
 * 1. Đọc các token đến EOF, không dùng điểm sentinel để kết thúc.
 * 2. Token không phải int hoặc điểm ngoài [0, 100]: báo lỗi,
 *    tăng số dữ liệu sai rồi continue; không cộng vào thống kê.
 * 3. Dùng switch (grade / 10) đếm A (90–100), B (80–89),
 *    C (70–79), D (60–69), F (0–59); case 9 và 10 dùng chung nhánh.
 * 4. In số điểm hợp lệ, tổng, trung bình với 2 chữ số thập phân,
 *    số lượng từng loại và số dữ liệu sai. Nếu chưa có điểm hợp lệ,
 *    chỉ báo chưa có điểm và số dữ liệu sai; không chia cho 0.
 * 5. Tổng bộ đếm A/B/C/D/F phải bằng số điểm hợp lệ.
 * Giả định tối đa 1 triệu token để các biến int không tràn.
 * EOF trong terminal macOS/Linux thường là Ctrl+D.
 *
 * TỰ KIỂM TRA
 * - 99 92 45 57 63 71 76 85 90 100: count=10, total=778,
 *   average=77.80; A=4, B=1, C=2, D=1, F=2; invalid=0.
 * - -1 101 abc 60: count=1, total=60, average=60.00, D=1, invalid=3.
 * - 59 60 69 70 79 80 89 90 100: A=2, B=2, C=2, D=2, F=1.
 * - EOF ngay hoặc chỉ nhập abc -1: không có điểm hợp lệ.
 */
public class Exercise04GradeReport {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int total = 0;
        int count = 0;
        int invalid = 0;
        int a = 0, b = 0, c = 0, d = 0, f = 0;

        while (input.hasNext()) {
            if (!input.hasNextInt()) {
                System.out.println("Token không nguyên: " + input.next());
                ++invalid;
                continue;
            }
            int grade = input.nextInt();
            if (grade < 0 || grade > 100) {
                System.out.println("Điểm ngoài [0, 100]: " + grade);
                ++invalid;
                continue;
            }

            total += grade;
            ++count;
            switch (grade / 10) {
                case 10:
                case 9:
                    ++a;
                    break;
                case 8:
                    ++b;
                    break;
                case 7:
                    ++c;
                    break;
                case 6:
                    ++d;
                    break;
                default:
                    ++f;
            }
        }

        if (count == 0) {
            System.out.println("Chưa có điểm hợp lệ");
        } else {
            System.out.printf(Locale.US, "Count: %d%nTotal: %d%nAverage: %.2f%n",
                    count, total, (double) total / count);
            System.out.printf("A: %d, B: %d, C: %d, D: %d, F: %d%n", a, b, c, d, f);
        }
        System.out.println("Invalid: " + invalid);
    }
}
