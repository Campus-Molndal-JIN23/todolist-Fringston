package org.campusmolndal;
import java.util.Scanner;

public class Menu {
    MongoDBFacade mongoDBFacade;
    Scanner scanner;

    public void displayMenu() {

        boolean running = true;

        while (running) {
            System.out.println("Welcome!");
            System.out.println("What would you like to do?\n");
            System.out.println("1. Create Todo");
            System.out.println("2. Show all existing Todos");
            System.out.println("3. Show specific Todo");
            System.out.println("4. Update a Todo");
            System.out.println("5. Delete a Todo");
            System.out.println("6. Exit\n");

            int choice = new Scanner(System.in).nextInt();

            switch (choice) {
                case 1:
                    createTodo();
                    break;
                case 2:
                    displayAllTodos();
                    break;
                case 3:
                    displaySpecificTodo();
                    break;
                case 4:
                    updateTodo();
                    break;
                case 5:
                    deleteTodo();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        }

        // Stäng scanner efter att menyn avslutas
        scanner.close();
    }

    private void createTodo() {
        System.out.println("Enter Todo text: ");
        String text = scanner.nextLine();
        System.out.println("Enter Todo status (true/false): ");
        boolean done = scanner.nextBoolean();
        try {
            Todo todo = new Todo(text, done);
            mongoDBFacade.insertTodo(todo);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private void displayAllTodos() {
    }

    private void displaySpecificTodo() {
    }

    private void updateTodo() {
    }

    private void deleteTodo() {
    }
}