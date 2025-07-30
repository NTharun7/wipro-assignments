package assignment.house;

public class Kitchen {
    public void showAppliances() {
        String[] appliances = {"Fridge", "Microwave", "Toaster"};

        System.out.println("Appliances:");
        for (String a : appliances) {
            System.out.println(a);
        }

        // Copy the array
        String[] copy = appliances.clone();
        System.out.println("Copied appliances:");
        for (String c : copy) {
            System.out.println(c);
        }
    }
}
