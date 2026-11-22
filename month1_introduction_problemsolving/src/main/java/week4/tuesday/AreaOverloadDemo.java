package week4.tuesday;

/**
 * Minh họa nạp chồng phương thức (§5.7) và mở rộng kiểu đối số (§5.11)
 * dựa trên Methods.md.
 *
 * Hai chữ ký area(double) và area(double, double) khác nhau về số lượng tham số.
 * Trình biên dịch chọn overload theo danh sách đối số: số lượng, kiểu và thứ tự kiểu.
 * Tên tham số và kiểu trả về không dùng để phân biệt hai chữ ký.
 * Các hàm area chỉ tính toán; main phụ trách hiển thị kết quả.
 */
public class AreaOverloadDemo {
    /**
     * Tính diện tích hình tròn theo công thức PI * radius * radius.
     * Tiền điều kiện: radius hữu hạn và không âm.
     *
     * @param radius bán kính hình tròn
     * @return diện tích hình tròn, có thể làm tròn theo số học double
     */
    public static double area(double radius) {
        return Math.PI * radius * radius;
    }

    /**
     * Tính diện tích hình chữ nhật theo công thức width * height.
     * Tiền điều kiện: width và height hữu hạn, không âm và cùng đơn vị đo.
     *
     * @param width chiều rộng
     * @param height chiều cao
     * @return diện tích hình chữ nhật, theo bình phương đơn vị đo hai cạnh
     */
    public static double area(double width, double height) {
        return width * height;
    }

    public static void main(String[] args) {
        // area(2): một đối số int, mở rộng 2 thành double 2.0.
        // Chọn area(double); bản hai tham số không phù hợp số lượng đối số.
        double circleArea = area(2);

        // area(3, 4): hai đối số int, mở rộng thành double 3.0 và 4.0.
        // Chọn area(double, double); bản một tham số không phù hợp số lượng đối số.
        double rectangleArea = area(3, 4);

        System.out.println("area(2) -> area(double): PI * 2.0 * 2.0 = " + circleArea);
        // Kết quả xấp xỉ 12.566370614359172.
        System.out.println("area(3, 4) -> area(double, double): 3.0 * 4.0 = " + rectangleArea);
        // Kết quả 12.0.

        // area();        // Không có overload nhận 0 đối số.
        // area(2, 3, 4); // Không có overload nhận 3 đối số.
        // area("2");    // String không tự chuyển thành double.
        System.out.println("Overload được chọn khi biên dịch theo danh sách tham số,"
                + " không theo tên tham số hoặc kiểu biến nhận kết quả.");
    }
}
