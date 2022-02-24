package ru.job4j.calculator;

import static ru.job4j.math.MathFunction.*;

public class MathCalculator {

    public static double sumAndMultiply(double first, double second) {
        return sum(first, second)
                + multiply(first, second);
    }

    public static double firAndMon(double third, double fourth) {
        return fir(third, fourth)
                + mon(third, fourth);
    }

    public static double sumAndMultiplyAndFirAndMon(double first, double second, double third, double fourth) {
        return sum(first, second)
                + multiply(first, second)
                + fir(third, fourth)
                + mon(third, fourth);
    }

    public static void main(String[] args) {
        System.out.println(" Результат расчета равен: " + sumAndMultiply(10, 20));
        System.out.println(" Результат расчета равен: " + firAndMon(20, 10));
        System.out.println(" Результат расчета равен: " + sumAndMultiplyAndFirAndMon(10, 20, 20, 10));
    }

}
