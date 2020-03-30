package mts.validator;

import mts.domain.Child;
import mts.domain.Person;
import mts.domain.register.AnswerCityRegisterItem;
import mts.domain.register.CityRegisterResponse;
import mts.domain.StudentOrder;
import mts.domain.register.AnswerCityRegister;
import mts.exception.CityRegException;
import mts.exception.TransportException;
import mts.validator.city.CityRegisterChecker;
import mts.validator.city.FakeCityRegChecker;

import java.util.List;

public class CityRegisterValidator {

    public String host;
    public String login;
    public String pwd;
    public static final String IN_CODE = "NO_GRN";

    private CityRegisterChecker pChecker;

    public CityRegisterValidator() {

        pChecker = new FakeCityRegChecker();
    }

    public AnswerCityRegister checkRegistration(StudentOrder so) {

        AnswerCityRegister answerCityRegister =  new AnswerCityRegister();

        answerCityRegister.addItem(checkPerson(so.getHusband()));
        answerCityRegister.addItem(checkPerson(so.getWife()));
        for (Child child : so.getChild()) {
            answerCityRegister.addItem(checkPerson(child));
        }

        return answerCityRegister;
    }

    private AnswerCityRegisterItem checkPerson(Person person) {

        AnswerCityRegisterItem.CityState state = null;
        AnswerCityRegisterItem.CityError error = null;

        try {
            CityRegisterResponse tmp = pChecker.checkPerson(person);
            state = tmp.isExisting() ?
                    AnswerCityRegisterItem.CityState.YES :
                    AnswerCityRegisterItem.CityState.NO;

        } catch (CityRegException e) {
            e.printStackTrace(System.out);
            state = AnswerCityRegisterItem.CityState.ERROR;
            error = new AnswerCityRegisterItem.CityError(e.getCode(), e.getMessage());

        } catch (TransportException e) {
            e.printStackTrace(System.out);
            state = AnswerCityRegisterItem.CityState.ERROR;
            error = new AnswerCityRegisterItem.CityError(IN_CODE, e.getMessage());
        }

        AnswerCityRegisterItem ans = new AnswerCityRegisterItem(state, person, error);

        return null;
    }
}

/*
*
            for (int i = 0; i < so.getChild().size(); i++) {
                CityRegisterCheckerResponse ca = pChecker.checkPerson(so.getChild().get(i));
            }

            for(Iterator<Child> it = so.getChild().iterator(); it.hasNext();) {
                Child child = it.next();
                CityRegisterCheckerResponse ca = pChecker.checkPerson(child);
            }


             for (Child child : children) {
                CityRegisterCheckerResponse cAns = pChecker.checkPerson(child);
            }
* */