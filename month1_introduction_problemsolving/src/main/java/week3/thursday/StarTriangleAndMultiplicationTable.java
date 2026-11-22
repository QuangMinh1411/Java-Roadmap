package week3.thursday;

import java.util.Scanner;

/**
 * Minh hoạ nhập liệu, for lồng nhau và bộ đếm.
 * Tham khảo: week3/Control Statement.md (§3.5, §4.2–4.5, §4.9–4.10)
 * và week3/Fundamental Programming Structure.md (§3.4–3.5, §3.7–3.8).
 *
 * Tam giác: vòng ngoài chạy n hàng; ở hàng i, vòng trong in i sao.
 * Tổng số sao là 1 + 2 + ... + n = n * (n + 1) / 2.
 * Ví dụ n = 3: in lần lượt 1, 2, 3 sao, tổng cộng 6 sao.
 *
 * Bảng nhân có hai cận cố định 1..10, độc lập với n của tam giác.
 * Vòng ngoài chạy 10 lần (10 dòng dữ liệu); vòng trong chạy 10 lần
 * cho mỗi dòng (100 ô tích). Tăng cellCount ngay sau mỗi lần in một ô,
 * tăng rowCount sau khi in xong một dòng. Không đếm tiêu đề vào dữ liệu.
 */
public class StarTriangleAndMultiplicationTable {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n;

        while (true) {
            System.out.print("Nhập số hàng n (1..10): ");
            if (!input.hasNext()) {
                System.out.println("Hết dữ liệu nhập, kết thúc chương trình.");
                return;
            }
            if (!input.hasNextInt()) {
                System.out.println("Lỗi: cần nhập số nguyên; bỏ qua: " + input.next());
                continue; // Bỏ token sai để lần sau đọc được dữ liệu mới.
            }

            n = input.nextInt();
            if (n >= 1 && n <= 10) {
                break;
            }
            System.out.println("Lỗi: n phải nằm trong khoảng 1..10.");
        }

        printStarTriangle(n);
        printMultiplicationTable();
    }

    private static void printStarTriangle(int n) {
        System.out.println("\nTAM GIÁC SAO");
        int starCount = 0;
        int rowCount = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print('*');
                starCount++; // Đếm từng sao thực sự được in.
            }
            System.out.println(); // Chỉ xuống dòng khi đã in hết hàng i.
            rowCount++;
        }

        int expectedStars = n * (n + 1) / 2;
        if (rowCount != n || starCount != expectedStars) {
            throw new AssertionError("Sai số hàng hoặc tổng số sao.");
        }
        System.out.printf("PASS: %d hàng, tổng %d sao (kỳ vọng %d).%n",
                rowCount, starCount, expectedStars);
    }

    private static void printMultiplicationTable() {
        System.out.println("\nBẢNG NHÂN 10 × 10 (ô = hàng × cột)");
        int cellCount = 0;
        int rowCount = 0;

        for (int row = 1; row <= 10; row++) {
            for (int column = 1; column <= 10; column++) {
                System.out.printf("%5d", row * column);
                cellCount++; // Nằm trong vòng trong: mỗi tích là một ô.
            }
            System.out.println();
            rowCount++; // Nằm ngoài vòng trong: mỗi hàng chỉ đếm một lần.
        }

        if (cellCount != 100 || rowCount != 10) {
            throw new AssertionError("Bảng nhân phải có 100 ô trên 10 dòng dữ liệu.");
        }
        System.out.printf("PASS: %d ô tích, %d dòng dữ liệu, mỗi dòng 10 ô.%n",
                cellCount, rowCount);
    }
}
