package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {

    public HttpRequest parse(Deque<String> rawRequest) {
        String[] firstLine = rawRequest.pollFirst().split(" ");
        Map<String, String> headers = new HashMap<>();

        while (!rawRequest.isEmpty()) {
            String line = rawRequest.pollFirst();
            if (line.isBlank()) {
                break;
            }
            String[] header = line.split(": ");
            headers.put(header[0], header[1]);
        }
        StringBuilder sb = new StringBuilder();
        while (!rawRequest.isEmpty()) {
            sb.append(rawRequest.pollFirst());
        }

        HttpRequest httpRequest = HttpRequest.createBuilder()
                .withBody(sb.toString())
                .withHeaders(headers)
                .withMethod(firstLine[0])
                .withUrl(firstLine[1])
                .build();
        return httpRequest;
    }
}
