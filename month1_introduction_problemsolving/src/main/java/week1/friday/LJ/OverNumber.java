package week1.friday.LJ;

import java.math.BigInteger;
import java.util.Scanner;

public class OverNumber {
    static void main() {
        Scanner input = new Scanner(System.in);

        System.out.print("Nhập N để tính giai thừa (1 - 35): ");
        int n = input.nextInt();

        long longFactorial = 1;
        BigInteger bigFactorial = BigInteger.ONE; // Tương đương BigInteger.valueOf(1)

        int counter = 1;
        while (counter <= n) {
            longFactorial *= counter;

            // BigInteger là bất biến: kết quả phép nhân phải được gán lại
            bigFactorial = bigFactorial.multiply(BigInteger.valueOf(counter));

            ++counter;
        }

        System.out.printf("Giai thừa tính bằng long:       %d%n", longFactorial);
        System.out.printf("Giai thừa tính bằng BigInteger: %s%n", bigFactorial.toString());

        // Chuyển kết quả long thành String để so sánh với BigInteger
        String longString = String.valueOf(longFactorial);

        if (bigFactorial.toString().equals(longString)) {
            System.out.println("Trạng thái: Kết quả kiểu long hoàn toàn chính xác.");
        } else {
            System.out.println("CẢNH BÁO: Đã xảy ra hiện tượng tràn số nguyên (Overflow) trên kiểu long!");
            System.out.println("Hành vi wraparound khiến kết quả kiểu long bị sai lệch, cần sử dụng BigInteger.");
        }
    }
}
