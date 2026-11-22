package week2.tuesday.LJ;

import java.util.Arrays;

/*
 * BÀI 4 — KHÁ: THAM CHIẾU, SAO CHÉP VÀ MỞ RỘNG MẢNG
 * Theo Java Language.md, mục 3.1, 3.4 và 7.1–7.4.
 *
 * YÊU CẦU — thực hiện đúng thứ tự sau
 * 1. Tạo final int[] source = {8, 6, 10, 7, 9}.
 *    Tạo alias bằng phép gán source và copy bằng Arrays.copyOf.
 * 2. Gán alias[0] = 100, sau đó gán copy[1] = 60.
 *    In source, alias, copy bằng Arrays.toString; giải thích trong comment
 *    vì sao source đổi qua alias nhưng không đổi qua copy.
 * 3. So sánh source == alias và source == copy, in hai kết quả boolean.
 *    Giải thích vì sao final vẫn cho phép sửa source[0].
 *    Nếu minh họa phép gán lại source bị cấm, chỉ viết trong comment.
 * 4. Tạo expanded bằng Arrays.copyOf(source, source.length + 2).
 *    In expanded trước khi sửa để thấy giá trị mặc định của hai ô mới,
 *    sau đó gán hai ô cuối lần lượt bằng 11 và 12 rồi in lại.
 * 5. Tạo slice bằng Arrays.copyOfRange(source, 1, 4); in slice.
 *    Giải thích chỉ số đầu được lấy, chỉ số cuối bị loại trừ.
 * 6. Duyệt expanded bằng enhanced for để tính tổng kiểu long và trung bình
 *    kiểu double. Không dùng Stream; không in sẵn các kết quả thống kê.
 *
 * TỰ KIỂM TRA
 * - source và alias: [100, 6, 10, 7, 9]; copy: [8, 60, 10, 7, 9].
 * - source == alias là true; source == copy là false.
 * - expanded trước khi sửa: [100, 6, 10, 7, 9, 0, 0].
 * - expanded sau khi sửa: [100, 6, 10, 7, 9, 11, 12].
 * - slice: [6, 10, 7]; tổng expanded = 155; trung bình = 155.0 / 7.
 * - Sau bước 4–6, source vẫn là [100, 6, 10, 7, 9].
 */
public class Exercise04ArrayCopyLab {
    public static void main(String[] args) {
        final int[] source = {8, 6, 10, 7, 9};
        int[] alias = source;
        int[] copy = Arrays.copyOf(source, source.length);

        // alias cùng trỏ đến mảng gốc; copy trỏ đến một mảng mới độc lập.
        alias[0] = 100;
        copy[1] = 60;
        System.out.println("source: " + Arrays.toString(source));
        System.out.println("alias: " + Arrays.toString(alias));
        System.out.println("copy: " + Arrays.toString(copy));
        System.out.println("source == alias: " + (source == alias));
        System.out.println("source == copy: " + (source == copy));
        // final ngăn gán lại biến source, không ngăn sửa phần tử mảng.
        // source = new int[5]; // Không hợp lệ vì source là final.

        int[] expanded = Arrays.copyOf(source, source.length + 2);
        System.out.println("expanded truoc khi sua: " + Arrays.toString(expanded));
        expanded[expanded.length - 2] = 11;
        expanded[expanded.length - 1] = 12;
        System.out.println("expanded sau khi sua: " + Arrays.toString(expanded));

        // Lấy các phần tử chỉ số 1, 2, 3; không lấy phần tử chỉ số 4.
        int[] slice = Arrays.copyOfRange(source, 1, 4);
        System.out.println("slice: " + Arrays.toString(slice));

        long sum = 0;
        for (int value : expanded) {
            sum += value;
        }
        // expanded chắc chắn có 7 phần tử nên không chia cho 0.
        double average = (double) sum / expanded.length;
        System.out.println("sum = " + sum);
        System.out.println("average = " + average);
        System.out.println("source cuoi cung: " + Arrays.toString(source));
    }
}
