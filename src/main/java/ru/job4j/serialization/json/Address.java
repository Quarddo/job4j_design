package ru.job4j.serialization.json;

public class Address {

    private final String street;

    public Address(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{"
                + "street'" + street + '\''
                + '}';
    }
}
