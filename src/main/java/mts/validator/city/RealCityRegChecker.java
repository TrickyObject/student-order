package mts.validator.city;

import mts.domain.register.CityRegisterResponse;
import mts.domain.Person;
import mts.exception.CityRegException;
import mts.exception.TransportException;

public class RealCityRegChecker implements CityRegisterChecker {


    @Override
    public CityRegisterResponse checkPerson(Person person)
            throws CityRegException, TransportException {
        return null;
    }
}
