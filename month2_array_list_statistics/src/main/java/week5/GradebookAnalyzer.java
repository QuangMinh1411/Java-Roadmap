package week5;

/**
 * Mini Project Chương 4: Hệ thống Quản lý & Thống kê Điểm Sinh viên
 * Áp dụng: Primitive types, String, Jagged Arrays, Switch Expression,
 * Enhanced For Loop, Labeled Break, và Operator Precedence.
 */
public class GradebookAnalyzer {

    public static void main(String[] args) {
        // 1. Mảng 1 chiều lưu tên các lớp học (tr. 122)
        String[] classNames = { "CS101 - Nhập môn Lập trình", "CS102 - Cấu trúc Dữ liệu", "CS103 - Mạng Máy tính" };

        // 2. Mảng đa chiều không chữ nhật (Jagged Array) lưu điểm sinh viên mỗi lớp (tr. 128)
        // Sĩ số mỗi lớp khác nhau: lớp 1 có 4 SV, lớp 2 có 5 SV, lớp 3 có 3 SV
        double[][] classScores = {
            { 8.5, 7.0, 9.2, 6.5 },
            { 5.5, 8.0, 9.5, 3.8, 7.2 },
            { 6.0, 7.5, 8.8 }
        };

        System.out.println("=== BÁO CÁO THỐNG KÊ ĐIỂM THEO LỚP HỌC ===\n");

        double totalSystemScore = 0.0;
        int totalStudents = 0;

        // 3. Duyệt từng lớp bằng vòng lặp for chuẩn
        for (int i = 0; i < classNames.length; i++) {
            double[] currentScores = classScores[i];
            double sum = 0.0;
            double max = currentScores[0];
            double min = currentScores[0];

            // 4. Dùng Enhanced For Loop tính tổng, tìm max và min (tr. 111)
            for (double score : currentScores) {
                sum += score;
                if (score > max) {
                    max = score;
                }
                if (score < min) {
                    min = score;
                }
            }

            double avg = sum / currentScores.length;
            totalSystemScore += sum;
            totalStudents += currentScores.length;

            // 5. Dùng Switch Expression (Java 14+) xếp loại mức điểm trung bình lớp (tr. 107)
            int bracket = (int) avg; // Ép kiểu tường minh từ double sang int (tr. 114)
            String rating = switch (bracket) {
                case 9, 10 -> "Xuất sắc (Excellent)";
                case 8 -> "Giỏi (Good)";
                case 7 -> "Khá (Fair)";
                case 5, 6 -> "Trung bình (Average)";
                default -> "Cần cải thiện (Needs Improvement)";
            };

            // In báo cáo chi tiết từng lớp
            System.out.println("Lớp: " + classNames[i]);
            System.out.println("  - Sĩ số: " + currentScores.length + " sinh viên");
            System.out.println("  - Điểm cao nhất: " + max + " | Thấp nhất: " + min);
            System.out.println("  - Điểm trung bình: " + (Math.round(avg * 100.0) / 100.0) + " -> Xếp loại: " + rating);
            System.out.println("--------------------------------------------------");
        }

        // 6. Tính điểm trung bình toàn khoa
        double overallAvg = totalSystemScore / totalStudents;
        System.out.println("Tổng số sinh viên toàn khoa: " + totalStudents);
        System.out.println("Điểm trung bình chung toàn khoa: " + (Math.round(overallAvg * 100.0) / 100.0));

        // 7. Sử dụng Labeled Break để rà soát trường hợp điểm liệt (< 4.0) đầu tiên (tr. 112)
        boolean hasCriticalFail = false;
        int alertClassIdx = -1;
        int alertStudentIdx = -1;

        auditLoop:
        for (int c = 0; c < classScores.length; c++) {
            for (int s = 0; s < classScores[c].length; s++) {
                if (classScores[c][s] < 4.0) {
                    hasCriticalFail = true;
                    alertClassIdx = c;
                    alertStudentIdx = s;
                    break auditLoop; // Thoát khỏi cả 2 vòng lặp ngay lập tức
                }
            }
        }

        if (hasCriticalFail) {
            System.out.println("\n[CẢNH BÁO HỌC VỤ] Phát hiện điểm dưới 4.0 tại lớp '"
                    + classNames[alertClassIdx] + "', sinh viên thứ #" + (alertStudentIdx + 1)
                    + " (Điểm: " + classScores[alertClassIdx][alertStudentIdx] + ")");
        } else {
            System.out.println("\n[TRẠNG THÁI TỐT] Không có sinh viên nào bị điểm dưới 4.0.");
        }
    }
}
