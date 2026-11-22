package week1.friday;

import java.util.Scanner;

/*
 * Nhập số lượng, đơn giá nguyên (đồng), rồi tên sản phẩm trên dòng tiếp theo.
 * Đọc tên sau số để minh họa cách xử lý ký tự xuống dòng còn sót lại.
 *
 * DỮ LIỆU THỬ
 * 3
 * 12500
 * Sữa tươi không đường
 *
 * Kết quả: giữ nguyên tên "Sữa tươi không đường", tổng 37500 đồng.
 */
public class ProductInvoice {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Nhập số lượng: ");
        int quantity = input.nextInt();
        System.out.print("Nhập đơn giá (đồng): ");
        long unitPrice = input.nextLong();

        // nextInt()/nextLong() để lại ký tự xuống dòng sau số.
        // Bỏ phần còn lại của dòng số trước khi đọc dòng tên sản phẩm.
        // Nếu thiếu lệnh này, nextLine() bên dưới có thể đọc chuỗi rỗng.
        input.nextLine();
        System.out.print("Nhập tên sản phẩm: ");
        // nextLine() giữ cả tên có khoảng trắng; next() chỉ đọc một từ.
        String productName = input.nextLine();

        long total = quantity * unitPrice;

        System.out.println();
        System.out.println("HÓA ĐƠN");
        System.out.println("Sản phẩm: " + productName);
        System.out.println("Số lượng: " + quantity);
        System.out.println("Đơn giá: " + unitPrice + " đồng");
        System.out.println("Tổng: " + total + " đồng");
    }
}
