package week5.tests;

import week5.GradeStatisticsComparisonDemo;
import week5.GradeStatisticsComparisonDemo.Statistics;

import java.util.ArrayList;
import java.util.Arrays;

/** Mười ca đối chiếu có kết quả kỳ vọng độc lập; chạy không cần thư viện test. */
public class GradeStatisticsComparisonTest {
    private static int passed;

    public static void main(String[] args) {
        double[] sample = GradeStatisticsComparisonDemo.sampleGrades();
        ArrayList<Double> list = GradeStatisticsComparisonDemo.toArrayList(sample);
        check("01 - Cùng 20 điểm", sample, list,
                expected(20, 0, 0, 10, 6.085, 6, 4, 4, 6));
        check("02 - Rỗng", new double[]{},
                expected(0, 0, Double.NaN, Double.NaN, Double.NaN, 0, 0, 0, 0));
        check("03 - [0]", new double[]{0}, expected(1, 0, 0, 0, 0, 1, 0, 0, 0));
        check("04 - [10]", new double[]{10}, expected(1, 0, 10, 10, 10, 0, 0, 0, 1));

        double[] sevens = new double[20];
        Arrays.fill(sevens, 7);
        check("05 - 20 điểm bằng 7", sevens, expected(20, 0, 7, 7, 7, 0, 0, 20, 0));
        check("06 - Các mốc histogram", new double[]{0, 4.9, 5, 6.5, 8, 10},
                expected(6, 0, 0, 10, 34.4 / 6, 2, 1, 1, 2));
        check("07 - Trộn điểm sai", new double[]{-1, 0, 5, 10, 11},
                expected(3, 2, 0, 10, 5, 1, 1, 0, 1));
        check("08 - Toàn điểm sai", new double[]{-1, 11, Double.NaN,
                        Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY},
                expected(0, 5, Double.NaN, Double.NaN, Double.NaN, 0, 0, 0, 0));

        // Tiếp tục từ đúng hai bộ dữ liệu ở ca 01: thêm 10 rồi tính lại.
        sample = Arrays.copyOf(sample, sample.length + 1);
        sample[sample.length - 1] = 10;
        list.add(10.0);
        check("09 - Thêm 10, tính lại", sample, list,
                expected(21, 0, 0, 10, 131.7 / 21, 6, 4, 4, 7));

        // Xóa phần tử ở index 0 (điểm 0) khỏi cả hai bản, rồi tính lại.
        sample = Arrays.copyOfRange(sample, 1, sample.length);
        list.remove(0); // remove(int index), không phải xóa giá trị 0.0.
        check("10 - Xóa index 0, tính lại", sample, list,
                expected(20, 0, 1, 10, 6.585, 5, 4, 4, 7));

        if (passed != 10) {
            throw new AssertionError("Phải chạy đủ 10 ca, thực tế: " + passed);
        }
        System.out.println("Đạt " + passed + "/10 ca đối chiếu.");
    }

    private static Statistics expected(int valid, int rejected, double min, double max,
                                       double average, int... histogram) {
        return new Statistics(valid, rejected, min, max, average, histogram);
    }

    private static void check(String name, double[] grades, Statistics expected) {
        check(name, grades, GradeStatisticsComparisonDemo.toArrayList(grades), expected);
    }

    private static void check(String name, double[] grades, ArrayList<Double> list,
                              Statistics expected) {
        double[] beforeArray = grades.clone();
        ArrayList<Double> beforeList = new ArrayList<>(list);
        if (grades.length != list.size()) {
            throw new AssertionError(name + ": khác số phần tử đầu vào");
        }
        for (int i = 0; i < grades.length; i++) {
            assertNumber(name + " input[" + i + "]", grades[i], list.get(i));
        }

        Statistics arrayResult = GradeStatisticsComparisonDemo.analyze(grades);
        Statistics listResult = GradeStatisticsComparisonDemo.analyze(list);
        assertStatistics(name + " mảng/kỳ vọng", arrayResult, expected);
        assertStatistics(name + " list/kỳ vọng", listResult, expected);
        assertStatistics(name + " mảng/list", arrayResult, listResult);
        if (!Arrays.equals(grades, beforeArray) || !list.equals(beforeList)) {
            throw new AssertionError(name + ": đầu vào bị sửa khi tính");
        }
        assertTotal(name, arrayResult, grades.length);
        assertTotal(name, listResult, list.size());
        passed++;
        System.out.println("PASS " + name + ": " + arrayResult);
    }

    private static void assertStatistics(String name, Statistics actual, Statistics expected) {
        if (actual.validCount() != expected.validCount()
                || actual.rejectedCount() != expected.rejectedCount()
                || !Arrays.equals(actual.histogram(), expected.histogram())) {
            throw new AssertionError(name + ": " + actual + " != " + expected);
        }
        assertNumber(name + " min", actual.min(), expected.min());
        assertNumber(name + " max", actual.max(), expected.max());
        assertNumber(name + " average", actual.average(), expected.average());
    }

    private static void assertNumber(String name, double actual, double expected) {
        if (Double.compare(actual, expected) == 0) {
            return; // Bao gồm trường hợp cả hai đều là NaN.
        }
        if (!Double.isFinite(actual) || !Double.isFinite(expected)
                || Math.abs(actual - expected) > 1e-9) {
            throw new AssertionError(name + ": " + actual + " != " + expected);
        }
    }

    private static void assertTotal(String name, Statistics result, int inputSize) {
        int total = 0;
        for (int count : result.histogram()) {
            if (count < 0) {
                throw new AssertionError(name + ": cột âm");
            }
            total += count;
        }
        if (total != result.validCount()
                || result.validCount() + result.rejectedCount() != inputSize) {
            throw new AssertionError(name + ": tổng số lượng không khớp");
        }
    }
}
