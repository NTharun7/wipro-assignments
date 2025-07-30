package assignment;

abstract class Person {
    abstract void eat();
    abstract void exercise();
}

class Athlete extends Person {
    public void eat() {
        System.out.println("Athlete eats a protein-rich diet.");
    }

    public void exercise() {
        System.out.println("Athlete exercises every day.");
    }
}

class LazyPerson extends Person {
    public void eat() {
        System.out.println("Lazy person eats junk food.");
    }

    public void exercise() {
        System.out.println("Lazy person rarely exercises.");
    }
}

public class Q21 {
    public static void main(String[] args) {
        Person p1 = new Athlete();
        Person p2 = new LazyPerson();

        p1.eat();
        p1.exercise();

        p2.eat();
        p2.exercise();
    }
}


/*
Athlete eats a protein-rich diet.
Athlete exercises every day.
Lazy person eats junk food.
Lazy person rarely exercises.
*/