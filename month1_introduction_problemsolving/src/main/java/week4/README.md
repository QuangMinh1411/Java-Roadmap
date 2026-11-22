# M01 — Chương trình console tổng hợp

Chương trình `week4.M01Menu` tích hợp 4 chức năng bằng các phương thức riêng:

| Menu | Chức năng | Dữ liệu |
| --- | --- | --- |
| 1 | Kiểm tra số nguyên tố | Số nguyên `int`; số nhỏ hơn 2 không nguyên tố |
| 2 | Đổi Celsius sang Fahrenheit | `F = C × 9.0 / 5.0 + 32` |
| 3 | Máy tính | Nhập số thứ nhất, phép toán `+ - * /`, số thứ hai |
| 4 | Thống kê điểm | Điểm trong `[0,10]`, `-1` kết thúc; in số lượng, trung bình, min, max |
| 0 | Thoát | Hoặc kết thúc khi hết dữ liệu đầu vào (EOF) |

Giữ nguyên mã menu 1, 2 của bản tuần 3. Mỗi thao tác xong đều quay lại menu.
Thống kê dùng biến cục bộ nên mỗi lần vào chức năng 4 sẽ bắt đầu danh sách mới.

## Quy ước nhập liệu

- Các token cách nhau bằng khoảng trắng hoặc xuống dòng. Dùng dấu chấm cho phần thập phân.
- Trước `nextInt()`/`nextDouble()` luôn kiểm tra `hasNextInt()`/`hasNextDouble()`.
  Token sai được bỏ qua bằng `next()` rồi yêu cầu nhập lại cùng trường dữ liệu.
- Số nguyên ngoài miền `int`, chữ thay số, `NaN` và vô cực đều bị từ chối.
- Phép toán sai được yêu cầu nhập lại. Chia cho `0` (kể cả `-0.0`) báo lỗi rồi về menu.
- Danh sách điểm rỗng nghĩa là nhập `-1` trước khi có điểm hợp lệ: báo chưa có điểm,
  không thực hiện phép chia. Dòng trắng chỉ là khoảng trắng phân tách của Scanner.
- EOF ở giữa một chức năng kết thúc chương trình, không in kết quả tính dở.
- Tính toán dùng `double`, kết quả hiển thị 2 chữ số thập phân; kết quả vô cực do tràn bị từ chối.

## Biên dịch và chạy từ thư mục mới

Cần JDK 17+ (`java`, `javac` trên PATH). Không cần Maven hoặc file `.class` có sẵn.
Các lệnh sau chạy được ở bất kỳ thư mục nào trên máy hiện tại; nếu chuyển máy,
thay `m01_source` bằng đường dẫn tới thư mục `week4` đã nhận.

```sh
m01_source='/Users/quangminh/Documents/Backend/Java/JavaRoadmap/month1_introduction_problemsolving/src/main/java/week4'
m01_work=$(mktemp -d /tmp/m01-submit.XXXXXX)
cp "$m01_source/M01Menu.java" "$m01_work/"
cp -R "$m01_source/tests" "$m01_work/tests"
cd "$m01_work"
javac --release 17 -encoding UTF-8 -d out M01Menu.java
java -Dfile.encoding=UTF-8 -cp out week4.M01Menu
```

Chạy tương tác rồi chọn `0` để thoát. Hoặc chạy luồng nhiều chức năng:

```sh
printf '1 17 2 25 3 9 / 3 4 6 8 -1 0\n' | java -Dfile.encoding=UTF-8 -cp out week4.M01Menu
```

Kết quả nghiệp vụ theo thứ tự: 17 nguyên tố; 25°C = 77°F; 9 / 3 = 3;
2 điểm có trung bình 7, thấp nhất 6, cao nhất 8; thoát.

## Bộ nộp 12 ca kiểm thử

Đầu vào và các thông báo mong đợi nằm trong [tests/cases.json](tests/cases.json).
Mỗi ca chạy một tiến trình Java mới; ca 05 và 12 kiểm tra nhiều thao tác trong cùng tiến trình.

| Ca | Nội dung |
| --- | --- |
| 01 | Số 1 không nguyên tố |
| 02 | Số 2 nguyên tố |
| 03 | Số chính phương 49 và số âm -7 |
| 04 | Nhiệt độ âm, bằng 0, thập phân |
| 05 | Cộng, trừ, nhân, chia liên tiếp |
| 06 | Chia 0 và -0.0, sau đó đổi nhiệt độ |
| 07 | Menu ngoài miền, chữ, số thực và tràn int |
| 08 | Chữ thay số ở cả 4 chức năng, phép toán sai, NaN/vô cực |
| 09 | Điểm rỗng, có dòng trắng trước sentinel -1 |
| 10 | Điểm 0, 10, thập phân và điểm ngoài miền |
| 11 | EOF khi đang nhập số thứ hai của phép tính |
| 12 | Cả 4 chức năng liên tiếp; mở lại thống kê để kiểm tra trạng thái được đặt lại |

Tại thư mục mới vừa tạo, chạy (cần Python 3):

```sh
python3 tests/test_m01.py
```

Script tự tạo thêm thư mục tạm, chỉ sao chép nguồn và bộ ca, biên dịch sạch rồi
kiểm tra mã thoát, stderr và các thông báo mong đợi theo thứ tự. Mỗi ca có giới hạn
thời gian để phát hiện vòng lặp kẹt. Báo cáo được ghi vào `tests/results.txt`
bên cạnh bộ ca đang chạy. Thành công khi in `12/12 PASS`.

## Hóa đơn nguyên đồng — InvoiceDiscount

`week4.InvoiceDiscount` là chương trình riêng. Nhập tổng tiền nguyên đồng trong
miền `int` (0..2147483647), sau đó trạng thái thành viên `true` hoặc `false`.
Chữ thay số, số lẻ đồng, tiền âm và trạng thái sai đều được yêu cầu nhập lại;
EOF kết thúc mà không in hóa đơn chưa đủ dữ liệu.

Quy tắc: tổng từ 1.000.000 đồng giảm 10%, dưới ngưỡng không giảm theo hóa đơn.
Thành viên giảm thêm 5% trên số tiền còn lại, kể cả khi dưới ngưỡng.
Giữ quy ước của `week2.InvoiceDiscount`: làm tròn **tiền còn lại sau mỗi bước**
đến đồng gần nhất, từ 0,5 đồng làm tròn lên. Tiền giảm bằng tiền trước trừ tiền sau.
Phép nhân trung gian dùng `long` để tránh tràn `int`, không dùng `double` tính tiền.

Các hàm tính toán không nhập/xuất và từ chối đối số âm bằng `IllegalArgumentException`:

- `calculateThresholdDiscount(total)`: tiền giảm theo ngưỡng.
- `calculateMemberDiscount(afterThreshold, member)`: tiền giảm thành viên,
  đầu vào là tiền còn lại sau bước giảm theo ngưỡng.
- `calculateTotalDiscount(total, member)`: tổng tiền giảm.
- `calculateInvoice(subtotal, member)`: hàm thuần trả số tiền cuối phải trả kiểu `int`,
  giữ nguyên quy tắc tuần 2; miền `subtotal` là `0..Integer.MAX_VALUE`.
- `calculatePayable(total, member)`: gọi lại `calculateInvoice` để giữ API cũ.

Nhập/xuất nằm riêng trong `readTotal`, `readMembership` và `printInvoice`;
`main` chỉ phối hợp các bước. Ví dụ thành viên có hóa đơn 1.000.000 đồng:
giảm ngưỡng 100.000, còn 900.000; giảm thành viên 45.000; phải trả **855.000** đồng.

`printInvoice` gọi `calculateInvoice` trước khi in bất kỳ dòng hóa đơn nào.
Tiền âm làm hàm tính ném ngoại lệ; chữ, số lẻ đồng và số vượt miền `int` bị hàm nhập
từ chối, không được thay thành 0. `run` chỉ in sau khi có đủ tiền và trạng thái
thành viên hợp lệ. Nhập sai rồi EOF sẽ không có tiêu đề hóa đơn hay dòng phải trả;
nhập lại đúng thì chỉ in hóa đơn cho dữ liệu đã sửa.

### 10 ca kiểm thử hóa đơn

Mỗi ca kiểm tra cả các hàm tính và hàm nhập/xuất. Các giá trị kỳ vọng được ghi cố định
trong [tests/InvoiceDiscountTest.java](tests/InvoiceDiscountTest.java), không tính lại
bằng công thức của chương trình. Khi nhập âm, hàm nhập yêu cầu nhập lại; các API tính
toán phải ném ngoại lệ. Mỗi ca còn kiểm tra chữ thay số và trạng thái thành viên sai.

| Ca | Tổng ban đầu | Thành viên | Giảm ngưỡng | Giảm thành viên | Phải trả |
| --- | ---: | --- | ---: | ---: | ---: |
| 01 | -1 | false | — | — | Từ chối |
| 02 | -1 | true | — | — | Từ chối |
| 03 | 0 | false | 0 | 0 | 0 |
| 04 | 0 | true | 0 | 0 | 0 |
| 05 | 999999 | false | 0 | 0 | 999999 |
| 06 | 999999 | true | 0 | 50000 | 949999 |
| 07 | 1000000 | false | 100000 | 0 | 900000 |
| 08 | 1000000 | true | 100000 | 45000 | **855000** |
| 09 | 1000001 | false | 100000 | 0 | 900001 |
| 10 | 1000001 | true | 100000 | 45000 | 855001 |

Biên dịch và chạy từ một thư mục mới, chỉ cần JDK 17+:

```sh
invoice_source='/Users/quangminh/Documents/Backend/Java/JavaRoadmap/month1_introduction_problemsolving/src/main/java/week4'
invoice_work=$(mktemp -d /tmp/invoice-submit.XXXXXX)
cp "$invoice_source/InvoiceDiscount.java" "$invoice_work/"
mkdir "$invoice_work/tests"
cp "$invoice_source/tests/InvoiceDiscountTest.java" "$invoice_work/tests/"
cd "$invoice_work"
javac --release 17 -encoding UTF-8 -d out InvoiceDiscount.java tests/InvoiceDiscountTest.java
printf '1000000 true\n' | java -Dfile.encoding=UTF-8 -cp out week4.InvoiceDiscount
java -Dfile.encoding=UTF-8 -cp out week4.tests.InvoiceDiscountTest
```

Lệnh cuối phải in `10/10 PASS`. Báo cáo lần chạy đã xác nhận nằm trong
[tests/invoice-results.txt](tests/invoice-results.txt).
Ngoài 10 ca tính toán (bao gồm `999999`/`1000000` × `true`/`false`), bộ kiểm thử
chạy thêm 6 luồng dữ liệu sai/thiếu để xác nhận không in hóa đơn, và 1 luồng nhập
lại đúng để xác nhận chỉ in một hóa đơn hợp lệ.

## GCD/LCM — GcdLcm

[GcdLcm.java](GcdLcm.java) cung cấp `gcd(int a, int b)` bằng thuật toán Euclid
và `lcm(int a, int b)` gọi lại `gcd`: `(long) (a / gcd(a, b)) * b` khi cả hai số dương.
Ví dụ `(12, 18)` cho GCD = **6**, LCM = **36**.

Miền đầu vào: **0..2147483647** cho mỗi đối số; số âm bị từ chối bằng
`IllegalArgumentException`. GCD trả `int`, LCM trả `long`.
Chia trước và ép sang `long` trước phép nhân; kết quả tối đa không vượt
`2147483647² = 4611686014132420609 < Long.MAX_VALUE`, nên không tràn trong miền này.

Quy ước số 0:

- `gcd(0, b) = b` với `b > 0`; `gcd(a, 0) = a` với `a > 0`.
- `gcd(0, 0)` không xác định và ném `IllegalArgumentException`, như bản tuần 3.
- `lcm(a, 0) = lcm(0, b) = 0`, kể cả `lcm(0, 0)`; xử lý trước phép chia.

Chạy từ thư mục `week4` (JDK 17+):

```sh
gcd_build=$(mktemp -d /tmp/gcd-lcm.XXXXXX)
javac --release 17 -encoding UTF-8 -d "$gcd_build" GcdLcm.java tests/GcdLcmTest.java
java -Dfile.encoding=UTF-8 -cp "$gcd_build" week4.GcdLcm
java -Dfile.encoding=UTF-8 -cp "$gcd_build" week4.tests.GcdLcmTest
```

Bộ kiểm thử bao phủ ví dụ 12/18, đảo đối số, từng đối số bằng 0, cả hai bằng 0,
số nguyên tố cùng nhau, hai số bằng nhau, số âm và biên `Integer.MAX_VALUE`.

## Đổi đơn vị — 4 hàm thuần

[UnitConverter.java](UnitConverter.java) chứa đúng 4 hàm chuyển đổi `static`:

| Hàm | Công thức | Hai ca tuần 1 (đầu vào → kết quả) |
| --- | --- | --- |
| `centimetersToMeters(cm)` | `cm / 100.0` | 0 → 0; 125 → 1.25 |
| `metersToCentimeters(m)` | `m * 100.0` | 0 → 0; 1.25 → 125 |
| `gramsToKilograms(g)` | `g / 1000.0` | 0 → 0; 2500 → 2.5 |
| `kilogramsToGrams(kg)` | `kg * 1000.0` | 0 → 0; 2.5 → 2500 |

Các hàm chỉ tính và trả `double`, không dùng `Scanner`, không in, không sửa trạng thái.
Giữ giả định tuần 1: đầu vào hữu hạn, không âm. Chiều nhân cần đầu vào không vượt
`Double.MAX_VALUE / hệ số` để kết quả hữu hạn. Đây là tiền điều kiện của người gọi,
không có kiểm tra hay làm tròn hiển thị trong hàm chuyển đổi; `double` có thể có sai số.

`week1.UnitConverter` giữ nguyên tên hàm và 8 ca ban đầu, gọi lại bản tuần 4.
Phần xuất và kiểm tra kỳ vọng nằm ở lớp tuần 1, báo `AssertionError` nếu kết quả
không hữu hạn hoặc sai quá dung sai `1e-12 * max(1, abs(expected))`.

Từ thư mục `week4`, biên dịch sạch và chạy lại đủ 8 ca:

```sh
unit_build=$(mktemp -d /tmp/unit-converter.XXXXXX)
javac --release 17 -encoding UTF-8 -d "$unit_build" UnitConverter.java ../week1/UnitConverter.java
java -Dfile.encoding=UTF-8 -cp "$unit_build" week1.UnitConverter
```

Kết quả phải có đúng 8 dòng `expected/actual` khớp nhau, mã thoát 0.
Báo cáo: [tests/unit-converter-results.txt](tests/unit-converter-results.txt).
