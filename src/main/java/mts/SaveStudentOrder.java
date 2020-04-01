package mts;

import mts.dao.DictionaryDaoImpl;
import mts.dao.StudentOrderDaoImpl;
import mts.dao.StudentOrderDao;
import mts.domain.*;

import java.time.LocalDate;
import java.util.List;

public class SaveStudentOrder {


    static long saveStudentOrder(StudentOrder studentOrder) {

        long answer = 199;
        return answer;
    }


    static void printStudentOrder(StudentOrder so) {
        System.out.println(so.getStudentOrderId());
    }
}
