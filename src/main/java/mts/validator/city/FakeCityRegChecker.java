package mts.validator.city;

import mts.domain.Adult;
import mts.domain.Child;
import mts.domain.register.CityRegisterResponse;
import mts.domain.Person;
import mts.exception.CityRegException;
import mts.exception.TransportException;

public class FakeCityRegChecker implements CityRegisterChecker {

    private static final String GOOD_H = "1000";
    private static final String GOOD_W = "2000";
    private static final String BAD_H = "1001";
    private static final String BAD_W = "2001";
    private static final String ERROR_H = "1002";
    private static final String ERROR_W = "2002";
    private static final String ERROR_T1 = "1003";
    private static final String ERROR_T2 = "2003";


    public CityRegisterResponse checkPerson(Person person)
            throws CityRegException, TransportException {

        CityRegisterResponse response = new CityRegisterResponse();

        if (person instanceof Adult) {
            Adult t = (Adult) person;
            String serial = t.getPassportSerial();

            if(serial.equals(GOOD_H) || serial.equals(GOOD_W)) {

                response.setRegistered(true);
                response.setTemporal(false);
            }

            if(serial.equals(BAD_H) || serial.equals(BAD_W)) {
                response.setRegistered(false);
            }

            if(serial.equals(BAD_H) || serial.equals(BAD_W)) {
                CityRegException ex = new CityRegException("1", "GRN ERROR " + serial);
                throw ex;
            }

            if(serial.equals(ERROR_T1) || serial.equals(ERROR_T2)) {
                TransportException ex = new TransportException("Transport ERROR " + serial);
                throw ex;
            }
        }

        if (person instanceof Child) {
            response.setRegistered(true);
            response.setTemporal(true);

        }

        System.out.println(response);

        return response;
    }
}
