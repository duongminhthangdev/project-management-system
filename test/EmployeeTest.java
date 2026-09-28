import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private Employee employee;

    @BeforeEach
    void setUp() {
        // Khởi tạo nhân viên ban đầu trước mỗi bài test
        employee = new Employee("EMP001", "Nguyen Van A", 10000000);
    }

    @Test
    @DisplayName("Test cập nhật Mã nhân viên (setId)")
    void setId() {
        // Cập nhật ID mới
        employee.setId("EMP002");

        // Kiểm tra xem ID đã được đổi thành EMP002 chưa
        assertEquals("EMP002", employee.getId(), "Mã nhân viên phải được cập nhật thành EMP002");
    }

    @Test
    @DisplayName("Test tăng lương cơ bản thêm số tiền cụ thể")
    void increaseSalary() {
        // Tăng thêm 2.000.000 vào lương cơ bản 10.000.000
        employee.increaseSalary(2000000);

        // Kiểm tra kết quả lương mới (10tr + 2tr = 12tr)
        assertEquals(12000000, employee.getBaseSalary(), "Lương sau khi tăng phải là 12,000,000");
    }

    @Test
    @DisplayName("Test tăng lương theo phần trăm (%) hoặc trường hợp đặc biệt")
    void testIncreaseSalary() {
        // Ví dụ: Tăng lương thêm 10% (nếu lớp Employee có phương thức nạp chồng tăng theo %)
        // Hoặc kiểm tra trường hợp tăng lương không hợp lệ (số tiền âm)
        assertThrows(IllegalArgumentException.class, () -> {
            employee.increaseSalary(-500000);
        }, "Phải ném ngoại lệ IllegalArgumentException khi số tiền tăng lương bị âm");
    }
}