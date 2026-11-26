package week5.thursday;

import java.util.Arrays;

/**
 * Minh họa mảng hai chiều (Arrays_and_ArrayLists.md, mục 6.16),
 * vòng lặp for, if và phạm vi biến (fundamental.md, mục 3.8).
 * Chỉ số dòng và cột đều bắt đầu từ 0.
 *
 * Chạy từ thư mục gốc dự án:
 * java month2_array_list_statistics/src/main/java/week5/thursday/RowSumAndMaxDemo.java
 */
public class RowSumAndMaxDemo {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] negativeMatrix = {
                {-1, -2, -3},
                {-4, -5, -6},
                {-7, -8, -9}
        };

        System.out.println("Ma trận 1..9:");
        printRowSumsAndMax(matrix); // Tổng: 6, 15, 24; max=9 tại (2,2).

        System.out.println("\nMa trận toàn âm:");
        printRowSumsAndMax(negativeMatrix); // Tổng: -6, -15, -24; max=-1 tại (0,0).
    }

    /**
     * Không sửa dữ liệu đầu vào; chấp nhận các dòng khác độ dài.
     * Nếu max xuất hiện nhiều lần, giữ vị trí đầu tiên khi duyệt theo dòng.
     * @throws IllegalArgumentException nếu mảng null, rỗng hoặc có dòng null/rỗng
     */
    public static void printRowSumsAndMax(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException("Ma trận phải có ít nhất một dòng.");
        }
        for (int row = 0; row < matrix.length; row++) {
            if (matrix[row] == null || matrix[row].length == 0) {
                throw new IllegalArgumentException("Dòng " + row + " phải có phần tử.");
            }
        }

        // Lấy phần tử có thật: max=0 sẽ sai khi tất cả phần tử đều âm.
        int max = matrix[0][0];
        int maxRow = 0;
        int maxColumn = 0;

        for (int row = 0; row < matrix.length; row++) {
            // Biến nằm trong vòng lặp ngoài nên được đặt lại cho mỗi dòng.
            long rowSum = 0; // Dùng long để tránh tràn int khi cộng nhiều số int.
            for (int column = 0; column < matrix[row].length; column++) {
                int value = matrix[row][column];
                rowSum += value;

                if (value > max) {
                    max = value;
                    maxRow = row;
                    maxColumn = column;
                }
            }
            System.out.printf("Tổng dòng %d %s = %d%n",
                    row, Arrays.toString(matrix[row]), rowSum);
        }

        System.out.printf("max=%d tại (%d,%d)%n", max, maxRow, maxColumn);
    }
}
