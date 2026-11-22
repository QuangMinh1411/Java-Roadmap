package week4.tests;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import week4.InvoiceDiscount;

/** Chạy độc lập, không cần JUnit; kiểm tra hàm tính toán và nhập/xuất với cùng 10 ca. */
public class InvoiceDiscountTest {
    public static void main(String[] args) {
        // {tổng, thành viên 0/1, giảm ngưỡng, giảm thành viên, phải trả}.
        // Kỳ vọng -1: API phải từ chối, hàm nhập phải yêu cầu nhập lại.
        int[][] cases = {
                {-1,        0, -1,      -1,     -1},
                {-1,        1, -1,      -1,     -1},
                {0,         0, 0,       0,      0},
                {0,         1, 0,       0,      0},
                {999_999,   0, 0,       0,      999_999},
                {999_999,   1, 0,       50_000, 949_999},
                {1_000_000, 0, 100_000, 0,      900_000},
                {1_000_000, 1, 100_000, 45_000, 855_000},
                {1_000_001, 0, 100_000, 0,      900_001},
                {1_000_001, 1, 100_000, 45_000, 855_001}
        };
        for (int i = 0; i < cases.length; i++) {
            int total = cases[i][0];
            boolean member = cases[i][1] == 1;
            var bytes = new ByteArrayOutputStream();
            try (var output = new PrintStream(bytes, true, StandardCharsets.UTF_8);
                 // Chữ thay số và boolean sai phải được bỏ qua trước khi đọc lại.
                 var input = new Scanner("abc " + total + (total < 0 ? " 0" : "")
                         + " invalid " + member)) {
                Integer readTotal = InvoiceDiscount.readTotal(input, output);
                Boolean readMember = InvoiceDiscount.readMembership(input, output);
                check(readTotal != null && readTotal == Math.max(0, total), "nhập tổng");
                check(readMember != null && readMember == member, "nhập thành viên");
                String prompts = bytes.toString(StandardCharsets.UTF_8);
                check(prompts.contains("bỏ qua: abc") && prompts.contains("bỏ qua: invalid"),
                        "bỏ qua token sai");
                if (total < 0) {
                    rejects(() -> InvoiceDiscount.calculateInvoice(total, member));
                    rejects(() -> InvoiceDiscount.calculateThresholdDiscount(total));
                    rejects(() -> InvoiceDiscount.calculateMemberDiscount(total, member));
                    rejects(() -> InvoiceDiscount.calculateTotalDiscount(total, member));
                    rejects(() -> InvoiceDiscount.calculatePayable(total, member));
                    check(prompts.contains("Tổng tiền không được âm."), "nhập tiền âm");
                    bytes.reset();
                    rejects(() -> InvoiceDiscount.printInvoice(output, total, member));
                    check(bytes.size() == 0, "không in hóa đơn âm");
                } else {
                    int threshold = InvoiceDiscount.calculateThresholdDiscount(total);
                    int membership = InvoiceDiscount.calculateMemberDiscount(total - threshold, member);
                    int discount = InvoiceDiscount.calculateTotalDiscount(total, member);
                    int payable = InvoiceDiscount.calculatePayable(total, member);
                    check(InvoiceDiscount.calculateInvoice(total, member) == cases[i][4],
                            "calculateInvoice");
                    check(threshold == cases[i][2], "giảm ngưỡng");
                    check(membership == cases[i][3], "giảm thành viên");
                    check(payable == cases[i][4], "tiền phải trả");
                    check(discount == threshold + membership && discount + payable == total,
                            "tổng tiền và tiền giảm phải khớp");
                    bytes.reset();
                    InvoiceDiscount.printInvoice(output, total, member);
                    String invoice = bytes.toString(StandardCharsets.UTF_8);
                    check(invoice.contains("Phải trả: " + cases[i][4] + " đồng"), "in tiền phải trả");
                    check(invoice.contains("Tổng giảm: " + (total - cases[i][4]) + " đồng"), "in tổng giảm");
                }
            }
            System.out.printf("PASS %02d: tổng=%d, thành viên=%b -> %s%n", i + 1, total, member,
                    total < 0 ? "từ chối tiền âm" : cases[i][4] + " đồng");
        }
        System.out.println("10/10 PASS (hàm tính toán và nhập/xuất).");
        checkInvalidInputDoesNotPrintInvoice();
        checkRetryUsesCorrectAmount();
    }

    private static void checkInvalidInputDoesNotPrintInvoice() {
        String[] invalidInputs = {"-1 true", "abc false", "1.5 true", "2147483648 false",
                "1000000", "1000000 invalid"};
        for (String text : invalidInputs) {
            String actual = runConsole(text);
            check(!actual.contains("=== HÓA ĐƠN ===") && !actual.contains("Phải trả:"),
                    "không in hóa đơn khi đầu vào sai hoặc thiếu: " + text);
            check(actual.contains("Hết dữ liệu nhập."), "thoát khi hết dữ liệu");
            System.out.println("PASS đầu vào sai/thiếu không in hóa đơn: " + text);
        }
    }

    private static void checkRetryUsesCorrectAmount() {
        String actual = runConsole("-1 abc 1000000 invalid true");
        check(actual.contains("Tổng tiền không được âm."), "báo tiền âm trước khi nhập lại");
        check(actual.contains("Tổng ban đầu: 1000000 đồng"), "dùng tiền đã sửa");
        check(actual.contains("Phải trả: 855000 đồng"), "tính lại sau nhập đúng");
        check(actual.indexOf("=== HÓA ĐƠN ===") == actual.lastIndexOf("=== HÓA ĐƠN ==="),
                "chỉ in một hóa đơn sau nhập đúng");
        System.out.println("PASS nhập lại: chỉ in hóa đơn 855000 đồng sau khi có dữ liệu đúng.");
    }

    private static String runConsole(String text) {
        var bytes = new ByteArrayOutputStream();
        try (var output = new PrintStream(bytes, true, StandardCharsets.UTF_8);
             var input = new Scanner(text)) {
            InvoiceDiscount.run(input, output);
        }
        return bytes.toString(StandardCharsets.UTF_8);
    }

    private static void rejects(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException expected) {
            return;
        }
        throw new AssertionError("Phải từ chối tiền âm.");
    }

    private static void check(boolean condition, String label) {
        if (!condition) {
            throw new AssertionError(label);
        }
    }
}
