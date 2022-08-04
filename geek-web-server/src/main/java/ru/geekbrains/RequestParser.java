package ru.geekbrains;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestParser {

    public String[] doParse(BufferedReader input) throws IOException {
        String firstLine = input.readLine();
        String[] parts = firstLine.split(" ");
        System.out.println(firstLine);
        while (input.ready()) {
            System.out.println(input.readLine());
        }
        return parts;
    }
}
