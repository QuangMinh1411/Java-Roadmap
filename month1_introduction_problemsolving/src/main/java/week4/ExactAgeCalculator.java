package week4;

import java.time.LocalDate;
import java.time.Period;

/**
 * Bài 5 — Tính tuổi theo năm, tháng, ngày bằng Java Date/Time API.
 * Kiến thức: phương thức static nhận đối tượng, trả về Period và biến cục bộ var.
 * LocalDate.now() lấy ngày hiện tại theo múi giờ mặc định của máy chạy.
 */
public class ExactAgeCalculator {
    /**
     * @param birthDate ngày sinh hợp lệ, không null và không ở tương lai
     * @return khoảng thời gian theo lịch từ ngày sinh đến hôm nay
     * @throws IllegalArgumentException nếu ngày sinh null hoặc ở tương lai
     */
    public static Period calculateExactAge(LocalDate birthDate) {
        var today = LocalDate.now();
        if (birthDate == null || birthDate.isAfter(today)) {
            throw new IllegalArgumentException("Ngày sinh phải khác null và không sau hôm nay.");
        }

        // Period tính theo lịch, không dùng số ngày / 365 để xấp xỉ tuổi.
        return Period.between(birthDate, today);
    }

    public static void main(String[] args) {
        var birthDate = LocalDate.of(2000, 2, 29);
        var age = calculateExactAge(birthDate);

        System.out.println("Ngày sinh: " + birthDate);
        System.out.println("Ngày hiện tại: " + LocalDate.now());
        System.out.printf("Tuổi chính xác: %d năm, %d tháng, %d ngày%n",
                age.getYears(), age.getMonths(), age.getDays());
        // Có thể đổi birthDate phía trên để thử ngày sinh khác.
        // Ngày sinh bằng hôm nay: cả ba thành phần đều bằng 0.
        // Kết quả thay đổi theo ngày chạy; getDays() là phần ngày còn lại,
        // không phải tổng số ngày đã sống.
    }
}
