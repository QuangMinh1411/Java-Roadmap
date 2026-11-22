package week2.tuesday.LJ;

/*
 * BÀI 3 — TRUNG BÌNH: THỐNG KÊ ĐẾN SỐ KẾT THÚC
 * Theo Java Language.md, mục 5.3–5.4, 6.1 và 7.1–7.2.
 *
 * YÊU CẦU
 * 1. Khai báo int[] values = {8, -2, 6, 10, 0, 99}.
 * 2. Dùng while và chỉ số để duyệt mảng, không truy cập ngoài phạm vi.
 *    Gặp số 0: dùng break để dừng ngay; 0 không thuộc dữ liệu thống kê.
 *    Gặp số âm: dùng continue để bỏ qua; nhớ cập nhật chỉ số để vòng lặp tiến.
 *    Nếu không có 0, xử lý đến hết mảng.
 * 3. Với các số dương trước số 0 đầu tiên, tính số lượng, tổng kiểu long,
 *    nhỏ nhất, lớn nhất và trung bình kiểu double.
 *    Khởi tạo min/max từ phần tử hợp lệ đầu tiên, không giả định min = 0.
 * 4. Nếu không có số hợp lệ, chỉ in "Khong co du lieu hop le";
 *    không tính trung bình và không in min/max chưa xác định.
 * 5. Mảng đầu vào luôn khác null; có thể rỗng. Không sửa mảng đầu vào.
 * Không cần nhập từ bàn phím; thay values để tự kiểm tra.
 *
 * TỰ KIỂM TRA
 * - {8, -2, 6, 10, 0, 99}: count = 3, sum = 24, min = 6,
 *   max = 10, average = 8.0. Số 99 không được xử lý.
 * - {1, 2}: count = 2, sum = 3, min = 1, max = 2, average = 1.5.
 * - {0, 5}, {-3, -2, 0} hoặc {}: Khong co du lieu hop le.
 */
public class Exercise03SentinelStatistics {
    public static void main(String[] args) {
        int[] values = {8, -2, 6, 10, 0, 99};
        int index = 0;
        int count = 0;
        long sum = 0;
        // Giá trị tạm để đáp ứng quy tắc khởi tạo biến cục bộ.
        // Min/max thực tế được lấy từ số hợp lệ đầu tiên ở dưới.
        int min = 0;
        int max = 0;

        while (index < values.length) {
            int value = values[index];
            // Cập nhật trước continue để không bị kẹt tại một số âm.
            index++;

            if (value == 0) {
                break;
            }
            if (value < 0) {
                continue;
            }

            if (count == 0) {
                min = value;
                max = value;
            } else {
                if (value < min) {
                    min = value;
                }
                if (value > max) {
                    max = value;
                }
            }
            count++;
            sum += value;
        }

        if (count == 0) {
            System.out.println("Khong co du lieu hop le");
            return;
        }

        double average = (double) sum / count;
        System.out.printf("count = %d, sum = %d, min = %d, max = %d, average = %s%n",
                count, sum, min, max, average);
    }
}
