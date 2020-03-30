package mts.dao;

import mts.domain.CountryArea;
import mts.domain.PassportOffice;
import mts.domain.RegisterOffice;
import mts.domain.Street;
import mts.exception.DaoException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Files;
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

    //тест нбора улиц
    @Test
    public void testStreet() throws DaoException {
        List<Street> d = new DictionaryDaoImpl().findStreets("про");
        Assert.assertTrue(d.size() == 0);
    }

    // тест ПО
    @Test
    public void testPassportOffice() throws DaoException {
        List<PassportOffice> po
                = new DictionaryDaoImpl()
                .findPassportOffices("010020000000");
        Assert.assertTrue(po.size() == 0);
    }

    @Test
    public void testRegistertOffice() throws DaoException {
        List<RegisterOffice> ro
                = new DictionaryDaoImpl().
                findRegisterOffices("010010000000");
        Assert.assertTrue(ro.size() == 0);
    }

    @Test
    public void testAreas() throws DaoException {


        List<CountryArea> ca1
                = new DictionaryDaoImpl().findAreas("");
        Assert.assertTrue(ca1.size() == 0);

        List<CountryArea> ca2
                = new DictionaryDaoImpl().findAreas("020000000000");
        Assert.assertTrue(ca2.size() == 0);
        List<CountryArea> ca3
                = new DictionaryDaoImpl().findAreas("020010000000");
        Assert.assertTrue(ca3.size() == 0);
        List<CountryArea> ca4
                = new DictionaryDaoImpl().findAreas("020010010000");
        Assert.assertTrue(ca4.size() == 0);
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