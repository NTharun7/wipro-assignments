package assignment;

import java.util.ArrayList;
import java.util.List;

// Without Generics
class MediaLibrary {
    private List items = new ArrayList();

    public void add(Object item) {
        items.add(item);
    }

    public Object get(int index) {
        return items.get(index);
    }
}

// With Generics
class GenericLibrary<T> {
    private List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public T get(int index) {
        return items.get(index);
    }
}

public class Q39 {
    public static void main(String[] args) {
        // Without Generics
        MediaLibrary lib = new MediaLibrary();
        lib.add("Book: Java");
        lib.add("Video: Java Tutorial");
        lib.add("Newspaper: The Times");

        System.out.println(" Media Library (No Generics):");
        System.out.println(lib.get(0));
        System.out.println(lib.get(1));
        System.out.println(lib.get(2));

        // With Generics
        GenericLibrary<String> genLib = new GenericLibrary<>();
        genLib.add("Book: Python");
        genLib.add("Video: Python Tutorial");
        genLib.add("Newspaper: The Hindu");

        System.out.println("\n Generic Library:");
        System.out.println(genLib.get(0));
        System.out.println(genLib.get(1));
        System.out.println(genLib.get(2));
    }
}

/*
  Media Library (No Generics):
Book: Java
Video: Java Tutorial
Newspaper: The Times

 Generic Library:
Book: Python
Video: Python Tutorial
Newspaper: The Hindu

 */
