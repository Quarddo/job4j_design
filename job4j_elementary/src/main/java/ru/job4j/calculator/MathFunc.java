package ru.job4j.calculator;

public class MathFunc {

    public static float func1(float x) {
        float y = x * x + 1;
        return y;
    }

    public static float func2(float x) {
        float y = 1 / x;
        return y;
    }

    public static void main(String[] args) {
        float result1 = MathFunc.func1(3);
        float result2 = MathFunc.func2(5);
        float result3 = MathFunc.func1(100);
        float total = result3 + result2;
        System.out.println(total);
    }
}
