package week2.friday;

public class DoubleComparison {
    public static void main(String[] args) {
        double actual = 0.1 + 0.2;
        double expected = 0.3;
        final double EPSILON = 1e-9;

        System.out.println("0.1 + 0.2 = " + actual);
        // Lỗi: yêu cầu bằng chính xác dù phép tính double có sai số biểu diễn.
        System.out.println("Sai: actual == expected = " + (actual == expected));
        // Sửa: chấp nhận chênh lệch tuyệt đối nhỏ hơn 1e-9.
        System.out.println("Sửa: Math.abs(actual - expected) < 1e-9 = "
                + (Math.abs(actual - expected) < EPSILON));
    }
}
