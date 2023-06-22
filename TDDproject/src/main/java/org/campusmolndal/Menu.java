package org.campusmolndal;
import java.util.Scanner;

public class Menu {
    MongoDBFacade mongoDBFacade;
    Scanner scanner;

    public Menu(MongoDBFacade mongoDBFacade) {
        this.mongoDBFacade = mongoDBFacade;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {

        boolean running = true;

        while (running) {
            System.out.println("\nWelcome to the app where you can store your to do´s!");
            System.out.println("What would you like to do?\n");
            System.out.println("1. Create a to-do");
            System.out.println("2. Show all existing to-do´s");
            System.out.println("3. Show a specific to-do");
            System.out.println("4. Update an already existing to-do");
            System.out.println("5. Delete a to-do");
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
        scanner.close();
    }

    private void createTodo() {
        System.out.println("Enter Todo text: ");
        String text = scanner.nextLine();
        boolean done = false;
        boolean validInput = false;
        while (!validInput) {
            System.out.println("Enter current status (true/false): ");
            String doneString = scanner.nextLine().toLowerCase();
            if (doneString.equals("true")) {
                done = true;
                validInput = true;
            } else if (doneString.equals("false")) {
                done = false;
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter either 'true' or 'false'.");
            }
        }
        try {
            Todo todo = new Todo(text, done);
            mongoDBFacade.insertTodo(todo);
            System.out.println("Todo successfully created! Your Todo ID is: " + todo.getId());
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private void displayAllTodos() {
        try {
            mongoDBFacade.printCollection();
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private void displaySpecificTodo() {
        System.out.println("Enter to-do´s ID: ");
        int id = scanner.nextInt();
        try {
            mongoDBFacade.findTodoById(id);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private void updateTodo() {
        System.out.println("Enter to-do´s ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new text: ");
        String text = scanner.nextLine();

        System.out.println("Enter new status (true/false): ");
        boolean done = scanner.nextBoolean();
        try {
            Todo todo = new Todo(text, done);
            mongoDBFacade.updateTodoByID(id, text, done);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private void deleteTodo() {
        System.out.println("Enter to-do´s ID: ");
        int id = scanner.nextInt();
        try {
            Todo todo = mongoDBFacade.findTodoById(id);
            if (todo != null) {
            mongoDBFacade.deleteTodoById(id);
            System.out.println("To-do with ID number " + id + " has been deleted.");
            } else {
                System.out.println("No to-do found with ID number " + id);
            }
            } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}