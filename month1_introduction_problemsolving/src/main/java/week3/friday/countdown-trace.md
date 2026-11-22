# Sửa vòng lặp đếm lùi

Bản sai: `for (int i = 10; i >= 0; i++)`.
Bản sửa trong [CountdownExample.java](CountdownExample.java):

```java
for (int i = 10; i >= 0; i--) {
    System.out.println(i);
}
```

## Trace 3 bước

Trạng thái tại dòng 7, trước khi in; cập nhật i diễn ra sau thân vòng lặp.

| Bước | i bản đầu | i >= 0 | Sau i++ | i bản sửa | i >= 0 | Sau i-- |
| --- | --- | --- | --- | --- | --- | --- |
| 1 | 10 | true | 11 | 10 | true | 9 |
| 2 | 11 | true | 12 | 9 | true | 8 |
| 3 | 12 | true | 13 | 8 | true | 7 |

Các giá trị i ở đầu bước được debugger quan sát thực tế; cột cập nhật mô tả phép tính tiếp theo. Bản đầu đã được Stop ngay tại lần dừng thứ ba (i=12), trước khi in 12 và tăng thành 13.

## Vì sao bản đầu sai?

Điều kiện tiếp tục là i >= 0, nên muốn thoát thì i phải nhỏ hơn 0.
Từ 10, i++ tạo 11, 12, 13..., đưa i xa mốc -1 hơn; không tiến gần điều kiện dừng.
i-- tạo 9, 8, 7..., đến 0 vẫn in, sau đó giảm thành -1 và điều kiện i >= 0 sai.
Bản sửa chạy đúng 11 lượt, in từ 10 đến 0 rồi thoát.

Chính xác với Java int: bản đầu cuối cùng có thể thoát khi tăng quá Integer.MAX_VALUE và tràn thành Integer.MIN_VALUE. Đó là tràn số sau rất nhiều lượt, không phải đếm lùi đúng; không chờ hiện tượng này để chẩn đoán.

## Bằng chứng debugger thực tế

Thu ngày 2026-11-11 bằng IntelliJ debugger theo skill ij-debugger.
- Session 435: breakpoint Suspend All tại CountdownBefore.java:7; kiểm tra stack và biến, Resume hai lần rồi Stop. i lần lượt 10, 11, 12.
- Session 461: logpoint tại CountdownExample.java:7; chạy kết thúc exit code 0, không có lỗi breakpoint. i lần lượt 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0.
- Đã gỡ hai breakpoint của bài sau khi thu trace.

Để chạy lại bản sai, đặt breakpoint dòng 7 trước khi chọn Debug cho CountdownBefore.main(); Resume hai lần rồi Stop. Chạy CountdownExample.main() để xem bản sửa.

### Stack bản sai

```text
{"frames":[{"presentation":"main:7, CountdownBefore (week3.friday)","index":0,"file":"file:///Users/quangminh/Documents/Backend/Java/JavaRoadmap/month1_introduction_problemsolving/src/main/java/week3/friday/CountdownBefore.java","line":7,"isCurrent":true}],"threadId":"\"main\"@1 in group \"main\": RUNNING","totalFrames":1}
```

### Bước 1

```text
├─ args = [] {String[0]@1123}
└─ i = 10

```

### Bước 2

```text
{"status":"paused","sessionId":"435","newPosition":{"filePath":"file:///Users/quangminh/Documents/Backend/Java/JavaRoadmap/month1_introduction_problemsolving/src/main/java/week3/friday/CountdownBefore.java","line":7},"frameValues":"├─ args = [] {String[0]@1123}\n└─ i = 11\n","breakpointErrorsTail":[]}
```

### Bước 3

```text
{"status":"paused","sessionId":"435","newPosition":{"filePath":"file:///Users/quangminh/Documents/Backend/Java/JavaRoadmap/month1_introduction_problemsolving/src/main/java/week3/friday/CountdownBefore.java","line":7},"frameValues":"├─ args = [] {String[0]@1123}\n└─ i = 12\n","breakpointErrorsTail":[]}
```

### Stop bản sai

```text
{"status":"stopped","sessionId":"435","breakpointErrorsTail":[]}
```

### Logpoint bản sửa

```text
{"status":"stopped","sessionId":"461","message":"The session is already stopped.","exitCode":0,"breakpointErrorsTail":[],"tracepointOutputsTail":[{"type":"TRACEPOINT_OUTPUT","timestampMs":1794380601178,"timestampIso":"2026-11-11T07:03:21.178Z","sessionId":"461","message":"i=10\n","breakpointId":"bp#13","filePath":"file:///Users/quangminh/Documents/Backend/Java/JavaRoadmap/month1_introduction_problemsolving/src/main/java/week3/friday/CountdownExample.java","line":7},{"type":"TRACEPOINT_OUTPUT","timestampMs":1794380601190,"timestampIso":"2026-11-11T07:03:21.190Z","sessionId":"461","message":"i=9\n","breakpointId":"bp#13","filePath":"file:///Users/quangminh/Documents/Backend/Java/JavaRoadmap/month1_introduction_problemsolving/src/main/java/week3/friday/CountdownExample.java","line":7},{"type":"TRACEPOINT_OUTPUT","timestampMs":1794380601192,"timestampIso":"2026-11-11T07:03:21.192Z","sessionId":"461","message":"i=8\n","breakpointId":"bp#13","filePath":"file:///Users/quangminh/Documents/Backend/Java/JavaRoadmap/month1_introduction_problemsolving/src/main/java/week3/friday/CountdownExample.java","line":7},{"type":"TRACEPOINT_OUTPUT","timestampMs":1794380601194,"timestampIso":"2026-11-11T07:03:21.194Z","sessionId":"461","message":"i=7\n","breakpointId":"bp#13","filePath":"file:///Users/quangminh/Documents/Backend/Java/JavaRoadmap/month1_introduction_problemsolving/src/main/java/week3/friday/CountdownExample.java","line":7},{"type":"TRACEPOINT_OUTPUT","timestampMs":1794380601195,"timestampIso":"2026-11-11T07:03:21.195Z","sessionId":"461","message":"i=6\n","breakpointId":"bp#13","filePath":"file:///Users/quangminh/Documents/Backend/Java/JavaRoadmap/month1_introduction_problemsolving/src/main/java/week3/friday/CountdownExample.java","line":7},{"type":"TRACEPOINT_OUTPUT","timestampMs":1794380601197,"timestampIso":"2026-11-11T07:03:21.197Z","sessionId":"461","message":"i=5\n","breakpointId":"bp#13","filePath":"file:///Users/quangminh/Documents/Backend/Java/JavaRoadmap/month1_introduction_problemsolving/src/main/java/week3/friday/CountdownExample.java","line":7},{"type":"TRACEPOINT_OUTPUT","timestampMs":1794380601198,"timestampIso":"2026-11-11T07:03:21.198Z","sessionId":"461","message":"i=4\n","breakpointId":"bp#13","filePath":"file:///Users/quangminh/Documents/Backend/Java/JavaRoadmap/month1_introduction_problemsolving/src/main/java/week3/friday/CountdownExample.java","line":7},{"type":"TRACEPOINT_OUTPUT","timestampMs":1794380601199,"timestampIso":"2026-11-11T07:03:21.199Z","sessionId":"461","message":"i=3\n","breakpointId":"bp#13","filePath":"file:///Users/quangminh/Documents/Backend/Java/JavaRoadmap/month1_introduction_problemsolving/src/main/java/week3/friday/CountdownExample.java","line":7},{"type":"TRACEPOINT_OUTPUT","timestampMs":1794380601200,"timestampIso":"2026-11-11T07:03:21.200Z","sessionId":"461","message":"i=2\n","breakpointId":"bp#13","filePath":"file:///Users/quangminh/Documents/Backend/Java/JavaRoadmap/month1_introduction_problemsolving/src/main/java/week3/friday/CountdownExample.java","line":7},{"type":"TRACEPOINT_OUTPUT","timestampMs":1794380601201,"timestampIso":"2026-11-11T07:03:21.201Z","sessionId":"461","message":"i=1\n","breakpointId":"bp#13","filePath":"file:///Users/quangminh/Documents/Backend/Java/JavaRoadmap/month1_introduction_problemsolving/src/main/java/week3/friday/CountdownExample.java","line":7},{"type":"TRACEPOINT_OUTPUT","timestampMs":1794380601203,"timestampIso":"2026-11-11T07:03:21.203Z","sessionId":"461","message":"i=0\n","breakpointId":"bp#13","filePath":"file:///Users/quangminh/Documents/Backend/Java/JavaRoadmap/month1_introduction_problemsolving/src/main/java/week3/friday/CountdownExample.java","line":7}]}
```

