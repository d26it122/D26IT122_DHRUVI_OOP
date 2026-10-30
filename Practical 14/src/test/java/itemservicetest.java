import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class itemservicetest {

    @Test
    public void testAddItemIncreasesCount() {
        itemservice service = new itemservice();

        service.addItem("Pen");

        assertEquals(1, service.getCount());
    }

    @Test
    public void testInvalidItemThrowsException() {
        itemservice service = new itemservice();

        assertThrows(IllegalArgumentException.class, () -> {
            service.addItem("");
        });
    }

    @Test
    public void testAddingTwoItems() {
        itemservice service = new itemservice();

        service.addItem("Pen");
        service.addItem("Notebook");

        assertEquals(2, service.getCount());
    }
}