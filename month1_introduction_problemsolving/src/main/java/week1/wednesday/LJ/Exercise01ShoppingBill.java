package week1.wednesday.LJ;

import java.util.Locale;

/*
 * BÀI 1 — CƠ BẢN: TÍNH TIỀN MUA HÀNG
 * Kiến thức: kiểu dữ liệu, biến, final, toán tử, nối chuỗi và chú thích.
 *
 * ĐỀ BÀI
 * 1. Khai báo tên sản phẩm bằng String, đơn giá bằng double, số lượng bằng int.
 * 2. Khai báo hằng số DISCOUNT_RATE = 0.1, giảm 10% cho đơn hàng.
 * 3. Tính thành tiền, tiền giảm và số tiền phải trả; in hóa đơn.
 * 4. Viết chú thích một dòng và nhiều dòng giải thích chương trình.
 * Giả sử đơn giá và số lượng không âm.
 *
 * KẾT QUẢ MẪU
 * Tên sản phẩm: Sách Java
 * Đơn giá: 100000.00
 * Số lượng: 3
 * Thành tiền: 300000.00
 * Tiền giảm: 30000.00
 * Phải trả: 270000.00
 *
 * TỰ KIỂM TRA: đổi quantity thành 0 thì số tiền phải trả bằng 0.
 * CHẠY RIÊNG: chạy main() trong IntelliJ, không cần nhập dữ liệu.
 */
public class Exercise01ShoppingBill {
    public static void main(String[] args) {
        String productName = "Sách Java";
        double unitPrice = 100_000;
        int quantity = 3;
        final double DISCOUNT_RATE = 0.1;

        // Tính thành tiền trước khi áp dụng giảm giá.
        double subtotal = unitPrice * quantity;
        /*
         * Tiền giảm được tính trên toàn bộ thành tiền.
         * Số tiền phải trả là phần còn lại sau khi trừ tiền giảm.
         */
        double discountAmount = subtotal * DISCOUNT_RATE;
        double total = subtotal - discountAmount;

        System.out.println("Tên sản phẩm: " + productName);
        System.out.printf(Locale.ROOT, "Đơn giá: %.2f%n", unitPrice);
        System.out.println("Số lượng: " + quantity);
        System.out.printf(Locale.ROOT, "Thành tiền: %.2f%n", subtotal);
        System.out.printf(Locale.ROOT, "Tiền giảm: %.2f%n", discountAmount);
        System.out.printf(Locale.ROOT, "Phải trả: %.2f%n", total);
    }
}