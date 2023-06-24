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
    private MongoDatabase database;
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
        Todo todo = new Todo("Sample Todo", false);

        when(database.getCollection(anyString())).thenReturn(collection);
        doNothing().when(collection).insertOne(any(Document.class));

        assertDoesNotThrow(() -> mongoDBFacade.insertTodo(todo));

        verify(database).getCollection("Cluster0");
        verify(collection).insertOne(any(Document.class));
    }

    @Test
    void testPrintCollection() {
        List<Document> documents = new ArrayList<>();
        documents.add(new Document("id", 1).append("text", "Sample Todo").append("done", false));

        when(database.getCollection(anyString())).thenReturn("Cluster0"");
        when(collection.find()).thenReturn(documents);
        doNothing().when(System.out).println(anyString());

        assertDoesNotThrow(() -> mongoDBFacade.printCollection());

        verify(database).getCollection("Cluster0");
        verify(collection).find();
        verify(System.out, times(1)).println(anyString());
    }

    @Test
    void testFindTodoById() {
        Document document = new Document("id", 1).append("text", "Sample Todo").append("done", false);

        when(database.getCollection(anyString())).thenReturn(collection);
        when(collection.find(any(Document.class))).thenReturn(new ArrayList<>(List.of(document)));

        Todo result = mongoDBFacade.findTodoById(1);

        assertNotNull(result);
        assertEquals("Sample Todo", result.getText());
        assertFalse(result.isDone());

        verify(database).getCollection("Cluster0");
        verify(collection).find(any(Document.class));
        verify(System.out, times(1)).println(anyString());
    }

}