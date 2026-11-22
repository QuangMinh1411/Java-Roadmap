package week1.monday.jfp;

/*
 * BÀI 1 — CƠ BẢN: IN THẺ GIỚI THIỆU
 * Kiến thức: cấu trúc lớp/main, biến String, print, println, printf,
 * ký tự đặc biệt \n, \t, \" và \\ (mục 2–7 của tài liệu).
 *
 * ĐỀ BÀI
 * Không cần nhập dữ liệu. Tạo biến name = "Minh", language = "Java".
 * In thẻ dưới đây; bắt buộc sử dụng cả print, println và printf.
 * Dùng \n để tách hai dòng đầu và \t để ngăn cách nhãn Name với tên.
 *
 * KẾT QUẢ MẪU (khoảng cách sau Name: là một ký tự tab)
 * ====================
 * MY FIRST JAVA CARD
 * Name:   Minh
 * Language: Java
 * Message: "Hello, Java!"
 * Folder: C:\Java\Practice
 * Ready: true; Progress: 100%
 * ====================
 *
 * TỰ KIỂM CHỨNG
 * - Đổi name thành "Lan": chỉ dòng Name thay đổi.
 * - Quan sát print không xuống dòng; println kết thúc dòng đang in.
 * - %% in ra dấu %, còn %n xuống dòng trong printf.
 */
public class Exercise01WelcomeCard {
    public static void main(String[] args) {
        String name = "Minh";
        String language = "Java";

        // Một chuỗi có thể biểu diễn nhiều dòng nhờ escape sequence.
        System.out.println("====================\nMY FIRST JAVA CARD");
        System.out.print("Name:\t");
        System.out.println(name);
        System.out.printf("Language: %s%n", language);
        System.out.println("Message: \"Hello, Java!\"");
        System.out.println("Folder: C:\\Java\\Practice");
        System.out.printf("Ready: %b; Progress: 100%%%n", true);
        System.out.println("====================");
    }
}
