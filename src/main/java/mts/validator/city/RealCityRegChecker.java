package mts.validator.city;

import mts.config.Config;
import mts.domain.register.CityRegisterRequest;
import mts.domain.register.CityRegisterResponse;
import mts.domain.Person;
import mts.exception.CityRegException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

public class RealCityRegChecker implements CityRegisterChecker {

    private static final Logger logger = LoggerFactory.getLogger(RealCityRegChecker.class);


    @Override
    public CityRegisterResponse checkPerson(Person person)
            throws CityRegException {

        try {
            CityRegisterRequest request = new CityRegisterRequest(person);

            Client client = ClientBuilder.newClient();
            CityRegisterResponse response = client.target(
                    Config.getProp(Config.CR_URL))
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(request, MediaType.APPLICATION_JSON))
                    .readEntity(CityRegisterResponse.class);

            return response;
        } catch (Exception e) {
            throw new CityRegException("410", e.getMessage(), e);
        }
    }
}
