package week3.friday;

/** Ba lỗi thường gặp; xem README.md để đối chiếu trace và cách dùng debugger. */
public class LoopBugExamples {
    public static void main(String[] args) {
        int[] values = {2, 3};
        try {
            sumBefore(values);
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("sumBefore: " + exception.getClass().getSimpleName());
        }
        System.out.println("sumAfter=" + sumAfter(values));
        System.out.println("countAfter=" + countAfter(3));
        System.out.println("averageBefore=" + averageBefore(values));
        System.out.println("averageAfter=" + averageAfter(values));
    }

    static int sumBefore(int[] values) {
        int n = values.length;
        int sum = 0;
        // SAI: chỉ số hợp lệ là 0..n-1, i == n sẽ vượt mảng.
        for (int i = 0; i <= n; i++) {
            sum += values[i];
        }
        return sum;
    }

    static int sumAfter(int[] values) {
        int n = values.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += values[i];
        }
        return sum;
    }

    static int countBefore(int n) {
        int i = 0;
        int count = 0;
        while (i < n) {
            count++; // Đặt breakpoint ở đây; SAI: thiếu i++ nên i luôn bằng 0.
        }
        return count;
    }

    static int countAfter(int n) {
        int i = 0;
        int count = 0;
        while (i < n) {
            count++;
            i++;
        }
        return count;
    }

    static double averageBefore(int[] values) {
        int sum = 0;
        int count = 0;
        for (int value : values) {
            sum += value;
            count++;
        }
        if (count == 0) {
            throw new IllegalArgumentException("Mảng phải có ít nhất một phần tử");
        }
        double average = sum / count; // SAI: chia int trước, rồi mới đổi sang double.
        return average;
    }

    static double averageAfter(int[] values) {
        int sum = 0;
        int count = 0;
        for (int value : values) {
            sum += value;
            count++;
        }
        if (count == 0) {
            throw new IllegalArgumentException("Mảng phải có ít nhất một phần tử");
        }
        double average = (double) sum / count;
        return average;
    }
}
