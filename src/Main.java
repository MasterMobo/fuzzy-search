import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Main {
    public static void main(String[] args) {
        BKTree tree = new BKTree();
        try {
            File file = new File("./src/words.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                tree.insert(data);
            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(tree.search("ass", 2));
    }
}