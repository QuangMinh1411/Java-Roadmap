package week2;

/**
 * Hóa đơn nguyên đồng:
 * - Tổng tiền >= 1000000: giảm 10%; dưới ngưỡng: không giảm theo hóa đơn.
 * - Thành viên giảm thêm 5% trên số tiền còn lại, kể cả khi dưới ngưỡng.
 * - Chấp nhận 0 đồng, từ chối số tiền âm.
 * - Quy ước: làm tròn đến đồng gần nhất sau MỖI bước giảm;
 *   phần lẻ từ 0.5 đồng làm tròn lên.
 * Ví dụ: 1000000 -> 900000 -> 855000 nếu là thành viên.
 */
public class InvoiceDiscount {
    public static int calculatePayable(int total, boolean member) {
        if (total < 0) {
            throw new IllegalArgumentException("Tổng tiền không được âm");
        }

        // Dùng long cho phép nhân trung gian để tránh tràn kiểu int.
        long payable;
        if (total >= 1_000_000) {
            payable = (total * 90L + 50) / 100;
        } else { // 0 <= total < 1000000: không giảm theo ngưỡng.
            payable = total;
        }

        // Đây là ưu đãi độc lập: áp dụng SAU mức giảm theo hóa đơn.
        // Không cộng 10% và 5% thành 15% trên tổng tiền ban đầu.
        if (member) {
            payable = (payable * 95 + 50) / 100;
        }

        // Số tiền sau giảm luôn nằm trong [0, total], vừa kiểu int.
        return (int) payable;
    }

    public static void main(String[] args) {
        // Mỗi dòng: {tổng tiền, thành viên (1 = có, 0 = không), kỳ vọng}.
        // Kỳ vọng -1 nghĩa là phải từ chối đầu vào.
        int[][] testCases = {
                {-1,        0, -1},
                {-1,        1, -1},
                {0,         0, 0},
                {0,         1, 0},
                {999_999,   0, 999_999},
                {999_999,   1, 949_999},
                {1_000_000, 0, 900_000},
                {1_000_000, 1, 855_000},
                {1_000_001, 0, 900_001},
                {1_000_001, 1, 855_001}
        };

        for (int i = 0; i < testCases.length; i++) {
            int total = testCases[i][0];
            boolean member = testCases[i][1] == 1;
            int expected = testCases[i][2];
            try {
                int actual = calculatePayable(total, member);
                if (actual != expected) {
                    throw new AssertionError("Ca " + (i + 1)
                            + ": kỳ vọng " + expected + ", thực tế " + actual);
                }
                System.out.printf("Ca %d: tổng=%d, thành viên=%b -> %d đồng (ĐẠT)%n",
                        i + 1, total, member, actual);
            } catch (IllegalArgumentException e) {
                if (expected != -1) {
                    throw new AssertionError("Ca " + (i + 1) + ": bị từ chối ngoài dự kiến", e);
                }
                System.out.printf("Ca %d: tổng=%d, thành viên=%b -> từ chối (ĐẠT)%n",
                        i + 1, total, member);
            }
        }
        System.out.println("Đạt " + testCases.length + "/" + testCases.length + " ca kiểm tra.");
    }
}
