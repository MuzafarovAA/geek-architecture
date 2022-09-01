package ru.geekbrains.handler;

import ru.geekbrains.domain.HttpRequest;

public interface MethodHandler {

    void handle(HttpRequest request);
}
