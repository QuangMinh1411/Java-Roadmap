# Thứ Sáu: tạo lỗi, debug và sửa

Bài bổ sung: [sửa đếm lùi và trace 3 bước](countdown-trace.md), code trong [CountdownExample.java](CountdownExample.java).

Ví dụ Java gồm ba lỗi độc lập trong `LoopBugExamples.java`.
Các hàm `Before` cố ý giữ lỗi để so sánh với hàm `After`.
Input nhỏ được gán ngay trong `main`: mảng `{2, 3}` và `n = 3`.
Trace dưới đây lấy từ debugger thực tế; dữ liệu gốc ở [debugger-trace.md](debugger-trace.md).

## 1. Duyệt `i <= n` thay vì `i < n`

Với `n = values.length = 2`, chỉ số hợp lệ là `0` và `1`.
Logpoint ở dòng 23 và 32 ghi trạng thái **trước** `sum += values[i]`.

| Bản | Lượt | i | n | sum trước cộng | Kết quả của lượt |
| --- | --- | --- | --- | --- | --- |
| Trước | 1 | 0 | 2 | 0 | Cộng 2, sum = 2 |
| Trước | 2 | 1 | 2 | 2 | Cộng 3, sum = 5 |
| Trước | 3 | 2 | 2 | 5 | `values[2]` gây `ArrayIndexOutOfBoundsException` |
| Sau | 1 | 0 | 2 | 0 | Cộng 2, sum = 2 |
| Sau | 2 | 1 | 2 | 2 | Cộng 3, sum = 5 |

Sau sửa, `i = 2` không vào thân vòng lặp; logpoint tại `return` ghi `sumAfter return=5`.

```java
// Trước
for (int i = 0; i <= n; i++) {
    sum += values[i];
}
// Sau
for (int i = 0; i < n; i++) {
    sum += values[i];
}
```

`<=` không luôn sai: nếu cần duyệt các số từ 1 đến n thì `i <= n` có thể đúng.
Ở đây lỗi xuất phát từ chỉ số mảng bắt đầu tại 0.

## 2. Quên tăng biến

Input `n = 3`. Đặt breakpoint có suspend tại dòng 41, trước `count++`.
Đã Debug `MissingIncrementBefore.main()`, kiểm tra stack/biến, Resume hai lần rồi Stop.

| Bản | Lần dừng / lượt | i | n | count trước `count++` |
| --- | --- | --- | --- | --- |
| Trước | Breakpoint 1 | 0 | 3 | 0 |
| Trước | Breakpoint 2 | 0 | 3 | 1 |
| Trước | Breakpoint 3 → Stop | 0 | 3 | 2 |
| Sau | Lượt 1 | 0 | 3 | 0 |
| Sau | Lượt 2 | 1 | 3 | 1 |
| Sau | Lượt 3 | 2 | 3 | 2 |
| Sau | Thoát vòng lặp | 3 | 3 | 3 |

Ở bản sai, `count` tăng nhưng `i` luôn là 0, nên `i < 3` luôn đúng.
Stack thực tế: `MissingIncrementBefore.main:6 → LoopBugExamples.countBefore:41`.
Ở bản sửa, logpoint tại dòng 50 ghi từng lượt và dòng 53 xác nhận thoát với `i=3, count=3`.

```java
// Trước
while (i < n) {
    count++;
}
// Sau
while (i < n) {
    count++;
    i++;
}
```

## 3. Tổng/count chia nguyên

Input `{2, 3}`. Cả `sum` và `count` đều là `int`.
Logpoint ở dòng 61/75 nằm sau cộng `sum`, trước tăng `count`.

| Bản | value | sum sau cộng | count trước tăng |
| --- | --- | --- | --- |
| Trước | 2 | 2 | 0 |
| Trước | 3 | 5 | 1 |
| Sau | 2 | 2 | 0 |
| Sau | 3 | 5 | 1 |

Khi chia, cả hai bản đều có `sum=5, count=2`:

| Bản | Phép tính | average thực tế (logpoint dòng 67/81) |
| --- | --- | --- |
| Trước | `double average = sum / count;` | `2.0` |
| Sau | `double average = (double) sum / count;` | `2.5` |

`5 / 2` chia nguyên được 2, rồi gán sang `double` thành 2.0.
Ép kiểu **trước phép chia** tạo phép chia thực. `(double) (sum / count)` vẫn mất phần lẻ.
Cả hai hàm kiểm tra `count == 0` trước khi chia và báo lỗi nếu mảng rỗng.

## Chạy lại

Từ thư mục gốc dự án, dùng JDK 25 theo cấu hình Maven:

```sh
mkdir -p /tmp/java-roadmap-friday
javac -g -encoding UTF-8 -d /tmp/java-roadmap-friday month1_introduction_problemsolving/src/main/java/week3/friday/*.java
java -cp /tmp/java-roadmap-friday week3.friday.LoopBugExamples
```

Kết quả đã chạy xác nhận (exit code 0):

```text
sumBefore: ArrayIndexOutOfBoundsException
sumAfter=5
countAfter=3
averageBefore=2.0
averageAfter=2.5
```

Để tái hiện lỗi quên tăng biến trong IntelliJ:

1. Đặt breakpoint tại dòng 41 của `LoopBugExamples.java`, chọn Suspend: All.
2. Chọn **Debug** cho `MissingIncrementBefore.main()`.
3. Xem `i`, `n`, `count` trong Variables ở lần dừng đầu tiên.
4. Resume hai lần để thấy `i` không đổi, `count` tăng; sau đó bấm **Stop** ngay.
5. Chạy `LoopBugExamples.main()` để xem bản sửa kết thúc với `countAfter=3`.

Điểm chạy thông thường `LoopBugExamples.main()` chỉ gọi bản đếm đã sửa.
`MissingIncrementBefore` dành riêng cho bước debug với breakpoint đã đặt sẵn.
