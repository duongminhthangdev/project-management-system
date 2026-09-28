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

        // 1. Nếu chưa có trong danh sách thì thêm nút mới vào đầu DSLK
        if (!alreadyInTeam) {
            MemberNode newNode = new MemberNode(employee);
            newNode.next = head;
            head = newNode;
        }

        // 2. Nếu makeLeader == true, gán làm Trưởng nhóm
        if (makeLeader) {
            this.leader = employee;
        }

        return !alreadyInTeam;
    }

    // Đổi Trưởng nhóm
    public boolean changeLeader(Employee employee) {
        if (employee == null) return false;

        // Trưởng nhóm mới phải được thêm vào nhóm nếu chưa phải thành viên
        if (!contains(employee.getId())) {
            addMember(employee);
        }
        this.leader = employee;
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