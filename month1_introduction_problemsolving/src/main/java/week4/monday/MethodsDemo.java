package week4.monday;

/**
 * Minh họa khai báo, gọi và trả về từ phương thức static trong Methods.md.
 * Mỗi phương thức có hợp đồng riêng: đầu vào, kết quả và trường hợp bị từ chối.
 * Các phương thức tính toán không nhập/xuất và không thay đổi trạng thái chung.
 * Các phép tính double tuân theo số học dấu phẩy động của Java, có thể làm tròn;
 * NaN và vô cực được xử lý theo quy tắc của double.
 */
public class MethodsDemo {
    /**
     * Hợp đồng:
     * Tiền điều kiện: nhận mọi giá trị int, kể cả số âm.
     * Hậu điều kiện: trả true khi n >= 2 và chỉ có hai ước dương là 1 và n;
     * trả false trong các trường hợp còn lại, không ném ngoại lệ với n < 2.
     *
     * @param n số nguyên cần kiểm tra
     * @return n có phải số nguyên tố hay không
     */
    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }

        // Chỉ cần thử đến căn bậc hai; dùng phép chia để tránh tràn divisor * divisor.
        for (int divisor = 2; divisor <= n / divisor; divisor++) {
            if (n % divisor == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Hợp đồng:
     * Tiền điều kiện: nhận mọi giá trị double biểu diễn nhiệt độ Celsius.
     * Hậu điều kiện: trả nhiệt độ Fahrenheit theo công thức celsius * (9.0 / 5.0) + 32.0.
     * Đây là phép đổi đơn vị, không kiểm tra giới hạn nhiệt độ vật lý.
     *
     * @param celsius nhiệt độ theo độ C
     * @return nhiệt độ theo độ F, tính bằng số học double
     */
    public static double celsiusToFahrenheit(double celsius) {
        // 9.0 / 5.0 cho 1.8; 9 / 5 là chia nguyên và chỉ cho 1.
        return celsius * (9.0 / 5.0) + 32.0;
    }

    /**
     * Hợp đồng:
     * Tiền điều kiện: count > 0; sum nhận mọi giá trị double biểu diễn tổng.
     * Hậu điều kiện khi hợp lệ: trả sum / count bằng phép chia double,
     * giữ phần thập phân (ví dụ tổng 10, count 3 cho xấp xỉ 3.3333333333333335).
     * Nếu count <= 0, ném IllegalArgumentException trước khi thực hiện phép chia.
     *
     * @param sum tổng các giá trị
     * @param count số lượng giá trị, phải dương
     * @return trung bình cộng, có thể được làm tròn theo kiểu double
     * @throws IllegalArgumentException nếu count bằng 0 hoặc âm
     */
    public static double average(double sum, int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("count phải > 0 để tính trung bình (count=" + count + ")");
        }
        // sum là double nên count được chuyển sang double trước phép chia.
        return sum / count;
    }

    public static void main(String[] args) {
        checkPrime(-7, false);
        checkPrime(1, false);
        checkPrime(2, true);
        checkPrime(17, true);
        checkPrime(18, false);
        checkPrime(49, false); // Phải kiểm tra cả ước đúng bằng căn bậc hai.

        checkClose("celsiusToFahrenheit(-40.0)", celsiusToFahrenheit(-40.0), -40.0);
        checkClose("average(10, 3)", average(10, 3), 3.3333333333333335);
        checkInvalidCount(0);
        checkInvalidCount(-1);
    }

    private static void checkPrime(int n, boolean expected) {
        boolean actual = isPrime(n);
        if (actual != expected) {
            throw new AssertionError("isPrime(" + n + "): mong đợi " + expected + ", nhận " + actual);
        }
        System.out.printf("isPrime(%d) = %b (PASS)%n", n, actual);
    }

    private static void checkClose(String label, double actual, double expected) {
        // Sai số nhỏ cho các kết quả hữu hạn trong ví dụ; không dùng == cho double nói chung.
        if (!Double.isFinite(actual) || Math.abs(actual - expected) > 1e-12) {
            throw new AssertionError(label + ": mong đợi " + expected + ", nhận " + actual);
        }
        System.out.println(label + " = " + actual + " (PASS)");
    }

    private static void checkInvalidCount(int count) {
        try {
            average(10, count);
        } catch (IllegalArgumentException e) {
            System.out.println("average(10, " + count + ") -> Đã chặn: " + e.getMessage() + " (PASS)");
            return;
        }
        throw new AssertionError("Phải chặn average khi count=" + count);
    }
}
