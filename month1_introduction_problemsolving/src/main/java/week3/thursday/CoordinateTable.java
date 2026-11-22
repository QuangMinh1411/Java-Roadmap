package week3.thursday;

/**
 * Minh hoạ for lồng nhau, phạm vi biến và bộ đếm.
 * Tham khảo: week3/Control Statement.md (§4.2–4.5)
 * và week3/Fundamental Programming Structure.md (§3.4–3.5, §3.7–3.8).
 *
 * Tọa độ viết theo thứ tự (hàng, cột), bắt đầu từ 1.
 * Hàng nằm ngang: row giữ nguyên khi column chạy từ trái sang phải.
 * Cột nằm dọc: các ô cùng column thuộc cùng một cột.
 * Vòng ngoài chọn 3 hàng; mỗi hàng khởi tạo lại column = 1,
 * rồi vòng trong chạy 4 lượt để in 4 ô. Tổng là 3 * 4 = 12 lượt.
 * Đây là số lần chạy thân vòng trong, không phải số lần kiểm tra điều kiện.
 * Số thứ tự ô = (row - 1) * COLUMNS + column, nên không đặt lại theo hàng.
 */
public class CoordinateTable {
    public static void main(String[] args) {
        final int ROWS = 3;
        final int COLUMNS = 4;
        int outerCount = 0;
        int innerCount = 0;

        System.out.println("BẢNG TỌA ĐỘ 3 HÀNG × 4 CỘT");
        System.out.println("Mỗi ô: số thứ tự (hàng,cột)");

        for (int row = 1; row <= ROWS; row++) {
            outerCount++;
            int innerCountInRow = 0; // Bắt đầu đếm lại ở mỗi hàng.

            for (int column = 1; column <= COLUMNS; column++) {
                innerCount++; // Bộ đếm tổng không đặt lại khi đổi hàng.
                innerCountInRow++;
                int cellNumber = (row - 1) * COLUMNS + column;
                System.out.printf("%2d (%d,%d)  ", cellNumber, row, column);
            }

            // Xuống dòng sau vòng trong: mỗi lượt vòng ngoài tạo một hàng.
            System.out.printf("| Vòng trong: %d lượt%n", innerCountInRow);
        }

        if (outerCount != ROWS || innerCount != ROWS * COLUMNS) {
            throw new AssertionError("Sai số hàng hoặc số lượt vòng trong.");
        }
        System.out.printf("Vòng ngoài: %d lượt = %d hàng.%n", outerCount, ROWS);
        System.out.printf("PASS: vòng trong chạy tổng %d lượt = %d × %d = %d ô.%n",
                innerCount, ROWS, COLUMNS, ROWS * COLUMNS);
        System.out.println("Hàng: đọc ngang; cột: đọc dọc; tọa độ là (hàng,cột).");
        System.out.println("Ví dụ: ô 5 ở (2,1); ô 12 ở (3,4).");
    }
}
