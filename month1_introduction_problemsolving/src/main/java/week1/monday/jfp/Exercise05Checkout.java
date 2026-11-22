package week1.monday.jfp;

import java.util.Scanner;

/*
 * BÀI 5 — NÂNG CAO TRONG PHẠM VI CHƯƠNG: HOÁ ĐƠN GIẢM GIÁ
 * Kiến thức: kết hợp Scanner, String, int/long, phép toán, so sánh,
 * if và printf (mục 6–13 của tài liệu). Không cần vòng lặp hay mảng.
 *
 * ĐỀ BÀI
 * Nhập lần lượt: tên sản phẩm (một dòng), mã giảm giá (một dòng),
 * đơn giá, số lượng, số tiền khách đưa (ba token số nguyên int).
 *
 * Kiểm tra dữ liệu:
 * - Tên không được rỗng (bài này không xoá khoảng trắng).
 * - Đơn giá từ 1 đến 1000000 đồng; số lượng từ 1 đến 1000.
 * - Tiền khách đưa không âm và nằm trong miền int.
 * In từng lỗi nếu có; chỉ tính và in hoá đơn khi mọi điều kiện hợp lệ.
 * Không cần xử lý token sai kiểu hoặc nhập thiếu dữ liệu.
 *
 * Quy tắc tính:
 * - Tạm tính = đơn giá * số lượng.
 * - Mã "VIP10" giảm 10%, "SAVE5" giảm 5%, mã khác giảm 0%.
 *   So sánh mã phân biệt hoa/thường; dòng mã rỗng vẫn được chấp nhận.
 * - Nếu tạm tính >= 500000 đồng, cộng thêm 5 điểm phần trăm giảm giá.
 *   Ví dụ VIP10 đủ ngưỡng sẽ giảm tổng cộng 15% trên tạm tính.
 * - Tiền giảm = tạm tính * tỷ lệ / 100, bỏ phần lẻ đồng.
 * - Phải trả = tạm tính - tiền giảm.
 * - Khách đưa đủ tiền: in tiền thừa; chưa đủ: in số tiền còn thiếu.
 * Dùng long cho các bước tính tiền để tránh tràn ở phép nhân với tỷ lệ.
 *
 * DỮ LIỆU MẪU (ba dòng):
 * Sach Java
 * VIP10
 * 120000 5 600000
 * KẾT QUẢ (không tính lời nhắc nhập):
 * San pham: Sach Java
 * Tam tinh: 600000 dong
 * Ty le giam: 15%
 * Tien giam: 90000 dong
 * Phai tra: 510000 dong
 * Tien thua: 90000 dong
 *
 * CA KIỂM CHỨNG KHÁC (giữ tên Sach Java):
 * - SAVE5; 999 1 1000: tạm tính 999, giảm 49, trả 950, thừa 50.
 * - Dòng mã rỗng; 250000 2 400000: giảm 25000, trả 475000,
 *   thiếu 75000 (kiểm tra đúng ngưỡng 500000).
 * - vip10; 100000 1 100000: giảm 0%, tiền thừa 0.
 * - VIP10; 1000000 1000 1000000000: giảm 150000000,
 *   trả 850000000, thừa 150000000 (phép nhân trung gian vượt int).
 * - VIP10; 100000 0 -1: báo lỗi số lượng và tiền khách đưa;
 *   không in hoá đơn.
 */
public class Exercise05Checkout {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Đọc các dòng chuỗi trước các số để không vướng ký tự xuống
        // dòng còn lại khi gọi nextLine() ngay sau nextInt().
        System.out.print("Nhap ten san pham: ");
        String productName = input.nextLine();
        System.out.print("Nhap ma giam gia (co the de trong): ");
        String discountCode = input.nextLine();
        System.out.print("Nhap don gia, so luong, tien khach dua: ");
        int unitPrice = input.nextInt();
        int quantity = input.nextInt();
        int amountPaid = input.nextInt();

        boolean validInput = true;
        if (productName.isEmpty()) {
            System.out.println("Loi: ten san pham khong duoc rong.");
            validInput = false;
        }
        if (unitPrice < 1) {
            System.out.println("Loi: don gia phai tu 1 den 1000000.");
            validInput = false;
        }
        if (unitPrice > 1_000_000) {
            System.out.println("Loi: don gia phai tu 1 den 1000000.");
            validInput = false;
        }
        if (quantity < 1) {
            System.out.println("Loi: so luong phai tu 1 den 1000.");
            validInput = false;
        }
        if (quantity > 1_000) {
            System.out.println("Loi: so luong phai tu 1 den 1000.");
            validInput = false;
        }
        if (amountPaid < 0) {
            System.out.println("Loi: tien khach dua khong duoc am.");
            validInput = false;
        }

        if (validInput) {
            long subtotal = unitPrice;
            subtotal = subtotal * quantity;

            int discountPercent = 0;
            if (discountCode.equals("VIP10")) {
                discountPercent = 10;
            }
            if (discountCode.equals("SAVE5")) {
                discountPercent = 5;
            }
            if (subtotal >= 500_000) {
                discountPercent = discountPercent + 5;
            }

            // subtotal là long nên phép nhân cũng tính bằng long.
            // Nhân trước rồi chia nguyên để chỉ bỏ phần lẻ ở bước cuối.
            long discountAmount = subtotal * discountPercent / 100;
            long total = subtotal - discountAmount;

            System.out.printf("San pham: %s%n", productName);
            System.out.printf("Tam tinh: %d dong%n", subtotal);
            System.out.printf("Ty le giam: %d%%%n", discountPercent);
            System.out.printf("Tien giam: %d dong%n", discountAmount);
            System.out.printf("Phai tra: %d dong%n", total);
            if (amountPaid >= total) {
                System.out.printf("Tien thua: %d dong%n", amountPaid - total);
            }
            if (amountPaid < total) {
                System.out.printf("Con thieu: %d dong%n", total - amountPaid);
            }
        }
    }
}
