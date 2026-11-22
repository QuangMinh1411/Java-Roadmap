package week2.friday;

public class StringEquality {
    public static void main(String[] args) {
        String first = new String("Java");
        String second = new String("Java");

        // Lỗi: == so sánh tham chiếu; hai đối tượng được tạo riêng biệt.
        System.out.println("Sai: first == second = " + (first == second));
        // Sửa: equals so sánh nội dung chuỗi.
        System.out.println("Sửa: first.equals(second) = " + first.equals(second));
    }
}
