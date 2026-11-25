package week5;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Ví dụ độc lập theo Arrays_and_ArrayLists.md: mảng, enhanced for, ArrayList,
 * boxing/unboxing và mảng đếm tần suất. Không gọi các bài tập khác trong week5.
 *
 * <p>Chạy main để xem hai cách tính và 10 ca đối chiếu với kết quả kỳ vọng.
 * Điểm hợp lệ phải hữu hạn và thuộc [0,10]; điểm sai bị bỏ qua và được đếm.
 * Khi không có điểm hợp lệ, min/max/average là NaN (không có giá trị).
 */
public class StandaloneGradeStatisticsDemo {
    private static final double EPSILON = 1e-9;
    private static int passed;

    public record Statistics(int validCount, int rejectedCount,
                             double min, double max, double average, int[] histogram) {
        @Override
        public String toString() {
            return "min=" + min + ", max=" + max + ", average=" + average
                    + ", histogram=" + Arrays.toString(histogram)
                    + ", hợp lệ=" + validCount + ", từ chối=" + rejectedCount;
        }
    }

    public static void main(String[] args) {
        passed = 0;
        System.out.println("Thứ tự cột: [0,5), [5,6.5), [6.5,8), [8,10]");

        // Một bộ 20 điểm được dùng để tạo cả mảng và ArrayList.
        double[] grades = {0, 2, 4.9, 5, 5.5, 6.4, 6.5, 7, 7.5, 7.9,
                8, 8.2, 8.5, 9, 9.5, 10, 3, 4, 6, 7.6};
        ArrayList<Double> list = toList(grades);
        System.out.println("20 điểm ban đầu: " + Arrays.toString(grades));
        check("01 - Cùng 20 điểm", grades, list,
                expected(20, 0, 0, 10, 6.325, 5, 4, 5, 6));

        check("02 - Rỗng", new double[0],
                expected(0, 0, Double.NaN, Double.NaN, Double.NaN, 0, 0, 0, 0));
        check("03 - [0]", new double[]{0},
                expected(1, 0, 0, 0, 0, 1, 0, 0, 0));
        check("04 - [10]", new double[]{10},
                expected(1, 0, 10, 10, 10, 0, 0, 0, 1));

        double[] sevens = new double[20];
        Arrays.fill(sevens, 7);
        check("05 - 20 điểm bằng 7", sevens,
                expected(20, 0, 7, 7, 7, 0, 0, 20, 0));
        check("06 - Các mốc biên", new double[]{0, 4.9, 5, 6.5, 8, 10},
                expected(6, 0, 0, 10, 34.4 / 6, 2, 1, 1, 2));
        check("07 - Có -1 và 11", new double[]{-1, 0, 5, 10, 11},
                expected(3, 2, 0, 10, 5, 1, 1, 0, 1));
        check("08 - Không có điểm hợp lệ", new double[]{-1, 11, Double.NaN,
                        Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY},
                expected(0, 5, Double.NaN, Double.NaN, Double.NaN, 0, 0, 0, 0));

        // Mảng có kích thước cố định: thêm phần tử cần tạo mảng dài hơn.
        // ArrayList tự quản lý dung lượng khi add(). Tính lại trên cả hai bản.
        grades = Arrays.copyOf(grades, grades.length + 1);
        grades[grades.length - 1] = 10;
        list.add(10.0);
        check("09 - Thêm 10 rồi tính lại", grades, list,
                expected(21, 0, 0, 10, 6.5, 5, 4, 5, 7));

        // Xóa điểm 0 ở đầu: mảng cần bản sao ngắn hơn; list dùng remove(index).
        grades = Arrays.copyOfRange(grades, 1, grades.length);
        list.remove(0); // Số nguyên 0 là chỉ số; remove(0.0) mới xóa theo giá trị.
        check("10 - Xóa index 0 rồi tính lại", grades, list,
                expected(20, 0, 2, 10, 6.825, 4, 4, 5, 7));

        require(passed == 10, "Chưa chạy đủ 10 ca đối chiếu.");
        System.out.println("\nĐạt " + passed + "/10 ca đối chiếu.");
    }

    /** Bản mảng: dùng length và chỉ số; chỉ đọc dữ liệu đầu vào. */
    public static Statistics analyzeArray(double[] grades) {
        int valid = 0;
        int rejected = 0;
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        double sum = 0;
        int[] histogram = new int[4]; // Các biến đếm mặc định bằng 0.

        for (int i = 0; i < grades.length; i++) {
            double grade = grades[i];
            int bucket = bucketIndex(grade);
            if (bucket == -1) {
                rejected++;
                continue;
            }
            min = Math.min(min, grade);
            max = Math.max(max, grade);
            sum += grade;
            valid++;
            histogram[bucket]++;
        }
        return finish(valid, rejected, min, max, sum, histogram);
    }

    /** Bản ArrayList: duyệt trực tiếp danh sách, không chuyển sang mảng để tính. */
    public static Statistics analyzeList(ArrayList<Double> grades) {
        int valid = 0;
        int rejected = 0;
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        double sum = 0;
        int[] histogram = new int[4];

        for (Double value : grades) {
            int bucket = value == null ? -1 : bucketIndex(value);
            if (bucket == -1) {
                rejected++;
                continue;
            }
            double grade = value; // Unboxing Double thành double sau kiểm tra null.
            min = Math.min(min, grade);
            max = Math.max(max, grade);
            sum += grade;
            valid++;
            histogram[bucket]++;
        }
        return finish(valid, rejected, min, max, sum, histogram);
    }

    /** Chỉ số -1 biểu thị điểm bị từ chối; mỗi điểm hợp lệ thuộc đúng một cột. */
    private static int bucketIndex(double grade) {
        if (!Double.isFinite(grade) || grade < 0 || grade > 10) {
            return -1;
        }
        if (grade < 5) {
            return 0;
        }
        if (grade < 6.5) {
            return 1;
        }
        if (grade < 8) {
            return 2;
        }
        return 3; // Bao gồm cả 8 và 10.
    }

    private static Statistics finish(int valid, int rejected, double min, double max,
                                     double sum, int[] histogram) {
        if (valid == 0) {
            // Không chia cho 0 hoặc trả về min/max vô cực khi dữ liệu rỗng.
            return new Statistics(0, rejected, Double.NaN, Double.NaN, Double.NaN, histogram);
        }
        return new Statistics(valid, rejected, min, max, sum / valid, histogram);
    }

    private static ArrayList<Double> toList(double[] grades) {
        ArrayList<Double> result = new ArrayList<>();
        for (double grade : grades) {
            result.add(grade); // Autoboxing double thành Double.
        }
        return result;
    }

    private static Statistics expected(int valid, int rejected, double min, double max,
                                       double average, int... histogram) {
        return new Statistics(valid, rejected, min, max, average, histogram);
    }

    private static void check(String name, double[] grades, Statistics expected) {
        check(name, grades, toList(grades), expected);
    }

    private static void check(String name, double[] grades, ArrayList<Double> list,
                              Statistics expected) {
        // Kỳ vọng được ghi rõ cho mỗi ca, không lấy kết quả bản này làm đáp án bản kia.
        double[] arrayBefore = grades.clone();
        ArrayList<Double> listBefore = new ArrayList<>(list);
        require(grades.length == list.size(), name + ": khác kích thước đầu vào");
        for (int i = 0; i < grades.length; i++) {
            require(Double.compare(grades[i], list.get(i)) == 0,
                    name + ": khác điểm tại index " + i);
        }

        Statistics arrayResult = analyzeArray(grades);
        Statistics listResult = analyzeList(list);
        compare(name + " - mảng/kỳ vọng", arrayResult, expected);
        compare(name + " - ArrayList/kỳ vọng", listResult, expected);
        compare(name + " - mảng/ArrayList", arrayResult, listResult);
        checkTotals(name, arrayResult, grades.length);
        checkTotals(name, listResult, list.size());
        require(Arrays.equals(grades, arrayBefore) && list.equals(listBefore),
                name + ": phép tính đã thay đổi dữ liệu đầu vào");

        passed++;
        System.out.println("\nPASS " + name);
        System.out.println("  Mảng:     " + arrayResult);
        System.out.println("  ArrayList: " + listResult);
    }

    private static void compare(String name, Statistics actual, Statistics expected) {
        require(actual.validCount() == expected.validCount()
                        && actual.rejectedCount() == expected.rejectedCount()
                        && sameNumber(actual.min(), expected.min())
                        && sameNumber(actual.max(), expected.max())
                        && sameNumber(actual.average(), expected.average())
                        && Arrays.equals(actual.histogram(), expected.histogram()),
                name + ": thực tế " + actual + "; kỳ vọng " + expected);
    }

    private static boolean sameNumber(double first, double second) {
        return Double.compare(first, second) == 0 || Math.abs(first - second) <= EPSILON;
    }

    private static void checkTotals(String name, Statistics result, int inputSize) {
        int total = 0;
        for (int count : result.histogram()) {
            require(count >= 0, name + ": tần suất âm");
            total += count;
        }
        require(total == result.validCount(), name + ": tổng cột khác số điểm hợp lệ");
        require(result.validCount() + result.rejectedCount() == inputSize,
                name + ": thiếu hoặc đếm trùng điểm đầu vào");
    }

    private static void require(boolean condition, String message) {
        // Kiểm tra luôn chạy, không cần bật tùy chọn -ea như câu lệnh assert.
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
