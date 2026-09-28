
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Kịch bản Kiểm thử Tích hợp (Integration Test) - Quản lý Nhóm Dự án")
class ProjectIntegrationTest {

    private Employee emp1;
    private Employee emp2;
    private SoftwareEngineer se1;
    private SoftwareEngineer se2;

    @BeforeEach
    void setUp() {
        // Bước 1: Tạo hai Employee bằng hai constructor khác nhau
        emp1 = new Employee("EMP01", "Nguyễn Văn A");
        emp1.setBaseSalary(8000000);
        emp2 = new Employee("EMP02", "Trần Thị B", 12000000);

        // Bước 2: Tạo hai SoftwareEngineer bằng hai constructor khác nhau
        se1 = new SoftwareEngineer("SE01", "Lê Văn C", "Java");
        se1.setBaseSalary(15000000);
        se1.setTechnicalAllowance(3000000);

        se2 = new SoftwareEngineer("SE02", "Phạm Minh D", 18000000, "C++", 4000000);
    }

    @Test
    @DisplayName("Kiểm thử luồng Tăng lương & Tích hợp Nhóm Dự án (Bước 3 -> Bước 10)")
    void testProjectTeamIntegrationFlow() {
        // Bước 3: Tăng lương emp1 bằng số tiền cố định (+2tr -> 10tr)
        emp1.increaseSalary(2000000);
        assertEquals(10000000, emp1.getBaseSalary());

        // Bước 4: Tăng lương se1 theo phần trăm (+10% của 15tr -> 16.5tr)
        se1.increaseSalary(10, true);
        assertEquals(16500000, se1.getBaseSalary());

        // Bước 5: Tạo nhóm dự án không có trưởng nhóm
        ProjectTeam team1 = new ProjectTeam("PRJ01", "Hệ thống Quản lý Bán hàng");

        // Bước 6: Thêm một nhân sự vào nhóm bằng addMember(employee)
        assertTrue(team1.addMember(emp1));

        // Bước 7: Thêm một kỹ sư bằng addMember(se1, true) để đặt làm trưởng nhóm
        assertTrue(team1.addMember(se1, true));

        // Bước 8: Thử thêm lại một thành viên đã tồn tại (emp1) -> Phải trả về false
        assertFalse(team1.addMember(emp1));

        // Thêm nốt se2 vào nhóm
        assertTrue(team1.addMember(se2));

        // Bước 9 & 10: Kiểm tra tính toán Đa hình & Tổng chi phí hàng tháng
        // - emp1: 10,000,000
        // - se1 : 16,500,000 + 3,000,000 (phụ cấp) = 19,500,000
        // - se2 : 18,000,000 + 4,000,000 (phụ cấp) = 22,000,000
        // Tổng chi phí = 10tr + 19.5tr + 22tr = 51,500,000 VND
        assertEquals(51500000, team1.calculateTotalMonthlyCost());
    }

    @Test
    @DisplayName("Kiểm thử Ràng buộc Trưởng nhóm (Bước 11 & Bước 12)")
    void testLeaderConstraintFlow() {
        ProjectTeam team1 = new ProjectTeam("PRJ01", "Hệ thống Quản lý Bán hàng", se1);
        team1.addMember(se2);

        // Bước 11: Thử xóa trưởng nhóm hiện tại (se1 / SE01) -> Phải bị TỪ CHỐI (trả về false)
        assertFalse(team1.removeMember("SE01"));
        assertTrue(team1.contains("SE01"), "Trưởng nhóm vẫn phải còn trong nhóm!");

        // Bước 12: Đổi trưởng nhóm sang SE02 rồi mới xóa người từng là trưởng nhóm (SE01)
        assertTrue(team1.changeLeader(se2));
        assertTrue(team1.removeMember("SE01"), "Bây giờ việc xóa SE01 phải THÀNH CÔNG");
        assertFalse(team1.contains("SE01"));
    }

    @Test
    @DisplayName("Kiểm thử Quan hệ Kết tập & Liên kết Không sở hữu (Bước 13 -> Bước 15)")
    void testNonOwningAggregationFlow() {
        // Bước 13 & 14: Tạo nhóm thứ hai trong Scope cục bộ rồi tiêu hủy nhóm đó
        {
            ProjectTeam team2 = new ProjectTeam("PRJ02", "Ứng dụng Mobile Banking");
            team2.addMember(emp2);
            team2.addMember(se2);

            assertTrue(team2.contains("EMP02"));
            assertTrue(team2.contains("SE02"));
            // Thoát khỏi khối lệnh {} -> đối tượng team2 bị tiêu hủy khỏi bộ nhớ
        }

        // Bước 15: Chứng minh nhân sự (emp2, se2) của nhóm thứ hai vẫn tồn tại bình thường
        assertNotNull(emp2);
        assertEquals("EMP02", emp2.getId());
        assertEquals("Trần Thị B", emp2.getFullName());

        assertNotNull(se2);
        assertEquals("SE02", se2.getId());
        assertEquals(22000000, se2.calculateMonthlyCost());
    }
}