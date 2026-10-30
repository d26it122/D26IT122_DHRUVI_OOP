import model.Account;
import model.SavingsAccount;
import model.annotation.Id;
import java.lang.reflect.Field;

public class CheckId {
    public static void main(String[] args) throws Exception {

        Account account = new SavingsAccount("Test User", 1000, 500);

        Class<?> currentClass = account.getClass();

        while (currentClass != null) {

            System.out.println("Checking: " + currentClass.getName());

            for (Field field : currentClass.getDeclaredFields()) {

                System.out.println(
                    field.getName() + " -> "
                    + field.isAnnotationPresent(Id.class)
                );
            }

            currentClass = currentClass.getSuperclass();
        }
    }
}