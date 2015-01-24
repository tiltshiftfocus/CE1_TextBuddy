import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TextBuddy {

    public static void main(String[] args) {
	    File currentFile = openFile(args[0]);
        Scanner sc = new Scanner(System.in);

        showWelcomeMessage(args[0]);

        while(true) {
            System.out.print("command: ");
            String userCommand = sc.nextLine();
            processCommand(userCommand);
        }

    }

    private static void processCommand(String userCommand) {
        String command = getFirstWord(userCommand);

        if(command.equals("add")){

        }
    }

    private static void showWelcomeMessage(String arg) {
        System.out.println("Welcome to TextBuddy. " + arg + " is ready for use.");
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

    private static String removeFirstWord(String userCommand) {
        return userCommand.replace(getFirstWord(userCommand), "").trim();
    }

    private static String getFirstWord(String userCommand) {
        return userCommand.trim().split("\\s+")[0];
    }
}
