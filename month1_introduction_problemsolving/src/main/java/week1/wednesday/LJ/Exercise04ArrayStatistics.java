package week1.wednesday.LJ;

/*
 * BÀI 4 — KHÁ: PHÂN TÍCH MẢNG SỐ NGUYÊN
 * Kiến thức: int[], chỉ số mảng, for-each, điều kiện và ép kiểu.
 *
 * ĐỀ BÀI
 * Cho một mảng int[] không null, hãy:
 * 1. Tìm giá trị nhỏ nhất và lớn nhất.
 * 2. Tính tổng bằng long và trung bình cộng bằng double.
 * 3. Đếm phần tử chẵn và lẻ.
 * 4. Tìm giá trị lớn thứ hai phân biệt, không tính các giá trị trùng thứ hạng.
 * 5. Mảng rỗng: thông báo không có dữ liệu. Không đủ hai giá trị phân biệt:
 *    thông báo không tồn tại giá trị lớn thứ hai.
 * Ràng buộc: không sắp xếp, không dùng Stream hoặc collections.
 *
 * DỮ LIỆU MẪU: {4, 7, 7, -2, 0, 5}
 * KẾT QUẢ MẪU
 * Nhỏ nhất: -2
 * Lớn nhất: 7
 * Tổng: 21
 * Trung bình: 3.5
 * Số chẵn: 3
 * Số lẻ: 3
 * Lớn thứ hai phân biệt: 5
 *
 * TỰ KIỂM TRA: {}, {-8, -3, -5}, {7, 7}, {0, Integer.MIN_VALUE}.
 * CHẠY RIÊNG: chạy main() trong IntelliJ, không cần nhập dữ liệu.
 * Đổi mảng numbers trong main() để thử dữ liệu khác.
 */
public class Exercise04ArrayStatistics {
    public static void main(String[] args) {
        int[] numbers = {4, 7, 7, -2, 0, 5};
        printStatistics(numbers);
    }

    static void printStatistics(int[] numbers) {
        if (numbers.length == 0) {
            System.out.println("Không có dữ liệu");
            return;
        }

        int minimum = numbers[0];
        int maximum = numbers[0];
        int secondMaximum = 0;
        boolean hasSecondMaximum = false;
        long sum = 0;
        int evenCount = 0;
        int oddCount = 0;

        for (int number : numbers) {
            sum += number;
            if (number < minimum) {
                minimum = number;
            }
            if (number > maximum) {
                secondMaximum = maximum;
                hasSecondMaximum = true;
                maximum = number;
            } else if (number < maximum) {
                // Dùng cờ riêng để cả Integer.MIN_VALUE cũng là kết quả hợp lệ.
                if (!hasSecondMaximum || number > secondMaximum) {
                    secondMaximum = number;
                    hasSecondMaximum = true;
                }
            }
            if (number % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }

        double average = (double) sum / numbers.length;
        System.out.println("Nhỏ nhất: " + minimum);
        System.out.println("Lớn nhất: " + maximum);
        System.out.println("Tổng: " + sum);
        System.out.println("Trung bình: " + average);
        System.out.println("Số chẵn: " + evenCount);
        System.out.println("Số lẻ: " + oddCount);
        if (hasSecondMaximum) {
            System.out.println("Lớn thứ hai phân biệt: " + secondMaximum);
        } else {
            System.out.println("Không tồn tại giá trị lớn thứ hai phân biệt");
        }
    }
}