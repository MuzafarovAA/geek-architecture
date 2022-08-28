package ru.geekbrains.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileServiceImpl implements FileService {

    private final String rootDir;

    public FileServiceImpl(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    public boolean exists(String filename) {
        return Files.exists(Path.of(rootDir, filename));
    }

    @Override
    public boolean isDirectory(String filename) {
        return Files.isDirectory(Path.of(rootDir, filename));
    }

    @Override
    public String readFile(String filename) {
        try {
            StringBuilder sb = new StringBuilder();
            Files.readAllLines(Path.of(rootDir, filename)).forEach(sb::append);
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
