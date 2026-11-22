# Trace đổi 90061 giây

Theo công thức trong [SecondsConverter.java](wednesday/LJ/SecondsConverter.java),
đầu vào là tổng số giây nguyên không âm. Lập bảng trước khi chạy:

| Bước | Biến | Phép tính | Giá trị |
|---:|---|---|---:|
| 1 | `totalSeconds` | Đầu vào | 90061 |
| 2 | `hours` | `90061 / 3600` (chia nguyên) | 25 |
| 3 | Phần giây còn lại sau khi tính giờ | `90061 % 3600` | 61 |
| 4 | `minutes` | `61 / 60` (chia nguyên) | 1 |
| 5 | `seconds` | `90061 % 60` | 1 |

Kết quả mong đợi: `25:1:1`.

Đã biên dịch và chạy `SecondsConverter` với đầu vào `90061`: thực tế `25:1:1`,
khớp với bảng trace.

Kiểm tra ngược: `25 * 3600 + 1 * 60 + 1 = 90061` giây.

Không lấy `hours % 24` vì đang biểu diễn **khoảng thời gian**, có thể dài hơn
một ngày. Tổng số giờ phải được giữ nguyên. Nếu lấy `25 % 24 = 1`, kết quả
thành `1:1:1`, chỉ còn 3661 giây và mất 86400 giây (một ngày).
Modulo 24 phù hợp khi cần giờ trong ngày theo đồng hồ 24 giờ, có xác định mốc
thời gian; nếu muốn tách ngày thì phải in cả số ngày thay vì bỏ đi.
