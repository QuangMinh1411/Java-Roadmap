package week1.tuesday.LJ;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/*
 * BÀI 4 — KHÁ: HAI ĐỐI TƯỢNG, HAI TRẠNG THÁI RIÊNG
 * Kiến thức: constructor, biến thể hiện, this, đối tượng, tham số main().
 *
 * ĐỀ BÀI
 * 1. Tạo lớp MessageComponent kế thừa JComponent, có constructor nhận
 *    nội dung, tọa độ x/y và màu chữ. Lưu chúng vào các biến thể hiện;
 *    không dùng static cho trạng thái của từng thành phần.
 * 2. paintComponent() tô nền trắng và vẽ thông điệp từ trạng thái đã lưu.
 * 3. Tạo hai đối tượng từ cùng lớp, đặt trong một JFrame 600 x 300
 *    bằng GridLayout(2, 1): đối tượng trên dùng màu xanh dương, (40, 60);
 *    đối tượng dưới dùng màu đỏ, (100, 60). Tọa độ tính trong từng ô.
 * 4. Nội dung trên lấy từ args[0], mặc định "Hello, Java!";
 *    nội dung dưới lấy từ args[1], mặc định "Each object has its own state".
 *    Kiểm tra số tham số trước khi truy cập; bỏ qua tham số còn lại.
 * 5. Khởi tạo giao diện trên EDT; đóng cửa sổ là thoát ứng dụng.
 *
 * TỰ KIỂM TRA
 * - Không truyền tham số: hai dòng mặc định có màu, vị trí khác nhau.
 * - Truyền "Xin chao": chỉ nội dung ô trên đổi.
 * - Truyền "Minh" "Lan": ô trên hiện Minh, ô dưới hiện Lan.
 * - Đổi tọa độ của đối tượng trên: đối tượng dưới không bị ảnh hưởng.
 * - Tự giải thích: đâu là lớp, hai đối tượng, constructor, biến thể hiện
 *   và biến cục bộ trong chương trình?
 *
 * CHẠY RIÊNG: chạy main() trong IntelliJ; cần môi trường có màn hình.
 * Đặt Program arguments nếu muốn thử nội dung khác, dùng dấu ngoặc kép
 * bao quanh mỗi thông điệp có khoảng trắng.
 * Mã bên dưới là một lời giải tham khảo; hãy tự viết trước khi đối chiếu.
 */
public class Exercise04IndependentMessages {
    public static void main(String[] args) {
        String firstMessage = args.length > 0 ? args[0] : "Hello, Java!";
        String secondMessage = args.length > 1
                ? args[1] : "Each object has its own state";

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Independent Messages");
            frame.setLayout(new GridLayout(2, 1));
            frame.add(new MessageComponent(firstMessage, 40, 60, Color.BLUE));
            frame.add(new MessageComponent(secondMessage, 100, 60, Color.RED));
            frame.setSize(600, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    static class MessageComponent extends JComponent {
        private final String message;
        private final int messageX;
        private final int messageY;
        private final Color messageColor;

        MessageComponent(String message, int messageX, int messageY, Color messageColor) {
            this.message = message;
            this.messageX = messageX;
            this.messageY = messageY;
            this.messageColor = messageColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(messageColor);
            g.drawString(message, messageX, messageY);
        }
    }
}
