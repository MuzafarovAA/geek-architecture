package ru.geekbrains;

public class SubImpl extends AbstractSum {

    public SubImpl(int a, int b) {
        super(a, b);
    }

    @Override
    public int sum() {
        return a - b;
    }
}
