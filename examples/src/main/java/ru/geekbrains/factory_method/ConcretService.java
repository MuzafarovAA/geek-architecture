package ru.geekbrains.factory_method;

public class ConcretService extends AbstractService {

    @Override
    public SomeInterface create() {
        return new SomeInterface() {
            @Override
            public void something() {
                System.out.println("Something 1");
            }
        };
    }
}
