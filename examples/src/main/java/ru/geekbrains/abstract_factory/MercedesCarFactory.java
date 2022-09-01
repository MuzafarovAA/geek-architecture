package ru.geekbrains.abstract_factory;

public class MercedesCarFactory implements CarFactory{
    @Override
    public String createSedan() {
        return "Mercedes Sedan";
    }

    @Override
    public String createHatchback() {
        return "Mercedes Hatchback";
    }

    @Override
    public String createUniversal() {
        return "Mercedes Universal";
    }
}
