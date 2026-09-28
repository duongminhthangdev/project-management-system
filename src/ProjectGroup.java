public class ProjectTeam {
    private String projectCode;
    private String projectName;
    private Employee leader;  // Tham chiếu không sở hữu
    private MemberNode head;  // Đầu danh sách liên kết đơn