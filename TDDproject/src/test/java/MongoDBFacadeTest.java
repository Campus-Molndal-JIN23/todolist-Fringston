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
    private MongoDatabase mockDatabase;
    @Mock
    private MongoCollection<Document> mockCollection;
    @Mock
    private Document mockDocument;

    private MongoDBFacade mongoDBFacade;
    @BeforeEach
    void setup() {

        MongoDBFacade mongoDBFacadeMock = mock(MongoDBFacade.class);
    }
    @Test
    void testInsertTodo_ShouldInsertDocumentInCollection() {
        //Arrange
        when(mockDatabase.getCollection("Cluster0")).thenReturn(mockCollection);
        when(mockTodo.getId()).thenReturn(1);
        when(mockTodo.getText()).thenReturn("Sample text");
        when(mockTodo.isDone()).thenReturn(false);

        //Act
        mongoDBFacade.insertTodo(mockTodo);

        //Assert
        verify(mockCollection, times(1)).insertOne(mockDocument);
        verify(mockDocument, times(1)).append("id", 1);
        verify(mockDocument, times(1)).append("text", "Sample text");
        verify(mockDocument, times(1)).append("done", false);
    }

    @Test
    void testPrintCollection_ShouldPrintAllDocuments() {
        //Arrange
        when(mockDatabase.getCollection("Cluster0")).thenReturn(mockCollection);
        when(mockCollection.find()).thenReturn(mockIterable(mockDocument));

        //Act
        mongoDBFacade.printCollection();

        //Assert
        verify(mockCollection, times(1)).find();
        verify(mockDocument, times(1)).toJson();
    }

    @Test
    void testFindTodoById_WithExistingId_ShouldReturnTodo() {
        //Arrange
        int id = 1;
        when(mockDatabase.getCollection("Cluster0")).thenReturn(mockCollection);
        when(mockCollection.find(any(Document.class))).thenReturn(mockIterable(mockDocument));
        when(mockDocument.getString("text")).thenReturn("Sample text");
        when(mockDocument.getBoolean("done")).thenReturn(false);

        //Act
        Todo result = mongoDBFacade.findTodoById(id);

        //Assert
        verify(mockCollection, times(1)).find(any(Document.class));
        verify(mockDocument, times(1)).getString("text");
        verify(mockDocument, times(1)).getBoolean("done");
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Sample text", result.getText());
        Assertions.assertFalse(result.isDone());
    }

    @Test
    void testFindTodoById_WithNonExistingId_ShouldReturnNull() {
        //Arrange
        int id = 1;
        when(mockDatabase.getCollection("Cluster0")).thenReturn(mockCollection);
        when(mockCollection.find(any(Document.class))).thenReturn(mockIterable());

        //Act
        Todo result = mongoDBFacade.findTodoById(id);

        //Assert
        verify(mockCollection, times(1)).find(any(Document.class));
        Assertions.assertNull(result);
    }

    @Test
    void testUpdateTodoByID_ShouldUpdateDocumentInCollection() {
        // Arrange
        int id = 1;
        String text = "Updated Todo";
        boolean done = true;
        Document query = new Document("id", id);
        Document updatedDocument = new Document("id", id)
                .append("text", text)
                .append("done", done);

        when(mockDatabase.getCollection("Cluster0")).thenReturn(mockCollection);

        // Act
        mongoDBFacade.updateTodoByID(id, text, done);

        // Assert
        verify(mockCollection).updateOne(query, new Document("$set", updatedDocument));
    }

    @Test
    void testDeleteTodoById_ShouldDeleteDocumentFromCollection() {
        // Arrange
        int id = 1;
        Document query = new Document("id", id);

        when(mockDatabase.getCollection("Cluster0")).thenReturn(mockCollection);

        // Act
        mongoDBFacade.deleteTodoById(id);

        // Assert
        verify(mockCollection).deleteOne(query);
    }

    @Test
    void close_ShouldCloseMongoClientConnection() {
        // Arrange

        // Act
        mongoDBFacade.close();

        // Assert
        verify(mockDatabase).close();
    }
}