
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
        System.out.println("Bước 1: Tạo hai Employee bằng hai constructor khác nhau");
        emp1 = new Employee("EMP01", "Công văn Nhân");
        emp2 = new Employee("EMP02", "Bán Thị Hàng", 12000000);
        emp1.displayInfo();
        emp2.displayInfo();
        System.out.println("Bước 2: Tạo hai SoftwareEngineer bằng hai constructor khác nhau");
        se1 = new SoftwareEngineer("SE01", "Kĩ văn Sư", "Java");
        se2 = new SoftwareEngineer("SE02", "Phần Thị Mềm", 15000000, "C++", 4000000);
        se1.displayInfo();
        se2.displayInfo();
    }

    @Test
    @DisplayName("Kiểm thử luồng Tăng lương & Tích hợp Nhóm Dự án (Bước 3 -> Bước 10)")

    void testProjectTeamIntegrationFlow() {
        System.out.println("Bước 3: Tăng lương emp2 bằng số tiền cố định (+2tr -> 14tr)");
        emp2.increaseSalary(2000000);
        assertEquals(14000000, emp2.getBaseSalary());
        System.out.println("Lương của emp2 là :" + emp2.getBaseSalary());


        System.out.println("Bước 4: Tăng lương se2 theo phần trăm (+10% của 15tr -> 16.5tr)");
        se2.increaseSalary(10, true);
        assertEquals(16500000, se2.getBaseSalary());
        System.out.println("lương của se2 là : " + se2.getBaseSalary());

        System.out.println("Bước 5: Tạo nhóm dự án không có trưởng nhóm");
        ProjectTeam team1 = new ProjectTeam("PRJ01", "Hệ thống Quản lý Bán hàng");
        team1.displayTeam();

        System.out.println("Bước 6: Thêm một nhân sự vào nhóm bằng addMember(employee)");
        assertTrue(team1.addMember(emp2));
        team1.displayTeam();

        System.out.println("Bước 7: Thêm một kỹ sư bằng addMember(se2, true) để đặt làm trưởng nhóm");
        assertTrue(team1.addMember(se2, true));
        team1.displayTeam();

        System.out.println("Bước 8: Thử thêm lại một thành viên đã tồn tại (emp1) -> Phải trả về false");
        assertFalse(team1.addMember(emp2));

        System.out.println("Bước 9 & 10: Kiểm tra tính toán Đa hình & Tổng chi phí hàng tháng");
        // - emp2: 14,000,000
        // - se2 : 16,500,000 + 4,000,000 (phụ cấp) = 20,500,000
        // Tổng chi phí = 14tr+ 20.5tr = 34,500,000 VND
        assertEquals(34500000, team1.calculateTotalMonthlyCost());
    }

    @Test
    @DisplayName("Kiểm thử Ràng buộc Trưởng nhóm (Bước 11 & Bước 12)")
    void testLeaderConstraintFlow() {
        ProjectTeam team1 = new ProjectTeam("PRJ01", "Hệ thống Quản lý Bán hàng", emp2);
        team1.displayTeam();

        System.out.println("Bước 11: Thử xóa trưởng nhóm hiện tại (emp2) -> Phải bị TỪ CHỐI (trả về false)");
        assertFalse(team1.removeMember("EMP02"));

        System.out.println("Bước 12: Đổi trưởng nhóm sang SE01 rồi mới xóa người từng là trưởng nhóm (EMP02)");
        assertFalse(team1.changeLeader(se1));
        team1.addMember(se1);
        assertTrue(team1.changeLeader(se1));
        assertTrue(team1.removeMember("EMP02"), "Bây giờ việc xóa EMP02 phải THÀNH CÔNG");
        assertFalse(team1.contains("EMP02"));
        team1.displayTeam();
    }

    @Test
    @DisplayName("Kiểm thử Quan hệ Kết tập & Liên kết Không sở hữu (Bước 13 -> Bước 15)")
    void testNonOwningAggregationFlow() {
        System.out.println("Bước 13 & 14: Tạo nhóm thứ hai trong Scope cục bộ rồi tiêu hủy nhóm đó");
        {
            ProjectTeam team2 = new ProjectTeam("PRJ02", "Ứng dụng Mobile Banking");
            team2.addMember(emp2);
            team2.addMember(se2);

            assertTrue(team2.contains("EMP02"));
            assertTrue(team2.contains("SE02"));
            // Thoát khỏi khối lệnh {} -> đối tượng team2 bị tiêu hủy khỏi bộ nhớ
        }

        System.out.println("Bước 15: Chứng minh nhân sự (emp2, se2) của nhóm thứ hai vẫn tồn tại bình thường");
        assertNotNull(emp2);
        assertEquals("EMP02", emp2.getId());
        assertNotNull(se2);
        assertEquals("SE02", se2.getId());
        emp2.displayInfo();
        se2.displayInfo();
    }
}