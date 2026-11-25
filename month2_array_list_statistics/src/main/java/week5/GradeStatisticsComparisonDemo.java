package week5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/** Bài ôn 2: tính cùng bộ thống kê bằng mảng và ArrayList, không sửa đầu vào. */
public class GradeStatisticsComparisonDemo {
    public record Statistics(int validCount, int rejectedCount, double min, double max,
                             double average, int[] histogram) {
        @Override
        public String toString() {
            return "valid=" + validCount + ", rejected=" + rejectedCount
                    + ", min=" + min + ", max=" + max + ", average=" + average
                    + ", histogram=" + Arrays.toString(histogram);
        }
    }

    public static void main(String[] args) {
        double[] grades = sampleGrades();
        ArrayList<Double> list = toArrayList(grades);
        System.out.println("Cùng 20 điểm: " + Arrays.toString(grades));
        System.out.println("Mảng:     " + analyze(grades));
        System.out.println("ArrayList: " + analyze(list));
    }

    public static double[] sampleGrades() {
        return new double[]{0, 1, 2, 3, 4, 4.9, 5, 5.5, 6, 6.4,
                6.5, 7, 7.5, 7.9, 8, 8.5, 9, 9.5, 10, 10};
    }

    public static ArrayList<Double> toArrayList(double[] grades) {
        ArrayList<Double> list = new ArrayList<>();
        for (double grade : grades) {
            list.add(grade); // Boxing double thành Double.
        }
        return list;
    }

    /** Bản mảng: duyệt theo index, dùng length. */
    public static Statistics analyze(double[] grades) {
        Objects.requireNonNull(grades, "Mảng điểm không được null.");
        int valid = 0;
        int rejected = 0;
        double sum = 0.0;
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        int[] histogram = new int[4];

        for (int i = 0; i < grades.length; i++) {
            double grade = grades[i];
            if (!isValid(grade)) {
                rejected++;
                continue;
            }
            histogram[GradeHistogramDemo.bucketIndex(grade)]++;
            min = Math.min(min, grade);
            max = Math.max(max, grade);
            sum += grade;
            valid++;
        }
        return finish(valid, rejected, min, max, sum, histogram);
    }

    /** Bản ArrayList: duyệt trực tiếp các Double, không chuyển sang mảng để tính. */
    public static Statistics analyze(ArrayList<Double> grades) {
        Objects.requireNonNull(grades, "Danh sách điểm không được null.");
        int valid = 0;
        int rejected = 0;
        double sum = 0.0;
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        int[] histogram = new int[4];

        for (Double value : grades) {
            if (value == null || !isValid(value)) {
                rejected++;
                continue;
            }
            double grade = value; // Unboxing sau khi đã kiểm tra null.
            histogram[GradeHistogramDemo.bucketIndex(grade)]++;
            min = Math.min(min, grade);
            max = Math.max(max, grade);
            sum += grade;
            valid++;
        }
        return finish(valid, rejected, min, max, sum, histogram);
    }

    private static boolean isValid(double grade) {
        return Double.isFinite(grade) && grade >= 0 && grade <= 10;
    }

    private static Statistics finish(int valid, int rejected, double min, double max,
                                     double sum, int[] histogram) {
        // Không có điểm hợp lệ: không có min/max/average, dùng NaN và không chia 0.
        if (valid == 0) {
            return new Statistics(0, rejected, Double.NaN, Double.NaN, Double.NaN, histogram);
        }
        return new Statistics(valid, rejected, min, max, sum / valid, histogram);
    }
}
