package week5.monday;

import java.util.Arrays;

/** Tìm điểm nhỏ nhất và lớn nhất; kiểm tra mảng rỗng trước khi đọc grades[0]. */
public class GradeMinMax {
    public static void main(String[] args) {
        minMax(new int[]{8, 6, 10, 6});
        minMax(new int[]{7});
        minMax(new int[]{});
    }

    public static void minMax(int[] grades) {
        if (grades.length == 0) {
            System.out.println(Arrays.toString(grades) + " → Không có điểm.");
            return;
        }

        // Lấy giá trị có thật trong mảng: min = 0 sẽ sai khi mọi điểm đều dương.
        int min = grades[0];
        int max = grades[0];

        // Phần tử đầu đã được dùng để khởi tạo, nên duyệt từ phần tử thứ hai.
        for (int i = 1; i < grades.length; i++) {
            if (grades[i] < min) {
                min = grades[i];
            }
            if (grades[i] > max) {
                max = grades[i];
            }
        }

        System.out.printf("%s → min=%d, max=%d%n", Arrays.toString(grades), min, max);
    }
}
