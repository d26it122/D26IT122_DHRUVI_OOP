import java.util.Scanner;

public class driver2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter password: ");
        String pw = sc.nextLine();

        String result = passwordcheker.strength(pw);

        System.out.println("Password: " + pw);
        System.out.println("Strength: " + result);

        sc.close();
    }
}
    

