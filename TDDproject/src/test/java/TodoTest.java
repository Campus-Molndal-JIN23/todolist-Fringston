import org.campusmolndal.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TodoTest {
    private Todo todo;

    @BeforeEach
    public void setup() {
        todo = new Todo("Sample Todo", false);
    }

    @Test
    public void testGetId() {
        Assertions.assertEquals(2, todo.getId());
    }

    @Test
    public void testSetId() {
        todo.setId(2);
        Assertions.assertEquals(2, todo.getId());
    }

    @Test
    public void testGetText() {
        Assertions.assertEquals("Sample Todo", todo.getText());
    }

    @Test
    public void testSetText() {
        todo.setText("Updated Todo");
        Assertions.assertEquals("Updated Todo", todo.getText());
    }

    @Test
    public void testGetDone() {
        Assertions.assertFalse(todo.getDone());
    }

    @Test
    public void testSetDone() {
        todo.setDone(true);
        Assertions.assertTrue(todo.getDone());
    }
}