package mts.config;

import java.io.*;
import java.util.Properties;

public class Config {

    public static final String DB_URL = "db.url";
    public static final String DB_LOGIN = "db.login";
    public static final String DB_PWD = "db.password";
    public static final String DB_LIMIT = "db.limit";
    public static final String CR_URL = "cr.url";


    private static Properties properties = new Properties();

    public static String getProp(String name) {

        String fileName = "config.properties";

        if (properties.isEmpty()) {
            try (InputStream is = Config.class.getClassLoader()
                    .getResourceAsStream(fileName)) {
                properties.load(is);

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

        }

        return properties.getProperty(name);
    }

}

/*
            try (
                    InputStream is = Config.class.getClassLoader().
                    getResourceAsStream("src/config.properties")

            ) {
                properties.load(is);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

 */

/*
        if (properties == null) {
            try (InputStream is = Config.class.getClassLoader().
                    getResourceAsStream(fileName)) {

                if (is == null) {
                    System.out.println("null");
                } else {
                    System.out.println(is);
                }
                properties.load(is);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

        }
 */

/*
РАБОЧИЙ ВАРИАНТ
   Properties properties = new Properties();

        try {
            FileInputStream in = new FileInputStream(fileName);
            properties.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

 */