public class MemberNode {
    public Employee employee; // Tham chiếu không sở hữu
    public MemberNode next;

    public MemberNode(Employee employee) {
        this.employee = employee;
        this.next = null;
    }
}