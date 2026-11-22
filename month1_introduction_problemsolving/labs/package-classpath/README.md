# Package study và classpath

## Biên dịch vào out rồi chạy

Từ thư mục gốc `JavaRoadmap`, chạy trong Terminal:

```sh
cd month1_introduction_problemsolving/labs/package-classpath
javac -d out study/Welcome.java
java -cp out study.Welcome
```

Đã thực thi bằng JDK 25.0.2: cả lệnh biên dịch và chạy đều có mã thoát 0.
Kết quả:

```text
Chao mung ban den voi Java!
```

Mã nguồn `study/Welcome.java` khai báo:

```java
package study;

public class Welcome {
    public static void main(String[] args) {
        System.out.println("Chao mung ban den voi Java!");
    }
}
```

`javac -d out` tạo thư mục đầu ra nếu cần và đặt bytecode tại `out/study/Welcome.class`, tương ứng với package `study`.
`-cp out` đặt thư mục gốc để tìm lớp là `out`; tên lớp đầy đủ `study.Welcome` được ánh xạ thành đường dẫn `study/Welcome.class` bên trong thư mục đó. Vì vậy classpath là `out`, không phải `out/study`.

## Vì sao chạy file .java khác chạy lớp đã biên dịch?

| Lệnh | Đầu vào và cách thực hiện |
|---|---|
| `java study/Welcome.java` | Chạy mã nguồn trực tiếp (source-file mode, có từ Java 11): Java đọc đường dẫn file `.java`, biên dịch trong bộ nhớ rồi chạy `main`. Không cần chạy `javac` trước và không ghi `Welcome.class` ra đĩa. |
| `java -cp out study.Welcome` | Chạy bytecode đã được biên dịch: Java tìm lớp mang tên đầy đủ `study.Welcome` trong classpath rồi gọi `main`. Không tự biên dịch lại file nguồn. |

Hai lệnh trên đã được chạy và đều in cùng lời chào. Tuy nhiên, khi sửa mã nguồn, lệnh chạy file `.java` dùng mã mới ngay; lệnh chạy lớp trong `out` chỉ dùng mã mới sau khi chạy lại `javac -d out study/Welcome.java`.

Khi chạy lớp đã biên dịch, dùng dấu chấm giữa package và tên lớp, không kèm `.java` hay `.class`: `study.Welcome`. Khi chạy file nguồn, dùng đường dẫn file: `study/Welcome.java`.
