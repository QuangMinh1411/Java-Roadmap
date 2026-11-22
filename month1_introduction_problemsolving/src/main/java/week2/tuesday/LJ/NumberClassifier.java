package week2.tuesday.LJ;

/*
 * Phân loại dấu và tính chẵn/lẻ độc lập.
 * Số 0 là "bằng 0, chẵn".
 * Dùng number % 2 == 0 để nhận biết số chẵn; còn lại là số lẻ,
 * vì số lẻ âm có thể có phần dư -1 khi chia cho 2.
 */
public class NumberClassifier {
    public static void main(String[] args) {
        int[] numbers = {-3, -2, 0, 1, 2, 7};

        for (int number : numbers) {
            printClassification(number);
        }
    }

    private static void printClassification(int number) {
        String sign;
        if (number < 0) {
            sign = "âm";
        } else if (number > 0) {
            sign = "dương";
        } else {
            sign = "bằng 0";
        }

        String parity;
        if (number % 2 == 0) {
            parity = "chẵn";
        } else {
            parity = "lẻ";
        }

        System.out.printf("%d: %s, %s%n", number, sign, parity);
    }
}
