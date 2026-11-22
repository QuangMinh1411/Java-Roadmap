package week3;

import java.util.Scanner;

/**
 * Game đoán số với tối đa 7 lần đoán hợp lệ.
 * Dùng secret = 42 cố định để dễ tự kiểm tra.
 * Mọi số nguyên int đều là một lần đoán hợp lệ; token khác bị bỏ qua,
 * không mất lượt. Nhập các token cách nhau bằng khoảng trắng hoặc xuống dòng.
 *
 * Ví dụ:
 *   20 60 42           -> thắng ở lượt 3.
 *   abc 20 3.5 60 42   -> vẫn thắng ở lượt 3.
 *   1 2 3 4 5 6 7      -> thua, công bố 42, không hỏi lượt 8.
 */
public class GuessNumberGame {
    public static void main(String[] args) {
        final int secret = 42;
        final int maxAttempts = 7;
        int attempts = 0;

        try (Scanner input = new Scanner(System.in)) {
            System.out.println("=== GAME ĐOÁN SỐ ===");
            System.out.println("Bạn có tối đa " + maxAttempts + " lần đoán hợp lệ.");

            while (attempts < maxAttempts) {
                System.out.printf("Lượt %d/%d - Nhập số nguyên: ", attempts + 1, maxAttempts);
                if (!input.hasNext()) {
                    System.out.println("Hết dữ liệu nhập, dừng game.");
                    return;
                }
                if (!input.hasNextInt()) {
                    // Phải lấy token sai khỏi luồng để tránh lặp mãi cùng dữ liệu.
                    System.out.println("Input không hợp lệ; không mất lượt. Bỏ qua: " + input.next());
                    continue;
                }

                int guess = input.nextInt();
                attempts++; // Chỉ tăng sau khi đọc được một số nguyên hợp lệ.

                if (guess == secret) {
                    System.out.println("Bạn thắng ở lượt " + attempts + "!");
                    return; // Thắng thì dừng ngay, kể cả ở lượt 7.
                }
                if (guess < secret) {
                    System.out.println("Số bạn đoán nhỏ hơn đáp án.");
                } else {
                    System.out.println("Số bạn đoán lớn hơn đáp án.");
                }
            }

            // Chỉ tới đây khi đã đoán sai đủ 7 lần; không hỏi lần thứ 8.
            System.out.println("Bạn thua sau " + maxAttempts + " lượt. Đáp án là " + secret + ".");
        }
    }
}
