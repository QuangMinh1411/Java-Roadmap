package week4;

import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

/**
 * Hóa đơn nguyên đồng — tách tính toán khỏi nhập/xuất.
 * Từ 1_000_000 đồng giảm 10%; thành viên giảm tiếp 5% trên tiền còn lại,
 * kể cả khi hóa đơn dưới ngưỡng. Không cộng hai mức thành 15%.
 * Chấp nhận 0 đồng, từ chối tiền âm.
 * Làm tròn tiền phải trả đến đồng gần nhất SAU MỖI bước, từ 0.5 làm tròn lên,
 * cùng quy ước với week2.InvoiceDiscount. Tiền giảm = tiền trước - tiền sau.
 */
public class InvoiceDiscount {
    public static int calculateThresholdDiscount(int total) {
        requireNonNegative(total);
        if (total < 1_000_000) {
            return 0;
        }
        return total - remainingAfterDiscount(total, 10);
    }

    /** afterThreshold là tiền còn lại sau khi đã giảm theo ngưỡng. */
    public static int calculateMemberDiscount(int afterThreshold, boolean member) {
        requireNonNegative(afterThreshold);
        return member ? afterThreshold - remainingAfterDiscount(afterThreshold, 5) : 0;
    }

    public static int calculateTotalDiscount(int total, boolean member) {
        int thresholdDiscount = calculateThresholdDiscount(total);
        int afterThreshold = total - thresholdDiscount;
        return thresholdDiscount + calculateMemberDiscount(afterThreshold, member);
    }

    /**
     * Hàm thuần trả số tiền phải trả, nguyên đồng, theo quy tắc tuần 2.
     * Miền subtotal: 0..Integer.MAX_VALUE; tiền âm bị từ chối trước khi tính.
     * Không nhập/xuất và không dùng 0 làm giá trị thay thế cho tiền sai.
     */
    public static int calculateInvoice(int subtotal, boolean member) {
        return subtotal - calculateTotalDiscount(subtotal, member);
    }

    public static int calculatePayable(int total, boolean member) {
        return calculateInvoice(total, member);
    }

    private static int remainingAfterDiscount(int amount, int percent) {
        // Nhân bằng long để không tràn int; +50 trước /100 để làm tròn nửa lên.
        // Kết quả không lớn hơn amount nên ép về int an toàn.
        return (int) ((amount * (100L - percent) + 50) / 100);
    }

    private static void requireNonNegative(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Tổng tiền không được âm.");
        }
    }

    /** Trả null khi EOF; yêu cầu nhập lại nếu sai kiểu hoặc âm. */
    public static Integer readTotal(Scanner input, PrintStream output) {
        while (true) {
            output.print("Nhập tổng hóa đơn (nguyên đồng, không âm): ");
            if (!input.hasNext()) {
                output.println("Hết dữ liệu nhập.");
                return null;
            }
            if (!input.hasNextInt()) {
                output.println("Phải nhập số nguyên trong miền int; bỏ qua: " + input.next());
                continue;
            }
            int total = input.nextInt();
            if (total >= 0) {
                return total;
            }
            output.println("Tổng tiền không được âm.");
        }
    }

    /** Kiểm tra Scanner trước khi đọc boolean; không dùng chuỗi bất kỳ làm false. */
    public static Boolean readMembership(Scanner input, PrintStream output) {
        while (true) {
            output.print("Là thành viên? (true/false): ");
            if (!input.hasNext()) {
                output.println("Hết dữ liệu nhập.");
                return null;
            }
            if (input.hasNextBoolean()) {
                return input.nextBoolean();
            }
            output.println("Phải nhập true hoặc false; bỏ qua: " + input.next());
        }
    }

    public static void printInvoice(PrintStream output, int total, boolean member) {
        // Tính trước khi in để không xuất hóa đơn dở dang nếu đầu vào sai.
        int payable = calculateInvoice(total, member);
        int thresholdDiscount = calculateThresholdDiscount(total);
        int memberDiscount = calculateMemberDiscount(total - thresholdDiscount, member);
        output.println("=== HÓA ĐƠN ===");
        output.println("Tổng ban đầu: " + total + " đồng");
        output.println("Thành viên: " + member);
        output.println("Giảm theo ngưỡng: " + thresholdDiscount + " đồng");
        output.println("Giảm thành viên: " + memberDiscount + " đồng");
        output.println("Tổng giảm: " + calculateTotalDiscount(total, member) + " đồng");
        output.println("Phải trả: " + payable + " đồng");
    }

    /** Chỉ in hóa đơn khi đã đọc được cả tiền và trạng thái thành viên hợp lệ. */
    public static void run(Scanner input, PrintStream output) {
        Integer total = readTotal(input, output);
        if (total == null) {
            return;
        }
        Boolean member = readMembership(input, output);
        if (member != null) {
            printInvoice(output, total, member);
        }
    }

    public static void main(String[] args) {
        try (var input = new Scanner(System.in).useLocale(Locale.ROOT)) {
            run(input, System.out);
        }
    }
}
