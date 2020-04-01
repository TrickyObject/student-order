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

        Adress adress = new Adress("195000", street, "58", "24");

        // Муж
        Adult husband = new Adult(
                "Мужиков " + id, "Муж "+id, "Мужикович "+id,
                LocalDate.of(1970, 1, 1));
        husband.setPassportSerial("1000"+id);
        husband.setPassportNum("100000"+id);
        husband.setPassportDate(LocalDate.of(2000, 1, 1));
        PassportOffice po1 = new PassportOffice(1L, "", "");
        husband.setPassportOffice(po1);
        husband.setStudentId(""+(100000+id));
        husband.setAdress(adress);
        husband.setUni(new University(2L, ""));
        husband.setStudentId("HH"+(100000 + id));

        // Жена
        Adult wife = new Adult(
                "Женова " + id, "Жена "+id, "Женская "+id,
                LocalDate.of(1970, 1, 1));
        wife.setPassportSerial("2000"+id);
        wife.setPassportNum("200000"+id);
        wife.setPassportDate(LocalDate.of(2000, 1, 1));
        PassportOffice po2 = new PassportOffice(2L, "", "");
        wife.setPassportOffice(po2);
        wife.setStudentId(""+(200000+id));
        wife.setAdress(adress);
        wife.setUni(new University(1L, ""));
        wife.setStudentId("WW"+(100000 + id));

        // ребёнок
        Child child = new Child(
                "TestC", "TEst", "TEst",
                LocalDate.of(2018,6,29));
        child.setCertNum(""+(300000+id));
        child.setIssueDate(LocalDate.of(2018, 7, 19));
        RegisterOffice ro2 = new RegisterOffice(2L, "", "");
        child.setIssueDep(ro2);
        child.setAdress(adress);
        // ребёнок
        Child child1 = new Child(
                "TestC1", "TEst1", "TEst1",
                LocalDate.of(2018,6,29));
        child1.setCertNum(""+(400000+id));
        child1.setIssueDate(LocalDate.of(2018, 7, 19));
        RegisterOffice ro3 = new RegisterOffice(3L, "", "");
        child1.setIssueDep(ro3);
        child1.setAdress(adress);

        so.setHusband(husband);
        so.setWife(wife);
        so.addChild(child);
        so.addChild(child1);


        return so;
    }
}