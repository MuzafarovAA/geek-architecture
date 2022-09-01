package ru.geekbrains;

public class SumImpl extends AbstractSum {

    public SumImpl(int a, int b) {
        super(a, b);
    }

    @Override
    public int sum() {
        return a + b;
    }
}
