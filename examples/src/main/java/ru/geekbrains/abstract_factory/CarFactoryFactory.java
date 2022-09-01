package ru.geekbrains.abstract_factory;

public final class CarFactoryFactory {

    public static CarFactory create(String type) {
        switch (type) {
            case "VW":
                return new VwCarFactory();
            case "Mercedes":
                return new MercedesCarFactory();
            case "BMW":
                return new BmwCarFactory();
            default:
                throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        CarFactory bmwFactory = CarFactoryFactory.create("BMW");
        String sedan = bmwFactory.createSedan();

    }
}
