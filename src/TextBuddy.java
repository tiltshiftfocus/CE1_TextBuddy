import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TextBuddy {

    private static final String ERROR_WRITING_FILE = "Error writing to file";
	private static final String ERROR_READING_FILE = "Unable to read file";

	public static void main(String[] args) {
	    File currentFile = openFile(args[0]);
        Scanner sc = new Scanner(System.in);

        showWelcomeMessage(args[0]);
		runProgramTillExit(sc,currentFile);
    }

	private static void runProgramTillExit(Scanner sc, File currentFile){
		while(true) {
			System.out.print("command: ");
			String userCommand = sc.nextLine();
			executeCommand(userCommand, currentFile);
		}
	}

    private static void executeCommand(String userCommand, File currentFile) {
        String command = getFirstWord(userCommand);
        
        if(command.equals("add")){
        	add(userCommand, currentFile);
        }else if(command.equals("display")){
        	displayFile(currentFile);
        }else if(command.equals("clear")){
        	clear(currentFile);
        }else if(command.equals("delete")){
        	deleteFromFile(userCommand,currentFile);
        }else if(command.equals("exit")){
        	System.exit(0);
        }else{
			System.out.println("command " + userCommand + " is invalid");
		}
    }


	private static void deleteFromFile(String userCommand, File currentFile) {
		String textLineToRemove = removeFirstWord(userCommand);
		int lineToRemove = Integer.parseInt(textLineToRemove)-1; 
		List<String> linesOfStringFromFile = new LinkedList<String>();
		
		// --- add all Strings from file to LinkedList, store deleted String,
		// --- and clear current file
		addStringToList(currentFile, linesOfStringFromFile);
		String deletedString = linesOfStringFromFile.remove(lineToRemove);
		clearFile(currentFile);
		// ------------------------------
		
		// using Iterator to search LinkedList
		Iterator<String> listIterator = linesOfStringFromFile.iterator(); 
		while(listIterator.hasNext()){
			String textToAdd = listIterator.next();
			addToFile(textToAdd, currentFile);
		}
		
		System.out.println("deleted from " + currentFile.getName()
				+ ": \"" + deletedString + "\"");

	}

	private static void addStringToList(File currentFile, List<String> linesOfStringFromFile) {
		
		try{
			BufferedReader inputFile = new BufferedReader(new 
					FileReader(currentFile.getName()));
			String line;
			while((line = inputFile.readLine()) != null){
				linesOfStringFromFile.add(line);
			}
		}catch(IOException e){
			System.out.println(ERROR_READING_FILE);
		}
	}
	
	private static void clear(File currentFile) {
		clearFile(currentFile);
		System.out.println("all content deleted from " + currentFile.getName());
	}

	private static void clearFile(File currentFile) {
		try {
			BufferedWriter outToFile = new BufferedWriter(new 
					FileWriter(currentFile.getName(),false));
			
			outToFile.write("");
			outToFile.close();
			
		} catch (IOException e) {
			System.out.println(ERROR_WRITING_FILE);
		}
	}

	private static void displayFile(File currentFile) {
		if(isFileEmpty(currentFile)){
			System.out.println(currentFile.getName() + " is empty");
		}else{
			try {
				BufferedReader inputFile = new BufferedReader(new 
						FileReader(currentFile.getName()));
				
				String line;
				int stringAtLine = 0;
				while((line = inputFile.readLine()) != null){
					stringAtLine++;
					System.out.println(stringAtLine + ". " + line);
				}
				
			} catch (Exception e) {
				System.out.println(ERROR_READING_FILE);
			}
		}
	}
	
	private static void add(String userCommand, File currentFile) {
		String textToAdd = removeFirstWord(userCommand);
		addToFile(textToAdd, currentFile);
		System.out.println("added to " + currentFile.getName()
				+ ": \"" + textToAdd + "\"");
	}

	private static void addToFile(String textToAdd, File currentFile) {
		
		try {
			BufferedWriter outToFile = new BufferedWriter(new 
					FileWriter(currentFile.getName(),true));
			
			// if file is not empty, create new line for next String
			if(!isFileEmpty(currentFile)){
				outToFile.newLine();	
			}
			
			outToFile.write(textToAdd);
			outToFile.close();
			
		} catch (IOException e) {
			System.out.println(ERROR_WRITING_FILE);
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
                System.exit(0);
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
