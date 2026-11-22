package week2.friday;

public class MissingParentheses {
    public static void main(String[] args) {
        boolean member = true;
        boolean student = false;
        int total = 90000;

        // Yêu cầu: là thành viên hoặc sinh viên, đồng thời tổng tiền >= 100000.
        // Lỗi: && ưu tiên hơn ||, nên thành viên bỏ qua điều kiện tổng tiền.
        boolean wrong = member || student && total >= 100000;
        // Sửa: nhóm hai đối tượng để tổng tiền luôn là điều kiện bắt buộc.
        boolean fixed = (member || student) && total >= 100000;

        System.out.println("Input: member = " + member + ", student = " + student
                + ", total = " + total);
        System.out.println("Sai: được giảm giá = " + wrong);
        System.out.println("Sửa: được giảm giá = " + fixed);
    }
}
