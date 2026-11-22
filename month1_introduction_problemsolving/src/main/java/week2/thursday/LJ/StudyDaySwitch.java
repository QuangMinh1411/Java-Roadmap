package week2.thursday.LJ;

/*
 * ĐỀ BÀI: PHÂN LOẠI NGÀY BẰNG SWITCH
 * - MON/TUE/WED/THU/FRI: "Ngày học".
 * - SAT/SUN: "Nghỉ".
 * - Mã khác: "Lỗi: mã ngày không hợp lệ".
 * - Các mã phân biệt chữ hoa/thường.
 * - Dùng case: và break, mỗi mã chỉ nhận đúng một kết quả.
 *
 * TỰ KIỂM TRA
 * Chạy MON, TUE, WED, THU, FRI, SAT, SUN, XYZ:
 * đúng 5 kết quả ngày học, 2 kết quả nghỉ, 1 lỗi.
 * Các nhãn case đứng liền nhau cố ý dùng chung phần xử lý;
 * break ngăn chạy xuyên sang phần xử lý của nhóm kế tiếp.
 */
public class StudyDaySwitch {
    public static String classify(String code) {
        String result = switch (code) {
            case "MON", "TUE", "WED", "THU", "FRI" -> "Ngày học";
            case "SAT", "SUN" -> "Nghỉ";
            default -> "Lỗi: mã ngày không hợp lệ";
        };
        return result;
    }

    public static void main(String[] args) {
        String[] codes = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN", "XYZ"};
        for (String code : codes) {
            System.out.println(code + ": " + classify(code));
        }
    }
}
