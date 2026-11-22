package project;

import java.util.Locale;
import java.util.Scanner;

/** Tầng menu: sở hữu Scanner, nhập liệu và in kết quả. */
public class StudyToolsCli {
    public static void main(String[] args) {
        try (var input = new Scanner(System.in).useLocale(Locale.ROOT)) {
            boolean running = true;
            do {
                System.out.println("""
                        === STUDYTOOLS CLI ===
                        1. Bốn phép toán (+, -, *, /)
                        2. Đổi độ C sang độ F
                        3. Kiểm tra số nguyên tố
                        4. Thống kê điểm (0..10, nhập -1 để kết thúc)
                        0. Thoát""");
                Integer choice = readInt(input, "Nhập lựa chọn: ");
                if (choice == null) {
                    break;
                }
                switch (choice) {
                    case 1 -> running = runCalculator(input);
                    case 2 -> running = runTemperature(input);
                    case 3 -> running = runPrime(input);
                    case 4 -> running = runGrades(input);
                    case 0 -> {
                        System.out.println("Thoát chương trình.");
                        running = false;
                    }
                    default -> System.out.println("Lựa chọn không hợp lệ. Hãy chọn 0..4.");
                }
            } while (running);
        }
    }

    private static Integer readInt(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!input.hasNext()) {
                System.out.println("Hết dữ liệu nhập.");
                return null;
            }
            if (input.hasNextInt()) {
                return input.nextInt();
            }
            // Tiêu thụ token sai để vòng lặp không mắc kẹt ở cùng một token.
            System.out.println("Phải nhập số nguyên; bỏ qua: " + input.next());
        }
    }

    private static Double readDouble(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!input.hasNext()) {
                System.out.println("Hết dữ liệu nhập.");
                return null;
            }
            if (!input.hasNextDouble()) {
                System.out.println("Phải nhập số; bỏ qua: " + input.next());
                continue;
            }
            double value = input.nextDouble();
            if (Double.isFinite(value)) {
                return value;
            }
            System.out.println("Phải nhập số hữu hạn.");
        }
    }

    private static boolean runPrime(Scanner input) {
        Integer n = readInt(input, "Nhập số nguyên n: ");
        if (n == null) {
            return false;
        }
        System.out.println(n + (StudyTools.isPrime(n)
                ? " là số nguyên tố." : " không phải là số nguyên tố."));
        return true;
    }

    private static boolean runTemperature(Scanner input) {
        Double celsius = readDouble(input, "Nhập nhiệt độ C: ");
        if (celsius == null) {
            return false;
        }
        double fahrenheit = StudyTools.celsiusToFahrenheit(celsius);
        if (!Double.isFinite(fahrenheit)) {
            System.out.println("Kết quả vượt phạm vi double.");
        } else {
            System.out.printf(Locale.ROOT, "%.2f°C = %.2f°F%n", celsius, fahrenheit);
        }
        return true;
    }

    private static boolean runCalculator(Scanner input) {
        Double a = readDouble(input, "Nhập số thứ nhất: ");
        if (a == null) {
            return false;
        }
        String operator;
        while (true) {
            System.out.print("Nhập phép toán (+, -, *, /): ");
            if (!input.hasNext()) {
                System.out.println("Hết dữ liệu nhập.");
                return false;
            }
            operator = input.next();
            if (StudyTools.isValidOperator(operator)) {
                break;
            }
            System.out.println("Phép toán không hợp lệ.");
        }
        Double b = readDouble(input, "Nhập số thứ hai: ");
        if (b == null) {
            return false;
        }
        if (operator.equals("/") && b == 0.0) {
            System.out.println("Không thể chia cho 0.");
            return true; // Quay về menu, vẫn dùng được các chức năng khác.
        }
        double result = StudyTools.calculate(a, operator, b);
        if (!Double.isFinite(result)) {
            System.out.println("Kết quả vượt phạm vi double.");
        } else {
            System.out.printf(Locale.ROOT, "%.4f %s %.4f = %.4f%n", a, operator, b, result);
        }
        return true;
    }

    private static boolean runGrades(Scanner input) {
        // Biến cục bộ: mỗi lần mở chức năng sẽ bắt đầu một danh sách điểm mới.
        long count = 0;
        double sum = 0;
        double min = 10;
        double max = 0;
        while (true) {
            Double grade = readDouble(input, "Nhập điểm (0..10, -1 kết thúc): ");
            if (grade == null) {
                return false;
            }
            if (grade == -1.0) {
                break;
            }
            if (!StudyTools.isValidGrade(grade)) {
                System.out.println("Điểm phải nằm trong [0, 10]; -1 để kết thúc.");
                continue;
            }
            count++;
            sum += grade;
            min = Math.min(min, grade);
            max = Math.max(max, grade);
        }
        if (count == 0) {
            System.out.println("Chưa có điểm; không thể tính trung bình.");
        } else {
            System.out.printf(Locale.ROOT,
                    "count=%d; min=%.4f; max=%.4f; average=%.4f%n",
                    count, min, max, StudyTools.average(sum, count));
        }
        return true;
    }
}
