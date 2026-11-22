# Hợp đồng input/output — StudyTools CLI

Chốt trước khi cài đặt: một Scanner cho cả phiên; token cách nhau bằng khoảng
trắng hoặc xuống dòng. Số thập phân dùng dấu chấm. Menu lặp sau mỗi chức năng.

| Chọn | Input tiếp theo | Output mẫu / quy tắc |
|---|---|---|
| 1 — Bốn phép toán | Số `a`, toán tử `+ - * /`, số `b` | `7 / 2` → `7.0000 / 2.0000 = 3.5000` |
| 2 — Đổi nhiệt độ | Độ C hữu hạn | `0` → `0.00°C = 32.00°F`; phạm vi nhỏ: chỉ C → F |
| 3 — Số nguyên tố | Số nguyên trong miền int | `1` → không nguyên tố; `2` → nguyên tố; âm, 0 → không nguyên tố |
| 4 — Thống kê điểm | Các điểm `[0,10]`, kết thúc bằng `-1` | `2 3 5 -1` → count=3, min=2.0000, max=5.0000, average=3.3333 |
| 0 — Thoát | Không có | `Thoát chương trình.` |

| Tình huống sai / biên | Xử lý đã chốt |
|---|---|
| Chữ tại ô số, số thực tại ô số nguyên, int quá lớn | Tiêu thụ token sai, báo lỗi, hỏi lại đúng ô đang nhập |
| Toán tử khác bốn phép toán | Báo lỗi, hỏi lại toán tử |
| Menu ngoài 0..4 | Báo lỗi, hiện lại menu |
| Chia cho 0 (kể cả -0.0) | Báo lỗi, về menu |
| Điểm ngoài miền (trừ sentinel -1) | Báo lỗi, không đưa vào thống kê, tiếp tục nhập |
| Chưa có điểm rồi nhập -1 | Thông báo chưa có điểm; không chia cho 0 |
| NaN, Infinity | Từ chối và hỏi lại số hữu hạn |
| Kết quả phép tính / đổi nhiệt độ vượt double | Báo vượt phạm vi, về menu |
| Hết đầu vào (EOF) tại bất kỳ ô nào | Thông báo hết dữ liệu và kết thúc bình thường; không in kết quả dở dang |

Mỗi lần chọn 4 bắt đầu nhóm điểm mới. Đầu vào còn lại vẫn được giữ để xử lý
chức năng tiếp theo; 0 trong chức năng là dữ liệu, chỉ 0 tại menu mới thoát.
