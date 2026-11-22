package week2.monday.jfp;

import java.math.BigInteger;
import java.util.Scanner;

/*
 * BÀI 5 — NÂNG CAO: BẢNG GIAI THỪA VÀ TỔNG SỐ LỚN
 * Theo Control_Statement.md, mục 6, 11–14: while, bộ đếm, miền kiểu
 * nguyên thuỷ và tái sử dụng API BigInteger bất biến.
 *
 * YÊU CẦU
 * 1. Nhập n trong 0–100; dùng while yêu cầu nhập lại khi sai miền.
 * 2. In bảng 0!, 1!, ..., n!. Quy ước 0! = 1;
 *    k! = (k - 1)! * k khi k >= 1.
 * 3. Tính S = 0! + 1! + ... + n! và in S sau bảng.
 *    Mỗi lượt dùng lại giai thừa trước đó, không tính lại từ đầu.
 * 4. Dùng BigInteger cho giai thừa và tổng; tạo số bằng valueOf,
 *    nhân bằng multiply, cộng bằng add và gán lại giá trị trả về.
 *    Không chuyển kết quả về int/long hoặc dùng double để tính.
 * 5. Nếu n >= 21, in "n! exceeds long range" sau tổng:
 *    20! còn vừa long, nhưng 21! đã vượt Long.MAX_VALUE.
 * Chỉ dùng while và if/else; không mảng, đệ quy, for hay do…while.
 * Giả định mọi token là int, có đủ dữ liệu để nhập được n hợp lệ.
 *
 * MẪU: 3
 * 0! = 1
 * 1! = 1
 * 2! = 2
 * 3! = 6
 * Sum: 10
 *
 * TỰ KIỂM TRA
 * - 0: chỉ in 0! = 1 và Sum: 1 (while tính giai thừa không chạy).
 * - -1 101 5: nhập lại hai lần; 5! = 120, Sum: 154.
 * - 20: 20! = 2432902008176640000, không có cảnh báo vượt long.
 * - 21: 21! = 51090942171709440000, có cảnh báo vượt long.
 * - 100: kết quả 100! có 158 chữ số; tổng vẫn là số nguyên chính xác.
 */
public class Exercise05BigIntegerFactorials {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Nhap n (0..100): ");
        int n = input.nextInt();
        boolean validInput = n >= 0;
        if (n > 100) {
            validInput = false;
        }
        while (!validInput) {
            System.out.print("n khong hop le. Nhap lai (0..100): ");
            n = input.nextInt();
            validInput = n >= 0;
            if (n > 100) {
                validInput = false;
            }
        }

        BigInteger factorial = BigInteger.valueOf(1);
        BigInteger sum = factorial; // Tổng ban đầu đã bao gồm 0!.
        System.out.println("0! = 1");
        int factor = 1;
        while (factor <= n) {
            factorial = factorial.multiply(BigInteger.valueOf(factor));
            sum = sum.add(factorial);
            System.out.printf("%d! = %s%n", factor, factorial);
            ++factor;
        }
        System.out.printf("Sum: %s%n", sum);
        if (n >= 21) {
            System.out.println("n! exceeds long range");
        }
    }
}
