package mts.dao;

import mts.domain.*;
import mts.exception.DaoException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class StudentOrderDaoImplTest {

    // выполянется 1 раз в самом начале
    @BeforeClass
    public static void startUp() throws Exception {
        DBinit.startUp();
    }

    @Test
    public void saveStudentOrder() throws DaoException {
        StudentOrder so = buildStudentOrder(10);
        Long id = new StudentOrderDaoImpl().saveStudentOrder(so);
    }

    @Test(expected = DaoException.class)
    public void saveStudentOrderError() throws DaoException {
            StudentOrder so = buildStudentOrder(10);
            so.getHusband().setsName(null);
            Long id = new StudentOrderDaoImpl().saveStudentOrder(so);
    }

    @Test
    public void getStudentOrders() throws DaoException {
        List<StudentOrder> list = new StudentOrderDaoImpl().getStudentOrders();
    }


    public StudentOrder buildStudentOrder(long id) {

        StudentOrder so = new StudentOrder();
        so.setStudentOrderId(id);
        so.setMarriageCertId(""+(123456000 + id));
        so.setMarriageDate(LocalDate.of(2016, 7,4));
        RegisterOffice ro = new RegisterOffice(1L, "", "");
        so.setMarriageOffice(ro);

        Street street = new Street(1L, "First Street");

        Adress adress = new Adress("195000", street, "10", "2", "121");

        // Муж
        Adult husband = new Adult(
                "Васильев", "Павел", "Николаевич",
                LocalDate.of(1995, 3, 18));
        husband.setPassportSerial("1000"+id);
        husband.setPassportNum("100000"+id);
        husband.setPassportDate(LocalDate.of(2007, 9, 15));
        PassportOffice po1 = new PassportOffice(1L, "", "");
        husband.setPassportOffice(po1);
        husband.setStudentId(""+(100000+id));
        husband.setAdress(adress);
        husband.setUni(new University(2L, ""));
        husband.setStudentId("HH12345");

        // Жена
        Adult wife = new Adult(
                "Васильева", "Ирина", "Петровна",
                LocalDate.of(1997, 8, 21));
        wife.setPassportSerial("2000"+id);
        wife.setPassportNum("200000"+id);
        wife.setPassportDate(LocalDate.of(2018, 4, 5));
        PassportOffice po2 = new PassportOffice(2L, "", "");
        wife.setPassportOffice(po2);
        wife.setStudentId(""+(200000+id));
        wife.setAdress(adress);
        wife.setUni(new University(1L, ""));
        wife.setStudentId("WW12345");

        // ребёнок
        Child child1 = new Child(
                "Васильева", "Евгения", "Павловна",
                LocalDate.of(2016,1,11));
        child1.setCertNum(""+(300000+id));
        child1.setIssueDate(LocalDate.of(2018, 6, 11));
        RegisterOffice ro2 = new RegisterOffice(2L, "", "");
        child1.setIssueDep(ro2);
        child1.setAdress(adress);
        // ребёнок
        Child child2 = new Child(
                "Васильев", "Александр", "Павлович",
                LocalDate.of(2018,10,24));
        child2.setCertNum(""+(400000+id));
        child2.setIssueDate(LocalDate.of(2018, 7, 19));
        RegisterOffice ro3 = new RegisterOffice(3L, "", "");
        child2.setIssueDep(ro3);
        child2.setAdress(adress);

        so.setHusband(husband);
        so.setWife(wife);
        so.addChild(child1);
        so.addChild(child2);


        return so;
    }
}