package test.java.tests.core;

import java.util.Map;

public class GenerateEnvironmentVariables {
    public static void main(String[] args) {
        // name and value of all environment variables in Java  program
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n", envName, env.get(envName));
        }
        // Read more: https://javarevisited.blogspot.com/2012/08/how-to-get-environment-variables-in.html#ixzz7Tl7Qv9x5
    }
}
