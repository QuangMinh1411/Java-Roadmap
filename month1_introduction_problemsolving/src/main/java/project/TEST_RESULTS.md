# Kết quả kiểm thử StudyTools CLI

Chạy bằng Java 25.0.2. Input là các token theo thứ tự; actual trích từ stdout thật.

| Ca | Input | Expected | Actual | Kết quả |
|---|---|---|---|---|
| 01 — Bốn phép toán | `1 7 + 2 1 7 - 2 1 7 * 2 1 7 / 2 0` | 7.0000 + 2.0000 = 9.0000<br>7.0000 - 2.0000 = 5.0000<br>7.0000 * 2.0000 = 14.0000<br>7.0000 / 2.0000 = 3.5000 | 7.0000 + 2.0000 = 9.0000<br>7.0000 - 2.0000 = 5.0000<br>7.0000 * 2.0000 = 14.0000<br>7.0000 / 2.0000 = 3.5000 | PASS |
| 02 — Chia 0 rồi tiếp tục | `1 7 / 0 1 7 / -0.0 2 0 0` | Không thể chia cho 0.<br>Không thể chia cho 0.<br>0.00°C = 32.00°F | Không thể chia cho 0.<br>Không thể chia cho 0.<br>0.00°C = 32.00°F | PASS |
| 03 — Sai số và toán tử | `1 abc 7 ^ / xyz 2 0` | Phải nhập số; bỏ qua: abc<br>Phép toán không hợp lệ.<br>Phải nhập số; bỏ qua: xyz<br>7.0000 / 2.0000 = 3.5000 | Phải nhập số; bỏ qua: abc<br>Phép toán không hợp lệ.<br>Phải nhập số; bỏ qua: xyz<br>7.0000 / 2.0000 = 3.5000 | PASS |
| 04 — Nhiệt độ | `2 0 2 -40 0` | 0.00°C = 32.00°F<br>-40.00°C = -40.00°F | 0.00°C = 32.00°F<br>-40.00°C = -40.00°F | PASS |
| 05 — Số nguyên tố và biên int | `3 1 3 2 3 9 3 -3 3 0 3 2147483647 0` | 1 không phải là số nguyên tố.<br>2 là số nguyên tố.<br>9 không phải là số nguyên tố.<br>-3 không phải là số nguyên tố.<br>0 không phải là số nguyên tố.<br>2147483647 là số nguyên tố. | 1 không phải là số nguyên tố.<br>2 là số nguyên tố.<br>9 không phải là số nguyên tố.<br>-3 không phải là số nguyên tố.<br>0 không phải là số nguyên tố.<br>2147483647 là số nguyên tố. | PASS |
| 06 — Mẫu nghiệm thu điểm | `4 2 3 5 -1 0` | count=3; min=2.0000; max=5.0000; average=3.3333 | count=3; min=2.0000; max=5.0000; average=3.3333 | PASS |
| 07 — Loại điểm sai, nhận biên | `4 11 -2 abc 0 10 -1 0` | Điểm phải nằm trong [0, 10]; -1 để kết thúc.<br>Điểm phải nằm trong [0, 10]; -1 để kết thúc.<br>Phải nhập số; bỏ qua: abc<br>count=2; min=0.0000; max=10.0000; average=5.0000 | Điểm phải nằm trong [0, 10]; -1 để kết thúc.<br>Điểm phải nằm trong [0, 10]; -1 để kết thúc.<br>Phải nhập số; bỏ qua: abc<br>count=2; min=0.0000; max=10.0000; average=5.0000 | PASS |
| 08 — Rỗng và khởi tạo lại | `4 -1 4 8 -1 4 2 -1 0` | Chưa có điểm; không thể tính trung bình.<br>count=1; min=8.0000; max=8.0000; average=8.0000<br>count=1; min=2.0000; max=2.0000; average=2.0000 | Chưa có điểm; không thể tính trung bình.<br>count=1; min=8.0000; max=8.0000; average=8.0000<br>count=1; min=2.0000; max=2.0000; average=2.0000 | PASS |
| 09 — Menu sai | `abc 9 2.5 2147483648 0` | Phải nhập số nguyên; bỏ qua: abc<br>Lựa chọn không hợp lệ. Hãy chọn 0..4.<br>Phải nhập số nguyên; bỏ qua: 2.5<br>Phải nhập số nguyên; bỏ qua: 2147483648 | Phải nhập số nguyên; bỏ qua: abc<br>Lựa chọn không hợp lệ. Hãy chọn 0..4.<br>Phải nhập số nguyên; bỏ qua: 2.5<br>Phải nhập số nguyên; bỏ qua: 2147483648 | PASS |
| 10 — Số nguyên sai kiểu | `3 chu 2.5 2147483648 2 0` | Phải nhập số nguyên; bỏ qua: chu<br>Phải nhập số nguyên; bỏ qua: 2.5<br>Phải nhập số nguyên; bỏ qua: 2147483648<br>2 là số nguyên tố. | Phải nhập số nguyên; bỏ qua: chu<br>Phải nhập số nguyên; bỏ qua: 2.5<br>Phải nhập số nguyên; bỏ qua: 2147483648<br>2 là số nguyên tố. | PASS |
| 11 — Số không hữu hạn và tràn | `2 NaN Infinity 1e309 1e308 1 1e308 * 10 2 0 0` | Phải nhập số hữu hạn.<br>Phải nhập số hữu hạn.<br>Phải nhập số hữu hạn.<br>Kết quả vượt phạm vi double.<br>Kết quả vượt phạm vi double.<br>0.00°C = 32.00°F | Phải nhập số hữu hạn.<br>Phải nhập số hữu hạn.<br>Phải nhập số hữu hạn.<br>Kết quả vượt phạm vi double.<br>Kết quả vượt phạm vi double.<br>0.00°C = 32.00°F | PASS |
| 12 — Liên tiếp bốn chức năng, EOF | `1 2 + 3 ↵ 2 0 ↵ 3 2 ↵ 4 2 3 5 -1 ↵ 1 7 /` | 2.0000 + 3.0000 = 5.0000<br>0.00°C = 32.00°F<br>2 là số nguyên tố.<br>count=3; min=2.0000; max=5.0000; average=3.3333<br>Hết dữ liệu nhập. | 2.0000 + 3.0000 = 5.0000<br>0.00°C = 32.00°F<br>2 là số nguyên tố.<br>count=3; min=2.0000; max=5.0000; average=3.3333<br>Hết dữ liệu nhập. | PASS |

Đạt 12/12 ca. Mỗi ca kiểm tra các đoạn output theo đúng thứ tự,
và chỉ có một thông báo kết thúc (chọn 0 hoặc EOF). Exception làm ca kiểm thử thất bại.
