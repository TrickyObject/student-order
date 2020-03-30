package mts.dao;

import mts.config.Config;
import mts.domain.CountryArea;
import mts.domain.PassportOffice;
import mts.domain.RegisterOffice;
import mts.domain.Street;
import mts.exception.DaoException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DictionaryDaoImpl implements DictionaryDao {

    private static final String GET_STREET = "SELECT street_code, street_name " +
            "FROM jc_street " +
            "WHERE UPPER(street_name) LIKE UPPER(?)";

    private static final String GET_PASSPORT = "SELECT * " +
            "FROM jc_passport_office " +
            "WHERE p_office_area_id = ?";

    private static final String GET_REGISTER = "SELECT * " +
            "FROM jc_register_office " +
            "WHERE r_office_area_id = ?";

    private static final String GET_AREA = "SELECT * " +
            "FROM jc_country_struct " +
            "WHERE area_id like ? and area_id <> ?";

    private Connection getConnection() throws SQLException {
         return ConnectionBuilder.getConnection();
    }

    public List<Street> findStreets(String pattern) throws DaoException {

        List<Street> result = new LinkedList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_STREET)){

            statement.setString(1, "%" + pattern + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Street str = new Street(rs.getLong("street_code"), rs.getString("street_name"));
                result.add(str);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }

        return result;
    }

    @Override
    public List<PassportOffice> findPassportOffices(String areaId) throws DaoException {
        List<PassportOffice> result = new LinkedList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_PASSPORT)){

            statement.setString(1, areaId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                PassportOffice str = new PassportOffice(
                        rs.getLong("p_office_id"),
                        rs.getString("p_office_area_id"),
                        rs.getString("p_office_name"));
                result.add(str);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }

        return result;
    }

    @Override
    public List<RegisterOffice> findRegisterOffices(String areaId) throws DaoException {
        List<RegisterOffice> result = new LinkedList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_REGISTER)){

            statement.setString(1, areaId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                RegisterOffice str = new RegisterOffice(
                        rs.getLong("r_office_id"),
                        rs.getString("r_office_area_id"),
                        rs.getString("r_office_name"));
                result.add(str);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }

        return result;
    }

    @Override
    public List<CountryArea> findAreas(String areaID) throws DaoException {
        List<CountryArea> result = new LinkedList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_AREA)){

            String param1 = buildParam(areaID);
            String param2 = areaID;

            statement.setString(1, param1);
            statement.setString(2, param2);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                CountryArea str = new CountryArea(
                        rs.getString("area_id"),
                        rs.getString("area_name"));
                result.add(str);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }

        return result;
    }

    private String buildParam(String areaID) throws SQLException {
        if (areaID == null || areaID.trim().isEmpty()) {
            return "__0000000000";
        } if (areaID.endsWith("0000000000")) {
            return areaID.substring(0, 2) + "___0000000";
        } else  if (areaID.endsWith("0000000")) {
            return areaID.substring(0, 5) + "___0000";
        } else if (areaID.endsWith("0000")) {
            return areaID.substring(0, 8) + "____";
        }
        throw new SQLException("invalid param areaID: " + areaID);
    }


}
