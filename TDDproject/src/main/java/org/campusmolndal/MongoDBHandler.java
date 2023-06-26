package org.campusmolndal;
import com.mongodb.client.*;
import org.bson.Document;

public class MongoDBHandler {

    private String connectionString = Keyreader.readConnectionString();
    private MongoClient mongoClient;
    private MongoDatabase database;

    //Konstruktor som skapar en anslutning till databasen
    public MongoDBHandler() {
        mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase("TODO");
    }

    //Metod som lägger till ett nytt dokument i samlingen
    public void insertTodo(Todo todo) {
        MongoCollection<Document> collection = database.getCollection("Cluster0");
        Document document = new Document("id", todo.getId())
                .append("text", todo.getText())
                .append("done", todo.getDone());
        collection.insertOne(document);
    }

    //Metod som hämtar och visar alla dokument i samlingen
    public void printCollection() {
        MongoCollection<Document> collection = database.getCollection("Cluster0");
        FindIterable<Document> documents = collection.find();
        for (Document document : documents) {
            System.out.println(document.toJson());
        }
    }

    //Metod som hämtar och visar ett specifikt dokument från samlingen
    public Todo findTodoById(int id) {
        MongoCollection<Document> collection = database.getCollection("Cluster0");
        Document query = new Document("id", id);
        Document result = collection.find(query).first();
        if (result != null) {
            Todo todo = new Todo(result.getString("text"),
                    result.getBoolean("done"));
            System.out.println("\nDocument " + id + " found:" + "\nTo do: " + todo.getText() + "\nDone: " + todo.getDone());
            return todo;
        }
        else {
            return null;
        }
    }

    //Metod som uppdaterar ett dokument i samlingen
    public void updateTodoByID(int id, String text, boolean done) {
        MongoCollection<Document> collection = database.getCollection("Cluster0");
        Document query = new Document("id", id);
        Document updatedDocument = new Document("id", id)
                .append("text", text)
                .append("done", done);
        collection.updateOne(query, new Document("$set", updatedDocument));
    }

    //Metod som tar bort ett dokument från samlingen
    public void deleteTodoById(int id) {
        MongoCollection<Document> collection = database.getCollection("Cluster0");
        Document query = new Document("id", id);
        collection.deleteOne(query);
    }

    //Metod som stänger anslutningen till databasen
    public void close() {
        mongoClient.close();
    }
}
