package org.campusmolndal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Keyreader {

    static String userHome = System.getProperty("user.home");

    public static String readConnectionString() {
        try {
            FileInputStream input = new FileInputStream(userHome + "/OneDrive/Dokument/Keys/TODOKEY.txt");
            Scanner scanner = new Scanner(input);
            String connectionString = scanner.nextLine();
            scanner.close();
            return connectionString;
        } catch (FileNotFoundException e) {
            return "mongodb://localhost:27017";
        }
    }
}
