package org.campusmolndal;
import com.mongodb.client.*;
import org.bson.Document;

public class MongoDBFacade {

    private String connectionString = Keyreader.readConnectionString();
    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoDBFacade(String connectionString, String databaseName) {
        this.connectionString = connectionString;
        mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase(databaseName);
    }

    public void insertTodo(Todo todo, String collectionName) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Document document = new Document("id", todo.getId())
                .append("text", todo.getText())
                .append("done", todo.isDone());
        collection.insertOne(document);
    }

    //Inte klar
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

    //Inte klar
    public void updateTodoById(int id, Todo updatedTodo, String collectionName) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Document query = new Document("id", id);
        Document updatedDocument = new Document("id", updatedTodo.getId())
                .append("text", updatedTodo.getText())
                .append("done", updatedTodo.isDone());
        collection.updateOne(query, new Document("$set", updatedDocument));
    }

    public void deleteTodoById(int id, String collectionName) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Document query = new Document("id", id);
        collection.deleteOne(query);
    }

    public void printCollection(String collectionName) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        FindIterable<Document> documents = collection.find();

        for (Document document : documents) {
            System.out.println(document.toJson());
        }
    }

    public void close() {
        mongoClient.close();
    }
}
