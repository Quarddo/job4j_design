package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        User user = new User(true, 26, "Maksim", new Address("Pushkina"),
                new String[]{"Teriushov", "Married"});
        /** Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(User.class);
        /** Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /** Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            /** Сериализуем */
            marshaller.marshal(user, writer);
            xml = writer.getBuffer().toString();
        }
        /** Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /** десериализуем */
            User result = (User) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
