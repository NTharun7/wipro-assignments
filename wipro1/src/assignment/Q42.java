package assignment;

public class Q42 {

    static void someMethod2() throws Exception {
        throw new Exception("Exception from someMethod2");
    }

    static void someMethod() throws Exception {
        try {
            someMethod2();
        } catch (Exception e) {
            System.out.println("Caught in someMethod: " + e.getMessage());
            throw e; // rethrowing
        }
    }

    public static void main(String[] args) {
        try {
            someMethod();
        } catch (Exception e) {
            System.out.println("Caught in main:");
            e.printStackTrace();
        }
    }
}
