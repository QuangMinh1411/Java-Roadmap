package week1.thursday;

/*
 * BẢNG DỰ ĐOÁN TRƯỚC KHI CHẠY
 * Biểu thức       | Dự đoán | Giải thích
 * 5 / 2           | 2       | Hai toán hạng int nên chia nguyên.
 * 5 / 2.0         | 2.5     | Có toán hạng double nên chia số thực.
 * (double)(5 / 2) | 2.0     | Chia nguyên ra 2 rồi mới ép sang double.
 * (double)5 / 2   | 2.5     | Ép trước khi chia nên chia số thực.
 * -5 % 2          | -1      | Phần dư khác 0 cùng dấu với số bị chia.
 *
 * Chạy main() để đối chiếu kết quả với bảng dự đoán.
 */
public class DivisionPrediction {
    public static void main(String[] args) {
        System.out.println("5 / 2 = " + (5 / 2));
        System.out.println("5 / 2.0 = " + (5 / 2.0));
        System.out.println("(double)(5 / 2) = " + (double)(5 / 2));
        System.out.println("(double)5 / 2 = " + ((double)5 / 2));
        System.out.println("-5 % 2 = " + (-5 % 2));

        int first = 5;
        int second = 2;
        // Sai: phép chia int cho kết quả 3, sau đó mới gán thành 3.0.
        double wrongAverage = (first + second) / 2;
        // Đúng: dùng 2.0 để giữ phần thập phân ngay khi chia.
        double correctAverage = (first + second) / 2.0;

        System.out.println("Trung bình sai (5 + 2) / 2 = " + wrongAverage);
        System.out.println("Trung bình đúng (5 + 2) / 2.0 = " + correctAverage);
    }
}
