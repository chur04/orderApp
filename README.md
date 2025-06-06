# orderApp
Project: Quản lý Đơn Hàng (Order Management System)

MÔ TẢ HỆ THỐNG – ORDERAPP (JAVA CORE)

1. Mục tiêu hệ thống
OrderApp là một ứng dụng mô phỏng quy trình quản lý và xử lý đơn hàng thực tế, được phát triển bằng Java Core. Hệ thống hỗ trợ thêm, sửa, xóa đơn hàng và sản phẩm, đồng thời lưu trữ thời gian tạo đơn nhằm phục vụ cho việc tra cứu và thống kê.

2. Cấu trúc hệ thống

• model – Lớp dữ liệu (Data Models)

1) Customer
   - customerName: String – Tên khách hàng
   - customerAddress: String – Địa chỉ khách hàng

2) Product
   - productID: String – Mã sản phẩm (tự động sinh)
   - productName: String – Tên sản phẩm
   - productPrice: double – Giá bán

3) Order
   - orderID: String – Mã đơn hàng (tự động sinh)
   - customer: Customer – Đối tượng khách hàng
   - orderDetails: List<OrderDetail> – Danh sách chi tiết đơn hàng
   - dateOrder: LocalDate – Thời gian tạo đơn
   - getTotalPrice(): double – Trả về tổng tiền đơn hàng

4) OrderDetail
   - product: Product – Sản phẩm
   - quantity: int – Số lượng mua
   - getSubPrice(): double – Trả về tổng tiền của sản phẩm trong đơn hàng

• manager – Lớp xử lý nghiệp vụ (Logic)

1) ManageProduct – Quản lý danh sách sản phẩm
   - createNewProduct(): Thêm sản phẩm mới
   - sortProduct(): Sắp xếp danh sách sản phẩm theo giá tăng dần
   - deleteProduct(): Xóa sản phẩm theo ID
   - updateProductPrice(): Cập nhật giá sản phẩm theo ID
   - displayProduct(): Hiển thị danh sách sản phẩm hiện có

2) ManageOrder – Quản lý đơn hàng
   - createNewOrder(): Tạo đơn hàng
   - printAllOrder(): Hiển thị tất cả đơn hàng
   - updateOrder(): Chỉnh sửa đơn hàng
   - exportInvoiceToFile(): Xuất đơn hàng ra file
   - deleteOrder(): Xóa đơn hàng

• util – Lớp tiện ích dùng chung

1) Validation
   - checkInputInt(): Kiểm tra số nguyên nhập vào
   - checkInputString(): Kiểm tra chuỗi nhập vào
   - checkInputYN(): Kiểm tra Yes/No
   - checkInputPrice(): Kiểm tra giá nhập vào
   - checkProductExist(): Kiểm tra sản phẩm đã tồn tại hay chưa
   - checkIDProductExist(): Kiểm tra ID sản phẩm khi sinh ngẫu nhiên
   - checkIDOrderExist(): Kiểm tra ID hóa đơn khi sinh ngẫu nhiên

• main – Lớp điều khiển

1) Main – Chương trình chính để điều hướng toàn bộ chức năng

3. Chức năng chính

• Thêm sản phẩm mới:
  - Tự động sinh mã sản phẩm (productID)
  - Kiểm tra dữ liệu đầu vào hợp lệ và tránh trùng ID
  - Hỏi người dùng có muốn tiếp tục thêm sản phẩm không

• Chỉnh sửa giá sản phẩm:
  - Cập nhật giá theo mã sản phẩm

• Xóa sản phẩm:
  - Xóa sản phẩm khỏi danh sách nếu không còn kinh doanh

• Sắp xếp sản phẩm:
  - Sắp xếp theo giá tăng dần

• Hiển thị sản phẩm:
  - In ra toàn bộ sản phẩm hiện có

• Tạo đơn hàng và xuất đơn hàng:
  - Nhập thông tin khách hàng và chọn nhiều sản phẩm
  - Tự động sinh mã đơn hàng và ghi lại ngày tạo
  - Kiểm tra dữ liệu đầu vào
  - Xuất hóa đơn ra file .txt bao gồm thông tin chi tiết đơn hàng và thời gian tạo

• Sửa đơn hàng:
  - Thay đổi số lượng hoặc sản phẩm trong đơn

• Xóa đơn hàng:
  - Xóa đơn theo mã đơn hàng

• Hiển thị đơn hàng:
  - In ra toàn bộ đơn đã tạo

4. Ưu điểm hệ thống
- Giao diện console thân thiện, dễ thao tác
- Áp dụng nguyên lý lập trình hướng đối tượng (OOP) như kế thừa, đóng gói, phân tách lớp rõ ràng
- Cấu trúc mã nguồn hợp lý, dễ đọc, dễ bảo trì và mở rộng
- Hệ thống có tiềm năng mở rộng với các chức năng nâng cao như tìm kiếm, thống kê, lọc dữ liệu,...

5. Công nghệ sử dụng
- Ngôn ngữ: Java (Java Core)
- Kỹ thuật: OOP, ArrayList, File I/O, LocalDate,...
- Môi trường phát triển: NetBeans IDE

