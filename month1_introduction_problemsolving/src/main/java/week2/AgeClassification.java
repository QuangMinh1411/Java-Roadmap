package week2;

/**
 * Phân lớp tuổi nguyên: 0–5, 6–17, 18–59, 60+; từ chối tuổi âm.
 *
 * Chứng minh không có khoảng trống:
 * Sau khi loại tuổi âm, miền hợp lệ là mọi số nguyên age >= 0.
 * - Nhánh age <= 5 nhận [0, 5].
 * - Nhánh age <= 17 chỉ được xét khi age > 5, nên nhận [6, 17].
 * - Nhánh age <= 59 chỉ được xét khi age > 17, nên nhận [18, 59].
 * - Nhánh else còn lại nhận [60, +∞).
 * Các khoảng liền nhau vì 6 = 5 + 1, 18 = 17 + 1, 60 = 59 + 1.
 * Hợp của chúng là toàn bộ miền tuổi nguyên không âm; chuỗi if/else
 * bảo đảm mỗi tuổi chỉ thuộc đúng một nhánh.
 */
public class AgeClassification {
    public static String classifyAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Tuổi không được âm");
        }

        if (age <= 5) {
            return "0–5";
        } else if (age <= 17) {
            return "6–17";
        } else if (age <= 59) {
            return "18–59";
        } else {
            return "60+";
        }
    }

    public static void main(String[] args) {
        // Hai phía của bốn ngưỡng: -1/0, 5/6, 17/18, 59/60.
        int[] ages = {-1, 0, 5, 6, 17, 18, 59, 60};
        String[] expected = {"Không hợp lệ", "0–5", "0–5", "6–17",
                "6–17", "18–59", "18–59", "60+"};

        for (int i = 0; i < ages.length; i++) {
            String actual;
            try {
                actual = classifyAge(ages[i]);
            } catch (IllegalArgumentException e) {
                actual = "Không hợp lệ";
            }
            if (!expected[i].equals(actual)) {
                throw new AssertionError("Tuổi " + ages[i] + ": mong đợi "
                        + expected[i] + ", thực tế " + actual);
            }
            System.out.println(ages[i] + " -> " + actual + " (PASS)");
        }
    }
}
