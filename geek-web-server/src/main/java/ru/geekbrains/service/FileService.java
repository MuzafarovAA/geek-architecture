package ru.geekbrains.service;

public interface FileService {

    boolean exists(String filename);

    boolean isDirectory(String filename);

    String readFile(String filename);

}
