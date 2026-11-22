package week2.wednesday.jfp;

/** Minh hoạ kiểm tra miền [0, 10] và xếp loại bằng chuỗi if…else if. */
public class GradeClassification {
    public static String classify(double score) {
        // Kiểm tra miền trước khi xếp loại; cách viết này cũng từ chối NaN.
        if (!(score >= 0 && score <= 10)) {
            throw new IllegalArgumentException("Điểm phải thuộc [0, 10]");
        }

        if (score < 5) {
            return "chưa đạt";       // [0, 5)
        } else if (score < 6.5) {
            return "trung bình";     // [5, 6.5)
        } else if (score < 8) {
            return "khá";            // [6.5, 8)
        } else {
            return "giỏi";           // [8, 10]
        }
    }

    public static void main(String[] args) {
        double[] scores = {-0.1, 0, 4.99, 5, 6.49, 6.5, 7.99, 8, 10, 10.1};
        int rejected = 0;

        for (double score : scores) {
            try {
                System.out.println(score + ": " + classify(score));
            } catch (IllegalArgumentException e) {
                rejected++;
                System.out.println(score + ": bị từ chối — " + e.getMessage());
            }
        }

        System.out.println("Số ca bị từ chối: " + rejected + "/" + scores.length);
    }
}
