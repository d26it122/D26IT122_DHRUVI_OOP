import java.util.*;

class Box<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}

class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }
}

public class genericcontainer {

    public static <T> T firstElement(T[] array) {
        return array[0];
    }

    public static void main(String[] args) {

        // Generic Box
        Box<Integer> b1 = new Box<>();
        b1.set(100);
        System.out.println("Box Integer: " + b1.get());

        Box<String> b2 = new Box<>();
        b2.set("Java");
        System.out.println("Box String: " + b2.get());

        // Generic Pair
        Pair<String, Integer> p1 =
                new Pair<>("dhruvi", 20);

        System.out.println("Pair First: " + p1.getFirst());
        System.out.println("Pair Second: " + p1.getSecond());

        // Generic method
        String[] names = {"dhruvii", "Riya", "priya"};
        Integer[] numbers = {10, 20, 30};

        System.out.println("First Name: " +
                firstElement(names));

        System.out.println("First Number: " +
                firstElement(numbers));
    }
}