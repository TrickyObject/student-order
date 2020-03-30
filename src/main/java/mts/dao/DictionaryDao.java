package mts.dao;

import mts.domain.CountryArea;
import mts.domain.PassportOffice;
import mts.domain.RegisterOffice;
import mts.domain.Street;
import mts.exception.DaoException;

import java.util.List;

public interface DictionaryDao {

    List<Street>findStreets(String pattern) throws DaoException;
    List<PassportOffice>findPassportOffices(String pattern) throws DaoException;
    List<RegisterOffice>findRegisterOffices(String pattern) throws DaoException;
    List<CountryArea>findAreas(String pattern) throws DaoException;

}
