package week5.wednesday;

import java.util.Arrays;

/**
 * Tính tổng từng cột sau khi kiểm tra tất cả các dòng có cùng độ dài.
 * Từ chối ma trận không chữ nhật: không bỏ qua dòng rỗng, không tự điền ô thiếu.
 */
public class ColumnSumDemo {
    public static void main(String[] args) {
        printColumnSums(new int[][]{{1, 2}, {3, 4}});
        printColumnSums(new int[][]{{1, 2}, {3, 4}, {}});
        printColumnSums(new int[][]{{}, {3, 4}});
        printColumnSums(new int[][]{{}, {}});
        printColumnSums(new int[][]{});
    }

    /**
     * Không sửa mảng đầu vào. Mảng không có dòng hoặc có 0 cột cho kết quả [].
     * Dòng rỗng chỉ hợp lệ khi tất cả các dòng đều rỗng (ma trận 0 cột).
     * @throws IllegalArgumentException nếu mảng/dòng null hoặc các dòng khác độ dài
     */
    public static int[] sumColumns(int[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Ma trận không được null.");
        }
        if (matrix.length == 0) {
            return new int[0];
        }
        if (matrix[0] == null) {
            throw new IllegalArgumentException("Dòng 0 không được null.");
        }

        int columns = matrix[0].length;
        // Kiểm tra toàn bộ trước khi cộng, kể cả khi dòng đầu có 0 cột.
        for (int row = 0; row < matrix.length; row++) {
            if (matrix[row] == null) {
                throw new IllegalArgumentException("Dòng " + row + " không được null.");
            }
            if (matrix[row].length != columns) {
                throw new IllegalArgumentException("Ma trận không chữ nhật: dòng " + row
                        + " có " + matrix[row].length + " cột, cần " + columns + ".");
            }
        }

        int[] sums = new int[columns];
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < columns; column++) {
                sums[column] += matrix[row][column];
            }
        }
        return sums;
    }

    private static void printColumnSums(int[][] matrix) {
        try {
            System.out.println(Arrays.deepToString(matrix) + " → "
                    + Arrays.toString(sumColumns(matrix)));
        } catch (IllegalArgumentException e) {
            System.out.println(Arrays.deepToString(matrix) + " → Từ chối: " + e.getMessage());
        }
    }
}
