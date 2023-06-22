package org.campusmolndal;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.Collection;

public class MongoDBFacade {

    private String connectionString = Keyreader.readConnectionString();
    private MongoClient mongoClient;
    private MongoDatabase database;

    //Konstruktor som skapar en anslutning till databasen
    public MongoDBFacade(String connectionString, String databaseName) {
        this.connectionString = connectionString;
        mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase(databaseName);
    }

    //Metod som lägger till ett dokument i en samling
    public void insertTodo(Todo todo, String collectionName) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Document document = new Document("id", todo.getId())
                .append("text", todo.getText())
                .append("done", todo.isDone());
        collection.insertOne(document);
    }

    //Metod som hämtar ett specifikt dokument från en samling
    public Todo findTodoById(int id, String collectionName) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Document query = new Document("id", id);
        Document result = collection.find(query).first();
        if (result != null) {
            Todo todo = new Todo(result.getInteger("id"),
                    result.getString("text"),
                    result.getBoolean("done"));
            System.out.println("Document found:" + "\nTo do: " + todo.getText() + "\nDone: " + todo.isDone());
            return todo;
        }
        else {
            System.out.println("No document found with this ID");
            return null;
        }
    }

    //Metod som uppdaterar ett dokument
    public void updateTodo(String collectionName, int id, String text, boolean done) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Document query = new Document("id", id);
        Document updatedDocument = new Document("id", id)
                .append("text", text)
                .append("done", done);
        collection.updateOne(query, new Document("$set", updatedDocument));
    }

    //Metod som tar bort ett dokument från en samling
    public void deleteTodoById(int id, String collectionName) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Document query = new Document("id", id);
        collection.deleteOne(query);
    }

    //Metod som skriver ut alla dokument i en samling
    public void printCollection(String collectionName) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        FindIterable<Document> documents = collection.find();

        for (Document document : documents) {
            System.out.println(document.toJson());
        }
    }

    //Metod som stänger anslutningen till databasen
    public void close() {
        mongoClient.close();
    }
}
