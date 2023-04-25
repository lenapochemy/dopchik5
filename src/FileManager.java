import java.io.*;

public class FileManager {

    final private String path;
    public FileManager(String path){
        this.path = path;
    }

    public String[] listDir(){

        File[] dirs = new File(path).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });

        String[] sDir = new String[dirs.length];
        for(int i=0; i< dirs.length; i++){
            sDir[i] = dirs[i].toString();
            String[] r = sDir[i].split("/");
            sDir[i] = r[r.length-1];

        }
        return sDir;
    }

    public boolean inList(String[] dirs, String name){
        for(String file: dirs){
            if(file.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void copy(String sSource, String sPurpose){
        File source = new File(path + "/" + sSource);
        if(source.isDirectory()){
            copyDir(sSource, sPurpose);
        } else {
            copyFile(sSource, sPurpose);
        }
    }

    public void copyFile(String source, String purpose){
        try(
                FileInputStream file = new FileInputStream(path + "/" + source);
                InputStreamReader in = new InputStreamReader(file);
                OutputStream out = new FileOutputStream(path + "/" + purpose + "/" + source)
                ) {
            BufferedReader bufferedReader = new BufferedReader(in);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line).append("\n");
            }
            bufferedReader.close();
            out.write(stringBuilder.toString().getBytes());

            ConsoleManager.printSuccess("File \"" + source + "\" is copied to \"" +purpose + "\" !");
        } catch(IOException e){
            ConsoleManager.printError("Error with copy files");
        }

    }

    public void copyDir(String sSource, String sPurpose){
        File source = new File(path + "/" + sSource);
        File purpose = new File(path + "/" + sPurpose + "/" + sSource);
        if(!purpose.exists()){
            purpose.mkdir();
        }
        for(String file : source.list()){
            copy(sSource + "/" + file, sPurpose);
        }
        ConsoleManager.printSuccess("Directory \"" + sSource + "\" is copied to \"" + sPurpose + "\" !");
    }

}
