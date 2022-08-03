package ru.geekbrains;

import java.io.PrintWriter;

public class ResponseProvider {
    public void response(int status, PrintWriter output) {

        if (status == 404) {
            output.println("HTTP/1.1 404 NOT_FOUND");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<h1>Файл не найден!</h1>");
            output.flush();

        } else if (status == 200) {
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
        }

    }
}
