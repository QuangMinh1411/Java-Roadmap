package week2.thursday.LJ;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;

/*
 * ĐỀ BÀI: MENU ĐỔI ĐƠN VỊ — case: với break để ngăn fall-through
 * 1. Hiện menu: 1: cm → m, 2: m → cm, 0: thoát.
 * 2. Chỉ lựa chọn 1 hoặc 2 mới đọc thêm giá trị cần chuyển đổi.
 * 3. Lựa chọn khác báo "Lựa chọn sai" rồi hiện lại menu.
 * 4. Lặp đến khi chọn 0 hoặc gặp EOF tại bước chọn menu.
 *    Giả định lựa chọn là int và giá trị chuyển đổi là số hữu hạn,
 *    vừa kiểu double; dùng dấu chấm thập phân.
 *
 * ĐỐI CHIẾU VỚI PHIÊN BẢN SWITCH CÒN LẠI
 * - Input: 1 125 0  → đúng một kết quả: 1.25 m, rồi thoát.
 * - Input: 2 1.25 0 → đúng một kết quả: 125 cm, rồi thoát.
 * - Input: -1 9 0   → báo lựa chọn sai hai lần, rồi thoát;
 *   không hỏi giá trị chuyển đổi, không in kết quả.
 * - Input: 0        → thoát ngay, không chạy nhánh chuyển đổi.
 */
public class UnitConverterClassicSwitch {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in).useLocale(Locale.US);
        // Bỏ số 0 thập phân dư: 125.0 được hiển thị thành 125.
        DecimalFormat output = new DecimalFormat("0.###############",
                DecimalFormatSymbols.getInstance(Locale.US));
        int choice;
        do {
            System.out.println("1: cm → m | 2: m → cm | 0: thoát");
            if (!input.hasNext()) {
                break;
            }
            choice = input.nextInt();
            switch (choice) {
                case 1: {
                    System.out.print("Nhập cm: ");
                    double cm = input.nextDouble();
                    System.out.println("Kết quả: " + output.format(cm / 100) + " m");
                    break; // Thoát switch, không chạy tiếp case 2.
                }
                case 2: {
                    System.out.print("Nhập m: ");
                    double m = input.nextDouble();
                    System.out.println("Kết quả: " + output.format(m * 100) + " cm");
                    break;
                }
                case 0:
                    System.out.println("Thoát");
                    break; // Vòng lặp kết thúc nhờ choice != 0 ở cuối.
                default:
                    System.out.println("Lựa chọn sai");
                    break;
            }
        } while (choice != 0);
    }
}
