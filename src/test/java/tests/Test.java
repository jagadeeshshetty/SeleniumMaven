package test.java.tests;

import org.testng.Assert;

import java.util.Random;

public class Test {

    public static void main(String[] args) {
        Random random = new Random();
        int rem = random.nextInt(10) % 2;
        Assert.assertTrue(rem == 0);
    }

}
