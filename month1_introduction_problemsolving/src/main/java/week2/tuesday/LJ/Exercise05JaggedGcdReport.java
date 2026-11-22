package week2.tuesday.LJ;

/*
 * BÀI 5 — NÂNG CAO: BÁO CÁO ƯỚC CHUNG LỚN NHẤT THEO HÀNG
 * Theo Java Language.md, mục 5.3–5.4, 6.2, 7.5 và 8.
 *
 * DỮ LIỆU MẪU
 * int[][] groups = {
 *     {48, 18, 30},
 *     {17, 13},
 *     {0, -6},
 *     {2701, 222},
 *     {},
 *     null,
 *     {12}
 * };
 *
 * YÊU CẦU
 * 1. Duyệt mảng ngoài bằng chỉ số, đánh số hàng từ 0. Mảng ngoài luôn
 *    khác null, nhưng có thể rỗng; mỗi hàng có thể null hoặc rỗng.
 *    Kiểm tra null trước khi truy cập length hoặc phần tử của hàng.
 * 2. Chỉ nhận số nguyên dương; dùng continue để bỏ qua số <= 0.
 *    Giả định mọi giá trị dương không vượt quá 10000.
 * 3. Với từng hàng, đếm số hợp lệ và tính UCLN của tất cả số hợp lệ.
 *    Lấy số hợp lệ đầu tiên làm kết quả tạm; sau đó ghép lần lượt
 *    UCLN(kết quả tạm, số tiếp theo). Hàng có một số có UCLN bằng chính nó.
 * 4. Tự viết thuật toán Euclid bằng phép trừ và while khi ghép hai số:
 *    chỉ thực hiện với hai giá trị dương. Không dùng BigInteger.gcd,
 *    phép % hay thư viện tính UCLN có sẵn.
 *    Có thể tách thành phương thức static int gcd(int a, int b).
 * 5. Hàng không có số hợp lệ (kể cả null/rỗng) in
 *    "Hang r: khong co du lieu hop le". Hàng còn lại in count và gcd.
 * 6. Cuối báo cáo in tổng số hàng có dữ liệu hợp lệ và tổng số phần tử
 *    hợp lệ. Không thay đổi dữ liệu trong groups khi tính toán.
 *
 * TỰ KIỂM TRA VỚI DỮ LIỆU MẪU
 * Hang 0: count = 3, gcd = 6
 * Hang 1: count = 2, gcd = 1
 * Hang 2: khong co du lieu hop le
 * Hang 3: count = 2, gcd = 37
 * Hang 4: khong co du lieu hop le
 * Hang 5: khong co du lieu hop le
 * Hang 6: count = 1, gcd = 12
 * Tổng: 4 hàng hợp lệ, 8 phần tử hợp lệ.
 * Thử thêm groups = {}: hai tổng đều bằng 0, không phát sinh exception.
 */
public class Exercise05JaggedGcdReport {
    public static void main(String[] args) {
        int[][] groups = {
                {48, 18, 30},
                {17, 13},
                {0, -6},
                {2701, 222},
                {},
                null,
                {12}
        };
        int validRows = 0;
        long validElements = 0;

        for (int row = 0; row < groups.length; row++) {
            int[] group = groups[row];
            if (group == null || group.length == 0) {
                System.out.printf("Hang %d: khong co du lieu hop le%n", row);
                continue;
            }

            int count = 0;
            int rowGcd = 0;
            for (int value : group) {
                if (value <= 0) {
                    continue;
                }

                if (count == 0) {
                    rowGcd = value;
                } else {
                    rowGcd = gcd(rowGcd, value);
                }
                count++;
            }

            if (count == 0) {
                System.out.printf("Hang %d: khong co du lieu hop le%n", row);
            } else {
                System.out.printf("Hang %d: count = %d, gcd = %d%n", row, count, rowGcd);
                validRows++;
                validElements += count;
            }
        }

        System.out.printf("Tong: %d hang hop le, %d phan tu hop le.%n",
                validRows, validElements);
    }

    /**
     * Tính UCLN bằng phép trừ lặp lại; hai đối số phải là số nguyên dương.
     * Các tham số là bản sao giá trị nên phép gán không sửa mảng bên gọi.
     */
    private static int gcd(int a, int b) {
        while (a != b) {
            if (a > b) {
                a -= b;
            } else {
                b -= a;
            }
        }
        return a;
    }
}
