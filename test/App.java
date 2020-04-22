import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class App {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        String l = scan.next();
        scan.close();
        if (s.equals("lol") && l.equals("fos")) {
            try {
                File myObj = new File("0-in.txt");
                Scanner myReader = new Scanner(myObj);
                PrintWriter writer = new PrintWriter("0-out.txt", "UTF-8");

                while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                writer.println(data);
                }
                myReader.close();
                writer.close();
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
}