public class Employee {
    String id;
    String name;
    double baseSalary;

    public Employee(){
        this.id = "UNKNOWN";
        this.name = "Unnamed employee";
        this.baseSalary = 0.0;
    }
    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
        this.baseSalary = 0.0;
    }

    public Employee(String id, String fullName, double baseSalary) {
        setId(id);
        setname(fullName);
        setBaseSalary(baseSalary);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã nhân sự (id) không được để rỗng.");
        }
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Họ tên nhân sự không được để rỗng.");
        }
        this.name = name;
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

    // Nạp chồng phương thức (Overloading)
    public void increaseSalary(double amount) {
        if (amount <= 0) {
            System.out.println("Lỗi: Giá trị tăng lương phải dương.");
            return;
        }
        this.baseSalary += amount;
    }

    public void increaseSalary(double value, boolean byPercentage) {
        if (value <= 0) {
            System.out.println("Lỗi: Giá trị tăng lương phải dương.");
            return;
        }
        if (byPercentage) {
            this.baseSalary += this.baseSalary * (value / 100.0);
        } else {
            this.baseSalary += value;
        }
    }

    // Các phương thức có thể ghi đè (Virtual-like in Java)
    public double calculateMonthlyCost() {
        return baseSalary;
    }

    public void displayInfo() {
        System.out.printf("ID: %-8s | Họ tên: %-20s | Lương CB: %,12.0f VND | Chi phí tháng: %,12.0f VND%n",
                id, name, baseSalary, calculateMonthlyCost());
    }


}
