
package service;

import model.annotation.Id;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class Repository<T> {

    private final HashMap<String, T> items = new HashMap<>();

    public T save(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }

        try {
            Class<?> currentClass = entity.getClass();

            while (currentClass != null) {
                for (Field field : currentClass.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Id.class)) {
                        field.setAccessible(true);

                        Object id = field.get(entity);

                        if (id == null) {
                            throw new IllegalArgumentException(
                                "@Id field cannot be null"
                            );
                        }

                        items.put(id.toString(), entity);
                        return entity;
                    }
                }

                currentClass = currentClass.getSuperclass();
            }

        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(
                "Unable to read @Id field", e
            );
        }

        throw new IllegalArgumentException(
            "No field annotated with @Id in "
            + entity.getClass().getName()
        );
    }

    @SafeVarargs
    public final void saveAll(T... entities) {
        for (T item : entities) {
            save(item);
        }
    }

    public T findById(String id) {
        return items.get(id);
    }

    public ArrayList<T> findAll() {
        return new ArrayList<>(items.values());
    }

    public boolean delete(String id) {
        return items.remove(id) != null;
    }

    public static <N extends Number> double average(N[] values) {
        if (values == null || values.length == 0) {
            return 0;
        }

        double sum = 0;

        for (N number : values) {
            sum += number.doubleValue();
        }

        return sum / values.length;
    }
}
