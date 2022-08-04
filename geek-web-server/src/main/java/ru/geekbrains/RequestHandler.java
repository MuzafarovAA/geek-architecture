package ru.geekbrains;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RequestHandler implements Runnable {

    private final Socket socket;

    private final String folder;

    private final RequestParser requestParser = new RequestParser();

    private final FileManager fileManager = new FileManager();

    private final ResponseProvider responseProvider = new ResponseProvider();

    public RequestHandler(Socket socket, String folder) {
        this.socket = socket;
        this.folder = folder;
    }

    @Override
    public void run() {
        try (BufferedReader input = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter output = new PrintWriter(socket.getOutputStream())
        ) {

            String[] parts = requestParser.doParse(input);

            Path path = Paths.get(folder, parts[1]);

            if (!fileManager.isFileExist(path)) {
               responseProvider.response(404, output);
            } else {
               responseProvider.response(200, output);
            }

            fileManager.transfer(path, output);

            System.out.println("Client disconnected!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
