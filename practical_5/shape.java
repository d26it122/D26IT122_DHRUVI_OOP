abstract class shape {

    abstract double area();
        public static void main(String[] args) {

        shape[] shapes = {
            new Circle(2),
            new Rectangle(4, 3),
            new Triangle(5, 2)
        };

        double total = 0;
        double largest = 0;

        for (int i = 0; i < shapes.length; i++) {

            double a = shapes[i].area();

            System.out.println("Area = " + a);

            total = total + a;

            if (a > largest) {
                largest = a;
            }
        }

        System.out.println("Total Area = " + total);
        System.out.println("Largest Area = " + largest);
    }
}


class Circle extends shape {

    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    double area() {
        return 3.14 * radius * radius;
    }
}


class Rectangle extends shape {

    double length;
    double width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    double area() {
        return length * width;
    }
}


class Triangle extends shape {

    double base;
    double height;

    Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    double area() {
        return 0.5 * base * height;
    }
}


