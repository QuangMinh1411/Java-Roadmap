package week5;

import java.util.Arrays;

/**
 * Minh họa Bài tập 5 trong Arrays_and_ArrayLists.md: gộp hai mảng đã tăng
 * bằng hai con trỏ, không dùng sort và không sửa mảng nguồn.
 *
 * Chạy từ thư mục gốc dự án:
 * java month2_array_list_statistics/src/main/java/week5/MergeSortedArraysDemo.java
 */
public class MergeSortedArraysDemo {
    public static void main(String[] args) {
        runExample(new int[]{1, 3, 5}, new int[]{2, 3}, new int[]{1, 2, 3, 3, 5});
        runExample(new int[]{}, new int[]{2, 3}, new int[]{2, 3});
        runExample(new int[]{1, 3, 5}, new int[]{}, new int[]{1, 3, 5});
        runExample(new int[]{}, new int[]{}, new int[]{});
    }

    /**
     * Đầu vào phải được sắp xếp không giảm (cho phép phần tử bằng nhau).
     * Trả về mảng mới, giữ mọi phần tử trùng lặp; khi bằng nhau lấy từ a trước.
     * Thời gian O(n + m), bộ nhớ kết quả O(n + m), bộ nhớ phụ ngoài kết quả O(1).
     * @throws IllegalArgumentException nếu một trong hai mảng là null
     */
    public static int[] mergeWithTrace(int[] a, int[] b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Hai mảng đầu vào không được null.");
        }

        int[] merged = new int[a.length + b.length];
        int i = 0; // Con trỏ đọc mảng a.
        int j = 0; // Con trỏ đọc mảng b.
        int k = 0; // Vị trí ghi tiếp theo trong merged; luôn có k = i + j.

        while (i < a.length && j < b.length) {
            System.out.printf("i=%d, j=%d, k=%d: so sánh a[%d]=%d với b[%d]=%d%n",
                    i, j, k, i, a[i], j, b[j]);
            if (a[i] <= b[j]) {
                System.out.printf("  → merged[%d] = a[%d] = %d; tăng i và k%n", k, i, a[i]);
                merged[k] = a[i];
                i++;
            } else {
                System.out.printf("  → merged[%d] = b[%d] = %d; tăng j và k%n", k, j, b[j]);
                merged[k] = b[j];
                j++;
            }
            k++;
        }

        // Một mảng đã hết: chép phần còn lại của mảng kia, không cần so sánh.
        // Các vòng lặp này cũng xử lý được khi một mảng rỗng ngay từ đầu.
        while (i < a.length) {
            System.out.printf("i=%d, j=%d, k=%d: b đã hết → merged[%d] = a[%d] = %d%n",
                    i, j, k, k, i, a[i]);
            merged[k] = a[i];
            i++;
            k++;
        }
        while (j < b.length) {
            System.out.printf("i=%d, j=%d, k=%d: a đã hết → merged[%d] = b[%d] = %d%n",
                    i, j, k, k, j, b[j]);
            merged[k] = b[j];
            j++;
            k++;
        }

        System.out.printf("Kết thúc: i=%d, j=%d, k=%d%n", i, j, k);
        return merged;
    }

    private static void runExample(int[] a, int[] b, int[] expected) {
        System.out.printf("%nGộp %s và %s:%n", Arrays.toString(a), Arrays.toString(b));
        int[] result = mergeWithTrace(a, b);
        System.out.println("Kết quả: " + Arrays.toString(result));
        if (!Arrays.equals(result, expected)) {
            throw new AssertionError("Sai kết quả; cần " + Arrays.toString(expected));
        }
        System.out.println("Đạt kết quả kỳ vọng.");
    }
}
