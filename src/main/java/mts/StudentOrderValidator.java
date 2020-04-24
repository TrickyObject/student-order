package mts;

import mts.dao.StudentOrderDaoImpl;
import mts.domain.StudentOrder;
import mts.domain.children.AnswerChild;
import mts.domain.register.AnswerCityRegister;
import mts.domain.marriage.AnswerMariage;
import mts.domain.student.AnswerStudent;
import mts.exception.DaoException;
import mts.mail.MailSender;
import mts.validator.ChildValidator;
import mts.validator.CityRegisterValidator;
import mts.validator.MariageValidator;
import mts.validator.StudentValidator;

import java.util.LinkedList;
import java.util.List;

public class StudentOrderValidator {

    private CityRegisterValidator cityRegisterVal;
    private MariageValidator mariageVal;
    private ChildValidator childVal;
    private StudentValidator studentVal;
    private MailSender mailSender;

    public StudentOrderValidator() {
        cityRegisterVal = new CityRegisterValidator();
        mariageVal  = new MariageValidator();
        childVal = new ChildValidator();
        studentVal = new StudentValidator();
        mailSender = new MailSender();
    }

    public static void main(String[] args) {

        StudentOrderValidator sov = new StudentOrderValidator();
        sov.checkAll();
    }

    public void checkAll() {

        try {
            List <StudentOrder> soList = readStudentOrders();
            for(StudentOrder so : soList) {
                checkOneOrder(so);
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public List<StudentOrder> readStudentOrders() throws DaoException {

        return new StudentOrderDaoImpl().getStudentOrders();
    }

    public void checkOneOrder(StudentOrder so) {

        AnswerCityRegister cityAnswer = checkRegistration(so);

//        AnswerMariage am = checkMarriage(so);
//        AnswerChild ac = checkChild(so);
//        AnswerStudent as = checkStudent(so);

//        sendMail(so);
    }

    //

    public AnswerCityRegister checkRegistration(StudentOrder so) {
        return cityRegisterVal.checkRegistration(so);
    }

    public AnswerMariage checkMarriage(StudentOrder so) {
        return mariageVal.checkMarriage(so);
    }

    public AnswerChild checkChild(StudentOrder so) {
        return childVal.checkChild(so);
    }

    public AnswerStudent checkStudent(StudentOrder so) {
        return studentVal.checkStudent(so);
    }

    public void sendMail(StudentOrder so) {
        mailSender.sendMail(so);
    }

}
