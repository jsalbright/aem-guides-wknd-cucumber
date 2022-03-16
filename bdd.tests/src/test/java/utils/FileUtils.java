package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class FileUtils {
    public static Properties getGlobalProperties() {
        Properties prop = null;

        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/global.properties");
            prop = new Properties();
            prop.load(fis);
        } catch (FileNotFoundException ex) {
            System.err.println("No global.properties file found!");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }
}
