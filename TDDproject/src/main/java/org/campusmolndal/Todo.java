package org.campusmolndal;

public class Todo {

    // Statisk variabel för att hålla reda på det nästa tillgängliga ID:t
    private static int nextId = 1;
    private int id;
    private String text;
    private boolean done;

//Konstruktor som skapar ett nytt Todo-objekt med ett unikt ID
    public Todo(String text, boolean done) {
        this.id = nextId;
        this.text = text;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
