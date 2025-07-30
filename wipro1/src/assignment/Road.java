package assignment;

class Vehicle {
    String color;
    int wheels;
    String model;

    public Vehicle(String color, int wheels, String model) {
        this.color = color;
        this.wheels = wheels;
        this.model = model;
    }

    void showDetails() {
        System.out.println("Color: " + color + ", Wheels: " + wheels + ", Model: " + model);
    }
}

class Car extends Vehicle {
    public Car(String color, int wheels, String model) {
        super(color, wheels, model);
    }

    void drive() {
        System.out.println("Car is driving");
    }
}

class Truck extends Vehicle {
    public Truck(String color, int wheels, String model) {
        super(color, wheels, model);
    }

    void loadGoods() {
        System.out.println("Truck is loading goods");
    }
}

class Bus extends Vehicle {
    public Bus(String color, int wheels, String model) {
        super(color, wheels, model);
    }

    void carryPassengers() {
        System.out.println("Bus is carrying passengers");
    }
}

public class Road {
    public static void main(String[] args) {
        Car car = new Car("Red", 4, "Swift");
        Truck truck = new Truck("Blue", 6, "Tata");
        Bus bus = new Bus("Yellow", 6, "Volvo");

        car.showDetails(); car.drive();
        truck.showDetails(); truck.loadGoods();
        bus.showDetails(); bus.carryPassengers();
    }
}
