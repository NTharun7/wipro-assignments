package assignment;

class Worker {
    String name;
    double salaryRate;

    Worker(String name, double rate) {
        this.name = name;
        this.salaryRate = rate;
    }

    double pay(int hours) {
        return hours * salaryRate;
    }
}

class DailyWorker extends Worker {
    DailyWorker(String name, double rate) {
        super(name, rate);
    }

    @Override
    double pay(int days) {
        return days * salaryRate;
    }
}

class SalariedWorker extends Worker {
    SalariedWorker(String name, double rate) {
        super(name, rate);
    }

    @Override
    double pay(int hours) {
        return 40 * salaryRate;
    }
}

public class WorkerTest {
    public static void main(String[] args) {
        DailyWorker d = new DailyWorker("Ravi", 500);
        SalariedWorker s = new SalariedWorker("Kiran", 700);
        System.out.println("Daily Worker Pay: " + d.pay(6));     // 6 days
        System.out.println("Salaried Worker Pay: " + s.pay(50)); // 50 hours ignored, fixed 40 * rate
    }
}
