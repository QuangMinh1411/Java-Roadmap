package week2.tuesday.LJ;

/*
 * BÀI 2 — CƠ BẢN MỞ RỘNG: MÁY TÍNH AN TOÀN
 * Theo Java Language.md, mục 3.6, 5.1–5.2 và 6.1–6.2.
 *
 * YÊU CẦU
 * 1. Khai báo int a, int b và String operator trong main để thay dữ liệu thử.
 *    Giả định a, b nằm trong [-10000, 10000].
 * 2. Nếu operator là null hoặc chuỗi rỗng, chỉ in "Thieu phep toan" rồi
 *    kết thúc. Dùng đoản mạch để không gọi phương thức trên null.
 * 3. Dùng switch xử lý các phép "+", "-", "*", "/", "%".
 *    Toán tử khác: chỉ in "Phep toan khong hop le" rồi kết thúc.
 * 4. Trước khi thực hiện / hoặc %, nếu b bằng 0, chỉ in
 *    "Khong the chia cho 0" rồi kết thúc. Kết hợp && và || có ngoặc rõ ràng.
 * 5. Kết quả dùng double; phép / phải giữ phần thập phân.
 *    Phép % lấy phần dư của hai số nguyên rồi chuyển kết quả sang double.
 * 6. In kết quả dạng "Ket qua: ...". Nếu cần so sánh nội dung chuỗi,
 *    dùng equals(), không dùng ==. Không cần nhập từ bàn phím.
 *
 * TỰ KIỂM TRA — thay a, b, operator rồi chạy lại
 * - (7, 2, "/") -> Ket qua: 3.5
 * - (-7, 3, "%") -> Ket qua: -1.0
 * - (7, 2, "+") -> 9.0; "-" -> 5.0; "*" -> 14.0.
 * - (7, 0, "/") và (7, 0, "%") -> Khong the chia cho 0.
 * - (7, 0, "+") -> Ket qua: 7.0, vẫn được tính bình thường.
 * - (7, 2, "^") -> Phep toan khong hop le.
 * - operator = null hoặc "" -> Thieu phep toan, không phát sinh exception.
 */
public class Exercise02SafeCalculator {
    public static void main(String[] args) {
        int a = 7;
        int b = 2;
        String operator = "/";

        // Nếu operator == null, Java bỏ qua lời gọi isEmpty().
        if (operator == null || operator.isEmpty()) {
            System.out.println("Thieu phep toan");
            return;
        }

        if ((operator.equals("/") || operator.equals("%")) && b == 0) {
            System.out.println("Khong the chia cho 0");
            return;
        }

        double result;
        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                // Ép kiểu trước khi chia để giữ phần thập phân.
                result = (double) a / b;
                break;
            case "%":
                // Tính phần dư nguyên trước, sau đó tự động chuyển sang double.
                result = a % b;
                break;
            default:
                System.out.println("Phep toan khong hop le");
                return;
        }
        System.out.println("Ket qua: " + result);
    }
}
