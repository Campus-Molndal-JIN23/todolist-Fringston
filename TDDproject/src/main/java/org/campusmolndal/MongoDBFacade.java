package org.campusmolndal;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBFacade {

    private String connectionString = Keyreader.readConnectionString();
    private MongoClient mongoClient;
    private MongoDatabase database;
    public MongoDBFacade(String connectionString, String databaseName) {
        mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase(databaseName);
    }

    public void insertDocument(String collectionName, Document document) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.insertOne(document);
    }

    public Document findDocument(String collectionName, Document query) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        return collection.find(query).first();
    }

    public void updateDocument(String collectionName, Document query, Document updatedDocument) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.updateOne(query, new Document("$set", updatedDocument));
    }

    public void deleteDocument(String collectionName, Document query) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.deleteOne(query);
    }

    public void close() {
        mongoClient.close();
    }
}
