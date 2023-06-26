import com.mongodb.client.*;
import org.bson.Document;
import org.campusmolndal.MongoDBHandler;
import org.campusmolndal.Todo;
import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MongoDBFacadeTest {
    @Mock
    private MongoClient mockClient;

    @Mock
    private MongoDatabase mockDatabase;

    @Mock
    private MongoCollection<Document> mockCollection;

    @InjectMocks
    private MongoDBHandler mongoDBFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(mockClient.getDatabase(anyString())).thenReturn(mockDatabase);
        when(mockDatabase.getCollection(anyString())).thenReturn(mockCollection);
    }

    @AfterEach
    void tearDown() {
        mongoDBFacade.close();
    }

    @Test
    void testInsertTodo() {
        // Arrange
        Todo mockTodo = mock(Todo.class);
        when(mockTodo.getId()).thenReturn(1);
        when(mockTodo.getText()).thenReturn("Sample text");
        when(mockTodo.getDone()).thenReturn(false);

        // Act
        mongoDBFacade.insertTodo(mockTodo);

        // Assert
        verify(mockCollection, times(1)).insertOne(any(Document.class));
    }

    @Test
    void testFindTodoById() {
        // Arrange
        int id = 1;
        Document mockDocument = Document.parse("{\"id\": 1, \"text\": \"Sample text\", \"done\": false}");
        FindIterable<Document> mockFindIterable = mock(FindIterable.class);
        when(mockCollection.find(any(Document.class))).thenReturn(mockFindIterable);
        when(mockFindIterable.first()).thenReturn(mockDocument);

        // Act
        Todo result = mongoDBFacade.findTodoById(id);

        // Assert
        assertNotNull(result);
        assertEquals("Sample text", result.getText());
        assertFalse(result.getDone());
    }

    @Test
    void testUpdateTodoByID() {
        // Arrange
        int id = 1;
        String newText = "Updated text";
        boolean newDone = true;

        // Act
        mongoDBFacade.updateTodoByID(id, newText, newDone);

        // Assert
        Document expectedQuery = Document.parse("{\"id\": 1}");
        Document expectedUpdate = Document.parse("{\"id\": 1, \"text\": \"Updated text\", \"done\": true}");
        verify(mockCollection, times(1)).updateOne(eq(expectedQuery), eq(new Document("$set", expectedUpdate)));
    }

    @Test
    void testDeleteTodoById() {
        // Arrange
        int id = 1;

        // Act
        mongoDBFacade.deleteTodoById(id);

        // Assert
        Document expectedQuery = Document.parse("{\"id\": 1}");
        verify(mockCollection, times(1)).deleteOne(eq(expectedQuery));
    }
}