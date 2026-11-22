package week1.monday.jfp;

import java.util.Scanner;

/*
 * BÀI 3 — TRUNG BÌNH: THỐNG KÊ BA SỐ NGUYÊN
 * Kiến thức: nhập → xử lý → xuất, biến, so sánh, nhiều if độc lập,
 * phép dư và chia số thực (mục 7–11, 13 của tài liệu).
 *
 * ĐỀ BÀI
 * Nhập ba số nguyên a, b, c. Không dùng mảng, vòng lặp hoặc Math.min/max.
 * Tìm số nhỏ nhất, lớn nhất; tính tổng và trung bình cộng của ba số.
 * Đếm có bao nhiêu số chẵn trong ba số, tính cả số âm và số 0.
 * Nếu cả ba số bằng nhau, in thêm "Ba so bang nhau.".
 *
 * GIẢ ĐỊNH
 * Đầu vào luôn đủ ba số nguyên thuộc toàn bộ miền int.
 * Tổng phải chính xác ngay cả khi cộng các giá trị int lớn nhất.
 *
 * DỮ LIỆU MẪU: -4 7 0
 * KẾT QUẢ (không tính lời nhắc nhập):
 * Nho nhat: -4
 * Lon nhat: 7
 * Tong: 3
 * Trung binh: 1.0
 * So luong so chan: 2
 *
 * CA KIỂM CHỨNG KHÁC
 * - 5 5 5: min = max = 5, tổng 15, trung bình 5.0, 0 số chẵn;
 *   có thông báo ba số bằng nhau.
 * - 9 3 -6: min -6, max 9, tổng 6, trung bình 2.0, 1 số chẵn.
 * - 2147483647 2147483647 2147483647: tổng 6442450941,
 *   trung bình in thành 2.147483647E9 (tức 2147483647.0),
 *   không tràn int trong bước cộng.
 */
public class Exercise03ThreeNumberStatistics {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Nhap ba so nguyen a, b, c: ");
        int a = input.nextInt();
        int b = input.nextInt();
        int c = input.nextInt();

        // Khởi tạo từ dữ liệu thật để xử lý đúng cả bộ toàn số âm/dương.
        int minimum = a;
        int maximum = a;
        if (b < minimum) {
            minimum = b;
        }
        if (c < minimum) {
            minimum = c;
        }
        if (b > maximum) {
            maximum = b;
        }
        if (c > maximum) {
            maximum = c;
        }

        // Đưa a vào long trước khi cộng. long sum = a + b + c vẫn có
        // thể tràn vì vế phải khi đó chỉ gồm các phép cộng int.
        long sum = a;
        sum = sum + b + c;
        double average = sum / 3.0;

        int evenCount = 0;
        if (a % 2 == 0) {
            evenCount = evenCount + 1;
        }
        if (b % 2 == 0) {
            evenCount = evenCount + 1;
        }
        if (c % 2 == 0) {
            evenCount = evenCount + 1;
        }

        System.out.printf("Nho nhat: %d%n", minimum);
        System.out.printf("Lon nhat: %d%n", maximum);
        System.out.printf("Tong: %d%n", sum);
        System.out.println("Trung binh: " + average);
        System.out.printf("So luong so chan: %d%n", evenCount);
        // Khi min bằng max, mọi phần tử đều có cùng giá trị.
        if (minimum == maximum) {
            System.out.println("Ba so bang nhau.");
        }
    }
}
