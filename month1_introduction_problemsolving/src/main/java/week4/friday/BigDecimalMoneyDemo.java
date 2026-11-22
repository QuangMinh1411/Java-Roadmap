package week4.friday;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Minh họa tính toán tiền với BigDecimal từ chuỗi thập phân.
 * new BigDecimal("0.10") giữ chính xác giá trị thập phân và scale = 2.
 * Tránh new BigDecimal(0.10): đối số double đã là giá trị nhị phân xấp xỉ.
 * BigDecimal bất biến: add, divide, setScale trả kết quả, không sửa đối tượng gốc.
 *
 * Giá trị BigDecimal = unscaledValue * 10^(-scale).
 * Ví dụ 0.30 có unscaledValue = 30, scale = 2; 0.3 có unscaledValue = 3, scale = 1.
 * Với scale không âm trong ví dụ, scale là số chữ số sau dấu thập phân.
 * compareTo chỉ xét giá trị số; equals xét cả giá trị lẫn scale.
 */
public class BigDecimalMoneyDemo {
    public static void main(String[] args) {
        BigDecimal first = new BigDecimal("0.10");
        BigDecimal second = new BigDecimal("0.20");
        BigDecimal sum = first.add(second);

        // add không có MathContext: cộng chính xác; scale kết quả là
        // max(scale hai toán hạng), ở đây max(2, 2) = 2.
        System.out.println("0.10 + 0.20 = " + sum.toPlainString()); // 0.30
        System.out.println("Scale của tổng = " + sum.scale()); // 2

        BigDecimal total = new BigDecimal("10.00");
        BigDecimal count = new BigDecimal("3");
        // 10 / 3 có phần thập phân vô hạn: phải chọn scale và cách làm tròn.
        // total.divide(count) không chỉ định làm tròn sẽ ném ArithmeticException.
        BigDecimal average = total.divide(count, 2, RoundingMode.HALF_UP);
        System.out.println("10.00 / 3 (scale=2, HALF_UP) = " + average.toPlainString()); // 3.33

        BigDecimal shorter = new BigDecimal("0.3");
        System.out.println("Scale của 0.30 = " + sum.scale()
                + "; scale của 0.3 = " + shorter.scale());
        System.out.println("0.30.compareTo(0.3) = " + sum.compareTo(shorter)); // 0
        System.out.println("0.30.equals(0.3) = " + sum.equals(shorter)); // false

        // Khi chỉ xét số tiền: compareTo trả 0 nghĩa là bằng nhau;
        // số âm nghĩa là nhỏ hơn, số dương nghĩa là lớn hơn.
        boolean sameAmount = sum.compareTo(shorter) == 0;
        System.out.println("Cùng giá trị tiền (compareTo == 0): " + sameAmount); // true

        // Tăng scale từ 1 lên 2 chỉ thêm số 0, không đổi giá trị số.
        BigDecimal normalized = shorter.setScale(2, RoundingMode.HALF_UP);
        System.out.println("0.3 sau setScale(2) = " + normalized.toPlainString()); // 0.30
        System.out.println("0.30.equals(giá trị đã chuẩn hóa) = " + sum.equals(normalized)); // true
        System.out.println("Đối tượng gốc vẫn là " + shorter.toPlainString()); // 0.3

        // Giảm scale có thể đổi giá trị do làm tròn. HALF_UP làm tròn đến số
        // gần nhất; nếu đúng nửa thì làm tròn ra xa 0: 1.235 -> 1.24.
        BigDecimal rounded = new BigDecimal("1.235").setScale(2, RoundingMode.HALF_UP);
        System.out.println("1.235 sau setScale(2, HALF_UP) = " + rounded.toPlainString()); // 1.24
    }
}
