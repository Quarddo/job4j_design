package ru.job4j.serialization.json;

public class Address {

    private String street;

    public Address() {

    }

    public Address(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    @Override
    public String toString() {
        return "Address{"
                + "street'" + street + '\''
                + '}';
    }
}
