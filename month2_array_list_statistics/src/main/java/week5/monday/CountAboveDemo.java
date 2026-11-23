package week5.monday;

import java.util.Arrays;

/** Đếm số điểm lớn hơn ngưỡng bằng vòng lặp for-each, không dùng streams. */
public class CountAboveDemo {
    public static void main(String[] args) {
        int[] scores = {4, 8, 8, 10};
        int[] emptyScores = {};
        int threshold = 8;

        System.out.printf("%s, threshold=%d → %d%n",
                Arrays.toString(scores), threshold, countAbove(scores, threshold));
        System.out.printf("%s, threshold=%d → %d%n",
                Arrays.toString(emptyScores), threshold, countAbove(emptyScores, threshold));
    }

    public static int countAbove(int[] scores, int threshold) {
        int count = 0;
        for (int score : scores) {
            // Điểm bằng ngưỡng không được tính.
            if (score > threshold) {
                count++;
            }
        }
        // Mảng rỗng không chạy vòng lặp nên trả về 0.
        return count;
    }
}
