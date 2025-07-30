package assignment;

class Shape {
    void area(int side) {
        System.out.println("Area of Square: " + (side * side));
    }

    void area(int length, int breadth) {
        System.out.println("Area of Rectangle: " + (length * breadth));
    }

    void perimeter(int side) {
        System.out.println("Perimeter of Square: " + (4 * side));
    }

    void perimeter(int length, int breadth) {
        System.out.println("Perimeter of Rectangle: " + (2 * (length + breadth)));
    }
}

public class ShapeTest {
    public static void main(String[] args) {
        Shape s = new Shape();
        s.area(5);                 // Square
        s.area(5, 10);             // Rectangle
        s.perimeter(5);            // Square
        s.perimeter(5, 10);        // Rectangle
    }
}
