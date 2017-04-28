package ru.stqua.pft.addressbook.web.appmanager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Александр on 28.04.2017.
 */
public class PropertiesProvider {

    private final Properties properties;

    public PropertiesProvider() {
        properties = new Properties();
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    private void loadProperties() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File("src/test/resources/%s.properties", target)));
    }
}
