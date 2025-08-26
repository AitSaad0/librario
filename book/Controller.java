package book;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Controller {
    public HashMap<String, BookAvailable> availableBookToMap(FileReader filename) {
        HashMap<String, BookAvailable> map = new HashMap<>();
        int year = 0;
        Category category = Category.UNKNOWN;

        try (BufferedReader br = new BufferedReader(filename)) {
            String str;
            while ((str = br.readLine()) != null) {
                String[] arr = str.split("\\s*,\\s*");
                switch (arr[4]) {
                    case "SCIFY":
                        category = Category.SCIFY;
                        break;
                    case "FANTASY":
                        category = Category.FANTASY;
                        break;
                    case "SCIENCE":
                        category = Category.SCIENCE;
                        break;
                    case "STORY":
                        category = Category.STORY;
                        break;
                    default:
                        category = Category.UNKNOWN;
                }
                try {
                    year = Integer.parseInt(arr[2]);
                } catch (NumberFormatException e) {
                    year = 0;
                    // i want to call the menu object or try somthing else you should design a
                    // higher-level mechanism (like throwing a custom exception or calling a
                    // callback
                }
                map.put(arr[0].toLowerCase(), new BookAvailable(arr[0], arr[1], year, category));
            }

        } catch (FileNotFoundException e) {
            System.out.println("❌ Couldn't open the file: " + filename);
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You try to access a value out of bound, see the borrowed book file");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public void fromAvailbleToBorrowed(HashMap<String, BorrowedBook> borrowedMap,
            HashMap<String, BookAvailable> availableBookMap, String title, String borrower) {
        if (availableBookMap.containsKey(title)) {
            BookAvailable book = availableBookMap.remove(title);
            borrowedMap.put(title,
                    new BorrowedBook(title, book.getAuthor(), book.getYear(), book.getCategory(), borrower));

        } else {
            if (borrowedMap.containsKey(title)) {
                System.out.println("Book already borrowed");

            } else {
                System.out.println("Book doesn't exist");
                System.exit(1); // i will change that
            }
        }
    }

    


    public HashMap<String, BorrowedBook> BorrowedBookToMap(FileReader filename) {
        HashMap<String, BorrowedBook> map = new HashMap<>();
        int year = 0;
        Category category = Category.UNKNOWN;

        try (BufferedReader br = new BufferedReader(filename)) {
            String str;
            while ((str = br.readLine()) != null) {
                String[] arr = str.split("\\s*,\\s*");
                switch (arr[4]) {
                    case "SCIFY":
                        category = Category.SCIFY;
                        break;
                    case "FANTASY":
                        category = Category.FANTASY;
                        break;
                    case "SCIENCE":
                        category = Category.SCIENCE;
                        break;
                    case "STORY":
                        category = Category.STORY;
                        break;
                    default:
                        category = Category.UNKNOWN;
                }
                try {
                    year = Integer.parseInt(arr[2]);
                } catch (NumberFormatException e) {
                    System.out.println("enter a valid year");
                    year = 0; // i want to call the menu object or try somthing else you should design a
                              // higher-level mechanism (like throwing a custom exception or calling a
                              // callback
                }
                map.put(arr[0].toLowerCase(), new BorrowedBook(arr[0], arr[1], year, category, arr[5]));
            }

        } catch (FileNotFoundException e) {
            System.out.println("❌ Couldn't open the file: " + filename);
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You try to access a value out of bound, see the borrowed book file");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public void fromBorrowedToAvailble(HashMap<String, BorrowedBook> borrowedMap,
            HashMap<String, BookAvailable> availableBookMap, String title) {
        if (borrowedMap.containsKey(title)) {
            BorrowedBook borrowedBook = borrowedMap.remove(title);
            availableBookMap.put(title, new BookAvailable(title, borrowedBook.getAuthor(), borrowedBook.getYear(),
                    borrowedBook.getCategory()));

        } else {
            if (availableBookMap.containsKey(title)) {
                BookAvailable book = availableBookMap.get(title);
                if (book.getAvailability() == Availability.AVAILABLE) {
                    System.out.println("The book isn't borrowed");
                } else {
                    book.setAvailability(Availability.AVAILABLE);
                    availableBookMap.put(title, book);
                }
            } else {
                System.out.println("Book doesn't exist");
                System.exit(1); // i will change that
            }
        }
    }

}
