package week4;

/**
 * Bốn hàm thuần đổi cm/m và g/kg: chỉ nhận giá trị và trả kết quả,
 * không đọc Scanner, không in và không thay đổi trạng thái bên ngoài.
 * Giữ hợp đồng tuần 1: đầu vào hữu hạn, không âm; kết quả theo số học double.
 * Với chiều nhân, đầu vào còn phải đủ nhỏ để kết quả hữu hạn:
 * meters <= Double.MAX_VALUE / 100, kilograms <= Double.MAX_VALUE / 1000.
 * Có thể có sai số làm tròn hoặc hụt dưới về 0 với giá trị rất nhỏ.
 */
public final class UnitConverter {
    private UnitConverter() {
        // Lớp tiện ích chỉ chứa phương thức static, không cần tạo đối tượng.
    }

    public static double centimetersToMeters(double centimeters) {
        return centimeters / 100.0;
    }

    public static double metersToCentimeters(double meters) {
        return meters * 100.0;
    }

    public static double gramsToKilograms(double grams) {
        return grams / 1000.0;
    }

    public static double kilogramsToGrams(double kilograms) {
        return kilograms * 1000.0;
    }
}
