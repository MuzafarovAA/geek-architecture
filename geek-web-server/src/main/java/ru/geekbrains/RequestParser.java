package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;

import java.util.Deque;

public class RequestParser {

    public HttpRequest parse(Deque<String> rawRequest) {
        HttpRequest httpRequest = new HttpRequest();
        String[] firstLine = rawRequest.pollFirst().split(" ");
        httpRequest.setMethod(firstLine[0]);
        httpRequest.setUrl(firstLine[1]);

        while (!rawRequest.isEmpty()) {
            String line = rawRequest.pollFirst();
            if (line.isBlank()) {
                break;
            }
            String[] header = line.split(": ");
            httpRequest.getHeaders().put(header[0], header[1]);
        }
        StringBuilder sb = new StringBuilder();
        while (!rawRequest.isEmpty()) {
            sb.append(rawRequest.pollFirst());
        }
        httpRequest.setBody(sb.toString());
        return httpRequest;
    }
}
