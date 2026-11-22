package week1.tuesday.LJ;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/*
 * BÀI 2 — CƠ BẢN: CỬA SỔ SWING ĐẦU TIÊN
 * Kiến thức: import, new, đối tượng, JFrame, JLabel, gọi phương thức.
 *
 * ĐỀ BÀI
 * 1. Tạo JFrame có tiêu đề "My First Java Window", kích thước 400 x 200.
 * 2. Tạo JLabel chứa "Hello, Java!", căn giữa theo chiều ngang và dọc,
 *    rồi thêm nhãn vào cửa sổ bằng add().
 * 3. Đặt cửa sổ ở giữa màn hình, hiển thị và kết thúc ứng dụng khi đóng.
 * 4. Tạo giao diện trong SwingUtilities.invokeLater() để thao tác Swing
 *    trên Event Dispatch Thread (EDT), luồng xử lý giao diện.
 *
 * TỰ KIỂM TRA
 * - Chạy main(): thấy đúng tiêu đề và dòng chữ giữa vùng nội dung.
 * - Thay đổi kích thước cửa sổ: nhãn vẫn nằm giữa vùng nội dung.
 * - Bấm nút đóng: tiến trình Java kết thúc.
 *
 * CHẠY RIÊNG: chạy main() trong IntelliJ; cần môi trường có màn hình.
 * Không cần file bài tập nào khác hoặc thư viện ngoài JDK.
 * Mã bên dưới là một lời giải tham khảo; hãy tự viết trước khi đối chiếu.
 */
public class Exercise02FirstWindow {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("My First Java Window");
            JLabel label = new JLabel("Hello, Java!", JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);

            frame.add(label);
            frame.setSize(400, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
