package project.tests;

import project.StudyToolsCli;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/** 12 ca tích hợp: chạy menu thật với đầu vào cố định, so expected/actual theo thứ tự. */
public class StudyToolsCliTest {
    private static int passed;

    public static void main(String[] args) {
        System.out.println("# Kết quả kiểm thử StudyTools CLI\n");
        System.out.println("Chạy bằng Java " + System.getProperty("java.version")
                + ". Input là các token theo thứ tự; actual trích từ stdout thật.\n");
        System.out.println("| Ca | Input | Expected | Actual | Kết quả |");
        System.out.println("|---|---|---|---|---|");
        check("01 — Bốn phép toán", "1 7 + 2 1 7 - 2 1 7 * 2 1 7 / 2 0",
                "7.0000 + 2.0000 = 9.0000", "7.0000 - 2.0000 = 5.0000",
                "7.0000 * 2.0000 = 14.0000", "7.0000 / 2.0000 = 3.5000");
        check("02 — Chia 0 rồi tiếp tục", "1 7 / 0 1 7 / -0.0 2 0 0",
                "Không thể chia cho 0.", "Không thể chia cho 0.", "0.00°C = 32.00°F");
        check("03 — Sai số và toán tử", "1 abc 7 ^ / xyz 2 0",
                "Phải nhập số; bỏ qua: abc", "Phép toán không hợp lệ.",
                "Phải nhập số; bỏ qua: xyz", "7.0000 / 2.0000 = 3.5000");
        check("04 — Nhiệt độ", "2 0 2 -40 0",
                "0.00°C = 32.00°F", "-40.00°C = -40.00°F");
        check("05 — Số nguyên tố và biên int", "3 1 3 2 3 9 3 -3 3 0 3 2147483647 0",
                "1 không phải là số nguyên tố.", "2 là số nguyên tố.",
                "9 không phải là số nguyên tố.", "-3 không phải là số nguyên tố.",
                "0 không phải là số nguyên tố.", "2147483647 là số nguyên tố.");
        check("06 — Mẫu nghiệm thu điểm", "4 2 3 5 -1 0",
                "count=3; min=2.0000; max=5.0000; average=3.3333");
        check("07 — Loại điểm sai, nhận biên", "4 11 -2 abc 0 10 -1 0",
                "Điểm phải nằm trong [0, 10]; -1 để kết thúc.",
                "Điểm phải nằm trong [0, 10]; -1 để kết thúc.",
                "Phải nhập số; bỏ qua: abc",
                "count=2; min=0.0000; max=10.0000; average=5.0000");
        check("08 — Rỗng và khởi tạo lại", "4 -1 4 8 -1 4 2 -1 0",
                "Chưa có điểm; không thể tính trung bình.",
                "count=1; min=8.0000; max=8.0000; average=8.0000",
                "count=1; min=2.0000; max=2.0000; average=2.0000");
        check("09 — Menu sai", "\n abc 9 2.5 2147483648 0",
                "Phải nhập số nguyên; bỏ qua: abc", "Lựa chọn không hợp lệ. Hãy chọn 0..4.",
                "Phải nhập số nguyên; bỏ qua: 2.5", "Phải nhập số nguyên; bỏ qua: 2147483648");
        check("10 — Số nguyên sai kiểu", "3 chu 2.5 2147483648 2 0",
                "Phải nhập số nguyên; bỏ qua: chu", "Phải nhập số nguyên; bỏ qua: 2.5",
                "Phải nhập số nguyên; bỏ qua: 2147483648", "2 là số nguyên tố.");
        check("11 — Số không hữu hạn và tràn", "2 NaN Infinity 1e309 1e308 1 1e308 * 10 2 0 0",
                "Phải nhập số hữu hạn.", "Phải nhập số hữu hạn.", "Phải nhập số hữu hạn.",
                "Kết quả vượt phạm vi double.", "Kết quả vượt phạm vi double.", "0.00°C = 32.00°F");
        check("12 — Liên tiếp bốn chức năng, EOF", "1 2 + 3\n2 0\n3 2\n4 2 3 5 -1\n1 7 /",
                "2.0000 + 3.0000 = 5.0000", "0.00°C = 32.00°F", "2 là số nguyên tố.",
                "count=3; min=2.0000; max=5.0000; average=3.3333", "Hết dữ liệu nhập.");
        System.out.println("\nĐạt " + passed + "/12 ca. Mỗi ca kiểm tra các đoạn output theo đúng thứ tự,");
        System.out.println("và chỉ có một thông báo kết thúc (chọn 0 hoặc EOF). Exception làm ca kiểm thử thất bại.");
    }

    private static void check(String name, String input, String... expected) {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try (PrintStream capture = new PrintStream(output, true, StandardCharsets.UTF_8)) {
            System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
            System.setOut(capture);
            StudyToolsCli.main(new String[0]);
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
        String actual = output.toString(StandardCharsets.UTF_8);
        int position = 0;
        StringBuilder excerpts = new StringBuilder();
        for (String fragment : expected) {
            int found = actual.indexOf(fragment, position);
            if (found < 0) {
                throw new AssertionError(name + ": thiếu/sai thứ tự: " + fragment + "\nActual:\n" + actual);
            }
            if (!excerpts.isEmpty()) excerpts.append("<br>");
            excerpts.append(actual, found, found + fragment.length());
            position = found + fragment.length();
        }
        String ending = name.startsWith("12") ? "Hết dữ liệu nhập." : "Thoát chương trình.";
        if (!actual.stripTrailing().endsWith(ending)
                || count(actual, "Hết dữ liệu nhập.") + count(actual, "Thoát chương trình.") != 1) {
            throw new AssertionError(name + ": kết thúc không đúng\n" + actual);
        }
        passed++;
        System.out.println("| " + name + " | `" + input.strip().replace("\n", " ↵ ")
                + "` | " + String.join("<br>", expected) + " | " + excerpts + " | PASS |");
    }

    private static int count(String text, String fragment) {
        return (text.length() - text.replace(fragment, "").length()) / fragment.length();
    }
}
