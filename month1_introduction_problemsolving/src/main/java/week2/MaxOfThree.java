package week2;

/** Tìm giá trị lớn nhất của ba số nguyên bằng if, không sắp xếp. */
public class MaxOfThree {
    public static int maxOfThree(int a, int b, int c) {
        int max = a;

        if (b > max) {
            max = b;
        }
        if (c > max) {
            max = c;
        }

        // Khi hòa, điều kiện > là false: giữ nguyên max hiện tại.
        // Sau hai lần so sánh, max là giá trị lớn nhất của a, b, c.
        return max;
    }

    public static void main(String[] args) {
        checkMax(3, 3, 1, 3);
        checkMax(-5, -2, -9, -2);
        checkMax(0, 0, 0, 0);
    }

    private static void checkMax(int a, int b, int c, int expected) {
        int actual = maxOfThree(a, b, c);
        if (actual != expected) {
            throw new AssertionError("Mong đợi " + expected + ", thực tế " + actual);
        }
        System.out.printf("(%d, %d, %d) -> %d (PASS)%n", a, b, c, actual);
    }
}
