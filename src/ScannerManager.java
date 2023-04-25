import java.util.Arrays;
import java.util.Scanner;
public class ScannerManager {
    public Scanner scanner;
    private final FileManager fileManager;

    public ScannerManager(Scanner scanner, FileManager fileManager){
        this.scanner = scanner;
        this.fileManager = fileManager;
    }

    public String sayDir(String asking){
        boolean correct = false;
        String dir = "";
        while(dir.equals("") || !correct){
            try{
                System.out.print("Directories list:");
                String[] dirs = fileManager.listDir();
                System.out.println(Arrays.toString(dirs));
                System.out.print(asking);
                dir = scanner.nextLine().trim();
                if(dir.equals("")) throw new NullException();
                correct = fileManager.inList(dirs, dir);
                if(!correct) {
                    throw new IncorrectValue();
                }
            } catch (NullException e){
                ConsoleManager.printError("Directory name can't be empty");

            } catch (IncorrectValue e){
                ConsoleManager.printError("Directory's name should be from the Directories list");
            }
        }
        return dir;
    }


    public boolean sayAnswer(String question){
        String answer = "";
        boolean correct = false;
        boolean ans = false;
        while (answer.equals("") || !correct){
            try{
                System.out.println(question + " (yes/no)");
                answer = scanner.nextLine().trim();
                switch (answer){
                    case "" -> throw new NullException();
                    case "yes" -> {
                        correct = true;
                        ans = true;
                    }
                    case "no" -> {
                        correct = true;
                        ans = false;
                    }
                    default -> throw new IncorrectValue();
                }
            } catch (NullException e){
                ConsoleManager.printError("Answer can't be empty");
            } catch (IncorrectValue e){
                ConsoleManager.printError("Answer should be \"yes\" or \"no\"");
            }
        }
        return ans;
    }

}
