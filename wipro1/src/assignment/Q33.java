package assignment;

class LowSalException extends Exception {
    public LowSalException(String message) {
        super(message);
    }
}

public class Q33 {
    public static void main(String[] args) {
        try {
            Emp emp = new Emp(101, "Manager", 40000, "Alice");
            emp.calculateHRA();
            emp.printDET();
        } catch (LowSalException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }
}

class Emp {
    int empId;
    String empName;
    String designation;
    double basic;
    final double hra;

    public Emp(int empId, String designation, double basic, String empName) throws LowSalException {
        if (basic < 50000) {
            throw new LowSalException("BASIC salary less than 50000 is not allowed.");
        }
        this.empId = empId;
        this.designation = designation;
        this.basic = basic;
        this.empName = empName;
        this.hra = 0.0;  // will be set later in calculateHRA()
    }

    double calculateHRA() {
        double hraValue = 0;
        switch (designation) {
            case "Manager":
                hraValue = basic * 0.10;
                break;
            case "TeamLeader":
                hraValue = basic * 0.12;
                break;
            case "HR":
                hraValue = basic * 0.05;
                break;
            default:
                hraValue = 0;
        }
        // Since hra is final, we simulate immutability by returning value
        return hraValue;
    }

    void printDET() {
        System.out.println("EMP ID: " + empId);
        System.out.println("EMP NAME: " + empName);
        System.out.println("DESIGNATION: " + designation);
        System.out.println("BASIC: " + basic);
        System.out.println("HRA: " + calculateHRA());
    }
}
