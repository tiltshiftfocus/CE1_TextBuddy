import java.io.File;
import java.io.IOException;

public class TextBuddy {

    public static void main(String[] args) {
	    File currentFile = openFile(args[0]);
        System.out.println("Welcome to TextBuddy. " + args[0] + " is ready for use.");
    }

    private static File openFile(String fileName) {
        File file = new File(fileName);

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error opening file.");
                System.exit(1);
            }
        }
        return file;
    }
}
