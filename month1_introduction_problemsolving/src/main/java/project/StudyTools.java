package project;

import week3.PrimeCounter;
import week4.TemperatureConverter;

/** Các hàm tính toán không đọc Scanner và không in ra màn hình. */
public class StudyTools {
    public static boolean isValidOperator(String operator) {
        return "+".equals(operator) || "-".equals(operator)
                || "*".equals(operator) || "/".equals(operator);
    }

    // Tái sử dụng cách rẽ nhánh của week2.tuesday.LJ.Exercise02SafeCalculator.
    public static double calculate(double a, String operator, double b) {
        if (!isValidOperator(operator)) {
            throw new IllegalArgumentException("Phép toán không hợp lệ.");
        }
        if ("/".equals(operator) && b == 0.0) {
            throw new IllegalArgumentException("Không thể chia cho 0.");
        }
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new IllegalArgumentException("Phép toán không hợp lệ.");
        };
    }

    public static double celsiusToFahrenheit(double celsius) {
        return TemperatureConverter.toFahrenheit(celsius);
    }

    public static boolean isPrime(int n) {
        return PrimeCounter.isPrime(n);
    }

    public static boolean isValidGrade(double grade) {
        return Double.isFinite(grade) && grade >= 0 && grade <= 10;
    }

    public static double average(double sum, long count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Cần ít nhất một điểm.");
        }
        return sum / count;
    }
}
