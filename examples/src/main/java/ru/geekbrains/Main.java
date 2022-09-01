package ru.geekbrains;

import java.util.Collections;

public class Main {

    public static class SomeClass implements Cloneable {

        public String field;

        public SomeClass(String field) {
            this.field = field;
        }

        public SomeClass clone() throws CloneNotSupportedException {
            return (SomeClass) super.clone();
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        SomeClass first = new SomeClass("aaaa");
        SomeClass second = first.clone();
        System.out.println(second.field);
    }
}
