package ru.geekbrains;

import ru.geekbrains.domain.HttpResponse;

public class ResponseSerializer {

    public static String serialize(HttpResponse response) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("HTTP/1.1 ").append(response.getStatusCode());
        if (response.getStatusCode() == 200) {
            stringBuilder.append(" OK\n");
        } else if (response.getStatusCode() == 404) {
            stringBuilder.append(" NOT_FOUND\n");
        }
        response.getHeaders().forEach((k, v) -> stringBuilder.append(k).append(": ").append(v).append("\n"));
        stringBuilder.append("\n").append(response.getBody());
        return stringBuilder.toString();
    }
}
