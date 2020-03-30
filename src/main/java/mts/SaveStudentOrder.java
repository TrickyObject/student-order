package mts;

import mts.dao.DictionaryDaoImpl;
import mts.dao.StudentOrderDaoImpl;
import mts.dao.StudentOrderDao;
import mts.domain.*;

import java.time.LocalDate;
import java.util.List;

public class SaveStudentOrder {

    public static void main(String[] args) throws Exception {

        List<Street> d = new DictionaryDaoImpl().findStreets("про");
/*
        for (Street s : d) {
            System.out.println(s.getStreet_name());
        }
        System.out.println("--------->");
        List<PassportOffice> po
                = new DictionaryDaoImpl().findPassportOffices("010020000000");
        for (PassportOffice p : po) {
            System.out.println(p.getOfficeName());
        }
        System.out.println("--------->");
        List<RegisterOffice> ro
                = new DictionaryDaoImpl().findRegisterOffices("010010000000");
        for (RegisterOffice r : ro) {
            System.out.println(r.getOfficeName());
        }
        System.out.println("--------->");
        List<CountryArea> ca1
                = new DictionaryDaoImpl().findAreas("");
        for (CountryArea c : ca1) {
            System.out.println(c.getAreaID() + ": " + c.getAreaName());
        }
        System.out.println("--------->");
        List<CountryArea> ca2
                = new DictionaryDaoImpl().findAreas("020000000000");
        for (CountryArea c : ca2) {
            System.out.println(c.getAreaID() + ": " + c.getAreaName());
        }
        System.out.println("--------->");
        List<CountryArea> ca3
                = new DictionaryDaoImpl().findAreas("020010000000");
        for (CountryArea c : ca3) {
            System.out.println(c.getAreaID() + ": " + c.getAreaName());
        }
        System.out.println("--------->");
        List<CountryArea> ca4
                = new DictionaryDaoImpl().findAreas("020010010000");
        for (CountryArea c : ca4) {
            System.out.println(c.getAreaID() + ": " + c.getAreaName());
        }
*/

//        StudentOrder so = buildStudentOrder(10);
//        StudentOrderDao dao = new StudentOrderDaoImpl();
//        Long id = dao.saveStudentOrder(so);
//        System.out.println("Добавлена запись с идентификатором:" + id);

        StudentOrderDao dao = new StudentOrderDaoImpl();
        List<StudentOrder> soList = dao.getStudentOrders();
        for (StudentOrder s : soList) {
            System.out.println(s.getStudentOrderId());
        }

        /*
        StudentOrder studentOrder = new StudentOrder();
        System.out.println("SaveStudentOrder is running");
        saveStudentOrder(studentOrder);*/
    }

    static long saveStudentOrder(StudentOrder studentOrder) {

        long answer = 199;
        return answer;
    }

    public static StudentOrder buildStudentOrder(long id) {

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

    static void printStudentOrder(StudentOrder so) {
        System.out.println(so.getStudentOrderId());
    }
}
