package week1.friday;

import java.util.Locale;
import java.util.Scanner;

/*
 * Nhập lần lượt tên (có thể có khoảng trắng), tuổi nguyên và chiều cao mét.
 * Dữ liệu thử: Quang Minh, tuổi 0 hoặc 25, chiều cao 1.705.
 * Chiều cao in với 2 chữ số thập phân: 1.71 m.
 *
 * nextInt() đọc số nhưng để lại ký tự xuống dòng.
 * Nếu cần đọc chuỗi bằng nextLine() ngay sau nextInt(), hãy gọi
 * input.nextLine(); một lần để bỏ phần còn lại của dòng chứa số,
 * rồi mới gọi input.nextLine() để đọc dòng văn bản tiếp theo.
 * Ở đây tên được đọc trước tuổi; nextDouble() sau nextInt() tự bỏ qua
 * khoảng trắng và xuống dòng nên không cần gọi nextLine() xen giữa.
 */
public class ProfileCard {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in).useLocale(Locale.ROOT);

        System.out.print("Nhập tên: ");
        String name = input.nextLine();
        System.out.print("Nhập tuổi: ");
        int age = input.nextInt();
        System.out.print("Nhập chiều cao (m, dùng dấu chấm thập phân): ");
        double height = input.nextDouble();

        System.out.println();
        System.out.println("Tên: " + name);
        System.out.println("Tuổi: " + age);
        System.out.printf(Locale.ROOT, "Chiều cao: %.2f m%n", height);
    }
}
