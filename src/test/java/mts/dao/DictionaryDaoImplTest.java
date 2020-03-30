package mts.dao;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

public class DictionaryDaoImplTest {

    // выполянется 1 раз в самом начале
    @BeforeClass
    public static void startUp() throws Exception {

        System.out.println("=== START UP ===");

        URL url1 = DictionaryDaoImplTest.class.getClassLoader()
                .getResource("student_project.sql");

        URL url2 = DictionaryDaoImplTest.class.getClassLoader()
                .getResource("data.sql");

        List<String> str1 = Files.readAllLines(Paths.get(url1.toURI()));
        String sql1 = str1.stream().collect(Collectors.joining());

        List<String> str2 = Files.readAllLines(Paths.get(url1.toURI()));
        String sql2 = str2.stream().collect(Collectors.joining());

        try (Connection connection = ConnectionBuilder.getConnection();
             Statement stms = connection.createStatement();
        ) {
            stms.executeUpdate(sql1);
            stms.executeUpdate(sql2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExample() {
        System.out.println("Result1");
    }

    @Test
//    @Ignore
    public void testExample2() {
        System.out.println("Result2");
    }

    @Test
    public void testExample3() {
        System.out.println("Result3");
//        throw new RuntimeException("Bad result");
    }

}

/*
    // Выполняется перед каждым методом
    @Before
    public void startTest() {
        System.out.println("START TEST");
    }
// ещё есть After

 */