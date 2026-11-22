package week1;

/*
 * Đổi cm <-> m và g <-> kg; giả sử đầu vào là số hữu hạn, không âm.
 * Mỗi chiều có hai ca kiểm tra: 0 và một số dương.
 * main() in đúng 8 dòng expected/actual, giữ giá trị double khi in.
 * Sau refactor: các hàm giữ nguyên chữ ký, gọi lại 4 hàm thuần ở week4.UnitConverter.
 * main() kiểm tra sai số; kết quả sai sẽ ném AssertionError.
 *
 * double lưu số theo hệ nhị phân với độ chính xác hữu hạn nên nhiều số
 * thập phân, như 0.1, chỉ được biểu diễn gần đúng. Phép nhân/chia có thể
 * làm tròn thêm; ví dụ metersToCentimeters(0.1 + 0.2) có thể cho
 * 30.000000000000004 thay vì 30.0.
 * Các ca dưới đây cho kết quả biểu diễn chính xác được bằng double,
 * nhưng không có nghĩa mọi đầu vào đều chính xác. Khi so sánh kết quả
 * gần đúng, dùng sai số cho phép phù hợp thay vì luôn dùng ==.
 * Định dạng ít chữ số khi in chỉ làm tròn phần hiển thị.
 */
public class UnitConverter {
    public static double centimetersToMeters(double centimeters) {
        return week4.UnitConverter.centimetersToMeters(centimeters);
    }

    public static double metersToCentimeters(double meters) {
        return week4.UnitConverter.metersToCentimeters(meters);
    }

    public static double gramsToKilograms(double grams) {
        return week4.UnitConverter.gramsToKilograms(grams);
    }

    public static double kilogramsToGrams(double kilograms) {
        return week4.UnitConverter.kilogramsToGrams(kilograms);
    }

    public static void main(String[] args) {
        printComparison("0 cm -> m", 0.0, centimetersToMeters(0));
        printComparison("125 cm -> m", 1.25, centimetersToMeters(125));
        printComparison("0 m -> cm", 0.0, metersToCentimeters(0));
        printComparison("1.25 m -> cm", 125.0, metersToCentimeters(1.25));
        printComparison("0 g -> kg", 0.0, gramsToKilograms(0));
        printComparison("2500 g -> kg", 2.5, gramsToKilograms(2500));
        printComparison("0 kg -> g", 0.0, kilogramsToGrams(0));
        printComparison("2.5 kg -> g", 2500.0, kilogramsToGrams(2.5));
    }

    private static void printComparison(String conversion, double expected, double actual) {
        double tolerance = 1e-12 * Math.max(1.0, Math.abs(expected));
        if (!Double.isFinite(actual) || Math.abs(actual - expected) > tolerance) {
            throw new AssertionError(conversion + ": expected=" + expected + ", actual=" + actual);
        }
        System.out.println(conversion + " | expected=" + expected + " | actual=" + actual);
    }
}
