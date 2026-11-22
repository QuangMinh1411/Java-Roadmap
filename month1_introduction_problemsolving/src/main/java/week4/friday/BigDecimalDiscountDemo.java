package week4.friday;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Tính tổng tiền 3 sản phẩm giá 19.99, giảm 10% trên tổng.
 * Các dữ liệu tính tiền được tạo từ chuỗi, không đi qua double.
 * Không làm tròn đơn giá sau giảm hoặc số tiền giảm; chỉ làm tròn tổng cuối.
 *
 * 19.99 * 3 = 59.97
 * 59.97 * 0.10 = 5.9970
 * 59.97 - 5.9970 = 53.9730
 * Làm tròn tổng về scale = 2, HALF_UP -> 53.97.
 */
public class BigDecimalDiscountDemo {
    public static void main(String[] args) {
        BigDecimal unitPrice = new BigDecimal("19.99");
        BigDecimal quantity = new BigDecimal("3");
        BigDecimal discountRate = new BigDecimal("0.10"); // 10% = 0.10.

        // multiply và subtract không có MathContext giữ kết quả thập phân chính xác.
        BigDecimal subtotal = unitPrice.multiply(quantity);
        BigDecimal discount = subtotal.multiply(discountRate);
        BigDecimal discountedTotal = subtotal.subtract(discount);
        BigDecimal payable = discountedTotal.setScale(2, RoundingMode.HALF_UP);

        System.out.println("Đơn giá từ chuỗi: " + unitPrice.toPlainString());
        System.out.println("Số lượng: " + quantity.toPlainString());
        System.out.println("Tổng trước giảm: " + subtotal.toPlainString());
        System.out.println("Giảm 10% (chưa làm tròn): " + discount.toPlainString());
        System.out.println("Tổng sau giảm (chưa làm tròn): " + discountedTotal.toPlainString());
        System.out.println("Tổng cuối (scale=2, HALF_UP): " + payable.toPlainString());

        // Chứng minh constructor nhận chuỗi giữ đúng số thập phân 19.99:
        // unscaledValue = 1999, scale = 2 -> giá trị chính xác 1999 * 10^(-2).
        System.out.println();
        System.out.println("BigDecimal từ chuỗi: unscaledValue=" + unitPrice.unscaledValue()
                + ", scale=" + unitPrice.scale());

        // Chỉ dùng double dưới đây để đối chiếu, không tham gia tính tiền phía trên.
        // Literal 19.99 dạng double đã được xấp xỉ bằng số nhị phân;
        // constructor BigDecimal(double) giữ chính xác giá trị nhị phân xấp xỉ đó.
        BigDecimal fromDouble = new BigDecimal(19.99);
        System.out.println("new BigDecimal(\"19.99\") = " + unitPrice.toPlainString());
        System.out.println("new BigDecimal(19.99)   = " + fromDouble.toPlainString());
        System.out.println("Hai giá trị bằng nhau (compareTo == 0): "
                + (unitPrice.compareTo(fromDouble) == 0)); // false, khác giá trị chứ không chỉ scale.
    }
}
