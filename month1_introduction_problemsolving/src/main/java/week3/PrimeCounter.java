package week3;

/**
 * Đếm các số nguyên tố trong đoạn [2, 30] bằng vòng lặp thử ước.
 * Số nguyên tố phải lớn hơn 1 và có đúng hai ước dương phân biệt: 1 và chính nó.
 * Số 1 chỉ có một ước dương là 1, nên không phải số nguyên tố và không được tính.
 *
 * Nếu n là hợp số thì n = a * b với ít nhất một thừa số <= căn bậc hai của n.
 * Vì vậy chỉ cần thử các ước từ 2 đến căn bậc hai của n (kể cả điểm cuối).
 * Viết divisor <= n / divisor thay cho divisor * divisor <= n để tránh tràn int.
 *
 * Kết quả: 2, 3, 5, 7, 11, 13, 17, 19, 23, 29 -> tổng cộng 10 số.
 */
public class PrimeCounter {
    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }

        for (int divisor = 2; divisor <= n / divisor; divisor++) {
            if (n % divisor == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int count = 0;
        System.out.print("Các số nguyên tố từ 2 đến 30: ");
        for (int n = 2; n <= 30; n++) {
            if (isPrime(n)) {
                if (count > 0) {
                    System.out.print(", ");
                }
                System.out.print(n);
                count++;
            }
        }
        System.out.println();
        System.out.println("Số lượng: " + count);
        System.out.println("Không tính 1 vì 1 chỉ có một ước dương; số nguyên tố phải có đúng hai ước dương.");
    }
}
