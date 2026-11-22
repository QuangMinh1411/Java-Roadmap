package week1.wednesday.LJ;

import java.util.Scanner;

/*
 * Nhập tổng số giây nguyên không âm (trong miền long).
 * In giờ:phút:giây, không thêm số 0 ở đầu và không giới hạn giờ ở 23.
 * Ví dụ: 0 -> 0:0:0; 59 -> 0:0:59; 60 -> 0:1:0; 3661 -> 1:1:1.
 */
public class SecondsConverter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long totalSeconds = input.nextLong();

        if (totalSeconds < 0) {
            System.out.println("So giay phai khong am.");
            return;
        }

        long hours = totalSeconds / 3600;
        // Lấy phần dư để bỏ các giờ đã tính, rồi đổi giây còn lại ra phút.
        // Nếu dùng totalSeconds / 60, ta nhận tổng số phút, có thể lớn hơn 59.
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;

        System.out.printf("%d:%d:%d%n", hours, minutes, seconds);
    }
}
