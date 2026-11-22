package week3.tuesday;

/**
 * Minh hoạ for lồng nhau, while, biến đếm và phép gán kết hợp.
 * Tham khảo: week3/Control Statement.md (chương 4: for)
 * và week3/Fundamental Programming Structure.md (§3.5, §3.7, §3.8).
 *
 * Thứ tự của for: khởi tạo -> kiểm tra -> thân -> cập nhật -> kiểm tra lại.
 * Với dãy giảm, while kiểm tra trước thân: i bắt đầu ở 19,
 * tiếp tục khi i >= 1, cập nhật i -= 2 ở cuối thân.
 * Có thể viết tương đương: for (int i = 19; i >= 1; i -= 2).
 * Số phần tử trên giấy: (19 - 1) / 2 + 1 = 10.
 * Lượt cuối in 1 rồi cập nhật thành -1; lần kiểm tra tiếp theo thất bại,
 * nên -1 là trạng thái sau cập nhật, không phải phần tử được in của dãy.
 *
 * Nếu thay bằng i += 2, ba lượt đầu là 19 -> 21 -> 23 -> 25.
 * Khi chưa tràn số, i càng tăng càng xa miền dừng i < 1.
 * Không gọi đây là vòng lặp vô hạn tuyệt đối với int: khi vượt
 * Integer.MAX_VALUE, int tràn thành số âm và điều kiện i >= 1 sai.
 * Đó là dừng do tràn số, không phải cách sinh đúng dãy giảm.
 */
public class MultiplicationTableAndOddSequence {
    public static void main(String[] args) {
        printMultiplicationTable();
        printDescendingOddNumbers();
        traceWrongUpdate();
    }

    private static void printMultiplicationTable() {
        System.out.println("BẢNG NHÂN 1–10 (hàng × cột)");
        System.out.printf("%5s", "×");
        for (int column = 1; column <= 10; column++) {
            System.out.printf("%5d", column);
        }
        System.out.println();

        for (int row = 1; row <= 10; row++) {
            System.out.printf("%5d", row);
            for (int column = 1; column <= 10; column++) {
                System.out.printf("%5d", row * column);
            }
            System.out.println();
        }
    }

    private static void printDescendingOddNumbers() {
        StringBuilder sequence = new StringBuilder();
        StringBuilder trace = new StringBuilder();
        int count = 0;
        boolean containsNegativeOne = false;
        int i = 19;

        while (i >= 1) {
            int before = i;
            if (count > 0) {
                sequence.append(", ");
            }
            sequence.append(i);
            count++;
            if (i == -1) {
                containsNegativeOne = true;
            }

            // In/ghi phần tử trước, cập nhật sau, rồi mới kiểm tra lại.
            i -= 2;
            if (count <= 3) {
                trace.append(String.format("%4d | %7d | %6b | %10d | %5d | %b%n",
                        count, before, before >= 1, before, i, i >= 1));
            }
        }

        String expected = "19, 17, 15, 13, 11, 9, 7, 5, 3, 1";
        if (count != 10 || containsNegativeOne || !expected.equals(sequence.toString())) {
            throw new AssertionError("Dãy sai: " + sequence + "; số lượng: " + count);
        }

        System.out.println("\nDÃY SỐ LẺ GIẢM");
        System.out.println(sequence);
        System.out.printf("PASS: đúng %d số, không có -1 trong dãy.%n", count);
        System.out.println("\nTrace 3 lượt đầu với i -= 2:");
        System.out.println("Lượt | i trước | i >= 1 | Số được in | i sau | Điều kiện tiếp theo");
        System.out.print(trace);
        System.out.printf("Lượt 10: in 1 -> cập nhật i = %d -> i >= 1 là %b -> dừng.%n",
                i, i >= 1);
    }

    private static void traceWrongUpdate() {
        System.out.println("\nTRACE BƯỚC CẬP NHẬT SAI i += 2 (chỉ mô phỏng 3 lượt)");
        System.out.println("Lượt | i trước | i >= 1 | i sau cập nhật | Điều kiện tiếp theo");
        int i = 19;
        // Dùng biến đếm riêng để ví dụ sai vẫn kết thúc sau đúng 3 lượt.
        for (int turn = 1; turn <= 3; turn++) {
            int before = i;
            boolean conditionBefore = i >= 1;
            i += 2;
            System.out.printf("%4d | %7d | %6b | %14d | %b%n",
                    turn, before, conditionBefore, i, i >= 1);
        }
        System.out.println("i tăng: 19, 21, 23, 25,... nên chưa tiến tới miền dừng i < 1.");
        System.out.println("Với int, cuối cùng tràn từ 2147483647 sang -2147483647 và dừng;");
        System.out.println("đây là lỗi tràn số, không phải cách kết thúc đúng. Dãy giảm cần i -= 2.");
    }
}
