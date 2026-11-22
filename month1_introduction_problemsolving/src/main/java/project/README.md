# StudyTools CLI — sản phẩm tháng 1

Menu gồm bốn phép toán, đổi C → F, kiểm tra số nguyên tố và thống kê điểm.
Chọn `0` tại menu để thoát. Không cần thư viện ngoài; dùng JDK 25 theo cấu hình
repository. Đã biên dịch và chạy bằng JDK 25.0.2.

## Biên dịch và chạy

Chạy các lệnh sau tại thư mục gốc `JavaRoadmap`:

```sh
javac -encoding UTF-8 -d month1_introduction_problemsolving/target/studytools \
  -sourcepath month1_introduction_problemsolving/src/main/java \
  month1_introduction_problemsolving/src/main/java/project/StudyToolsCli.java \
  month1_introduction_problemsolving/src/main/java/project/tests/StudyToolsCliTest.java

java -cp month1_introduction_problemsolving/target/studytools project.StudyToolsCli
```

`-sourcepath` giúp javac tìm và biên dịch cả `StudyTools`, `PrimeCounter` và
`TemperatureConverter`. Trong IntelliJ có thể chạy `StudyToolsCli.main()`.
Nhập số thập phân bằng dấu chấm; mỗi giá trị cách nhau bằng khoảng trắng hoặc Enter.
Một Scanner được dùng xuyên suốt, không trộn `nextLine()` với `nextInt()`.
Token sai bị tiêu thụ trước khi hỏi lại, nên không mắc vòng lặp ở token cũ.

## Input/output và cấu trúc

[INPUT_OUTPUT.md](INPUT_OUTPUT.md) là bảng chốt giao diện trước khi cài đặt.

- `StudyToolsCli`: một Scanner, menu `do...while`, đọc/kiểm tra kiểu đầu vào,
  điều phối bốn chức năng, tích lũy điểm bằng vòng lặp và in kết quả.
- `StudyTools`: sáu phương thức không có I/O: `isValidOperator`, `calculate`,
  `celsiusToFahrenheit`, `isPrime`, `isValidGrade`, `average`.
- Điểm được cộng/đếm và cập nhật min/max ngay khi nhập hợp lệ; không lưu danh sách.
  Mỗi lượt chọn thống kê bắt đầu nhóm mới. `-1` chỉ kết thúc nhóm và không được tính.
  Không có điểm thì không tính trung bình. Trung bình hiển thị 4 chữ số thập phân.
- `calculate` bảo vệ phép chia cho 0 và toán tử sai cả khi gọi trực tiếp;
  tầng menu kiểm tra trước để thông báo và tiếp tục phiên nhập.

| Nguồn bài cũ | Cách tái sử dụng |
|---|---|
| Tuần 1: `week1/wednesday/LJ/Exercise04ArrayStatistics.java` | Cách cộng tổng và cập nhật min/max, chuyển sang xử lý từng điểm |
| Tuần 2: `week2/tuesday/LJ/Exercise02SafeCalculator.java` | Rẽ nhánh phép toán và chặn chia 0; thu gọn còn `+ - * /` |
| Tuần 2: `week2/monday/jfp/Exercise03SentinelStatistics.java` | Vòng lặp sentinel, bỏ điểm sai; đổi miền điểm thành `[0,10]` |
| Tuần 3: `week3/PrimeCounter.java` | Gọi trực tiếp `isPrime`, thử ước với `divisor <= n / divisor` để tránh tràn int |
| Tuần 4: `week4/TemperatureConverter.java` | Gọi trực tiếp `toFahrenheit` |
| Tuần 4: `week4/M01Menu.java` | Tái sử dụng khung menu và cách đọc token an toàn, sắp lại lựa chọn theo đề |

## Chạy lại 12 ca kiểm thử

Sau bước biên dịch trên:

```sh
java -cp month1_introduction_problemsolving/target/studytools project.tests.StudyToolsCliTest
```

[TEST_RESULTS.md](TEST_RESULTS.md) ghi input, expected và actual trích từ stdout thật.
Test chạy `main()` với đầu vào cố định, kiểm tra kết quả theo thứ tự và cách kết thúc;
không cần bật `-ea`. Sai kết quả sẽ ném `AssertionError` và trả exit code khác 0.
Để tạo lại báo cáo:

```sh
java -cp month1_introduction_problemsolving/target/studytools project.tests.StudyToolsCliTest \
  > month1_introduction_problemsolving/src/main/java/project/TEST_RESULTS.md
```

## Kịch bản demo 5 phút

| Thời gian | Thao tác / nội dung trình bày |
|---|---|
| 0:00–0:40 | Giới thiệu bảng input/output, bốn lựa chọn, miền điểm và sentinel; biên dịch rồi chạy CLI |
| 0:40–1:30 | Nhập `1 7 / 2` → 3.5000; `1 7 / 0` → báo lỗi và trở về menu |
| 1:30–2:10 | Nhập `2 0` → 32°F; `3 1` → không nguyên tố; `3 2` → nguyên tố |
| 2:10–3:10 | Nhập `4 2 3 11 abc 5 -1` → báo hai lỗi, count=3, min=2, max=5, average=3.3333 |
| 3:10–3:50 | Nhập `abc 9` tại menu → báo sai và vẫn tiếp tục; `4 -1` → chưa có điểm; `0` → thoát |
| 3:50–4:30 | Mở hai lớp: chỉ menu giữ Scanner/in kết quả, chỉ ra sáu hàm và hai lời gọi lại module cũ |
| 4:30–5:00 | Chạy test, xem 12/12 PASS và bảng expected/actual, đối chiếu các tiêu chí nghiệm thu |

Có thể chạy luồng demo bằng file có sẵn (không có khoảng dừng để thuyết trình):

```sh
java -cp month1_introduction_problemsolving/target/studytools project.StudyToolsCli \
  < month1_introduction_problemsolving/src/main/java/project/demo-input.txt
```
