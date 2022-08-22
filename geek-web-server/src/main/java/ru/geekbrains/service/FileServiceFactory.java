package ru.geekbrains.service;

public class FileServiceFactory {

    public static FileService create(String rootDir) {
        return new FileServiceImpl(rootDir);
    }

}
