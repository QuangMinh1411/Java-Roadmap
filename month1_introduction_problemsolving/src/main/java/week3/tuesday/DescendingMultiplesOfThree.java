package week3.tuesday;

/**
 * In các bội số của 3 từ 30 về 0 bằng for.
 * Tham khảo: Control Statement.md (chương 4: for)
 * và Fundamental Programming Structure.md (§3.5–3.8).
 *
 * Khởi tạo: i = 30.
 * Điều kiện tiếp tục: i >= 0; điều kiện dừng tương ứng: i < 0.
 * Cập nhật: i -= 3, tương đương i = i - 3.
 * Số phần tử, tính cả hai đầu: (30 - 0) / 3 + 1 = 11.
 *
 * for kiểm tra điều kiện trước khi chạy thân:
 *   i = 3: in 3, cập nhật i = 0, kiểm tra 0 >= 0 đúng.
 *   i = 0: in 0, cập nhật i = -3, kiểm tra -3 >= 0 sai -> dừng.
 * Vì vậy -3 chỉ là giá trị sau cập nhật, không được in trong dãy.
 * Nếu dùng i > 0, sẽ bỏ mất 0 và chỉ in 10 số.
 */
public class DescendingMultiplesOfThree {
    public static void main(String[] args) {
        int count = 0;
        boolean printedNegativeThree = false;
        StringBuilder sequence = new StringBuilder();
        int i;

        for (i = 30; i >= 0; i -= 3) {
            if (count > 0) {
                System.out.print(", ");
                sequence.append(", ");
            }
            System.out.print(i);
            sequence.append(i);
            count++;

            // Theo dõi chính các giá trị đã in trong thân vòng lặp.
            if (i == -3) {
                printedNegativeThree = true;
            }
        }
        System.out.println();

        String expected = "30, 27, 24, 21, 18, 15, 12, 9, 6, 3, 0";
        if (count != 11 || printedNegativeThree || !expected.equals(sequence.toString())) {
            throw new AssertionError("Dãy sai: " + sequence + "; số lượng: " + count);
        }
        System.out.printf("PASS: đúng %d số, không in -3 trong dãy.%n", count);
        System.out.printf("Sau cập nhật cuối: i = %d; i >= 0 là %b -> dừng.%n",
                i, i >= 0);
    }
}
