package week4.wednesday;

/**
 * Minh họa bản sao đối số, return và khung lời gọi dựa trên Methods.md.
 * Java truyền đối số theo giá trị: tham số int n nhận bản sao giá trị của x.
 * Trả về một giá trị không tự động gán giá trị đó vào biến ở nơi gọi.
 *
 * Sơ đồ 1 — increment(x); với x = 5:
 *
 *   Khung main                    Khung increment
 *   +---------+    sao chép        +---------+
 *   | x = 5   | ----------------> | n = 5   |
 *   +---------+                   +---------+
 *                                 | n + 1 = 6
 *                                 v
 *                          return 6 -> bị bỏ qua
 *   Sau lời gọi: x vẫn bằng 5; khung increment kết thúc.
 *
 * Sơ đồ 2 — x = increment(x); với x vẫn bằng 5:
 *
 *   Khung main                    Khung increment (lần gọi mới)
 *   +---------+    sao chép        +---------+
 *   | x = 5   | ----------------> | n = 5   |
 *   +---------+                   +---------+
 *        ^                        | n + 1 = 6
 *        |                        v
 *        +---- gán tại main <--- return 6
 *   Sau phép gán: x bằng 6; khung increment kết thúc.
 *
 * Trong cả hai lần gọi, n vẫn bằng 5: biểu thức n + 1 không thay đổi n.
 * Chính phép gán tại main trong trường hợp 2 mới thay đổi x.
 */
public class IncrementPassByValueDemo {
    /**
     * Trả n + 1 theo phép cộng int của Java, không nhập/xuất hoặc sửa biến nơi gọi.
     * Nếu n = Integer.MAX_VALUE, kết quả tràn int thành Integer.MIN_VALUE.
     */
    public static int increment(int n) {
        return n + 1;
    }

    public static void main(String[] args) {
        int x = 5;
        System.out.println("Ban đầu: x=" + x);

        increment(x); // Trả 6 nhưng bỏ qua kết quả; x vẫn bằng 5.
        System.out.println("Sau increment(x): x=" + x);

        x = increment(x); // Sao chép 5 vào n, trả 6, rồi gán 6 cho x.
        System.out.println("Sau x = increment(x): x=" + x);
    }
}
