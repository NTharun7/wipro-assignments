package assignment;

import assignment.house.Hall;
import assignment.house.Kitchen;

public class Q23 {
    public static void main(String[] args) {
        Hall hall = new Hall();
        hall.show();

        Kitchen kitchen = new Kitchen();
        kitchen.showAppliances();
    }
}
/*
This is the first room while entering the house
Appliances:
Fridge
Microwave
Toaster
Copied appliances:
Fridge
Microwave
Toaster
*/