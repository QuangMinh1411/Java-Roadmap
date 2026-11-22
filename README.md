# Java Roadmap

Lộ trình tự học Java hướng tới phát triển **Java Backend**, gồm ghi chú lý thuyết, bài tập theo tuần, bài thực hành tìm lỗi và dự án console tổng hợp. Mục tiêu là rèn tư duy giải quyết vấn đề, viết hàm rõ trách nhiệm và kiểm tra kết quả bằng các ca dữ liệu cụ thể.

Repository hiện có bài tập Java nền tảng của tháng 1 và tài liệu mảng/`ArrayList` cho tháng 2. [ProjectJava.md](ProjectJava.md) mô tả mục tiêu capstone cuối lộ trình 12 tháng; phần backend trong tài liệu này là định hướng phát triển tiếp theo.

## Lộ trình học

| Giai đoạn | Nội dung | Thực hành / tài liệu hiện có |
| --- | --- | --- |
| Tháng 1 · Tuần 1 | Cấu trúc chương trình, kiểu dữ liệu, toán tử, nhập/xuất; làm quen Swing | Hello Java, đổi đơn vị, hóa đơn, tính toán, cửa sổ và sự kiện chuột |
| Tháng 1 · Tuần 2 | `if/else`, `switch`, biểu thức boolean, kiểm tra dữ liệu, vòng lặp cơ bản | Phân loại điểm/độ tuổi, năm nhuận, phí vận chuyển, máy tính an toàn |
| Tháng 1 · Tuần 3 | `for`, `while`, `do...while`, sentinel, thuật toán số học | Tổng chữ số, GCD Euclid, số nguyên tố, đoán số, menu và bài tập debugger |
| Tháng 1 · Tuần 4 | Phương thức `static`, nạp chồng, phạm vi biến, truyền tham trị, tách tính toán khỏi I/O | Đổi nhiệt độ, GCD/LCM, hóa đơn, số ngẫu nhiên, menu tổng hợp và kiểm thử |
| Dự án tháng 1 | Kết hợp kiến thức qua một ứng dụng console | [StudyTools CLI](month1_introduction_problemsolving/src/main/java/project/README.md): máy tính, đổi nhiệt độ, kiểm tra nguyên tố, thống kê điểm |
| Tháng 2 · Tuần 5 | Mảng một/hai chiều, mảng răng cưa, `ArrayList`, `Arrays`, thống kê | [Ghi chú và đề bài Arrays and ArrayLists](month2_array_list_statistics/src/main/java/week5/Arrays_and_ArrayLists.md); mã ví dụ hiện nằm trong tài liệu Markdown |

Hướng phát triển sau phần nền tảng: Java Core và cấu trúc dữ liệu/giải thuật → Spring và REST API → cơ sở dữ liệu, bảo mật, xử lý đồng thời → kiểm thử, Docker, CI/CD và capstone. Chi tiết yêu cầu capstone, ba đề tài gợi ý và tiêu chí đánh giá nằm trong [ProjectJava.md](ProjectJava.md).

Cách học mỗi chủ đề:

1. Đọc ghi chú trong thư mục tuần tương ứng.
2. Tự giải bài tập, ghi lại đầu vào và kết quả mong đợi.
3. Chạy với dữ liệu thường, dữ liệu biên và dữ liệu sai; sửa lỗi trước khi chuyển bài.
4. Tách logic thành phương thức để tái sử dụng trong bài tổng hợp.

## Cấu trúc repository

```text
Java-Roadmap/
├── README.md
├── ProjectJava.md                         # Đặc tả capstone cuối lộ trình
├── pom.xml                                # Maven parent, khai báo hai module
├── month1_introduction_problemsolving/
│   ├── pom.xml
│   ├── labs/                              # Lab lỗi Java, package và classpath
│   └── src/main/java/
│       ├── week1/
│       ├── week2/
│       ├── week3/
│       ├── week4/                         # Có README và bộ kiểm thử riêng
│       └── project/                       # StudyTools, demo, test và sơ đồ HTML
└── month2_array_list_statistics/
    ├── pom.xml
    └── src/main/java/week5/                # Tài liệu mảng và ArrayList
```

Một số thư mục tuần được chia tiếp theo ngày và nhóm bài (`jfp`, `LJ`). Tên package Java đi theo cấu trúc này và có phân biệt chữ hoa/thường.

## Môi trường

- **JDK 25**: `pom.xml` hiện đặt `maven.compiler.source` và `maven.compiler.target` là `25`. Cần cả `java` và `javac` trên `PATH`.
- **Maven** để biên dịch các module cùng lúc. Các lệnh bên dưới đã được kiểm tra với Maven 3.9.9 và JDK 25.0.2. Repository chưa có Maven Wrapper (`mvnw`).
- **Git** để clone; **Python 3** nếu chạy bộ kiểm thử console M01 bổ sung.
- IntelliJ IDEA có thể dùng để đọc mã, chạy từng `main()` và debug. Bài Swing cần môi trường có giao diện đồ họa.

Kiểm tra công cụ:

```sh
java -version
javac -version
mvn -version
```

Java được Maven sử dụng cũng cần là JDK 25. Yêu cầu Java 17/21 trong tài liệu capstone áp dụng cho dự án tương lai; cấu hình bài tập hiện tại dùng Java 25.

## Clone và chạy nhanh

Các lệnh trong README chạy từ thư mục gốc repository, trừ khi có ghi chú khác. Ví dụ dùng shell macOS/Linux hoặc Git Bash; trong PowerShell có thể chạy từng lệnh một và dùng cách chuyển hướng đầu vào phù hợp.

```sh
git clone https://github.com/QuangMinh1411/Java-Roadmap.git
cd Java-Roadmap
mvn compile
java -cp month1_introduction_problemsolving/target/classes project.StudyToolsCli
```

Lần build đầu Maven cần kết nối mạng để tải các plugin cần thiết. Các bài tập Java hiện dùng thư viện chuẩn của JDK.

### StudyTools CLI

| Lựa chọn | Chức năng |
| --- | --- |
| `1` | Bốn phép toán `+`, `-`, `*`, `/` |
| `2` | Đổi Celsius sang Fahrenheit |
| `3` | Kiểm tra số nguyên tố |
| `4` | Thống kê điểm trong `[0, 10]`; nhập `-1` để kết thúc nhóm điểm |
| `0` | Thoát |

Dùng dấu chấm cho số thập phân. Các giá trị cách nhau bằng khoảng trắng hoặc Enter. CLI hỏi lại khi token sai, chặn chia cho 0 và kết thúc khi hết dữ liệu đầu vào.

Ví dụ nhập `1 7 / 2 0`: chọn máy tính, tính `7 / 2`, rồi thoát; kết quả là `3.5000`.

Chạy kịch bản demo có sẵn trên macOS/Linux hoặc Git Bash:

```sh
java -cp month1_introduction_problemsolving/target/classes project.StudyToolsCli \
  < month1_introduction_problemsolving/src/main/java/project/demo-input.txt
```

Xem thêm [hướng dẫn StudyTools](month1_introduction_problemsolving/src/main/java/project/README.md), [bảng input/output](month1_introduction_problemsolving/src/main/java/project/INPUT_OUTPUT.md) và [sơ đồ luồng dữ liệu, business rule](month1_introduction_problemsolving/src/main/java/project/studytools.html). Để xem sơ đồ tương tác, mở file HTML đã clone bằng trình duyệt; trang xem mã trên GitHub không chạy sơ đồ.

### Chạy một bài tập bất kỳ

Sau `mvn compile`, dùng **tên lớp đầy đủ gồm package**:

```sh
java -cp month1_introduction_problemsolving/target/classes week1.monday.jfp.HelloJava
java -cp month1_introduction_problemsolving/target/classes week3.PrimeCounter
java -cp month1_introduction_problemsolving/target/classes week4.TemperatureConverter
java -cp month1_introduction_problemsolving/target/classes week4.M01Menu
```

Mỗi lệnh chạy một chương trình riêng; nếu bài có menu, thoát chương trình trước khi chạy lệnh tiếp theo. `week4.M01Menu` có thứ tự lựa chọn riêng, hãy đọc menu khi chạy.

Lấy dòng `package` trong file, ghép với tên lớp có `main()` để xác định tên chạy. Ví dụ `package week3;` và `class PrimeCounter` → `week3.PrimeCounter`. Các lớp chỉ chứa hàm hỗ trợ không có `main()` được gọi thông qua bài tập hoặc bộ kiểm thử.

### Biên dịch riêng, không cần Maven

Ví dụ biên dịch StudyTools cùng các lớp phụ thuộc:

```sh
javac -encoding UTF-8 -d month1_introduction_problemsolving/target/studytools \
  -sourcepath month1_introduction_problemsolving/src/main/java \
  month1_introduction_problemsolving/src/main/java/project/StudyToolsCli.java \
  month1_introduction_problemsolving/src/main/java/project/tests/StudyToolsCliTest.java

java -cp month1_introduction_problemsolving/target/studytools project.StudyToolsCli
java -cp month1_introduction_problemsolving/target/studytools project.tests.StudyToolsCliTest
```

`-sourcepath` giúp `javac` tìm các lớp `StudyTools`, `PrimeCounter` và `TemperatureConverter`. Các file `.class` sau biên dịch nằm trong `target/`, được Git bỏ qua.

### Chạy trong IntelliJ IDEA

1. Mở `pom.xml` ở thư mục gốc dưới dạng Maven project.
2. Đặt Project SDK và Maven Runner JRE là JDK 25; tải lại Maven project.
3. Mở lớp có `public static void main(String[] args)`, nhấn Run cạnh phương thức.
4. Nhập dữ liệu tại cửa sổ Run; dùng Debug để quan sát biến và vòng lặp.

## Kiểm thử

Các test Java hiện là chương trình có `main()` trong `src/main/java`, chưa tích hợp JUnit/Surefire. Vì vậy `mvn test` không tự chạy các bộ kiểm thử này; cần gọi trực tiếp sau `mvn compile`:

```sh
java -cp month1_introduction_problemsolving/target/classes project.tests.StudyToolsCliTest
java -cp month1_introduction_problemsolving/target/classes week4.tests.InvoiceDiscountTest
java -cp month1_introduction_problemsolving/target/classes week4.tests.GcdLcmTest
```

StudyTools kiểm tra 12 ca gồm phép tính, chia cho 0, token sai, nhiệt độ, nguyên tố, thống kê điểm và EOF. Bộ hóa đơn kiểm tra quy tắc giảm giá; GCD/LCM kiểm tra số 0, số âm và biên chống tràn. Test sai ném lỗi và trả mã thoát khác 0.

Bộ console M01 có thể chạy riêng bằng Python 3:

```sh
python3 month1_introduction_problemsolving/src/main/java/week4/tests/test_m01.py
```

Script biên dịch trong thư mục tạm, chạy 12 ca và ghi lại `week4/tests/results.txt`. Xem [README tuần 4](month1_introduction_problemsolving/src/main/java/week4/README.md) để biết thêm các ca kiểm thử và bài thực hành.

## Lab và xử lý lỗi thường gặp

- [Lab lỗi Java](month1_introduction_problemsolving/labs/java-errors/README.md) chứa cả mã cố ý sai để luyện phân biệt lỗi biên dịch và lỗi khi chạy. Thực hiện theo hướng dẫn riêng của lab.
- [Lab package/classpath](month1_introduction_problemsolving/labs/package-classpath/README.md) giải thích cách biên dịch và tìm lớp.
- `invalid target release: 25`: kiểm tra JDK mà `mvn -version` báo và chuyển sang JDK 25.
- `Could not find or load main class`: biên dịch trước, chạy tại thư mục gốc và kiểm tra đúng classpath/tên package.
- Không thấy điểm dừng khi chạy bài nhập liệu: chương trình đang chờ token; đọc prompt và nhập đúng kiểu dữ liệu.
