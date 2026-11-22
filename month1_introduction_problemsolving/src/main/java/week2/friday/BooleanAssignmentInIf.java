package week2.friday;

public class BooleanAssignmentInIf {
    public static void main(String[] args) {
        boolean ready = true;
        // Lỗi: phép gán trả về false, đồng thời đổi giá trị ready.
        if (ready = false) {
            System.out.println("Sai: vào if");
        } else {
            System.out.println("Sai: vào else, ready = " + ready);
        }

        ready = true;
        // Sửa: kiểm tra trạng thái mà không thay đổi ready.
        if (!ready) {
            System.out.println("Sửa: vào if");
        } else {
            System.out.println("Sửa: vào else, ready = " + ready);
        }
    }
}
