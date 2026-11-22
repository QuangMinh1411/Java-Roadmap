package week1.tuesday.LJ;

/*
 * BÀI 1 — CƠ BẢN: LỜI CHÀO TỪ CONSOLE
 * Kiến thức: class, main(), String[] args, biến cục bộ, println, javac/java.
 *
 * ĐỀ BÀI
 * 1. Tạo chương trình in đúng hai dòng theo mẫu bên dưới.
 * 2. Nếu có tham số dòng lệnh, dùng args[0] làm tên người học;
 *    nếu không, dùng tên mặc định "Java Learner".
 * 3. Khai báo tên bằng biến cục bộ trong main(). Kiểm tra args.length
 *    trước khi truy cập args[0]. Bỏ qua các tham số sau tham số đầu tiên.
 *
 * KẾT QUẢ MẪU KHI KHÔNG CÓ THAM SỐ
 * Hello, Java!
 * Welcome, Java Learner!
 *
 * TỰ KIỂM TRA
 * - Truyền Minh: dòng thứ hai là "Welcome, Minh!".
 * - Truyền "Nguyen Minh" trong dấu ngoặc kép: giữ nguyên tên có dấu cách.
 * - Đóng và mở lại chương trình: không cần lưu dữ liệu giữa các lần chạy.
 *
 * CHẠY RIÊNG (terminal tại thư mục src/main/java của module)
 * javac -encoding UTF-8 -d /tmp/first-application week1/tuesday/LJ/Exercise01ConsoleGreeting.java
 * java -cp /tmp/first-application week1.tuesday.LJ.Exercise01ConsoleGreeting "Nguyen Minh"
 * Có thể chạy trực tiếp main() trong IntelliJ và đặt Program arguments.
 * Mã bên dưới là một lời giải tham khảo; hãy tự viết trước khi đối chiếu.
 */
public class Exercise01ConsoleGreeting {
    public static void main(String[] args) {
        String name = "Java Learner";
        if (args.length > 0) {
            name = args[0];
        }

        System.out.println("Hello, Java!");
        System.out.println("Welcome, " + name + "!");
    }
}
