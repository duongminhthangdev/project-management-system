public class SoftwareEngineer extends Employee {
    private String primaryLanguage;
    private double technicalAllowance;

    public SoftwareEngineer(String id, String fullName, String primaryLanguage) {
        super(id, fullName, 0.0);
        setPrimaryLanguage(primaryLanguage);
        this.technicalAllowance = 0.0;
    }

    public SoftwareEngineer(String id, String fullName, double baseSalary, String primaryLanguage, double technicalAllowance) {
        super(id, fullName, baseSalary);
        setPrimaryLanguage(primaryLanguage);
        setTechnicalAllowance(technicalAllowance);
    }

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
            throw new IllegalArgumentException("Phụ cấp không được âm.");
        }
        this.technicalAllowance = technicalAllowance;
    }

    @Override
    public double calculateMonthlyCost() {
        return getBaseSalary() + technicalAllowance;
    }

    @Override
    public void displayInfo() {
        System.out.printf("ID: %-8s | Họ tên: %-20s | Lương CB: %,12.0f VND | Ngôn ngữ: %-8s | Phụ cấp: %,10.0f VND | Chi phí tháng: %,12.0f VND%n",
                id, fullName, baseSalary, primaryLanguage, technicalAllowance, calculateMonthlyCost());
    }
}