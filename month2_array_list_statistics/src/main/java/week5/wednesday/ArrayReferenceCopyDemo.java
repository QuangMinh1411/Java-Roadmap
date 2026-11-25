package week5.wednesday;

import java.util.Arrays;

/** Phân biệt sao chép reference với sao chép các phần tử của mảng int. */
public class ArrayReferenceCopyDemo {
    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        int[] b = a; // Cùng trỏ đến mảng của a, không tạo mảng mới.
        int[] c = Arrays.copyOf(a, a.length); // Tạo mảng riêng, sao chép giá trị int.

        printArrays("Ban đầu", a, b, c);
        System.out.println("a == b: " + (a == b));
        System.out.println("a == c: " + (a == c));
        System.out.println("Arrays.equals(a, c): " + Arrays.equals(a, c));

        b[0] = 99; // Sửa mảng chung của a và b; c giữ nguyên.
        printArrays("Sau b[0] = 99", a, b, c);

        c[1] = 88; // Chỉ sửa mảng riêng của c.
        printArrays("Sau c[1] = 88", a, b, c);
    }

    private static void printArrays(String stage, int[] a, int[] b, int[] c) {
        System.out.println(stage + ":");
        System.out.println("a = " + Arrays.toString(a));
        System.out.println("b = " + Arrays.toString(b));
        System.out.println("c = " + Arrays.toString(c));
    }
}
