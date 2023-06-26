package org.campusmolndal;

public class Todo {
    private static int nextId = 1;
    private int id;
    private String text;
    private boolean done;

//Konstruktor som skapar ett nytt Todo-objekt
    public Todo(String text, boolean done) {
        this.id = nextId++;
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

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Text: " + text + ", Done: " + done;
    }
}
