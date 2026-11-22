package week1.monday.jfp;

import java.util.Scanner;

/*
 * BÀI 2 — CƠ BẢN MỞ RỘNG: MÁY TÍNH HAI SỐ NGUYÊN
 * Kiến thức: Scanner, int, phép gán, +, -, *, /, %, if, printf
 * (mục 6–11 của tài liệu).
 *
 * ĐỀ BÀI
 * Nhập hai số nguyên a, b. In tổng, hiệu a - b, tích và trung bình cộng.
 * Nếu b khác 0, in thương nguyên a / b và phần dư a % b.
 * Nếu b bằng 0, in thông báo không thể chia và không thực hiện / hoặc %.
 * Trung bình cộng phải giữ phần lẻ, ví dụ 7 và 4 có trung bình 5.5.
 *
 * GIẢ ĐỊNH
 * Nhập đủ hai token số nguyên trong [-10000, 10000], không cần kiểm tra
 * định dạng hay miền đầu vào. Giới hạn này giúp tổng, hiệu, tích vừa int.
 *
 * DỮ LIỆU MẪU: 7 4
 * KẾT QUẢ (không tính lời nhắc nhập):
 * Tong: 11
 * Hieu: 3
 * Tich: 28
 * Trung binh: 5.5
 * Thuong nguyen: 1
 * Phan du: 3
 *
 * CA KIỂM CHỨNG KHÁC
 * - -17 5: tổng -12, hiệu -22, tích -85, trung bình -6.0,
 *   thương -3, dư -2 (chia nguyên bỏ phần lẻ theo hướng về 0).
 * - 5 0: tổng 5, hiệu 5, tích 0, trung bình 2.5;
 *   in "Khong the chia hoac lay du cho 0.".
 * - 0 0: trung bình 0.0; không thực hiện chia hay lấy dư cho 0.
 */
public class Exercise02IntegerArithmetic {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Nhap hai so nguyen a va b: ");
        int a = input.nextInt();
        int b = input.nextInt();

        int sum = a + b;
        int difference = a - b;
        int product = a * b;
        // 2.0 khiến phép chia là chia số thực ngay từ đầu.
        double average = sum / 2.0;

        System.out.printf("Tong: %d%n", sum);
        System.out.printf("Hieu: %d%n", difference);
        System.out.printf("Tich: %d%n", product);
        System.out.println("Trung binh: " + average);

        // Phải kiểm tra mẫu số TRƯỚC khi tính / và %.
        if (b != 0) {
            System.out.printf("Thuong nguyen: %d%n", a / b);
            System.out.printf("Phan du: %d%n", a % b);
        }
        if (b == 0) {
            System.out.println("Khong the chia hoac lay du cho 0.");
        }
    }
}
