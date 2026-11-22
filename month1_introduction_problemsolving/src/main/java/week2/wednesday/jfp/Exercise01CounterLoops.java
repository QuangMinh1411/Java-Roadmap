package week2.wednesday.jfp;

/*
 * BÀI 1 — CƠ BẢN: BỘ ĐẾM VÀ CỘNG DỒN
 * Tham khảo More_Control_Statement.md, mục 2–4.
 *
 * ĐỀ BÀI
 * 1. Dùng for in các số từ 1 đến 5, gồm cả hai cận.
 * 2. Dùng for bước -2 in các số lẻ từ 19 giảm về 1 và đếm số lượt.
 * 3. Dùng for bước +2 tính tổng số chẵn 2–20 và tổng số lẻ 1–99.
 *    Khởi tạo các biến tích luỹ trước vòng lặp.
 * 4. In dãy bắt đầu từ 2, tăng mỗi lần 3, không vượt quá 20.
 *    Không dùng điều kiện != để kiểm tra cận cuối.
 *
 * TỰ KIỂM TRA
 * - Dãy tăng: 1 2 3 4 5.
 * - Dãy giảm: 19 17 15 13 11 9 7 5 3 1; đúng 10 lượt.
 * - Tổng chẵn: 110; tổng lẻ: 2500.
 * - Bước 3: 2 5 8 11 14 17 20.
 */
public class Exercise01CounterLoops {
    public static void main(String[] args) {
        System.out.print("Dãy tăng: ");
        for (int i = 1; i <= 5; ++i) {
            System.out.print(i + " ");
        }

        int count = 0;
        System.out.print("\nDãy giảm: ");
        for (int i = 19; i >= 1; i -= 2) {
            System.out.print(i + " ");
            ++count;
        }
        System.out.println("\nSố lượt: " + count);

        int evenTotal = 0;
        for (int i = 2; i <= 20; i += 2) {
            evenTotal += i;
        }
        int oddTotal = 0;
        for (int i = 1; i <= 99; i += 2) {
            oddTotal += i;
        }
        System.out.println("Tổng chẵn: " + evenTotal);
        System.out.println("Tổng lẻ: " + oddTotal);

        System.out.print("Bước 3: ");
        for (int i = 2; i <= 20; i += 3) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
