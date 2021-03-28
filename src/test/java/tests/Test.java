package test.java.tests;

import java.util.Random;

public class Test {

    public static void main(String[] args) {
        Random random = new Random();
        int rem = random.nextInt(10) % 2;
        System.out.println(rem);
        System.out.println(rem == 0);
    }

}
