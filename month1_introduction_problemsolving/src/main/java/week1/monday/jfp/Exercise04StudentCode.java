package week1.monday.jfp;

import java.util.Scanner;

/*
 * BÀI 4 — KHÁ: TẠO LỜI CHÀO VÀ KIỂM TRA MÃ HỌC VIÊN
 * Kiến thức: String, nối chuỗi, length, equals, isEmpty, startsWith,
 * endsWith và if (mục 8, 11–13 của tài liệu).
 *
 * ĐỀ BÀI
 * Nhập hai dòng: tên học viên và mã học viên.
 * 1. Nếu tên là chuỗi rỗng, thay tên bằng "Ban" rồi tạo lời chào.
 * 2. In độ dài tên sau khi xử lý và độ dài mã.
 * 3. Mã hợp lệ theo bài này khi bắt đầu bằng "JAVA-", kết thúc bằng
 *    "-VN" và dài ít nhất 11 đơn vị UTF-16. In true/false.
 * 4. In true/false cho việc mã có đúng nội dung "JAVA-001-VN" hay không.
 * Không dùng regex, vòng lặp hoặc == để so nội dung chuỗi.
 *
 * QUY ƯỚC
 * Dùng nextLine() để đọc nguyên dòng (phương thức Scanner bổ sung).
 * Nhập đủ hai dòng; không tự xoá khoảng trắng. Tên " " không rỗng.
 * Mã phân biệt hoa/thường. Phần giữa không bắt buộc là chữ số;
 * "JAVA-ABC-VN" cũng hợp lệ theo đúng ba điều kiện của bài này.
 * length() đo đơn vị UTF-16, không luôn bằng số ký tự nhìn thấy.
 *
 * DỮ LIỆU MẪU (hai dòng):
 * Minh Nguyen
 * JAVA-001-VN
 * KẾT QUẢ (không tính lời nhắc nhập):
 * Xin chao, Minh Nguyen!
 * Do dai ten: 11
 * Do dai ma: 11
 * Ma hop le: true
 * Trung ma mau: true
 *
 * CA KIỂM CHỨNG KHÁC
 * - Tên rỗng, mã JAVA-01-VN: tên thành Ban, dài 3;
 *   mã dài 10, không hợp lệ, không trùng mẫu.
 * - Tên Lan, mã java-001-VN: không hợp lệ, không trùng mẫu.
 * - Tên Lan, mã JAVA-ABC-VN: hợp lệ nhưng không trùng mẫu.
 */
public class Exercise04StudentCode {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Nhap ten hoc vien: ");
        String name = input.nextLine();
        System.out.print("Nhap ma hoc vien: ");
        String studentCode = input.nextLine();

        if (name.isEmpty()) {
            name = "Ban";
        }
        String greeting = "Xin chao, " + name + "!";

        // Chỉ công nhận hợp lệ khi cả ba điều kiện đều đúng.
        // if lồng nhau giúp giải bài chỉ với cấu trúc đã học.
        boolean validCode = false;
        if (studentCode.startsWith("JAVA-")) {
            if (studentCode.endsWith("-VN")) {
                if (studentCode.length() >= 11) {
                    validCode = true;
                }
            }
        }

        System.out.println(greeting);
        System.out.printf("Do dai ten: %d%n", name.length());
        System.out.printf("Do dai ma: %d%n", studentCode.length());
        System.out.printf("Ma hop le: %b%n", validCode);
        System.out.printf("Trung ma mau: %b%n", studentCode.equals("JAVA-001-VN"));
    }
}
