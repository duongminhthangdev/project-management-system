// Mã sinh viên: 20227067
// Họ và tên:   Dương Minh Thắng
public class Employee {
    protected String id;
    protected String fullName;
    protected double baseSalary;

    public Employee() {
        this.id = "UNKNOWN";
        this.fullName = "Unnamed employee";
        this.baseSalary = 0.0;
    }

    public Employee(String id, String fullName) {
        setId(id);
        setFullName(fullName);
        this.baseSalary = 0.0;
    }

    public Employee(String id, String fullName, double baseSalary) {
        setId(id);
        setFullName(fullName);
        setBaseSalary(baseSalary);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã nhân sự không được để rỗng.");
        }
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Họ tên không được để rỗng.");
        }
        this.fullName = fullName;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        if (baseSalary < 0) {
            throw new IllegalArgumentException("Lương cơ bản không được âm.");
        }
        this.baseSalary = baseSalary;
    }

    // Nạp chồng phương thức tăng lương
    public void increaseSalary(double amount) {
        if (amount <= 0) {
            System.out.println("Giá trị tăng lương phải dương.");
            return;
        }
        this.baseSalary += amount;
    }

    public void increaseSalary(double value, boolean byPercentage) {
        if (value <= 0) {
            System.out.println("Giá trị tăng lương phải dương.");
            return;
        }
        if (byPercentage) {
            this.baseSalary += this.baseSalary * (value / 100.0);
        } else {
            this.baseSalary += value;
        }
    }

    public double calculateMonthlyCost() {
        return baseSalary;
    }

    public void displayInfo() {
        System.out.printf("ID: %-8s | Họ tên: %-20s | Lương CB: %,12.0f VND | Chi phí tháng: %,12.0f VND%n",
                id, fullName, baseSalary, calculateMonthlyCost());
    }
}

