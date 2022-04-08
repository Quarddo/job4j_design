package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    @XmlAttribute
    private boolean car;
    @XmlAttribute
    private int age;
    @XmlAttribute
    private String name;
    private Address address;
    /** Чтобы выводилось так как мы хотим мы можем переименовать
     * элемент statuses в status и использовать тег @XmlElementWrapper */
    @XmlElementWrapper(name = "statuses")
    @XmlElement(name = "status")
    private String[] statuses;

    public User() {

    }

    public User(boolean car, int age, String name, Address address, String[] statuses) {
        this.car = car;
        this.age = age;
        this.name = name;
        this.address = address;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "User{"
                + "car=" + car
                + ", age=" + age
                + ", name=" + name
                + ", street=" + address
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        final User user = new User(true, 26, "Maksim",
                new Address("Pushkina"),
                new String[] {"Teriushov", "Married"});
        JAXBContext context = JAXBContext.newInstance(User.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(user, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {

        }
    }
}
