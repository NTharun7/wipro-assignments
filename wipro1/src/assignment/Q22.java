package assignment;

interface Drawable {
    void drawingColor();
    void thickness();
}

interface Fillable {
    void fillingColor();
    void size();
}

class Line implements Drawable {
    public void drawingColor() {
        System.out.println("Line drawing color: Black");
    }

    public void thickness() {
        System.out.println("Line thickness: 1px");
    }
}

class Circle implements Drawable, Fillable {
    public void drawingColor() {
        System.out.println("Circle drawing color: Blue");
    }

    public void thickness() {
        System.out.println("Circle thickness: 2px");
    }

    public void fillingColor() {
        System.out.println("Circle filling color: Yellow");
    }

    public void size() {
        System.out.println("Circle size: Radius 10");
    }
}

class Square implements Drawable, Fillable {
    public void drawingColor() {
        System.out.println("Square drawing color: Green");
    }

    public void thickness() {
        System.out.println("Square thickness: 3px");
    }

    public void fillingColor() {
        System.out.println("Square filling color: Red");
    }

    public void size() {
        System.out.println("Square size: Side 5");
    }
}

public class Q22 {
    public static void main(String[] args) {
        Drawable line = new Line();
        line.drawingColor();
        line.thickness();

        Circle circle = new Circle();
        circle.drawingColor();
        circle.thickness();
        circle.fillingColor();
        circle.size();
    }
}
/*
Line drawing color: Black
Line thickness: 1px
Circle drawing color: Blue
Circle thickness: 2px
Circle filling color: Yellow
Circle size: Radius 10
*/
