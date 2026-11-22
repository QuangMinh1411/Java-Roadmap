package week2.tuesday.LJ;

/*
 * BÀI 1 — CƠ BẢN: KIỂU DỮ LIỆU VÀ PHÉP TÍNH
 * Theo Java Language.md, mục 3.1–3.3, 3.6 và 6.1.
 *
 * YÊU CẦU
 * 1. Khai báo ba biến int bằng literal thập phân, nhị phân và hexadecimal,
 *    cùng biểu diễn giá trị 42. In mỗi biến kèm tên hệ đếm.
 * 2. Khai báo final int LESSONS = 7 và int DAYS = 2.
 *    Tính và in LESSONS / DAYS, LESSONS / (double) DAYS,
 *    (double) (LESSONS / DAYS). Giải thích sự khác biệt bằng comment.
 * 3. Khai báo long population = 8_000_000_000L và double price = 19.9.
 *    Ép price sang int, in cả giá trị trước và sau khi ép kiểu.
 * 4. In hai biểu thức "Total: " + 2 + 3 và "Total: " + (2 + 3).
 *    Giải thích bằng comment vì sao kết quả khác nhau.
 * Không cần nhập từ bàn phím. Tự viết các phép tính, không in sẵn đáp án.
 *
 * TỰ KIỂM TRA
 * - Ba literal đều in 42.
 * - Ba phép chia lần lượt cho 3, 3.5 và 3.0.
 * - population giữ nguyên 8000000000; price ép sang int được 19.
 * - Hai dòng nối chuỗi lần lượt là "Total: 23" và "Total: 5".
 */
public class Exercise01NumericTypes {
    public static void main(String[] args) {
        int decimal = 42;
        int binary = 0b101010;
        int hexadecimal = 0x2A;
        System.out.println("Thap phan: " + decimal);
        System.out.println("Nhi phan: " + binary);
        System.out.println("Hexadecimal: " + hexadecimal);

        final int LESSONS = 7;
        int DAYS = 2;
        // Hai toán hạng int: phép chia nguyên bỏ phần thập phân.
        System.out.println("Chia nguyen: " + LESSONS / DAYS);
        // Ép một toán hạng trước khi chia để thực hiện phép chia số thực.
        System.out.println("Ep kieu truoc khi chia: " + LESSONS / (double) DAYS);
        // Ép sau khi chia không khôi phục được phần thập phân đã mất.
        System.out.println("Ep kieu sau khi chia: " + (double) (LESSONS / DAYS));

        long population = 8_000_000_000L;
        double price = 19.9;
        int truncatedPrice = (int) price;
        System.out.println("Population: " + population);
        System.out.println("Price truoc khi ep: " + price);
        System.out.println("Price sau khi ep: " + truncatedPrice);

        // + được nhóm từ trái sang phải: nối 2 rồi nối 3 vào chuỗi.
        System.out.println("Total: " + 2 + 3);
        // Dấu ngoặc buộc cộng hai số trước, sau đó mới nối vào chuỗi.
        System.out.println("Total: " + (2 + 3));
    }
}
