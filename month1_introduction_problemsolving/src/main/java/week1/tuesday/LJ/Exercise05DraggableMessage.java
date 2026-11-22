package week1.tuesday.LJ;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/*
 * BÀI 5 — NÂNG CAO TRONG PHẠM VI CHƯƠNG: KÉO THÔNG ĐIỆP BẰNG CHUỘT
 * Kiến thức: kế thừa, interface, constructor, trạng thái, sự kiện, repaint().
 *
 * ĐỀ BÀI
 * 1. Tạo lớp DraggableMessageComponent extends JComponent implements
 *    MouseMotionListener trong cùng file. Khai báo các biến thể hiện:
 *    theMessage, messageX = 125, messageY = 95, dragEventCount = 0.
 * 2. Constructor nhận nội dung và đăng ký addMouseMotionListener(this).
 *    Nội dung lấy từ args[0], mặc định "Drag me!"; bỏ qua tham số sau đó.
 * 3. paintComponent() tô lại nền trắng, vẽ thông điệp màu xanh dương
 *    ở (messageX, messageY), và dòng "Drag events: N" màu đen tại (10, 20).
 * 4. Trong mouseDragged(), cập nhật tọa độ bằng e.getX()/e.getY(), tăng
 *    bộ đếm một đơn vị rồi gọi repaint(). Triển khai cả mouseMoved(),
 *    nhưng không thay đổi trạng thái trong phương thức đó.
 * 5. Cho phép bắt đầu nhấn giữ và kéo ở bất cứ đâu trong thành phần;
 *    chữ nhảy tới tọa độ chuột, không cần nhấn trúng chữ. Không yêu cầu
 *    giới hạn chữ trong cửa sổ; chữ có thể bị cắt khi kéo sát mép.
 * 6. Hiển thị trong JFrame 600 x 350 trên EDT, đóng là thoát ứng dụng.
 *    Không gọi trực tiếp paintComponent(), không dùng getGraphics()
 *    để vẽ trong phương thức xử lý chuột.
 *
 * TỰ KIỂM TRA
 * - Mới chạy: chữ tại (125, 95), bộ đếm bằng 0.
 * - Di chuột không giữ nút: chữ và bộ đếm không đổi.
 * - Nhấn giữ rồi kéo: chữ theo chuột, bộ đếm tăng theo số sự kiện kéo.
 *   Một lần kéo có thể phát sinh nhiều sự kiện; không đếm số lần nhấn.
 * - Thả chuột, thu nhỏ rồi khôi phục: chữ giữ vị trí và số đếm cuối.
 * - Truyền "Hello Minh": nội dung đổi, thao tác kéo vẫn hoạt động.
 * - Tự giải thích chuỗi: sự kiện -> cập nhật trạng thái -> repaint()
 *   -> Swing gọi paintComponent(); vì sao cần đăng ký listener?
 *
 * CHẠY RIÊNG: chạy main() trong IntelliJ; cần môi trường có màn hình.
 * Mã bên dưới là một lời giải tham khảo; hãy tự viết trước khi đối chiếu.
 */
public class Exercise05DraggableMessage {
    public static void main(String[] args) {
        String message = args.length > 0 ? args[0] : "Drag me!";

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Draggable Message");
            frame.add(new DraggableMessageComponent(message));
            frame.setSize(600, 350);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    static class DraggableMessageComponent extends JComponent implements MouseMotionListener {
        private final String theMessage;
        private int messageX = 125;
        private int messageY = 95;
        private int dragEventCount = 0;

        DraggableMessageComponent(String message) {
            this.theMessage = message;
            addMouseMotionListener(this);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.BLUE);
            g.drawString(theMessage, messageX, messageY);
            g.setColor(Color.BLACK);
            g.drawString("Drag events: " + dragEventCount, 10, 20);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            messageX = e.getX();
            messageY = e.getY();
            dragEventCount++;
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            // Chỉ kéo khi giữ nút chuột mới thay đổi trạng thái.
        }
    }
}
