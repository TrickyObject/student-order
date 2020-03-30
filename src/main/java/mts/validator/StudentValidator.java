package mts.validator;

import mts.domain.StudentOrder;
import mts.domain.student.AnswerStudent;

public class StudentValidator {

    public String host;
    public String login;
    public String pwd;

    public AnswerStudent checkStudent(StudentOrder so) {

        System.out.println("Student running.." + host +" "+ login +" "+ pwd);
        return new AnswerStudent();
    }
}
