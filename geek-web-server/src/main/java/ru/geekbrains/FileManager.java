package ru.geekbrains;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {

    private ResponseProvider responseProvider;

        public boolean isFileExist(Path path) {
            return Files.exists(path);
        }

    public void transfer(Path path, PrintWriter output) throws IOException {
        Files.newBufferedReader(path).transferTo(output);
    }
}
