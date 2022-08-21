package ru.geekbrains.config;

import java.nio.file.Files;
import java.nio.file.Path;

public final class ServerConfigFactory {

    public static ServerConfig create(String[] args) {
        if (args.length >= 2) {
            return new ConfigFromCli(args);
        } else if (Files.exists(Path.of("../../../server.properties"))) {
            return new ConfigFromFile("../../../server.properties");
        } else {
            return new ConfigFromFixedValues();
        }
    }
}
