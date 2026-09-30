// Mã sinh viên: 20227067
// Họ và tên:   Dương Minh Thắng

public class ProjectTeam {
    private String projectCode;
    private String projectName;
    private Employee leader;  // Tham chiếu không sở hữu
    private MemberNode head;  // Đầu DSLK tự cài đặt

    // Constructor 1: Chưa có trưởng nhóm
    public ProjectTeam(String projectCode, String projectName) {
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.leader = null;
        this.head = null;
    }

    // Constructor 2: Có sẵn trưởng nhóm (Tự động đưa trưởng nhóm vào danh sách)
    public ProjectTeam(String projectCode, String projectName, Employee leader) {
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.head = null;
        this.leader = null;
        addMember(leader, true);
    }

    // Kiểm tra nhân sự đã thuộc nhóm hay chưa
    public boolean contains(String employeeId) {
        MemberNode current = head;
        while (current != null) {
            if (current.employee.getId().equalsIgnoreCase(employeeId)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Nạp chồng addMember
    public boolean addMember(Employee employee) {
        return addMember(employee, false);
    }

    public boolean addMember(Employee employee, boolean makeLeader) {
        if (employee == null) return false;

        boolean alreadyInTeam = contains(employee.getId());

        // Không thêm trùng lặp nhân sự đã có trong nhóm
        if (alreadyInTeam) {
            System.out.println("⚠️ Nhân sự " + employee.getFullName() + " (ID: " + employee.getId() + ") đã có trong nhóm!");
            return false;
        }

        // Thêm nút mới vào đầu danh sách liên kết
        MemberNode newNode = new MemberNode(employee);
        newNode.next = head;
        head = newNode;

        // Nếu makeLeader == true, gán ngay làm Trưởng nhóm
        if (makeLeader) {
            this.leader = employee;
            System.out.println("✅ Đã thêm " + employee.getFullName() + " vào nhóm và bổ nhiệm làm Trưởng nhóm!");
        } else {
            System.out.println("✅ Đã thêm " + employee.getFullName() + " vào nhóm!");
        }

        return true;
    }

    // Đổi Trưởng nhóm (Ràng buộc: Bắt buộc phải là thành viên hiện tại của nhóm)
    public boolean changeLeader(Employee employee) {
        if (employee == null) {
            System.out.println("❌ Lỗi: Đối tượng nhân sự không hợp lệ (null)!");
            return false;
        }

        // Kiểm tra xem nhân sự đã thuộc danh sách thành viên hay chưa
        if (!contains(employee.getId())) {
            System.out.println("❌ Lỗi bổ nhiệm: Nhân sự '" + employee.getFullName()
                    + "' (ID: " + employee.getId() + ") chưa phải là thành viên của nhóm. "
                    + "Vui lòng thêm nhân sự vào nhóm bằng addMember() trước!");
            return false; // Từ chối đổi Trưởng nhóm
        }

        // Đã là thành viên -> Tiến hành cập nhật vị trí Trưởng nhóm
        this.leader = employee;
        System.out.println("✅ Đã đổi Trưởng nhóm mới sang: " + employee.getFullName() + " (ID: " + employee.getId() + ")");
        return true;
    }
    // Xóa thành viên khỏi DSLK
    public boolean removeMember(String employeeId) {
        // Ràng buộc: Không được xóa trưởng nhóm khi chưa chọn trưởng nhóm thay thế
        if (leader != null && leader.getId().equalsIgnoreCase(employeeId)) {
            System.out.println("❌ Lỗi: Không thể xóa Trưởng nhóm (ID: " + employeeId + ") khi chưa chọn Trưởng nhóm thay thế!");
            return false;
        }

        MemberNode current = head;
        MemberNode prev = null;

        while (current != null) {
            if (current.employee.getId().equalsIgnoreCase(employeeId)) {
                if (prev == null) {
                    head = current.next; // Xóa nút đầu
                } else {
                    prev.next = current.next; // Bỏ qua nút hiện tại
                }
                return true;
            }
            prev = current;
            current = current.next;
        }

        return false;
    }

    // Tính tổng chi phí
    public double calculateTotalMonthlyCost() {
        double total = 0.0;
        MemberNode current = head;
        while (current != null) {
            total += current.employee.calculateMonthlyCost(); // Đa hình
            current = current.next;
        }
        return total;
    }

    // Hiển thị thông tin nhóm
    public void displayTeam() {
        System.out.println("\n==========================================================================================================");
        System.out.println("MÃ DỰ ÁN   : " + projectCode);
        System.out.println("TÊN DỰ ÁN  : " + projectName);
        System.out.println("TRƯỞNG NHÓM: " + (leader != null ? leader.getFullName() + " (ID: " + leader.getId() + ")" : "Chưa có"));
        System.out.println("DANH SÁCH THÀNH VIÊN:");

        MemberNode current = head;
        if (current == null) {
            System.out.println("  (Nhóm chưa có thành viên nào)");
        } else {
            while (current != null) {
                System.out.print("  • ");
                current.employee.displayInfo(); // Đa hình
                current = current.next;
            }
        }
        System.out.printf("👉 TỔNG CHI PHÍ HÀNG THÁNG CỦA NHÓM: %,.0f VND%n", calculateTotalMonthlyCost());
        System.out.println("==========================================================================================================\n");
    }
}