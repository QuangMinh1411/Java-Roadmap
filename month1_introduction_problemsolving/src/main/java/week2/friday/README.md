# 5 lỗi logic Java

Mỗi lớp có `main()` chạy đoạn lỗi trước, rồi chạy bản sửa. Các đoạn lỗi đều
biên dịch được nhưng cho kết quả không đúng với ý định.

## Dự đoán trước khi chạy

Đây là các **dự đoán sai minh họa của người mới học**, dùng để đối chiếu khi chạy:

| Lớp | Dự đoán cho đoạn lỗi | Kết quả đúng mong muốn sau khi sửa |
| --- | --- | --- |
| `BooleanAssignmentInIf` | `ready = false` chỉ kiểm tra nên `ready` vẫn là `true`. | Vào `else`, giữ `ready = true`. |
| `StringEquality` | Hai chuỗi cùng nội dung nên `first == second` là `true`. | So nội dung bằng `equals` được `true`. |
| `MissingParentheses` | Thành viên có tổng tiền 90000 không được giảm giá, kết quả `false`. | Luôn phải có tổng tiền >= 100000, kết quả `false`. |
| `VariableShadowing` | Gọi `setScoreWrong(8)` sẽ đổi trường `score` thành `8`. | Trường `score` được cập nhật thành `8`. |
| `DoubleComparison` | `0.1 + 0.2 == 0.3` là `true`. | Chênh lệch nhỏ hơn `1e-9`, kết quả `true`. |

## Output thực tế và bản sửa

### 1. Gán boolean trong `if`

```text
Sai: vào else, ready = false
Sửa: vào else, ready = true
```

`if (ready = false)` gán `false` cho `ready`, rồi dùng giá trị vừa gán làm điều
kiện. Dùng `if (!ready)` để kiểm tra `ready` có bằng `false` hay không.

### 2. So sánh String bằng `==`

```text
Sai: first == second = false
Sửa: first.equals(second) = true
```

`==` so sánh tham chiếu, `equals` so sánh nội dung. Ví dụ cố ý dùng hai lần
`new String("Java")` để tạo hai đối tượng khác nhau; nếu chỉ dùng literal,
String pool có thể khiến `==` cho `true` và che giấu lỗi.

### 3. Thiếu ngoặc ở biểu thức hỗn hợp

```text
Input: member = true, student = false, total = 90000
Sai: được giảm giá = true
Sửa: được giảm giá = false
```

Quy tắc mong muốn: là thành viên hoặc sinh viên, đồng thời tổng tiền phải
ít nhất 100000. Input phân biệt hai phiên bản:
`member = true`, `student = false`, `total = 90000`.

- Sai: `member || student && total >= 100000` được hiểu là
  `member || (student && total >= 100000)`, vì `&&` ưu tiên hơn `||`.
  `member = true` khiến kết quả là `true` dù tổng tiền chưa đạt ngưỡng.
- Sửa: `(member || student) && total >= 100000` cho kết quả `false`.

Hai phiên bản khác nhau khi `member = true` và `total < 100000`, bất kể
giá trị của `student`.

### 4. Biến che khuất

```text
Sai: score = 0
Sửa: score = 8
```

Tham số `score` che khuất trường cùng tên. `score = score` chỉ tự gán tham số,
không cập nhật trường. Sửa thành `this.score = score`.

### 5. So sánh `0.1 + 0.2` với `0.3`

```text
0.1 + 0.2 = 0.30000000000000004
Sai: actual == expected = false
Sửa: Math.abs(actual - expected) < 1e-9 = true
```

`double` biểu diễn số theo hệ nhị phân nên không lưu chính xác các số thập phân
này. Bản sửa dùng `Math.abs(actual - expected) < EPSILON` với
`final double EPSILON = 1e-9` để so sánh với sai số tuyệt đối trong ví dụ này.

## Cách chạy

Từ thư mục `month1_introduction_problemsolving`:

```sh
mkdir -p target/friday
javac -encoding UTF-8 -d target/friday src/main/java/week2/friday/*.java
for example in BooleanAssignmentInIf StringEquality MissingParentheses VariableShadowing DoubleComparison; do
  java -cp target/friday week2.friday.$example
done
```
