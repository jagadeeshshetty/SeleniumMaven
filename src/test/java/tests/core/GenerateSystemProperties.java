package test.java.tests.core;

import java.util.Properties;

public class GenerateSystemProperties {
    public static void main(String[] args) {
        // name and value of all environment variables in Java  program
        Properties prop = System.getProperties();
        for (Object propName : prop.keySet()) {
            System.out.format("%s=%s%n", propName, prop.get(propName));
        }
    }
}
