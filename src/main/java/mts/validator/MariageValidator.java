package mts.validator;

import mts.domain.StudentOrder;
import mts.domain.marriage.AnswerMariage;

public class MariageValidator {

    public String host;
    public String login;
    public String pwd;

    public AnswerMariage checkMarriage(StudentOrder so) {
        System.out.println("Maggiege running.." + host +" "+ login +" "+ pwd);
        return new AnswerMariage();
    }
}
