package week1.tuesday.LJ;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/*
 * BÀI 3 — TRUNG BÌNH: TỰ VẼ THÀNH PHẦN GIAO DIỆN
 * Kiến thức: extends, @Override, JComponent, Graphics, paintComponent().
 *
 * ĐỀ BÀI
 * 1. Trong cùng file, tạo lớp MessageComponent kế thừa JComponent.
 *    Có thể dùng lớp static lồng trong lớp chính để file chạy độc lập.
 * 2. Ghi đè protected void paintComponent(Graphics g), gọi phiên bản
 *    của lớp cha trước khi vẽ. Tô nền trắng phủ toàn bộ vùng thành phần.
 * 3. Vẽ "Hello, Java!" màu xanh dương tại (125, 95), rồi vẽ
 *    "My custom component" màu đen tại (125, 125).
 *    Tọa độ drawString là điểm đầu trên đường cơ sở (baseline) của chữ,
 *    không phải góc trên bên trái. Gốc (0, 0) ở góc trên trái thành phần.
 * 4. Thêm thành phần vào JFrame kích thước 500 x 250; đóng là thoát.
 *    Khởi tạo giao diện trên EDT; không gọi trực tiếp paintComponent().
 *
 * TỰ KIỂM TRA
 * - Cửa sổ có hai dòng chữ đúng màu và vị trí yêu cầu.
 * - Thu nhỏ rồi khôi phục cửa sổ: nội dung được vẽ lại đầy đủ.
 * - Phóng lớn: nền vẫn trắng; tọa độ chữ giữ nguyên, không tự căn giữa.
 *
 * CHẠY RIÊNG: chạy main() trong IntelliJ; cần môi trường có màn hình.
 * Mã bên dưới là một lời giải tham khảo; hãy tự viết trước khi đối chiếu.
 */
public class Exercise03CustomMessage {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Custom Message");
            frame.add(new MessageComponent());
            frame.setSize(500, 250);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    static class MessageComponent extends JComponent {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.BLUE);
            g.drawString("Hello, Java!", 125, 95);
            g.setColor(Color.BLACK);
            g.drawString("My custom component", 125, 125);
        }
    }
}
