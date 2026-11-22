package week2;

/** Tính phí gửi xe theo số giờ nguyên. */
public class ParkingFee {
    public static int calculateFee(int hours) {
        if (hours <= 0) {
            throw new IllegalArgumentException("Số giờ phải lớn hơn 0");
        }

        if (hours <= 2) {
            return 10000; // 1–2 giờ
        } else if (hours <= 5) {
            return 20000; // 3–5 giờ
        } else {
            return 30000; // Trên 5 giờ
        }
    }

    public static void main(String[] args) {
        int[] testHours = {2, 3, 5, 6, 0, -1};

        for (int hours : testHours) {
            try {
                System.out.println(hours + " giờ: " + calculateFee(hours) + " đồng");
            } catch (IllegalArgumentException e) {
                System.out.println(hours + " giờ: bị từ chối — " + e.getMessage());
            }
        }
    }
}
