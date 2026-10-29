import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

// NotBlank annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {
}

// MaxLength annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

// Signup form
class SignupForm {
    @NotBlank
    private String username;

    @NotBlank
    @MaxLength(10)
    private String password;

    @MaxLength(30)
    private String email;

    public SignupForm(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}

// Validator class
class Validator {

    public static List<String> validate(Object obj)
            throws IllegalAccessException {

        List<String> errors = new ArrayList<>();

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            Object value = field.get(obj);
            String name = field.getName();

            if (field.isAnnotationPresent(NotBlank.class)) {
                if (value == null || value.toString().trim().isEmpty()) {
                    errors.add(name + " must not be blank");
                }
            }

            if (field.isAnnotationPresent(MaxLength.class)) {
                MaxLength annotation = field.getAnnotation(MaxLength.class);

                if (value != null &&
                    value.toString().length() > annotation.value()) {

                    errors.add(name + " must not exceed "
                            + annotation.value() + " characters");
                }
            }
        }

        return errors;
    }
}

public class formvalidator {
    public static void main(String[] args)
            throws IllegalAccessException {

        SignupForm form =
                new SignupForm("", "123456789012", "vi@example.com");

        List<String> errors = Validator.validate(form);

        if (errors.isEmpty()) {
            System.out.println("Form is valid");
        } else {
            System.out.println("Validation Errors:");

            for (String error : errors) {
                System.out.println(error);
            }
        }
    }
}