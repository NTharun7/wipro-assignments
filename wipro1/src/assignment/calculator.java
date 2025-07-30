package assignment;

public class calculator {
	void add(int a, int b) {
        System.out.println("Addition: " + (a + b));
    }

    void diff(int a, int b) {
        System.out.println("Difference: " + (a - b));
    }

    void mul(int a, int b) {
        System.out.println("Multiplication: " + (a * b));
    }

    void div(int a, int b) {
        if (b != 0) {
            System.out.println("Division: " + (a / b));
        } else {
            System.out.println("Cannot divide by zero");
        }
    }

    public static void main(String[] args) {
        calculator calc = new calculator();
        calc.add(10, 5);
        calc.diff(10, 5);
        calc.mul(10, 5);
        calc.div(10, 5);
    }

}
