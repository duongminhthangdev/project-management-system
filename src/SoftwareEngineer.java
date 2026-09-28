

public class SoftwareEngineer extends Employee {
    private String primaryLanguage;
    private double technicalAllowance;

    // Constructor 3 tham số
    public SoftwareEngineer(String id, String name, String primaryLanguage) {
        super(id, name, 0.0);
        setPrimaryLanguage(primaryLanguage);
        this.technicalAllowance = 0.0;
    }

    // Constructor 5 tham số
    public SoftwareEngineer(String id, String name, double baseSalary, String primaryLanguage, double technicalAllowance) {
        super(id, name, baseSalary);
        setPrimaryLanguage(primaryLanguage);
        setTechnicalAllowance(technicalAllowance);
    }

    // Getters & Setters với Ràng buộc
    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    public void setPrimaryLanguage(String primaryLanguage) {
        if (primaryLanguage == null || primaryLanguage.trim().isEmpty()) {
            throw new IllegalArgumentException("Ngôn ngữ chính không được để rỗng.");
        }
        this.primaryLanguage = primaryLanguage;
    }

    public double getTechnicalAllowance() {
        return technicalAllowance;
    }

    public void setTechnicalAllowance(double technicalAllowance) {
        if (technicalAllowance < 0) {
            throw new IllegalArgumentException("Phụ cấp kỹ thuật không được âm.");
        }
        this.technicalAllowance = technicalAllowance;
    }

    // Ghi đè phương thức (Overriding)
    @Override
    public double calculateMonthlyCost() {
        return getBaseSalary() + technicalAllowance;
    }

    @Override
    public void displayInfo() {
        System.out.printf("ID: %-8s | Họ tên: %-20s | Lương CB: %,12.0f VND | Ngôn ngữ: %-8s | Phụ cấp: %,10.0f VND | Tổng chi phí: %,12.0f VND%n",
                id, name, baseSalary, primaryLanguage, technicalAllowance, calculateMonthlyCost());
    }
}