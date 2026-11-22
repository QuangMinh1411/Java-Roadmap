package week4.tuesday;

/**
 * Minh họa nạp chồng (§5.7), phạm vi biến (§5.6) và mở rộng kiểu (§5.11)
 * trong Methods.md. Hai hàm max chỉ tính và trả kết quả; main phụ trách xuất.
 *
 * Hai chữ ký khác nhau: max(int, int) và max(double, double).
 * Trình biên dịch chọn overload theo kiểu đối số tại nơi gọi.
 * Chỉ đổi kiểu trả về hoặc tên tham số không tạo được overload mới.
 */
public class MaxOverloadScopeDemo {
    /** Trả số lớn hơn trong hai số nguyên; nhận mọi giá trị int. */
    public static int max(int a, int b) {
        int result = a >= b ? a : b;
        // result thuộc riêng max(int, int), từ khai báo đến hết khối này.
        return result;
    }

    /**
     * Trả số lớn hơn trong hai số thực hữu hạn.
     * Ví dụ này giả định đầu vào hữu hạn, không xét NaN hoặc vô cực.
     */
    public static double max(double a, double b) {
        double result = a >= b ? a : b;
        // Cùng tên result nhưng là biến khác, thuộc riêng max(double, double).
        // Hai phạm vi nằm trong hai hàm khác nhau nên không xung đột.
        return result;
    }

    public static void main(String[] args) {
        // Cả hai đối số là int: bản int khớp trực tiếp, được ưu tiên hơn
        // bản double cần mở rộng cả hai đối số.
        int positiveMax = max(3, 7);
        int negativeMax = max(-2, -5);

        // Cả hai đối số là double: chọn max(double, double).
        double decimalMax = max(2.5, 2.4);

        /*
         * Trace max(2, 3.0):
         * 1. Kiểu đối số lần lượt là int và double.
         * 2. max(int, int) không phù hợp: Java không tự thu hẹp 3.0 thành int
         *    khi gọi hàm, dù giá trị 3.0 biểu diễn một số nguyên.
         * 3. max(double, double) phù hợp: mở rộng 2 từ int thành double 2.0.
         * 4. Khi biên dịch, chọn max(double, double); kiểu trả về là double.
         * 5. Khi chạy: a = 2.0, b = 3.0; a >= b là false;
         *    result cục bộ của bản double nhận 3.0 rồi được trả về.
         * Kiểu biến nhận kết quả không quyết định overload được chọn.
         */
        var mixedMax = max(2, 3.0); // var suy ra double.

        // Kiểm tra kết quả; lỗi sẽ làm chương trình dừng ngay.
        if (positiveMax != 7 || negativeMax != -2
                || Double.compare(decimalMax, 2.5) != 0
                || Double.compare(mixedMax, 3.0) != 0) {
            throw new AssertionError("Kết quả max không đúng như mong đợi");
        }

        System.out.println("max(3, 7) -> max(int, int) = " + positiveMax + " (PASS)");
        System.out.println("max(-2, -5) -> max(int, int) = " + negativeMax + " (PASS)");
        System.out.println("max(2.5, 2.4) -> max(double, double) = " + decimalMax + " (PASS)");
        System.out.println("Trace max(2, 3.0): int, double -> mở rộng 2 thành 2.0"
                + " -> max(double, double) = " + mixedMax + " (PASS)");

        // Không thể dùng tên result ở main: result chỉ thuộc thân từng hàm max.
        // System.out.println(result); // Bỏ comment sẽ gây lỗi cannot find symbol.
        // Mỗi lần gọi có biến cục bộ riêng; khi hàm trả về, biến đó hết vòng đời.
        System.out.println("Scope: hai biến result thuộc hai hàm max riêng biệt;"
                + " main không truy cập trực tiếp được chúng.");
    }
}
