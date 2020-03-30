package mts.validator;

import mts.domain.StudentOrder;
import mts.domain.children.AnswerChild;

public class ChildValidator {

    public String host;
    public String login;
    public String pwd;

    public AnswerChild checkChild(StudentOrder so) {
        System.out.println("Child running.." + host +" "+ login +" "+ pwd);
        return new AnswerChild();
    }
}
