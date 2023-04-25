import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String PATH = System.getenv("REPLACE_PATH");
        Scanner scanner = new Scanner(System.in);
        FileManager fileManager = new FileManager(PATH);
        ScannerManager scannerManager = new ScannerManager(scanner, fileManager);

        ConsoleManager.printSuccess("Program is working!");

        while(scannerManager.sayAnswer("Do you want to copy directory?")){
            String source = scannerManager.sayDir("Give directory to copy: ");
            String purpose = scannerManager.sayDir("Give directory to copy to: ");
            fileManager.copy(source, purpose);
            ConsoleManager.printSuccess("Directory is copied!");
        }
        ConsoleManager.printSuccess("Program is finished!");

    }
}