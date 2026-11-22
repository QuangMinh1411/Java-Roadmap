package week2.wednesday.jfp;

import java.util.Scanner;

/*
 * BÀI 2 — CƠ BẢN MỞ RỘNG: MENU LẶP
 * Tham khảo More_Control_Statement.md, mục 6–7 và 9.
 *
 * ĐỀ BÀI
 * 1. Dùng do…while hiện menu ít nhất một lần:
 *    1: in 1–5; 2: in 5–1; 0: kết thúc.
 * 2. Dùng switch dạng case ...: và break cho từng lựa chọn.
 * 3. Lựa chọn khác: báo "Lựa chọn không hợp lệ" rồi hiện lại menu.
 * 4. Token không nguyên: tiêu thụ token, thông báo rồi continue.
 *    Nếu gặp EOF, dùng break thoát vòng lặp.
 * 5. Giải thích bằng comment tại mã vì sao break trong case 0
 *    chưa trực tiếp thoát vòng do…while.
 *
 * TỰ KIỂM TRA (các token cách nhau bởi khoảng trắng hoặc xuống dòng)
 * - 1 2 0: in dãy tăng, dãy giảm rồi kết thúc.
 * - 9 abc 0: báo hai lỗi, vẫn đọc được lựa chọn 0.
 * - 0: menu xuất hiện đúng một lần.
 * - EOF ngay: hiện menu rồi kết thúc, không lặp vô hạn.
 */
public class Exercise02LoopMenu {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice = -1;

        do {
            System.out.println("1: In 1–5 | 2: In 5–1 | 0: Kết thúc");
            if (!input.hasNext()) {
                break;
            }
            if (!input.hasNextInt()) {
                System.out.println("Token không nguyên: " + input.next());
                continue;
            }
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    for (int i = 1; i <= 5; ++i) {
                        System.out.print(i + " ");
                    }
                    System.out.println();
                    break;
                case 2:
                    for (int i = 5; i >= 1; --i) {
                        System.out.print(i + " ");
                    }
                    System.out.println();
                    break;
                case 0:
                    System.out.println("Kết thúc");
                    // break chỉ thoát switch; choice != 0 kết thúc vòng lặp.
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        } while (choice != 0);
    }
}
