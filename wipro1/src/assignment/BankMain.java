package assignment;

abstract class Bank {
    String accNo, custName, custJob;
    int custGender;
    double curBal;

    Bank(String accNo, String custName, int custGender, String custJob, double curBal) {
        this.accNo = accNo;
        this.custName = custName;
        this.custGender = custGender;
        this.custJob = custJob;
        this.curBal = curBal;
    }

    public String toString() {
        return "Account No: " + accNo + ", Name: " + custName + ", Balance: " + curBal;
    }

    public abstract double calcBalance();
}

class Saving extends Bank {
    double savRate;

    Saving(String accNo, String custName, int custGender, String custJob, double curBal, double savRate) {
        super(accNo, custName, custGender, custJob, curBal);
        this.savRate = savRate;
    }

    public double calcBalance() {
        return curBal + (savRate * curBal);
    }
}

class Current extends Bank {
    boolean fixedDep;
    double curRate;

    Current(String accNo, String custName, int custGender, String custJob, double curBal, double curRate, boolean fixedDep) {
        super(accNo, custName, custGender, custJob, curBal);
        this.curRate = curRate;
        this.fixedDep = fixedDep;
    }

    public double calcBalance() {
        double result = curBal + (curRate * curBal);
        if (fixedDep) result -= 150;
        return result;
    }
}

public class BankMain {
    public static void main(String[] args) {
        Bank[] customers = {
            new Saving("A001", "Alice", 2, "Teacher", 10000, 0.04),
            new Current("C001", "Bob", 1, "Engineer", 20000, 0.02, true),
            new Current("C002", "Charlie", 1, "Doctor", 15000, 0.03, false)
        };

        // b) Search
        String searchAcc = "C001";
        boolean found = false;
        for (Bank b : customers) {
            if (b.accNo.equals(searchAcc)) {
                System.out.println("Customer found: " + b);
                System.out.println("Final Balance: " + b.calcBalance());
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Customer not found!");

        // c) Count current accounts and total balance
        int count = 0;
        double total = 0;
        for (Bank b : customers) {
            if (b instanceof Current) {
                count++;
                total += b.calcBalance();
            }
        }
        System.out.println("Current account holders: " + count);
        System.out.println("Total balance: " + total);
    }
}
