package week4;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class PersonalFinanceApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Vòng lặp do...while duy trì Menu điều khiển cho đến khi người dùng chọn Thoát
        do {
            System.out.println("""
            ==================================================
                 HỆ THỐNG QUẢN LÝ TÀI CHÍNH CÁ NHÂN (DEITEL CH4)
            ==================================================
            1. Mô phỏng Sổ tiết kiệm Lãi kép (BigDecimal)
            2. Kiểm tra điều kiện Gói vay Tín dụng (Toán tử Logic)
            3. Thoát chương trình
            Vui lòng chọn chức năng (1-3):""");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Nhập số tiền gửi ban đầu (VNĐ): ");
                    String principalStr = scanner.next();
                    System.out.print("Nhập lãi suất cơ bản hàng năm (ví dụ 0.06 cho 6%): ");
                    String rateStr = scanner.next();
                    System.out.print("Nhập số năm gửi tiết kiệm: ");
                    int years = scanner.nextInt();

                    BigDecimal principal = new BigDecimal(principalStr);
                    BigDecimal rate = new BigDecimal(rateStr);
                    BigDecimal bonusRate = new BigDecimal("0.005"); // Thưởng 0.5%
                    BigDecimal threshold = new BigDecimal("100000000.00"); // 100 triệu

                    // Kiểm tra điều kiện hưởng ưu đãi bằng toán tử logic điều kiện &&
                    if ((years >= 5) && (principal.compareTo(threshold) >= 0)) {
                        rate = rate.add(bonusRate);
                        System.out.println(">> Đủ điều kiện nhận Ưu đãi VIP: Cộng thêm 0.5% lãi suất/năm!");
                    }

                    System.out.printf("%nLãi suất áp dụng: %s%n", rate);
                    System.out.printf("%s%25s %30s%n", "Năm", "Số dư làm tròn (VNĐ)", "Số dư chính xác");

                    // Vòng lặp for tính toán số dư từng năm
                    for (int year = 1; year <= years; ++year) {
                        // Công thức: principal * (1 + rate)^year
                        BigDecimal totalAmount = principal.multiply(
                                BigDecimal.ONE.add(rate).pow(year));

                        // Làm tròn ngân hàng (HALF_EVEN) đến 2 chữ số thập phân
                        BigDecimal rounded = totalAmount.setScale(2, RoundingMode.HALF_EVEN);
                        System.out.printf("%4d%,25.2f %30s%n", year, rounded, totalAmount);
                    }
                    System.out.println();
                    break;

                case 2:
                    System.out.print("Nhập độ tuổi của bạn: ");
                    int age = scanner.nextInt();
                    System.out.print("Nhập thu nhập hàng tháng (triệu VNĐ): ");
                    double income = scanner.nextDouble();
                    System.out.print("Có nợ xấu ngân hàng không? (true/false): ");
                    boolean hasBadCredit = scanner.nextBoolean();

                    // Đánh giá điều kiện phức hợp với ngắn mạch
                    if (!hasBadCredit && (age >= 22 && age <= 60) && (income >= 15.0)) {
                        System.out.println(">> KẾT QUẢ: Bạn ĐỦ ĐIỀU KIỆN xét duyệt gói vay tín chấp tiêu chuẩn.");
                    } else {
                        System.out.println(">> KẾT QUẢ: Rất tiếc, hồ sơ KHÔNG THỎA MÃN các tiêu chí tín dụng.");
                    }
                    System.out.println();
                    break;

                case 3:
                    System.out.println("Cảm ơn bạn đã sử dụng hệ thống!");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại (1 - 3).\n");
                    break;
            }
        } while (choice != 3);
    }
}
