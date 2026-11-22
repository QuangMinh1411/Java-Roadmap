package week3;

import java.math.BigDecimal;

/**
 * Lãi tăng trưởng: gốc 1000000, lãi suất 5% mỗi kỳ, trong 3 kỳ.
 * Lãi mỗi kỳ được nhập vào vốn để tính lãi cho kỳ tiếp theo.
 *
 * Trace vòng lặp (mỗi kỳ nhân số dư với 1 + 0.05 = 1.05):
 *   Kỳ | Số dư trước | Phép tính          | Số dư sau | Tổng số lần nhân
 *    1 |     1000000 | 1000000 * 1.05    |   1050000 |                1
 *    2 |     1050000 | 1050000 * 1.05    |   1102500 |                2
 *    3 |     1102500 | 1102500 * 1.05    |   1157625 |                3
 *
 * Đối chiếu: A = P * (1 + r)^n = 1000000 * 1.05^3 = 1157625.
 * Vòng lặp chạy đúng 3 lần, thực hiện đúng 3 phép nhân cập nhật số dư.
 * Số đếm này không bao gồm phép nhân khi tính công thức đối chiếu
 * và không mô tả số phép nhân bên trong BigDecimal.pow.
 * Với 0 kỳ, vòng lặp không chạy: 0 lần nhân và số dư bằng tiền gốc.
 */
public class CompoundGrowth {
    public static void main(String[] args) {
        // Khởi tạo từ chuỗi để tính chính xác số thập phân, không làm tròn từng kỳ.
        BigDecimal principal = new BigDecimal("1000000");
        BigDecimal rate = new BigDecimal("0.05");
        BigDecimal growth = BigDecimal.ONE.add(rate);
        int periods = 3;
        BigDecimal balance = principal;
        int multiplicationCount = 0;

        for (int period = 1; period <= periods; period++) {
            BigDecimal before = balance;
            balance = balance.multiply(growth);
            multiplicationCount++;
            System.out.printf("Kỳ %d: %s * %s = %s; số lần nhân = %d%n",
                    period, before.stripTrailingZeros().toPlainString(),
                    growth.toPlainString(), balance.stripTrailingZeros().toPlainString(),
                    multiplicationCount);
        }

        BigDecimal formula = principal.multiply(growth.pow(periods));
        System.out.println("Kết quả vòng lặp: " + balance.stripTrailingZeros().toPlainString());
        System.out.println("Công thức: 1000000 * 1.05^3 = "
                + formula.stripTrailingZeros().toPlainString());
        System.out.println("Số lần nhân cập nhật số dư trong vòng lặp: " + multiplicationCount);

        if (balance.compareTo(new BigDecimal("1157625")) != 0
                || balance.compareTo(formula) != 0
                || multiplicationCount != periods) {
            throw new AssertionError("Kết quả hoặc số lần nhân không đúng.");
        }
        System.out.println("PASS: vòng lặp khớp công thức, đúng 3 lần nhân.");
    }
}
