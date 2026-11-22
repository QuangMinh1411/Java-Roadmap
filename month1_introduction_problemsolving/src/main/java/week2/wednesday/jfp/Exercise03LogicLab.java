package week2.wednesday.jfp;

/*
 * BÀI 3 — TRUNG BÌNH: BẢNG CHÂN TRỊ VÀ ĐOẢN MẠCH
 * Tham khảo More_Control_Statement.md, mục 10.
 *
 * ĐỀ BÀI
 * 1. Dùng hai vòng for chạy 0–1, đổi sang boolean bằng so sánh == 1.
 *    In đủ bốn cặp A, B cùng kết quả &&, ||, ^, !A, & và |.
 * 2. Với divisor từ 0 đến 5, tính điều kiện:
 *    divisor != 0 && 10 / divisor == 2.
 *    In kết quả; không được chia nguyên cho 0.
 * 3. Minh hoạ || bằng divisor == 0 || 10 / divisor > 1.
 *    Viết comment giải thích vế phải bị bỏ qua khi nào.
 * 4. Giải thích tại sao không thay && bằng & ở điều kiện bảo vệ.
 *
 * TỰ KIỂM TRA
 * - AND: false false false true; OR: false true true true.
 * - XOR: false true true false (thứ tự FF, FT, TF, TT).
 * - && và & có cùng kết quả boolean; || và | cũng vậy.
 * - Điều kiện ở bước 2 đúng với divisor = 4, 5 vì chia nguyên.
 * - divisor = 0: bước 2 false, bước 3 true, không có ngoại lệ.
 */
public class Exercise03LogicLab {
    public static void main(String[] args) {
        System.out.println("A B && || ^ !A & |");
        for (int a = 0; a <= 1; ++a) {
            for (int b = 0; b <= 1; ++b) {
                boolean left = a == 1;
                boolean right = b == 1;
                System.out.printf("%b %b %b %b %b %b %b %b%n",
                        left, right, left && right, left || right,
                        left ^ right, !left, left & right, left | right);
            }
        }

        for (int divisor = 0; divisor <= 5; ++divisor) {
            // && bỏ qua vế phải khi vế trái false; & vẫn đánh giá vế phải,
            // nên thay bằng & sẽ gây chia nguyên cho 0 tại divisor = 0.
            boolean quotientIsTwo = divisor != 0 && 10 / divisor == 2;
            // || bỏ qua vế phải khi vế trái true, bảo vệ phép chia cho 0.
            boolean zeroOrLargeQuotient = divisor == 0 || 10 / divisor > 1;
            System.out.printf("divisor=%d: AND=%b, OR=%b%n",
                    divisor, quotientIsTwo, zeroOrLargeQuotient);
        }
    }
}
