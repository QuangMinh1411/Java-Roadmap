package week3;

import java.util.Locale;
import java.util.Scanner;

/**
 * M01: menu lặp bằng do...while, chỉ thoát khi chọn 0 (hoặc hết dữ liệu nhập).
 * 1: kiểm tra số nguyên tố; 2: đổi độ C sang độ F.
 *
 * Chạy thử với các token cách nhau bằng khoảng trắng hoặc xuống dòng:
 *   1 1  1 2  1 9  2 0  9  0
 * Kết quả: 1 không nguyên tố, 2 nguyên tố, 9 không nguyên tố,
 * 0°C = 32°F; lựa chọn 9 báo sai rồi hiển thị lại menu; 0 mới thoát.
 */
public class M01Menu {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in).useLocale(Locale.ROOT)) {
            int choice = -1;
            do {
                System.out.println("=== MENU M01 ===");
                System.out.println("1. Kiểm tra số nguyên tố");
                System.out.println("2. Đổi độ C sang độ F");
                System.out.println("0. Thoát");
                System.out.print("Nhập lựa chọn: ");

                if (!input.hasNext()) {
                    System.out.println("Hết dữ liệu nhập.");
                    break;
                }
                if (!input.hasNextInt()) {
                    System.out.println("Lựa chọn phải là số nguyên; bỏ qua: " + input.next());
                    continue;
                }

                choice = input.nextInt();
                switch (choice) {
                    case 1: {
                        System.out.print("Nhập số nguyên n: ");
                        if (!input.hasNext()) {
                            System.out.println("Hết dữ liệu nhập.");
                            return;
                        }
                        if (!input.hasNextInt()) {
                            System.out.println("n phải là số nguyên; bỏ qua: " + input.next());
                            break;
                        }
                        int n = input.nextInt();
                        System.out.println(n + (isPrime(n)
                                ? " là số nguyên tố." : " không phải là số nguyên tố."));
                        break;
                    }
                    case 2: {
                        System.out.print("Nhập nhiệt độ C (dùng dấu chấm cho phần thập phân): ");
                        if (!input.hasNext()) {
                            System.out.println("Hết dữ liệu nhập.");
                            return;
                        }
                        if (!input.hasNextDouble()) {
                            System.out.println("Nhiệt độ phải là số; bỏ qua: " + input.next());
                            break;
                        }
                        double celsius = input.nextDouble();
                        System.out.printf(Locale.ROOT, "%.2f°C = %.2f°F%n",
                                celsius, celsiusToFahrenheit(celsius));
                        break;
                    }
                    case 0:
                        System.out.println("Thoát chương trình.");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ. Hãy chọn 0, 1 hoặc 2.");
                        break; // Chỉ thoát switch, sau đó quay lại menu.
                }
            } while (choice != 0);
        }
    }

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false; // Số âm, 0 và 1 đều không phải số nguyên tố.
        }
        // Chỉ thử ước đến căn bậc hai của n; dùng n / divisor để tránh tràn int.
        for (int divisor = 2; divisor <= n / divisor; divisor++) {
            if (n % divisor == 0) {
                return false;
            }
        }
        return true;
    }

    public static double celsiusToFahrenheit(double celsius) {
        return celsius * 9.0 / 5.0 + 32;
    }
}
