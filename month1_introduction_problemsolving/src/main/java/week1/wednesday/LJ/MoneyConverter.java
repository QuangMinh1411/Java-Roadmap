package week1.wednesday.LJ;

/*
 * Đổi 98765 đồng thành số tờ 50000, 20000, 10000 và tiền lẻ.
 * Dùng chia nguyên và lấy dư, không dùng vòng lặp.
 * Kết quả: 1 tờ 50000, 2 tờ 20000, 0 tờ 10000, tiền lẻ 8765 đồng.
 */
public class MoneyConverter {
    public static void main(String[] args) {
        int amount = 98_765;

        int notes50000 = amount / 50_000;
        // Chỉ đổi tiếp phần tiền còn lại sau mỗi mệnh giá.
        int remaining = amount % 50_000;

        int notes20000 = remaining / 20_000;
        remaining = remaining % 20_000;

        int notes10000 = remaining / 10_000;
        int change = remaining % 10_000;

        System.out.println("Số tờ 50000: " + notes50000);
        System.out.println("Số tờ 20000: " + notes20000);
        System.out.println("Số tờ 10000: " + notes10000);
        System.out.println("Tiền lẻ: " + change + " đồng");
    }
}
