package week5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CustomerServiceAnalyticsApp {
   public static void main(String[] args) {
      System.out.println("====================================================");
      System.out.println("  HỆ THỐNG PHÂN TÍCH KHẢO SÁT KHÁCH HÀNG (DEITEL CH6)");
      System.out.println("====================================================\n");

      // 1. Khởi tạo danh sách động ArrayList thu thập dữ liệu thô
      var rawFeedbacks = new ArrayList<Integer>();
      // Giả lập dữ liệu khách hàng nhập vào (có lẫn điểm ngoại lai 9 và -1)
      int[] sampleInput = {5, 4, 5, 3, 2, 5, 4, 1, 9, 3, 4, 5, 2, 4, 3, -1, 5, 4, 5};
      for (int score : sampleInput) {
         rawFeedbacks.add(score);
      }

      System.out.printf(">> Tổng số lượng phản hồi thu thập: %d%n", rawFeedbacks.size());

      // 2. Thống kê tần suất điểm hợp lệ (1-5) và bắt ngoại lệ cho điểm sai
      int[] frequency = new int[6]; // Bỏ qua index 0, dùng index 1..5
      int invalidCount = 0;

      for (int score : rawFeedbacks) {
         try {
            // Nếu score < 0 hoặc score > 5, truy xuất frequency[score] sẽ ném ngoại lệ
            if (score < 1 || score > 5) {
               throw new ArrayIndexOutOfBoundsException("Điểm không nằm trong thang 1-5: " + score);
            }
            ++frequency[score];
         }
         catch (ArrayIndexOutOfBoundsException e) {
            ++invalidCount;
            System.out.printf("[CẢNH BÁO BẢO VỆ DỮ LIỆU]: %s%n", e.getMessage());
         }
      }

      // 3. Trực quan hóa dữ liệu bằng biểu đồ cột (Bar Chart Visualization)
      System.out.println("\n----------------- BIỂU ĐỒ HÀI LÒNG KHÁCH HÀNG -----------------");
      for (int rating = 1; rating < frequency.length; ++rating) {
         System.out.printf("%2d sao (%3d lượt): %s%n", 
            rating, frequency[rating], "*".repeat(frequency[rating]));
      }
      System.out.printf("Số lượng phản hồi sai lệch bị cô lập: %d%n%n", invalidCount);

      // 4. Ma trận đánh giá đa chiều: 3 Chi nhánh x 3 Tiêu chí (Phục vụ, Món ăn, Không gian)
      System.out.println("----------------- ĐÁNH GIÁ CHI TIẾT CHI NHÁNH -----------------");
      String[] branches = {"Chi nhánh Ba Đình", "Chi nhánh Hoàn Kiếm", "Chi nhánh Cầu Giấy"};
      double[][] criteriaScores = {
         {4.5, 4.8, 4.2}, // Ba Đình
         {4.0, 4.1, 3.9}, // Hoàn Kiếm
         {4.8, 4.9, 4.7}  // Cầu Giấy
      };

      // Mảng lưu trữ điểm trung bình tổng hợp của từng chi nhánh
      double[] branchAverages = new double[branches.length];

      for (int i = 0; i < criteriaScores.length; ++i) {
         double sum = 0.0;
         // Duyệt từng cột tiêu chí của hàng hiện tại
         for (double score : criteriaScores[i]) {
            sum += score;
         }
         branchAverages[i] = sum / criteriaScores[i].length;
         System.out.printf("%-22s: Điểm trung bình = %.2f%n", branches[i], branchAverages[i]);
      }

      // 5. Tận dụng lớp tiện ích java.util.Arrays để phân tích bảng xếp hạng
      double[] sortedAverages = branchAverages.clone(); // Sao chép mảng
      Arrays.sort(sortedAverages); // Sắp xếp tăng dần

      System.out.printf("%n>> Điểm hiệu năng thấp nhất: %.2f%n", sortedAverages[0]);
      System.out.printf(">> Điểm hiệu năng cao nhất  : %.2f%n", sortedAverages[sortedAverages.length - 1]);

      // Kiểm tra sự tồn tại của mốc chuẩn 4.80 bằng tìm kiếm nhị phân
      int searchIndex = Arrays.binarySearch(sortedAverages, 4.80);
      if (searchIndex >= 0) {
         System.out.println(">> Đạt chuẩn chất lượng tối ưu (có chi nhánh đạt đúng mốc 4.80).");
      }
      else {
         System.out.println(">> Chưa có chi nhánh nào đạt chuẩn mốc 4.80.");
      }

      System.out.println("\nHoàn tất phân tích dữ liệu thành công!");
   }
}
