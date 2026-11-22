# ĐẶC TẢ YÊU CẦU BÀI TẬP LỚN CUỐI KHOÁ (CAPSTONE PROJECT)
## LỘ TRÌNH JAVA BACKEND DEVELOPER (12 THÁNG)

---

## 1. MỤC TIÊU DỰ ÁN
Bài tập lớn cuối khoá nhằm chứng minh năng lực toàn diện của học viên sau 12 tháng đào tạo chuyên sâu từ Java Core, Cấu trúc dữ liệu & Giải thuật đến Spring Ecosystem, Tối ưu cơ sở dữ liệu và Kiến trúc phân tán.

* **Chuẩn đầu ra:** Tương đương trình độ **Fresher cứng / Junior Java Backend Developer** có khả năng tự thiết kế hệ thống, xử lý đồng thời (concurrency), tối ưu hiệu năng và tự động hoá triển khai.
* **Thời gian thực hiện khuyến nghị:** 6 – 8 tuần.
* **Hình thức:** Cá nhân hoặc nhóm tối đa 2 người (nếu làm nhóm, bắt buộc phân định rõ module nghiệp vụ của từng thành viên).

---

## 2. ĐỀ TÀI GỢI Ý & BÀI TOÁN KỸ THUẬT CỐT LÕI

Học viên chọn **01 trong 03** đề tài dưới đây (hoặc đề tài tương đương được giảng viên phê duyệt). Dự án phải giải quyết được bài toán chịu tải hoặc nghiệp vụ phức tạp, tuyệt đối không dừng lại ở mức CRUD đơn giản:

### Đề tài 1: Hệ thống E-Commerce & Flash Sale
* **Nghiệp vụ chính:** Quản lý danh mục, sản phẩm, giỏ hàng, đặt hàng, quản lý kho, áp dụng mã giảm giá (voucher/coupon), tích hợp cổng thanh toán (VNPay / MoMo Sandbox).
* **Bài toán kỹ thuật:**
  * Giải quyết tranh chấp tồn kho khi có hàng nghìn lượt mua cùng lúc (Race Condition / Over-selling).
  * Đồng bộ số lượng tồn kho giữa Redis Cache và Cơ sở dữ liệu chính.
  * Tích hợp Message Queue để hoãn xử lý đơn hàng và trả kết quả bất đồng bộ.

### Đề tài 2: Nền tảng Đặt vé Trực tuyến (Rạp phim / Sự kiện / Chuyến bay)
* **Nghiệp vụ chính:** Xem lịch chiếu/chuyến bay, chọn vị trí ghế trên sơ đồ trực quan, đặt chỗ tạm thời, thanh toán và phát hành vé điện tử kèm mã QR qua email.
* **Bài toán kỹ thuật:**
  * Giữ chỗ tạm thời có thời hạn (Distributed Lock / TTL trên Redis), tự động nhả ghế nếu người dùng không hoàn tất thanh toán trong 10 phút.
  * Xử lý giao dịch phân tán và rollback an toàn khi cổng thanh toán gặp lỗi.
  * Xuất vé định dạng PDF/QR code chạy bất đồng bộ qua hàng đợi.

### Đề tài 3: Hệ thống Quản trị Doanh nghiệp / Quản lý Dự án SaaS (Multi-tenant)
* **Nghiệp vụ chính:** Quản lý không gian làm việc (workspace), bảng công việc (Kanban/Sprint), giao việc, phân quyền nhiều cấp, thảo luận và thông báo thời gian thực.
* **Bài toán kỹ thuật:**
  * Thiết kế cơ sở dữ liệu Multi-tenant (tách schema hoặc dùng chung schema lọc theo `tenant_id`).
  * Phân quyền đa tầng chi tiết (Role-Based Access Control - RBAC / Permission-Based).
  * Thông báo thời gian thực qua WebSocket (STOMP).
  * Xử lý xuất báo cáo thống kê dữ liệu lớn (Excel/PDF) chạy ngầm (Background job) bằng đa luồng.

---

## 3. TECH STACK BẮT BUỘC

| Hạng mục | Công nghệ yêu cầu |
|---|---|
| **Ngôn ngữ & Runtime** | Java 17 hoặc Java 21 LTS (áp dụng Record, Pattern Matching, Sealed Classes, CompletableFuture hoặc Virtual Threads) |
| **Framework chính** | Spring Boot 3.x, Spring MVC / WebFlux, Spring Data JPA, Spring Validation |
| **Bảo mật** | Spring Security 6, Stateless JWT (Access Token + Refresh Token lưu Redis có blacklist), OAuth2 Login (Google/GitHub) |
| **Cơ sở dữ liệu chính** | PostgreSQL hoặc MySQL (Database Engine InnoDB) |
| **Database Migration** | Flyway hoặc Liquibase (nghiêm cấm bật `hibernate.ddl-auto: update` trên môi trường chạy thử/sản xuất) |
| **Caching & In-Memory** | Redis (Cache layer, Rate Limiting, Session/Token Blacklist, Distributed Locking) |
| **Message Broker** | RabbitMQ hoặc Apache Kafka (Xử lý tác vụ gửi mail, thông báo, kiểm toán - audit log) |
| **API Documentation** | OpenAPI 3.0 / Swagger UI (Được chú thích đầy đủ mã lỗi và schema) |
| **Testing** | JUnit 5, Mockito, AssertJ, Testcontainers (hoặc H2 Database) |
| **Container & CI/CD** | Docker (Multi-stage build), Docker Compose, GitHub Actions / GitLab CI |

---

## 4. TIÊU CHUẨN KỸ THUẬT CHI TIẾT

### 4.1. Thiết kế API & Clean Architecture
* **Chuẩn RESTful:** Sử dụng đúng chuẩn đường dẫn danh từ số nhiều, đúng HTTP Methods (`GET`, `POST`, `PUT`, `PATCH`, `DELETE`) và HTTP Status Codes (`200`, `201`, `204`, `400`, `401`, `403`, `404`, `409`, `422`, `500`).
* **Chuẩn hoá phản hồi:** Thiết kế định dạng JSON trả về đồng nhất:
  ```json
  {
    "success": true,
    "statusCode": 200,
    "message": "Xử lý thành công",
    "data": { ... },
    "timestamp": "2026-11-01T23:30:00Z"
  }
  ```
* **Phân trang & Lọc động:** Tầng danh sách bắt buộc hỗ trợ phân trang (`page`, `size`), sắp xếp (`sort`) và lọc động an toàn bằng **JPA Specification** hoặc **QueryDSL**.
* **Xử lý ngoại lệ tập trung:** Triển khai `@RestControllerAdvice` bắt và xử lý toàn bộ lỗi hệ thống, kiểm tra tính hợp lệ dữ liệu (`@Valid`), tuyệt đối không để lộ StackTrace ra ngoài client.
* **Chuyển đổi dữ liệu:** Sử dụng **MapStruct** để mapping giữa Entity và DTO, tách bạch ranh giới giữa tầng hiển thị và tầng lưu trữ.

### 4.2. Tối ưu Cơ sở dữ liệu & Xử lý Concurrency
* **Thiết kế CSDL:** Chuẩn hoá tối thiểu 3NF, khoá ngoại, chỉ mục (Index) trên các trường thường xuyên query/join/filter.
* **Giải quyết N+1 Query:** Triệt để xử lý lỗi N+1 của Hibernate bằng cách sử dụng `@EntityGraph`, `JOIN FETCH`, hoặc Projection DTOs.
* **Xử lý Race Condition & Khung khoá:**
  * Áp dụng **Optimistic Locking** (`@Version`) cho các bản ghi sửa đổi ít xung đột.
  * Áp dụng **Pessimistic Locking** (`PESSIMISTIC_WRITE`) hoặc **Redis Distributed Lock (Redisson)** cho các tài nguyên giới hạn (trừ tiền, trừ kho Flash Sale, đặt ghế).
* **Quản trị Transaction:** Cấu hình `@Transactional` tường minh, xử lý đúng `readOnly = true` cho truy vấn đọc, và hiểu rõ phạm vi rollback (`rollbackFor = Exception.class`).

### 4.3. Kiểm thử Tự động (Automated Testing)
* **Unit Test:**
  * Sử dụng **JUnit 5** và **Mockito**.
  * Viết unit test cho toàn bộ logic nghiệp vụ tại tầng Service.
  * Tỷ lệ bao phủ code (Code Coverage) của tầng Service đo bằng công cụ **JaCoCo** phải đạt tối thiểu **70%**.
* **Integration Test:**
  * Viết ít nhất 3 – 5 test tích hợp cho các luồng nghiệp vụ quan trọng nhất bằng `@SpringBootTest` kết hợp **Testcontainers**.

### 4.4. Đóng gói & Triển khai (DevOps)
* **Dockerfile:** Viết Dockerfile tối ưu theo cơ chế Multi-stage Build (Stage 1: Maven/Gradle build JAR; Stage 2: Chạy trên JRE Slim/Alpine tối ưu dung lượng < 250MB).
* **Docker Compose:** File `docker-compose.yml` có khả năng khởi chạy toàn bộ dịch vụ (Backend App, PostgreSQL, Redis, RabbitMQ) bằng một câu lệnh `docker compose up -d`.
* **CI Pipeline:** Tạo GitHub Actions chạy tự động:
  1. Checkout source code.
  2. Setup JDK tương ứng.
  3. Chạy `mvn clean verify` kiểm tra cú pháp và chạy toàn bộ Unit/Integration Test.

---

## 5. HỒ SƠ BÀN GIAO (DELIVERABLES)

Kho lưu trữ mã nguồn (GitHub / GitLab) công khai hoặc mời người chấm điểm, bao gồm:
1. **Source Code hoàn chỉnh:** Commit theo chuẩn Conventional Commits (`feat:`, `fix:`, `refactor:`, `test:`, v.v.).
2. **File `README.md` chuyên nghiệp:**
   * Giới thiệu tổng quan dự án.
   * **Sơ đồ Kiến trúc Hệ thống (System Architecture Diagram)**.
   * **Sơ đồ Cơ sở Dữ liệu (ERD)** có chú thích quan hệ và Index.
   * **Sơ đồ Tuần tự (Sequence Diagram)** cho ít nhất 02 luồng nghiệp vụ phức tạp nhất.
   * Hướng dẫn chi tiết các bước cài đặt và khởi chạy dự án nội bộ (Local) qua Docker.
3. **Tài liệu API:** Đường dẫn Swagger UI hoạt động kèm file export Postman Collection (kèm theo biến môi trường Environment).
4. **Báo cáo kiểm thử:** File báo cáo độ bao phủ mã nguồn JaCoCo (HTML export).

---

## 6. THANG ĐIỂM ĐÁNH GIÁ (RUBRIC)

| Tiêu chí | Trọng số | Mô tả chi tiết |
|---|---|---|
| **1. Kiến trúc, Cấu trúc Code & Thiết kế CSDL** | **20%** | Phân tầng sạch sẽ (Layered/Clean Architecture), chuẩn hoá CSDL (3NF, Index), quản lý migration bằng Flyway/Liquibase, mapping DTO rõ ràng. |
| **2. Độ phức tạp Nghiệp vụ & Xử lý Concurrency** | **30%** | Nghiệp vụ sâu sắc, giải quyết triệt để bài toán Race Condition/Over-selling, áp dụng đúng Locking, xử lý bất đồng bộ qua Queue, Caching hiệu quả. |
| **3. Chuẩn REST API & Bảo mật** | **15%** | Đúng chuẩn RESTful, xử lý Exception tập trung, Spring Security + Stateless JWT, phân quyền Role/Permission chặt chẽ, validate dữ liệu đầu vào. |
| **4. Hiệu năng & Tối ưu hoá** | **15%** | Không lỗi N+1 Query, thời gian phản hồi API tối ưu (<200ms với API thường), áp dụng Design Patterns phù hợp, không hardcode mật khẩu/khoá bí mật. |
| **5. Testing & Vận hành (Docker, CI/CD)** | **20%** | Service Coverage >= 70% (JaCoCo), có Integration Test, Docker Compose chạy 1 chạm, GitHub Actions pipeline build & test thành công. |
| **Tổng điểm** | **100%** | **Điều kiện đạt:** Tổng điểm >= 65% và không có tiêu chí nào dưới 50%. |