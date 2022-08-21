package ru.geekbrains;

import ru.geekbrains.domain.HttpResponse;

public class ResponseSerializer {

    public String serialize(HttpResponse response) {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 " + response.getStatusCode() + " " + response.getStatusCodeName() + "\n");
        response.getHeaders().forEach((header, value) -> sb.append(header + ": " + value + "\n"));
        sb.append('\n');
        sb.append(response.getBody());
        return sb.toString();
    }
}
