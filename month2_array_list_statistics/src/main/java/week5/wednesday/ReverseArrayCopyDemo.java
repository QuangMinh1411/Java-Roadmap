package week5.wednesday;

import java.util.Arrays;

/** Sao chép mảng int rồi đảo ngược bản sao, giữ nguyên mảng nguồn. */
public class ReverseArrayCopyDemo {
    public static void main(String[] args) {
        check("Ba phần tử", new int[]{1, 2, 3}, new int[]{3, 2, 1});
        check("Mảng rỗng", new int[]{}, new int[]{});
        check("Một phần tử", new int[]{7}, new int[]{7});
        System.out.println("Đạt 3/3 trường hợp.");
    }

    public static int[] reversedCopy(int[] source) {
        // Tạo mảng mới; nếu dùng int[] copy = source thì sẽ sửa cả mảng nguồn.
        int[] copy = Arrays.copyOf(source, source.length);

        // Hai chỉ số tiến vào giữa, hoán đổi các phần tử trên bản sao.
        // Với mảng rỗng hoặc một phần tử, điều kiện left < right luôn sai.
        for (int left = 0, right = copy.length - 1; left < right; left++, right--) {
            int temporary = copy[left];
            copy[left] = copy[right];
            copy[right] = temporary;
        }
        return copy;
    }

    private static void check(String name, int[] source, int[] expected) {
        int[] before = source.clone();
        int[] result = reversedCopy(source);
        boolean unchanged = Arrays.equals(source, before);

        // Kiểm tra cả nội dung lẫn việc kết quả là một đối tượng mảng riêng.
        if (!Arrays.equals(result, expected) || !unchanged || result == source) {
            throw new AssertionError(name + ": sao chép hoặc đảo ngược chưa đúng.");
        }

        System.out.println("PASS " + name + ": " + Arrays.toString(before)
                + " -> " + Arrays.toString(result));
        System.out.println("  Nguồn sau khi đảo: " + Arrays.toString(source)
                + "; giữ nguyên: " + unchanged);
    }
}
