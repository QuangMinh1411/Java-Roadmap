package week4.thursday;

import java.util.Arrays;
import java.util.Random;

/**
 * Minh họa mô phỏng xúc xắc (§5.4) và enum (§5.5) trong Methods.md.
 * Dùng java.util.Random với seed cố định để có thể tái hiện kết quả.
 * nextInt(6) sinh int trong [0, 5]; cộng 1 để được mặt xúc xắc trong [1, 6].
 *
 * Cùng seed và cùng thứ tự gọi phương thức sinh số sẽ cho cùng dãy.
 * Tạo Random một lần rồi dùng tiếp; không tạo lại cùng seed ở mỗi lượt,
 * vì như vậy sẽ luôn lấy lại phần tử đầu tiên của dãy.
 * Tần suất mỗi mặt có thể khác nhau; không yêu cầu mỗi mặt xuất hiện bằng nhau.
 */
public class DiceRandomDemo {
    private static final long SEED = 20261117L;
    private static final int ROLLS = 1000;

    /** Biểu diễn bổ sung bằng tên; kết quả sinh ban đầu vẫn là int. */
    public enum Face {
        ONE, TWO, THREE, FOUR, FIVE, SIX;

        /** Đổi số mặt 1–6 sang enum, không sinh thêm số ngẫu nhiên. */
        public static Face fromValue(int value) {
            return switch (value) {
                case 1 -> ONE;
                case 2 -> TWO;
                case 3 -> THREE;
                case 4 -> FOUR;
                case 5 -> FIVE;
                case 6 -> SIX;
                default -> throw new IllegalArgumentException("Mặt xúc xắc phải trong [1,6]: " + value);
            };
        }
    }

    /**
     * Nhận bộ sinh khác null; trả một int trong [1, 6].
     * Hàm cập nhật trạng thái bộ sinh nhưng không nhập/xuất.
     */
    public static int rollDie(Random random) {
        return 1 + random.nextInt(6);
    }

    public static void main(String[] args) {
        Random random = new Random(SEED);
        Random replay = new Random(SEED); // Bộ sinh độc lập để kiểm tra tái hiện.
        int[] firstSequence = new int[ROLLS];
        int[] replaySequence = new int[ROLLS];
        int[] frequencies = new int[7]; // Dùng chỉ số 1–6; bỏ chỉ số 0.
        int outsideRange = 0;

        for (int i = 0; i < ROLLS; i++) {
            int face = rollDie(random);
            firstSequence[i] = face;
            replaySequence[i] = rollDie(replay);

            // Kiểm tra trước khi dùng face làm chỉ số để vẫn đếm được giá trị lỗi.
            if (face < 1 || face > 6) {
                outsideRange++;
            } else {
                frequencies[face]++;
            }
        }

        // So sánh toàn bộ 1000 phần tử, không chỉ đoạn mẫu được in ra.
        boolean sameSequence = Arrays.equals(firstSequence, replaySequence);
        if (outsideRange != 0) {
            throw new AssertionError("Có " + outsideRange + " kết quả ngoài [1,6]");
        }
        if (!sameSequence) {
            throw new AssertionError("Hai bộ sinh cùng seed và cùng cách gọi phải cho cùng dãy");
        }

        System.out.println("Seed: " + SEED);
        System.out.println("Số lượt mỗi dãy: " + ROLLS);
        System.out.println("10 lượt đầu, dãy 1: " + Arrays.toString(Arrays.copyOf(firstSequence, 10)));
        System.out.println("10 lượt đầu, dãy 2: " + Arrays.toString(Arrays.copyOf(replaySequence, 10)));
        System.out.println("Số kết quả ngoài [1,6]: " + outsideRange + " (PASS)");
        System.out.println("Cùng seed cho cùng toàn bộ dãy: " + sameSequence + " (PASS)");

        System.out.println("Tần suất quan sát (không yêu cầu bằng nhau):");
        for (int face = 1; face <= 6; face++) {
            System.out.printf("Mặt %d (%s): %d%n", face, Face.fromValue(face), frequencies[face]);
        }
        int firstFace = firstSequence[0];
        System.out.println("Đổi kết quả đầu sang enum: " + firstFace + " -> " + Face.fromValue(firstFace));
    }
}
