import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BookApp {
    public static void main(String args[]) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String Line;
        try {
            while ((Line = bf.readLine()) != "q") {
                int i = 3;
                do{
                    System.out.println("Welcome to Librerio where you find, know, and borrow books");

                }while(i>0);
               

            }
        } catch (IOException e) {

        }
    }
}
