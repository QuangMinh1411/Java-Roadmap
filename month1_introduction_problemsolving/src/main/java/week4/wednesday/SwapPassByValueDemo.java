package week4.wednesday;

/**
 * Minh họa bản sao đối số (§5.1–5.3) và khung lời gọi (§5.10) trong Methods.md.
 * Java truyền đối số theo giá trị (pass-by-value).
 * Với int, tham số nhận bản sao giá trị số, không liên kết với biến ở nơi gọi.
 *
 * Sơ đồ khi main gọi swap(x, y):
 *
 *   Khung main                  Khung swap
 *   +---------+   sao chép      +---------+
 *   | x = 3   | -------------> | a = 3   |
 *   | y = 7   | -------------> | b = 7   |
 *   +---------+                +---------+
 *
 * Sau ba lệnh đổi chỗ trong swap:
 *   +---------+                +---------+
 *   | x = 3   |                | a = 7   |
 *   | y = 7   |                | b = 3   |
 *   +---------+                | temp = 3|
 *                              +---------+
 *
 * Khi swap trả về: khung swap kết thúc; không có bước tự gán ngược vào x, y.
 * Khung main tiếp tục với x = 3, y = 7.
 */
public class SwapPassByValueDemo {
    /**
     * Đổi chỗ hai bản sao a, b rồi in để quan sát; nhận mọi giá trị int.
     * Không đổi được các biến int ở nơi gọi và không trả về kết quả.
     */
    public static void swap(int a, int b) {
        System.out.printf("Trong swap, trước đổi: a=%d, b=%d%n", a, b);
        int temp = a;
        a = b;
        b = temp;
        System.out.printf("Trong swap, sau đổi:   a=%d, b=%d%n", a, b);
    }

    /**
     * Trả kết quả đổi chỗ trong một mảng mới có đúng hai phần tử.
     * Phần tử [0] bằng b ban đầu, [1] bằng a ban đầu.
     * Hàm không nhập/xuất; nơi gọi phải nhận kết quả và tự gán lại các biến.
     *
     * @param a giá trị thứ nhất
     * @param b giá trị thứ hai
     * @return mảng mới {b, a}
     */
    public static int[] swapped(int a, int b) {
        return new int[] {b, a};
    }

    public static void main(String[] args) {
        int x = 3;
        int y = 7;

        System.out.printf("Trước swap: x=%d, y=%d%n", x, y);
        swap(x, y);
        System.out.printf("Sau swap:   x=%d, y=%d%n", x, y);
        System.out.println("a, b là các bản sao; đổi chúng không đổi x, y.");

        System.out.println();
        System.out.println("Nhận kết quả để đổi ở nơi gọi:");
        int[] result = swapped(x, y); // result chứa {7, 3}; x, y vẫn là 3, 7.
        System.out.printf("Sau swapped, trước gán lại: x=%d, y=%d; result={%d, %d}%n",
                x, y, result[0], result[1]);

        // Chính các phép gán trong main dưới đây mới thay đổi x, y.
        x = result[0];
        y = result[1];
        System.out.printf("Sau gán lại ở main: x=%d, y=%d%n", x, y);
        // Kết quả: x=7, y=3. Java vẫn truyền đối số theo giá trị.
    }
}
