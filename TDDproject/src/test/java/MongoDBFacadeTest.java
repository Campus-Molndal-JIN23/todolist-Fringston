import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.campusmolndal.MongoDBFacade;
import org.campusmolndal.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.swing.text.Document;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MongoDBFacadeTest {

    @Mock
    private MongoClient mongoClient;
    @Mock
    private MongoDatabase mockDatabase;
    @Mock
    private MongoCollection<Document> collection;

    private MongoDBFacade mongoDBFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mongoDBFacade = new MongoDBFacade();
    }

    @Test
    void testInsertTodo() {
    }
    @Test
    void testPrintCollection() {
    }
    @Test
    void testFindTodoById() {
    }
    @Test
    void testUpdateTodoByID() {
    }
    @Test
    void testDeleteTodoByID() {
    }

}