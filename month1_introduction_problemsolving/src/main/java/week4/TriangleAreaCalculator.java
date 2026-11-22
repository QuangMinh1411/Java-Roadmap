package week4;

/**
 * Bài 4 — Kiểm tra tam giác và tính diện tích theo công thức Heron.
 * Kiến thức: phương thức boolean, gọi phương thức khác, biến cục bộ và Math.sqrt.
 * Ví dụ dùng các độ dài thông thường; số học double có giới hạn độ chính xác.
 */
public class TriangleAreaCalculator {
    public static boolean isValidTriangle(double a, double b, double c) {
        // Các cạnh phải hữu hạn, dương và thỏa cả ba bất đẳng thức nghiêm ngặt.
        return Double.isFinite(a) && Double.isFinite(b) && Double.isFinite(c)
                && a > 0 && b > 0 && c > 0
                && a + b > c && a + c > b && b + c > a;
    }

    public static double calculateArea(double a, double b, double c) {
        if (!isValidTriangle(a, b, c)) {
            return -1.0;
        }

        // p và area chỉ tồn tại trong phạm vi phương thức này.
        double p = (a + b + c) / 2.0;
        double area = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        return area;
    }

    public static void main(String[] args) {
        printTriangle(3, 4, 5);   // Hợp lệ, diện tích = 6.
        printTriangle(5, 5, 6);   // Hợp lệ, diện tích = 12.
        printTriangle(1, 2, 3);   // Suy biến: 1 + 2 = 3, không hợp lệ.
        printTriangle(-3, 4, 5); // Cạnh âm, không hợp lệ.
        printTriangle(0, 4, 5);  // Cạnh bằng 0, không hợp lệ.
    }

    private static void printTriangle(double a, double b, double c) {
        double area = calculateArea(a, b, c);
        System.out.printf("Các cạnh (%.1f, %.1f, %.1f): ", a, b, c);
        if (area == -1.0) {
            System.out.println("không hợp lệ, calculateArea trả về -1.0");
        } else {
            System.out.printf("diện tích = %.2f%n", area);
        }
    }
}
