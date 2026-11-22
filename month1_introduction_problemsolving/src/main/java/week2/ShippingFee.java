package week2;

/**
 * Bài tập minh hoạ: chuyển bảng quy tắc cước thành các nhánh không chồng lấn.
 * Vùng hợp lệ: "noi_vung" hoặc "ngoai_vung".
 *
 * Bảng quy tắc (kg phải là số hữu hạn và lớn hơn 0):
 * Vùng       | 0 < kg <= 2 | kg > 2
 * noi_vung   | 20000       | 20000 + ceil(kg - 2) * 5000
 * ngoai_vung | 35000       | 35000 + ceil(kg - 2) * 5000
 *
 * Chỉ làm tròn lên PHẦN vượt 2 kg. Ví dụ: 2.01 kg vượt 0.01 kg,
 * tính thêm 1 * 5000 đồng. Ngoại vùng cộng 15000 đồng đúng một lần.
 */
public class ShippingFee {
    public static int calculateFee(double kg, String region) {
        if (!Double.isFinite(kg) || kg <= 0) {
            throw new IllegalArgumentException("Khối lượng phải hữu hạn và lớn hơn 0 kg");
        }

        int surcharge;
        if ("noi_vung".equals(region)) {
            surcharge = 0;
        } else if ("ngoai_vung".equals(region)) {
            surcharge = 15000;
        } else {
            throw new IllegalArgumentException("Vùng không hợp lệ: " + region);
        }

        double fee;
        if (kg <= 2) { // Đã loại kg <= 0: nhánh này chỉ còn 0 < kg <= 2.
            fee = 20000;
        } else { // Nhánh còn lại chỉ có kg > 2, không trùng nhánh trên.
            fee = 20000 + Math.ceil(kg - 2) * 5000;
        }
        fee += surcharge;

        // Tránh tràn số khi chuyển cước về số nguyên đồng.
        if (fee > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Cước vượt phạm vi kiểu int");
        }
        return (int) fee;
    }

    public static void main(String[] args) {
        printFee(2, "noi_vung");      // 20000
        printFee(2.01, "noi_vung");   // 25000
        printFee(2, "ngoai_vung");    // 35000
        printFee(2.01, "ngoai_vung"); // 40000
        printFee(3, "noi_vung");      // 25000: vượt đúng 1 kg.
        printFee(3.01, "noi_vung");   // 30000: vượt 1.01 kg, làm tròn thành 2.
        printFee(0, "noi_vung");      // Bị từ chối.
        printFee(-1, "ngoai_vung");   // Bị từ chối.
        printFee(2, "vung_la");       // Bị từ chối.
    }

    private static void printFee(double kg, String region) {
        try {
            System.out.println(kg + " kg, " + region + ": "
                    + calculateFee(kg, region) + " đồng");
        } catch (IllegalArgumentException e) {
            System.out.println(kg + " kg, " + region + ": bị từ chối — " + e.getMessage());
        }
    }
}
