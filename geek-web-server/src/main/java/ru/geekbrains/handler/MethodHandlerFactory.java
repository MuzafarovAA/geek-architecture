package ru.geekbrains.handler;

import org.reflections.Reflections;
import ru.geekbrains.ResponseSerializer;
import ru.geekbrains.config.ServerConfig;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class MethodHandlerFactory {

    public static MethodHandler create(SocketService socketService, ResponseSerializer responseSerializer, FileService fileService) {
        PutMethodHandler putHandler = new PutMethodHandler(null, socketService, responseSerializer, fileService);
        PostMethodHandler postHandler = new PostMethodHandler(putHandler, socketService, responseSerializer, fileService);
        return new GetMethodHandler(postHandler, socketService, responseSerializer, fileService);
    }

    public static MethodHandler createAnnotated(SocketService socketService, ResponseSerializer responseSerializer, ServerConfig config, FileService fileService) {
        Reflections reflections = new Reflections("ru.geekbrains.handler");
        List<Class<?>> classes = new ArrayList<>(reflections.getTypesAnnotatedWith(Handler.class));

        classes.sort(Comparator.comparingInt((Class<?> theClass) -> getOrder(theClass)).reversed());
        MethodHandler prev = null;
        try {
            for (Class<?> theClass : classes) {
                Constructor<?> constructor = theClass.getConstructor(MethodHandler.class, SocketService.class, ResponseSerializer.class, FileService.class);
                prev = (MethodHandler) constructor.newInstance(prev, socketService, responseSerializer, fileService);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return prev;
    }

    private static int getOrder(Class<?> theClass) {
        return theClass.getAnnotation(Handler.class).order();
    }

}
