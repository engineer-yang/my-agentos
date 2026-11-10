package io.oryxos.cli;

import java.io.IOException;
import java.util.Properties;

public class OryxOsCli {
    public static void main(String[] args) throws IOException {
        var properties = new Properties();
        try (var input = OryxOsCli.class.getResourceAsStream("version.properties")) {
            if (input == null) {
                throw new IOException("Missing CLI version resource; build the project with Maven first.");
            }
            properties.load(input);
        }
        System.out.println("OryxOS " + properties.getProperty("version"));
    }
}
