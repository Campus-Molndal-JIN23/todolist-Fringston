package org.campusmolndal;
import com.mongodb.client.*;
import org.bson.Document;

public class MongoDBFacade {

    private String connectionString = Keyreader.readConnectionString();
    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoDBFacade(String connectionString, String databaseName) {
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

    public Todo findTodoById(int id, String collectionName) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Document query = new Document("id", id);
        Document result = collection.find(query).first();
        if (result != null) {
            return new Todo(result.getInteger("id"),
                    result.getString("text"),
                    result.getBoolean("done"));
        }
        return null;
    }

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
