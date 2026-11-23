package week5;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Locale;

/**
 * Mini project mục 10 trong fundamental.md: tính lương và xuất phiếu lương console.
 * Chạy main() trong IDE, không cần nhập dữ liệu hay cài thêm thư viện.
 *
 * Quy ước minh hoạ (tài liệu chưa quy định đầy đủ cách chia bậc thuế):
 * - Thu nhập tính thuế = max(0, Gross - bảo hiểm - TAX_THRESHOLD).
 * - 5 triệu đầu chịu thuế 5%, phần vượt chịu thuế 10% (luỹ tiến).
 * - Tiền VND làm tròn đến đồng theo HALF_UP ở từng khoản trên phiếu lương.
 * Các hằng số bên dưới là cấu hình bài tập, không phải quy định thuế thực tế.
 */
public class PayrollPayslipGenerator {
    private static final BigDecimal TAX_THRESHOLD = new BigDecimal("11000000");
    private static final BigDecimal LOW_TAX_BAND = new BigDecimal("5000000");
    private static final BigDecimal TAX_RATE_LOW = new BigDecimal("0.05");
    private static final BigDecimal TAX_RATE_HIGH = new BigDecimal("0.10");
    private static final BigDecimal INSURANCE_RATE = new BigDecimal("0.105");
    private static final BigDecimal STANDARD_WORK_HOURS = new BigDecimal("176");
    private static final BigDecimal OVERTIME_MULTIPLIER = new BigDecimal("1.5");
    private static final int HOURLY_RATE_SCALE = 12;
    private static final int MONEY_SCALE = 0;
    private static final RoundingMode ROUNDING = RoundingMode.HALF_UP;
    private static final Locale VIETNAMESE = Locale.forLanguageTag("vi-VN");

    public static void main(String[] args) {
        // Ba mảng song song: cùng chỉ số i là dữ liệu của cùng một nhân viên.
        String[] employeeNames = {
                "Nguyễn Minh Anh", "Trần Bảo Ngọc", "José García",
                "Anna Müller", "Łukasz Kowalski"
        };
        BigDecimal[] baseSalaries = {
                new BigDecimal("10000000"), new BigDecimal("15000000"),
                new BigDecimal("20000000"), new BigDecimal("30000000"),
                new BigDecimal("12000000")
        };
        int[] overtimeHours = {0, 10, 20, 8, 4};
        BigDecimal[] netSalaries = new BigDecimal[employeeNames.length];

        StringBuilder report = new StringBuilder();
        report.append("""
                HỆ THỐNG TÍNH LƯƠNG & XUẤT PHIẾU LƯƠNG
                Đơn vị: VND | Dữ liệu và bậc thuế minh hoạ
                Thu nhập tính thuế = max(0, Gross - bảo hiểm - 11.000.000)
                Thuế: 5% cho 5.000.000 đầu tiên, 10% cho phần vượt.
                Làm tròn từng khoản đến đồng bằng HALF_UP.

                """);

        for (int i = 0; i < employeeNames.length; i++) {
            BigDecimal baseSalary = baseSalaries[i];
            BigDecimal overtimePay = calculateOvertimePay(baseSalary, overtimeHours[i]);
            BigDecimal gross = baseSalary.add(overtimePay);
            BigDecimal insurance = baseSalary.multiply(INSURANCE_RATE)
                    .setScale(MONEY_SCALE, ROUNDING);
            BigDecimal taxableIncome = gross.subtract(insurance).subtract(TAX_THRESHOLD)
                    .max(BigDecimal.ZERO);
            BigDecimal tax = calculateTax(taxableIncome);
            BigDecimal net = gross.subtract(insurance).subtract(tax);
            netSalaries[i] = net;

            appendPayslip(report, i + 1, employeeNames[i], overtimeHours[i],
                    baseSalary, overtimePay, gross, insurance, taxableIncome, tax, net);
        }

        appendSummary(report, netSalaries);
        System.out.print(report);
    }

    private static BigDecimal calculateOvertimePay(BigDecimal baseSalary, int hours) {
        // Phép chia cho 176 có thể vô hạn tuần hoàn: phải chỉ định scale và cách làm tròn.
        // Giữ 12 chữ số ở đơn giá giờ, chỉ làm tròn tiền sau khi nhân đủ hệ số và số giờ.
        return baseSalary.divide(STANDARD_WORK_HOURS, HOURLY_RATE_SCALE, ROUNDING)
                .multiply(OVERTIME_MULTIPLIER)
                .multiply(new BigDecimal(Integer.toString(hours)))
                .setScale(MONEY_SCALE, ROUNDING);
    }

    private static BigDecimal calculateTax(BigDecimal taxableIncome) {
        BigDecimal tax;
        // BigDecimal dùng compareTo() để so sánh giá trị, không dùng == hay equals().
        if (taxableIncome.compareTo(BigDecimal.ZERO) <= 0) {
            tax = BigDecimal.ZERO;
        } else if (taxableIncome.compareTo(LOW_TAX_BAND) <= 0) {
            tax = taxableIncome.multiply(TAX_RATE_LOW);
        } else {
            BigDecimal lowBandTax = LOW_TAX_BAND.multiply(TAX_RATE_LOW);
            BigDecimal highBandTax = taxableIncome.subtract(LOW_TAX_BAND)
                    .multiply(TAX_RATE_HIGH);
            tax = lowBandTax.add(highBandTax);
        }
        return tax.setScale(MONEY_SCALE, ROUNDING);
    }

    private static void appendPayslip(StringBuilder report, int number, String name,
                                      int overtimeHours, BigDecimal baseSalary,
                                      BigDecimal overtimePay, BigDecimal gross,
                                      BigDecimal insurance, BigDecimal taxableIncome,
                                      BigDecimal tax, BigDecimal net) {
        report.append(String.format(VIETNAMESE, """
                ========================================================
                PHIẾU LƯƠNG #%d
                Nhân viên: %s
                Giờ làm thêm: %d
                --------------------------------------------------------
                """, number, name, overtimeHours));
        appendMoneyRow(report, "Lương cơ bản", baseSalary);
        appendMoneyRow(report, "Lương làm thêm", overtimePay);
        appendMoneyRow(report, "Tổng thu nhập (Gross)", gross);
        appendMoneyRow(report, "Bảo hiểm (10,5%)", insurance);
        appendMoneyRow(report, "Thu nhập tính thuế", taxableIncome);
        appendMoneyRow(report, "Thuế thu nhập", tax);
        appendMoneyRow(report, "THỰC LĨNH (Net)", net);
        report.append('\n');
    }

    private static void appendSummary(StringBuilder report, BigDecimal[] netSalaries) {
        // Sao chép trước khi sort để giữ liên kết tên - lương trong mảng ban đầu.
        BigDecimal[] sortedNetSalaries = Arrays.copyOf(netSalaries, netSalaries.length);
        Arrays.sort(sortedNetSalaries);

        BigDecimal totalPayroll = BigDecimal.ZERO;
        for (BigDecimal netSalary : netSalaries) {
            totalPayroll = totalPayroll.add(netSalary);
        }

        report.append("""
                ========================================================
                BÁO CÁO TỔNG HỢP CUỐI KỲ
                --------------------------------------------------------
                """);
        report.append(String.format(VIETNAMESE, "Số nhân viên: %d%n", netSalaries.length));
        if (sortedNetSalaries.length == 0) {
            report.append("Chưa có dữ liệu lương.\n");
            return;
        }
        appendMoneyRow(report, "Thực lĩnh thấp nhất", sortedNetSalaries[0]);
        appendMoneyRow(report, "Thực lĩnh cao nhất", sortedNetSalaries[sortedNetSalaries.length - 1]);
        appendMoneyRow(report, "Tổng quỹ lương thực lĩnh", totalPayroll);
    }

    private static void appendMoneyRow(StringBuilder report, String label, BigDecimal amount) {
        String money = String.format(VIETNAMESE, "%,.0f VND", amount);
        report.append(String.format(VIETNAMESE, "%-30s %25s%n", label, money));
    }
}
