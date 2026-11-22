# Thực hành ba lỗi Java từ Terminal

Đã chạy bằng `javac 25.0.2` và `java 25.0.2`.
Các mẫu cố ý sai nằm trong `labs`, ngoài `src/main/java`, để không ảnh hưởng quá trình biên dịch Maven của dự án.
Mỗi lỗi được thử riêng để thông báo không bị lẫn với lỗi khác.

Từ thư mục gốc `JavaRoadmap`, chạy:

```sh
cd month1_introduction_problemsolving/labs/java-errors
mkdir -p target/wrong-filename target/missing-semicolon target/correct
```

## 1. Tên file khác tên public class

File `wrong-filename/WrongFileName.java` khai báo `public class CompileRunDemo`.

```sh
javac -d target/wrong-filename wrong-filename/WrongFileName.java
```

Thông báo thực tế (mã thoát 1):

```text
wrong-filename/WrongFileName.java:1: error: class CompileRunDemo is public, should be declared in a file named CompileRunDemo.java
public class CompileRunDemo {
       ^
1 error
```

- **Giai đoạn:** Biên dịch (`javac`).
- **Nguyên nhân:** Tên file không trùng tên public class, kể cả chữ hoa và chữ thường.
- **Cách sửa:** Đổi tên file thành `CompileRunDemo.java`, hoặc đổi tên public class thành `WrongFileName`. Bản `correct/CompileRunDemo.java` dùng cách thứ nhất.

## 2. Thiếu dấu chấm phẩy

File `missing-semicolon/MissingSemicolon.java` thiếu `;` cuối câu lệnh `System.out.println(...)` ở dòng 3.

```sh
javac -d target/missing-semicolon missing-semicolon/MissingSemicolon.java
```

Thông báo thực tế (mã thoát 1):

```text
missing-semicolon/MissingSemicolon.java:3: error: ';' expected
        System.out.println("Chay chuong trinh Java thanh cong!")
                                                                ^
1 error
```

- **Giai đoạn:** Biên dịch, khi phân tích cú pháp (`javac`).
- **Cách sửa:** Thêm `;` ngay sau dấu `)`:

```java
System.out.println("Chay chuong trinh Java thanh cong!");
```

## 3. Chạy sai tên class

Biên dịch bản đúng trước, rồi cố ý gọi tên `WrongClass`:

```sh
javac -d target/correct correct/CompileRunDemo.java
java -cp target/correct WrongClass
```

Biên dịch thành công. Lệnh `java` báo (mã thoát 1):

```text
Error: Could not find or load main class WrongClass
Caused by: java.lang.ClassNotFoundException: WrongClass
```

- **Giai đoạn:** Khởi chạy/nạp lớp bằng `java`, trước khi thực thi `main`; đây không phải lỗi biên dịch.
- **Nguyên nhân:** Thư mục classpath có `CompileRunDemo.class`, không có lớp `WrongClass`.
- **Cách sửa:** Gọi đúng tên `CompileRunDemo` (không kèm `.java` hoặc `.class`) và giữ classpath trỏ đến thư mục chứa file `.class`. Ví dụ này không khai báo package; nếu có package thì phải dùng tên đầy đủ như `week1.friday.ProfileCard`.

## 4. Biên dịch và chạy đúng từ Terminal

File `correct/CompileRunDemo.java`:

```java
public class CompileRunDemo {
    public static void main(String[] args) {
        System.out.println("Chay chuong trinh Java thanh cong!");
    }
}
```

Chạy từ thư mục bài thực hành đã `cd` ở trên:

```sh
javac -d target/correct correct/CompileRunDemo.java
java -cp target/correct CompileRunDemo
```

Kết quả thực tế (cả hai lệnh có mã thoát 0):

```text
Chay chuong trinh Java thanh cong!
```

`javac` tạo file `.class` trong `target/correct`; `java -cp` tìm lớp tại đó rồi gọi phương thức `main`. Hai mẫu sai được giữ nguyên để có thể thực hành lại.
