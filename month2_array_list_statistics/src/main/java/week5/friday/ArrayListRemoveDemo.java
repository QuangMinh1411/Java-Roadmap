package week5.friday;

import java.util.ArrayList;

/**
 * Minh họa ArrayList<Integer>, boxing và remove (Arrays_and_ArrayLists.md, mục 6.20),
 * kết hợp if/else và phạm vi biến trong fundamental.md.
 *
 * Chính sách báo lỗi:
 * - Tìm không thấy: indexOf trả -1; báo "không tìm thấy", không dùng -1 làm chỉ số.
 * - Xóa theo giá trị không tồn tại: remove(Object) trả false, danh sách không đổi.
 * - Xóa theo chỉ số ngoài [0, size()): bắt IndexOutOfBoundsException, báo lỗi và
 *   tiếp tục chạy; danh sách không đổi. Danh sách rỗng không có chỉ số hợp lệ.
 *
 * Chạy từ thư mục gốc dự án:
 * java month2_array_list_statistics/src/main/java/week5/friday/ArrayListRemoveDemo.java
 */
public class ArrayListRemoveDemo {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(5); // Autoboxing: int 5 thành Integer.
        numbers.add(7);
        numbers.add(5);
        System.out.println("Ban đầu: " + numbers);

        // Đối số int chọn remove(int index), trả về phần tử vừa bị xóa.
        int removed = numbers.remove(1);
        System.out.printf("remove(1) → %s; phần tử bị xóa: %d%n", numbers, removed);

        // Đối số Integer chọn remove(Object), chỉ xóa giá trị 5 đầu tiên.
        // Nếu viết remove(5), Java sẽ hiểu là xóa tại chỉ số 5!
        boolean changed = numbers.remove(Integer.valueOf(5));
        System.out.printf("remove(Integer.valueOf(5)) → %s; thành công: %b%n",
                numbers, changed);

        System.out.println("\nTìm và xóa giá trị không tồn tại:");
        printSearchResult(numbers, 9);
        printRemoveByValue(numbers, 9);

        System.out.println("\nXóa theo chỉ số ngoài miền:");
        printRemoveByIndex(numbers, -1);
        printRemoveByIndex(numbers, numbers.size()); // size=1: chỉ số hợp lệ duy nhất là 0.

        System.out.println("\nDanh sách rỗng:");
        ArrayList<Integer> empty = new ArrayList<>();
        System.out.println("Danh sách: " + empty + "; isEmpty()=" + empty.isEmpty());
        printSearchResult(empty, 9);
        printRemoveByValue(empty, 5);
        printRemoveByIndex(empty, 0);
    }

    private static void printSearchResult(ArrayList<Integer> numbers, int value) {
        int index = numbers.indexOf(value);
        if (index == -1) {
            System.out.printf("indexOf(%d) = -1 → Không tìm thấy %d trong %s.%n",
                    value, value, numbers);
        } else {
            System.out.printf("indexOf(%d) = %d trong %s.%n", value, index, numbers);
        }
    }

    private static void printRemoveByValue(ArrayList<Integer> numbers, int value) {
        boolean changed = numbers.remove(Integer.valueOf(value));
        System.out.printf("remove(Integer.valueOf(%d)) = %b → %s%n",
                value, changed, numbers);
        if (!changed) {
            System.out.println("Không có giá trị cần xóa; danh sách không đổi.");
        }
    }

    private static void printRemoveByIndex(ArrayList<Integer> numbers, int index) {
        try {
            int removed = numbers.remove(index);
            System.out.printf("remove(%d) → %s; phần tử bị xóa: %d%n",
                    index, numbers, removed);
        } catch (IndexOutOfBoundsException e) {
            // Bắt đúng loại lỗi; thông báo do chương trình định nghĩa để dễ đọc.
            System.out.printf("Lỗi IndexOutOfBoundsException: chỉ số %d ngoài [0, %d). "
                            + "Danh sách không đổi: %s%n",
                    index, numbers.size(), numbers);
        }
    }
}
