package mts.validator.city;

import mts.domain.register.CityRegisterResponse;
import mts.domain.Person;
import mts.exception.CityRegException;

public interface CityRegisterChecker {

    CityRegisterResponse checkPerson(Person person)
            throws CityRegException;

}
