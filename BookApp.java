import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;

import book.BookAvailable;
import book.BorrowedBook;
import book.Controller;
import user.Student;

class StudentLoop {

    /**
     * Print the menu for the logged-in student and handle commands.
     */
    static void printMenu(Student currentStudent, BufferedReader bf,
            HashMap<String, BookAvailable> availableBookMap,
            HashMap<String, BorrowedBook> borrowedBookMap) throws IOException {
        String input = "";
        String title = "";
        String fullName = currentStudent.getFullName();

        System.out.printf("\nwelcome %s! What do you want to do today? :)\n", fullName);
        while (true) {
            // Print menu
            System.out.println("1: Search for a book");
            System.out.println("2: Borrow a book");
            System.out.println("3: Return a book");
            System.out.println("q: Quit");

            input = bf.readLine().trim();

            if (input.equalsIgnoreCase("q")) {
                System.out.println("Exiting menu...");
                break; // gracefully exit menu
            }

            switch (input) {
                case "1":
                    System.out.print("Enter the title (q to quit): ");
                    title = bf.readLine().trim();
                    if (!title.equalsIgnoreCase("q")) {
                        currentStudent.bookInfo(title, availableBookMap, borrowedBookMap);
                        System.out.println("------------------------------------");
                    }
                    break;
                case "2":
                    System.out.print("Enter the title (q to quit): ");
                    title = bf.readLine().trim();
                    if (!title.equalsIgnoreCase("q")) {
                        currentStudent.borrowBook(title, availableBookMap, borrowedBookMap);
                        ;
                    }
                    System.out.println(
                            "you know had " + title + " please return it after you finish reading it, enjoy :)");
                    System.out.println("------------------------------------");
                    break;
                case "3":
                    System.out.print("Enter the title (q to quit): ");
                    title = bf.readLine().trim();
                    if (!title.equalsIgnoreCase("q")) {
                        currentStudent.returnBook(title, availableBookMap, borrowedBookMap);
                    }

                    System.out.println("you returned " + title + " thank you, please enjoy other book");
                    System.out.println("------------------------------------");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }

    /**
     * Handles student login with username and password.
     * Returns the logged-in Student or null if login fails.
     */
    static Student login(BufferedReader bf, HashMap<String, Student> studentMap) throws IOException {
        int attempts = 3;

        while (attempts > 0) {
            System.out.print("Username (q to quit): ");
            String username = bf.readLine().trim();
            if (username.equalsIgnoreCase("q"))
                return null;

            System.out.print("Password (q to quit): ");
            String password = bf.readLine().trim();
            if (password.equalsIgnoreCase("q"))
                return null;

            Student student = studentMap.get(username.toLowerCase());
            if (student != null && student.getPassword().equals(password)) {
                return student; // successful login
            } else {
                attempts--;
                System.out.println("Invalid credentials. Attempts left: " + attempts);
            }
        }

        System.out.println("No attempts left. Exiting login.");
        return null;
    }

}

public class BookApp {

    public static void main(String[] args) {

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {

            Controller controller = new Controller();

            // Load books only once at startup
            HashMap<String, BookAvailable> availableBookMap = new HashMap<>();
            HashMap<String, BorrowedBook> borrowedBookMap = new HashMap<>();
            try (FileReader f = new FileReader("files/bookfile.txt")) {
                availableBookMap = controller.availableBookToMap(f);
            }
            try (FileReader f = new FileReader("files/borrowedbook.txt")) {
                borrowedBookMap = controller.BorrowedBookToMap(f);
            }

            // Load students only once
            HashMap<String, Student> studentMap = new HashMap<>();
            try (BufferedReader br = new BufferedReader(new FileReader("files/student.txt"))) {
                String line;
                int id;
                while ((line = br.readLine()) != null) {
                    String[] arr = line.split("\\s*,\\s*");
                    try {
                        id = Integer.parseInt(arr[0]);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid student id, skipping entry.");
                        continue;
                    }
                    studentMap.put(arr[1].toLowerCase(), new Student(id, arr[1], arr[2]));
                }
            }

            // Main menu
            String line = "";
            while (true) {
                System.out.println("\nWelcome to Librerio");
                System.out.println("Are you:");
                System.out.println("1: Student");
                System.out.println("q: Quit");

                line = bf.readLine().trim();
                if (line.equalsIgnoreCase("q")) {
                    System.out.println("Exiting application...");
                    break;
                }

                switch (line) {
                    case "1":
                        Student currentStudent = StudentLoop.login(bf, studentMap);
                        if (currentStudent != null) {
                            StudentLoop.printMenu(currentStudent, bf, availableBookMap, borrowedBookMap);
                        }
                        break;
                    default:
                        System.out.println("Invalid option, try again.");
                }
            }

        } catch (IOException e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
