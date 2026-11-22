package week1.monday.jfp;

public class Receipt {
    public static void main(String[] args) {
        String name1 = "Bút";
        int quantity1 = 2;
        long price1 = 5_000;

        String name2 = "Vở";
        int quantity2 = 3;
        long price2 = 12_000;

        String name3 = "Thước";
        int quantity3 = 1;
        long price3 = 8_000;

        // Tên căn trái, số căn phải; %n xuống dòng.
        System.out.printf("%-15s %10s %12s %12s%n",
                "Tên", "Số lượng", "Giá", "Thành tiền");
        System.out.printf("%-15s %10d %12d %12d%n",
                name1, quantity1, price1, quantity1 * price1);
        System.out.printf("%-15s %10d %12d %12d%n",
                name2, quantity2, price2, quantity2 * price2);
        System.out.printf("%-15s %10d %12d %12d%n",
                name3, quantity3, price3, quantity3 * price3);
    }
}
