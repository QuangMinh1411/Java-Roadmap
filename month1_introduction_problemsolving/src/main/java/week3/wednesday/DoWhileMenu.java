package week3.wednesday;

import java.util.Scanner;

/**
 * Menu lặp đến khi người dùng chọn 0.
 * Tham khảo: Control Statement.md (§4.2–4.9)
 * và Fundamental Programming Structure.md (§3.7–3.8).
 *
 * do...while hiển thị menu ít nhất một lần rồi mới kiểm tra choice != 0.
 * break trong switch chỉ thoát switch; điều kiện cuối vòng lặp quyết định dừng.
 * continue trong do...while chuyển tới bước kiểm tra điều kiện.
 *
 * Nhập các lựa chọn cách nhau bằng khoảng trắng hoặc xuống dòng:
 *   9 1 0   -> báo sai 1 lần, thực hiện chức năng 1 lần, thoát 1 lần.
 *   abc 1 0 -> bỏ qua abc và báo lỗi 1 lần, thực hiện 1 lần, thoát 1 lần.
 *   0       -> hiển thị menu 1 lần rồi thoát, không thực hiện chức năng.
 */
public class DoWhileMenu {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Khởi tạo khác 0 để nhánh continue cũng có điều kiện lặp hợp lệ.
        int choice = -1;

        do {
            System.out.println("=== MENU ===");
            System.out.println("1. Hiển thị lời chào");
            System.out.println("0. Kết thúc");
            System.out.print("Nhập lựa chọn: ");

            if (!input.hasNext()) {
                System.out.println("Hết dữ liệu nhập, kết thúc chương trình.");
                break; // Ở ngoài switch nên thoát trực tiếp vòng do...while.
            }

            if (!input.hasNextInt()) {
                // hasNextInt chỉ kiểm tra, không lấy dữ liệu khỏi luồng.
                // Phải gọi next để bỏ token sai, tránh kiểm tra mãi cùng token.
                String invalid = input.next();
                System.out.println("Lỗi: lựa chọn phải là số nguyên; bỏ qua: " + invalid);
                continue;
            }

            choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Thực hiện chức năng: Xin chào, Java!");
                    break; // Ngăn chạy tiếp xuống case 0.
                case 0:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn sai: chỉ chấp nhận 0 hoặc 1.");
                    break;
            }
        } while (choice != 0); // Chú ý dấu chấm phẩy cuối do...while.
    }
}
