package week2.wednesday.jfp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

/*
 * BÀI 5 — NÂNG CAO: LÃI KÉP VÀ CHÍNH SÁCH LÀM TRÒN
 * Tham khảo More_Control_Statement.md, mục 5 và 11–13.
 *
 * ĐỀ BÀI
 * Tiền gốc 1000.00, lãi suất năm 0.05, thời hạn 10 năm.
 * 1. Dùng for in bảng năm 1–10, gồm:
 *    - Tiền theo double: principal * Math.pow(1 + rate, year).
 *    - Tiền BigDecimal chính xác tính từ gốc: p * (1 + r).pow(year).
 *    - Kết quả đó làm tròn 2 chữ số với HALF_EVEN và HALF_UP.
 * 2. Khởi tạo BigDecimal từ chuỗi; không dùng new BigDecimal(double).
 *    Giữ nguyên kết quả chính xác, chỉ làm tròn bản dùng để hiển thị.
 * 3. Đếm và in số năm mà HALF_EVEN và HALF_UP cho kết quả khác nhau.
 *    So sánh giá trị số bằng compareTo, không dùng ==.
 * 4. Tính thêm số dư làm tròn HALF_EVEN sau mỗi năm rồi tái đầu tư.
 *    In cạnh bảng để thấy thời điểm làm tròn ảnh hưởng kết quả.
 * 5. Giải thích bằng comment vì sao setScale phải dùng giá trị trả về.
 *
 * TỰ KIỂM TRA
 * - Năm 1: cả hai cách làm tròn đều là 1050.00.
 * - Năm 3: chính xác 1157.625; HALF_EVEN=1157.62, HALF_UP=1157.63.
 * - Chỉ năm 3 có hai kết quả làm tròn khác nhau.
 * - Năm 10 tính từ gốc: 1628.89; làm tròn và tái đầu tư mỗi năm: 1628.88.
 * - Đây là mô hình học tập; hai thời điểm làm tròn là hai quy tắc khác nhau.
 */
public class Exercise05CompoundInterest {
    public static void main(String[] args) {
        double principalDouble = 1000.00;
        double rateDouble = 0.05;
        BigDecimal principal = new BigDecimal("1000.00");
        BigDecimal rate = new BigDecimal("0.05");
        BigDecimal growth = BigDecimal.ONE.add(rate);
        BigDecimal yearlyBalance = principal;
        int differentYears = 0;

        System.out.printf("%3s %12s %26s %12s %12s %12s%n",
                "Năm", "double", "Chính xác", "HALF_EVEN", "HALF_UP", "Từng năm");
        for (int year = 1; year <= 10; ++year) {
            double approximate = principalDouble * Math.pow(1 + rateDouble, year);
            BigDecimal exact = principal.multiply(growth.pow(year));
            // BigDecimal bất biến: setScale trả về kết quả mới.
            BigDecimal halfEven = exact.setScale(2, RoundingMode.HALF_EVEN);
            BigDecimal halfUp = exact.setScale(2, RoundingMode.HALF_UP);
            yearlyBalance = yearlyBalance.multiply(growth)
                    .setScale(2, RoundingMode.HALF_EVEN);
            if (halfEven.compareTo(halfUp) != 0) {
                ++differentYears;
            }
            System.out.printf(Locale.US, "%3d %12.2f %26s %12s %12s %12s%n",
                    year, approximate, exact.stripTrailingZeros().toPlainString(),
                    halfEven.toPlainString(), halfUp.toPlainString(),
                    yearlyBalance.toPlainString());
        }
        System.out.println("Số năm HALF_EVEN khác HALF_UP: " + differentYears);
    }
}
