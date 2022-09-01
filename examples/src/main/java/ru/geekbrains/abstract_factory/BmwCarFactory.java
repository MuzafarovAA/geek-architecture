package ru.geekbrains.abstract_factory;

public class BmwCarFactory implements CarFactory {
    @Override
    public String createSedan() {
        return "BMW Sedan";
    }

    @Override
    public String createHatchback() {
        return "BMW Hatchback";
    }

    @Override
    public String createUniversal() {
        return "BMW Universal";
    }
}
