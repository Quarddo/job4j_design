package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Maksim Teriushov";
        short age = 26;
        double weight = 75.5;
        float height = 181.1f;
        byte child = 1;
        boolean haveCar = true;
        int priceCar = 50000;
        char currency = '$';
        LOG.debug("User info : name - {}, age - {}, weight - {}, height - {}, child - {}, have car - {}, price car - {}, currency - {}",
                name, age, weight, height, child, haveCar, priceCar, currency);




        //LOG.trace("trace message");
        //LOG.debug("debug message");
        //LOG.info("info message");
        //LOG.warn("warn message");
        //LOG.error("error message");
    }
}
