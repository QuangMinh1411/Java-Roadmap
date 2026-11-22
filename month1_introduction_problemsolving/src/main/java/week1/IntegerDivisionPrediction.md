# Dự đoán phép chia trước khi chạy

Bảng này được ghi trước khi chạy chương trình. Hàng cuối cố ý dùng một
dự đoán sai thường gặp để luyện đối chiếu và sửa lỗi.

| Biểu thức | Expected (dự đoán ban đầu) | Actual |
|---|---:|---|
| `7 / 3` | 2 | 2 |
| `7 % 3` | 1 | 1 |
| `7 / 3.0` | 2.3333333333333335 | 2.3333333333333335 |
| `(double)(7 / 3)` | 2.3333333333333335 (dự đoán sai minh họa) | 2.0 |

Đã biên dịch và chạy `IntegerDivisionPrediction.java` sau khi ghi dự đoán;
cột Actual được điền từ kết quả chạy thực tế.

Sửa dự đoán hàng cuối: kết quả đúng là **2.0**. Java tính biểu thức trong
ngoặc trước: `7 / 3` là chia nguyên nên được `2`, sau đó `(double)` đổi
`2` thành `2.0`. Phần lẻ đã mất không thể khôi phục bằng ép kiểu sau phép chia.

Nếu muốn giữ phần lẻ, ép kiểu trước phép chia: `(double) 7 / 3`,
cho `2.3333333333333335`. Với phép dư, `7 = 2 * 3 + 1`, nên `7 % 3 = 1`.
