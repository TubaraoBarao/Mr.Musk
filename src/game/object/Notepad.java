package game.object;

import java.io.*;

public class Notepad {

    private static final String FILE_NAME = "progress.txt";

    public void update(String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) { writer.write(text); }
        catch (IOException e) { System.err.println(e.getMessage()); }
    }

    public String read() {

        File file = new File(FILE_NAME);
        String content = "00000";

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                content = reader.readLine();
            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            }
        }

        return content;
        
    }

}
