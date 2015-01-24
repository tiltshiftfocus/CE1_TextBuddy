import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
            executeCommand(userCommand, currentFile);
        }

    }

    private static void executeCommand(String userCommand, File currentFile) {
        String command = getFirstWord(userCommand);
        
        if(command.equals("add")){
        	addToFile(userCommand, currentFile);
        }else if(command.equals("display")){
        	displayFile(currentFile);
        }
    }

	private static void displayFile(File currentFile) { 
		try {
			BufferedReader inputFile = new BufferedReader(new 
					FileReader(currentFile.getName()));
			
			String line;
			int numberOfLines = 0;
			while((line = inputFile.readLine()) != null){
				numberOfLines++;
				System.out.println(numberOfLines + ". " + line);
			}
			
		} catch (Exception e) {
			System.out.println("Unable to read file");
		}
	}

	private static void addToFile(String userCommand, File currentFile) {
		String textToAdd = removeFirstWord(userCommand);
		
		BufferedWriter outToFile;
		
		try {
			outToFile = new BufferedWriter(new 
					FileWriter(currentFile.getName(),true));
			
			if(!isFileEmpty(currentFile)){
				outToFile.newLine();
			}
			
			outToFile.write(textToAdd);
			outToFile.close();
			System.out.println("added to " + currentFile.getName()
					+ ": \"" + textToAdd + "\"");
			
		} catch (IOException e) {
			System.out.println("Error writing to file");
		}
	}

	private static boolean isFileEmpty(File currentFile) {
		return currentFile.length()<=0;
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
