import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class OutputToFile {
    List<Path> fileNames;
    String fileName = "src.java";

    public OutputToFile(List<Path> fileNames) {
        this.fileNames = fileNames;
    }

    public void execute() {
        StringBuilder builder = new StringBuilder();
        for (Path file : fileNames) {
            builder.append(fileOut(file));
        }
        createFile(fileName);
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(builder.toString());
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFile(String name) {
        try {
            File myFile = new File(name);
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists: " + myFile.getName());
            }
        } catch (IOException e) {
            System.out.println("Error in creating file.");
            e.printStackTrace();
        }
    }

    private String fileOut(Path path) {
        String line = "";
        StringBuilder builder = new StringBuilder();
        builder.append(path.getFileName() + "\n\n");
        try {
            BufferedReader reader = Files.newBufferedReader(path);
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    break;
                }
                builder.append(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error in file!");
            e.printStackTrace();
        }
        return builder.toString() + "\n\n";
    }
}
