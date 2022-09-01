package ru.geekbrains;

public abstract class AbstractSum {

    protected int a;

    protected int b;

    public AbstractSum(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public abstract int sum();
}
