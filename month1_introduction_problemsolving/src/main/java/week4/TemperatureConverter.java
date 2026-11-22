package week4;

/**
 * Bài 1 — Chuyển đổi nhiệt độ (Methods_ch5.md, mục 9).
 * Kiến thức: phương thức static, tham số, return và nạp chồng phương thức.
 * Chạy main() để xem các lời gọi với đối số int và double.
 */
public class TemperatureConverter {
    public static double toCelsius(double fahrenheit) {
        // Dùng 5.0 / 9.0 để tránh phép chia nguyên 5 / 9 cho kết quả 0.
        return (fahrenheit - 32) * (5.0 / 9.0);
    }

    public static double toFahrenheit(double celsius) {
        return celsius * (9.0 / 5.0) + 32;
    }

    public static double toCelsius(int fahrenheit) {
        // Ép sang double để gọi phiên bản phía trên, tránh lặp lại công thức.
        return toCelsius((double) fahrenheit);
    }

    public static void main(String[] args) {
        System.out.printf("toCelsius(32)     = %.2f °C (đối số int)%n", toCelsius(32));
        System.out.printf("toCelsius(98.6)   = %.2f °C (đối số double)%n", toCelsius(98.6));
        System.out.printf("toCelsius(212)    = %.2f °C%n", toCelsius(212));
        // 100 là int, được tự động thăng cấp thành double khi truyền vào.
        System.out.printf("toFahrenheit(100) = %.2f °F%n", toFahrenheit(100));
        System.out.printf("toFahrenheit(-40) = %.2f °F%n", toFahrenheit(-40));
        // Kết quả lần lượt: 0.00, 37.00, 100.00, 212.00, -40.00.
    }
}
