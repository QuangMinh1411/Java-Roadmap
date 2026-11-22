package week1.wednesday.LJ;

import java.util.Scanner;

/*
 * BÀI 3 — TRUNG BÌNH: THỐNG KÊ SỐ NGUYÊN TỐ
 * Kiến thức: do-while, for, vòng lặp lồng nhau, toán tử % và break.
 *
 * ĐỀ BÀI
 * 1. Nhập số nguyên n, yêu cầu 1 <= n <= 1000.
 * 2. Dùng do-while yêu cầu nhập lại khi n nằm ngoài phạm vi.
 * 3. Liệt kê các số nguyên tố từ 2 đến n, in số lượng và tổng của chúng.
 * 4. Khi tìm được ước chứng minh một số không nguyên tố, dùng break.
 *
 * DỮ LIỆU MẪU: 10
 * KẾT QUẢ MẪU (không tính lời nhắc)
 * Các số nguyên tố: 2 3 5 7
 * Số lượng: 4
 * Tổng: 17
 *
 * TỰ KIỂM TRA
 * - n = 1: không có số nguyên tố, số lượng và tổng đều bằng 0.
 * - Nhập 0, 1001 rồi 10: yêu cầu nhập lại hai lần trước khi tính.
 * CHẠY RIÊNG: chạy main() trong IntelliJ và nhập n từ console.
 */
public class Exercise03PrimeStatistics {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = 0;
        do {
            System.out.print("Nhập n (1..1000): ");
            if (!input.hasNext()) {
                System.out.println("Không có dữ liệu đầu vào.");
                return;
            }
            if (input.hasNextInt()) {
                n = input.nextInt();
            } else {
                input.next();
                n = 0;
            }
            if (n < 1 || n > 1000) {
                System.out.println("Vui lòng nhập số nguyên từ 1 đến 1000.");
            }
        } while (n < 1 || n > 1000);

        int count = 0;
        int sum = 0;
        System.out.print("Các số nguyên tố:");
        for (int number = 2; number <= n; number++) {
            boolean prime = true;
            // Một hợp số luôn có ít nhất một ước không vượt quá căn bậc hai.
            for (int divisor = 2; divisor * divisor <= number; divisor++) {
                if (number % divisor == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) {
                System.out.print(" " + number);
                count++;
                sum += number;
            }
        }
        if (count == 0) {
            System.out.print(" Không có");
        }
        System.out.println();
        System.out.println("Số lượng: " + count);
        System.out.println("Tổng: " + sum);
    }
}