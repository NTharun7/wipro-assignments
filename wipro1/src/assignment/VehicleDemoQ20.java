package assignment;



abstract class VehicleQ20 {
 abstract void startEngine();
 abstract void stopEngine();
}

class CarQ20 extends VehicleQ20 {
 @Override
 void startEngine() {
     System.out.println("Car engine started.");
 }

 @Override
 void stopEngine() {
     System.out.println("Car engine stopped.");
 }
}
class MotorcycleQ20 extends VehicleQ20 {
 @Override
 void startEngine() {
     System.out.println("Motorcycle engine started.");
 }

 @Override
 void stopEngine() {
     System.out.println("Motorcycle engine stopped.");
 }
}

public class VehicleDemoQ20 {
 public static void main(String[] args) {
     VehicleQ20 car = new CarQ20();
     VehicleQ20 motorcycle = new MotorcycleQ20();

     car.startEngine();
     car.stopEngine();

     motorcycle.startEngine();
     motorcycle.stopEngine();
 }
}
