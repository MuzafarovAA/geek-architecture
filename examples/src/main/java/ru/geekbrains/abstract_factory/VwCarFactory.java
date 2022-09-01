package ru.geekbrains.abstract_factory;

public class VwCarFactory implements CarFactory {
    @Override
    public String createSedan() {
        return "VW Sedan";
    }

    @Override
    public String createHatchback() {
        return "VW Hatchback";
    }

    @Override
    public String createUniversal() {
        return "VW Universal";
    }
}
