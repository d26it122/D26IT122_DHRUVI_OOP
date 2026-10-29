import java.util.Scanner;

class DivideByZeroException extends Exception {
    public DivideByZeroException(String message) {
        super(message);
    }
}

public class gradedcalc {

    static double calculate(double a, double b, char op)
            throws DivideByZeroException {

        if (op == '/' && b == 0) {
            throw new DivideByZeroException("Cannot divide by zero.");
        }

        switch (op) {
            case '+':
                return a + b;

            case '-':
                return a - b;

            case '*':
                return a * b;

            case '/':
                return a / b;

            default:
                throw new IllegalArgumentException("Invalid operator.");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean success = false;

        while (!success) {
            try {
                System.out.print("Enter first number: ");
                double a = Double.parseDouble(sc.nextLine());

                System.out.print("Enter second number: ");
                double b = Double.parseDouble(sc.nextLine());

                System.out.print("Enter operator (+, -, *, /): ");
                char op = sc.nextLine().charAt(0);

                double result = calculate(a, b, op);

                System.out.println("Result = " + result);
                success = true;

            } catch (NumberFormatException e) {
                System.out.println("Invalid number! Please enter numbers only.");

            } catch (DivideByZeroException e) {
                System.out.println(e.getMessage());

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());

            } finally {
                System.out.println("Attempt logged.");
                System.out.println("--------------------");
            }
        }

        sc.close();
    }
}