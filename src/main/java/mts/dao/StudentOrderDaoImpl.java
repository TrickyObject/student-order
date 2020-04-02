package mts.dao;

import mts.config.Config;
import mts.domain.*;
import mts.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static mts.config.Config.DB_LIMIT;

public class StudentOrderDaoImpl implements StudentOrderDao {

    private static final Logger logger = LoggerFactory.getLogger(StudentOrderDaoImpl.class);

    private static final String INSERT_ORDER
            = "INSERT INTO jc_student_order(" +
            "student_order_status, student_order_date, h_sur_name, h_given_name," +
            " h_patronymic, h_date_of_birth, h_passport_seria, h_passport_number, h_passport_date, h_passport_office_id," +
            " h_post_index, h_street_code, h_building, h_extension, h_apartment, h_university_id, h_student_number," +
            " w_sur_name, w_given_name, w_patronymic, w_date_of_birth, w_passport_seria, w_passport_number," +
            " w_passport_date, w_passport_office_id, w_post_index, w_street_code, w_building, w_extension, w_apartment," +
            " w_university_id, w_student_number, " +
            "certificate_id, register_office_id, marriage_date) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String INSERT_CHILD
            = "INSERT INTO jc_student_child(" +
            "student_order_id, c_sur_name, c_given_name, c_patronymic, " +
            "c_date_of_birth, c_certificate_number, c_certificate_date, c_register_office_id, " +
            "c_post_index, c_street_code, c_building, c_extension, c_apartment)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
/*
    private static final String SELECT_ORDERS =
            "SELECT ro.r_office_area_id, ro.r_office_name, so.* " +
                    "FROM jc_student_order so  " +
                    "INNER JOIN jc_register_office ro ON ro.r_office_id = so.register_office_id " +
                    "WHERE student_order_status = 0 " +
                    "ORDER BY student_order_date";
*/
    private static final String SELECT_ORDERS =
        "SELECT so.*, ro.r_office_area_id, ro.r_office_name, " +
                "po_h.p_office_area_id as h_p_office_area_id, po_h.p_office_name as h_p_office_name, " +
                "po_w.p_office_area_id as w_p_office_area_id, po_w.p_office_name as w_p_office_name " +
                "FROM jc_student_order so  " +
                "INNER JOIN jc_register_office ro ON ro.r_office_id = so.register_office_id " +
                "INNER JOIN jc_passport_office po_h ON po_h.p_office_id = so.register_office_id " +
                "INNER JOIN jc_passport_office po_w ON po_w.p_office_id = so.register_office_id " +
                "WHERE student_order_status = ? ORDER BY student_order_date LIMIT ?";

    private static final String SELECT_CHILD =
            "SELECT soc.*, ro.r_office_area_id,  ro.r_office_name " +
                    "FROM jc_student_child soc " +
                    "INNER JOIN jc_register_office ro ON ro.r_office_id = soc.c_register_office_id " +
                    "WHERE soc.student_order_id IN ";

    private static final String SELECT_ORDERS_FULL =
            "SELECT so.*, ro.r_office_area_id, ro.r_office_name, " +
                    "po_h.p_office_area_id as h_p_office_area_id, po_h.p_office_name as h_p_office_name, " +
                    "po_w.p_office_area_id as w_p_office_area_id, po_w.p_office_name as w_p_office_name, " +
                    "soc.*, ro_c.r_office_area_id,  ro_c.r_office_name " +
                    "FROM jc_student_order so  " +
                    "INNER JOIN jc_register_office ro ON ro.r_office_id = so.register_office_id " +
                    "INNER JOIN jc_passport_office po_h ON po_h.p_office_id = so.register_office_id " +
                    "INNER JOIN jc_passport_office po_w ON po_w.p_office_id = so.register_office_id " +
                    "INNER JOIN jc_student_child soc ON soc.student_order_id = so.student_order_id  " +
                    "INNER JOIN jc_register_office ro_c ON ro_c.r_office_id = soc.c_register_office_id " +
                    "WHERE student_order_status = ? ORDER BY soc.student_order_id LIMIT ?";


    private Connection getConnection() throws SQLException {
        return ConnectionBuilder.getConnection();
    }

    @Override
    public Long saveStudentOrder(StudentOrder so) throws DaoException {

        Long res = -1L;

        logger.debug("SO: {}", so);

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_ORDER, new String[] {"student_order_id"} )) {

            connection.setAutoCommit(false);

            try {
                // Заголовок
                ps.setInt(1, StudentOrderStatus.START.ordinal());
                ps.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));
                // Муж
                setParamsForAdult(ps, 3, so.getHusband());
                //Жена
                setParamsForAdult(ps, 18, so.getWife());
                //Свидетелдьсвто о браке
                ps.setString(33, so.getMarriageCertId());
                ps.setLong(34, so.getMarriageOffice().getOfficeID());
                ps.setDate(35, java.sql.Date.valueOf(so.getMarriageDate()));

                ps.executeUpdate();
                ResultSet gkRs = ps.getGeneratedKeys();
                if (gkRs.next()) {
                    res = gkRs.getLong(1);
                }
                gkRs.close();

                saveChildren(connection, so, res);

                connection.commit();

            } catch (SQLException e) {
                connection.rollback();
                throw new DaoException(e);
            }


        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DaoException(e);
        }

        return res;
    }

    @Override
    public List<StudentOrder> getStudentOrders() throws DaoException {
        return getStudentOrdersSingleSelect();
        //return getStudentOrdersTwoSelects();
    }
    
    private List<StudentOrder> getStudentOrdersSingleSelect() throws DaoException {
        List<StudentOrder> res = new LinkedList<>();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ORDERS_FULL)) {

            Map<Long, StudentOrder> maps = new HashMap<>();

            ps.setInt(1, StudentOrderStatus.START.ordinal());
//            int limit = Integer.parseInt(Config.getProp(Config.DB_LIMIT));
            ps.setInt(2, Integer.parseInt(Config.getProp(DB_LIMIT)));

            ResultSet rs = ps.executeQuery();
            int counter = 0;


            while (rs.next()) {
                Long soID = rs.getLong("student_order_id");
                if (!maps.containsKey(soID)) {
                    StudentOrder so = getFullStudentOrder(rs);

                    res.add(so);
                    maps.put(soID, so);
                }

                StudentOrder so = maps.get(soID);
                so.addChild(fillChild(rs));
                counter++;

            }
            if (counter >= Integer.parseInt(Config.getProp(DB_LIMIT))) {
                res.remove(res.size()-1);
            }

            rs.close();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DaoException(e);
        }

        return res;
    }

    private List<StudentOrder> getStudentOrdersTwoSelects() throws DaoException {
        List<StudentOrder> res = new LinkedList<>();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ORDERS)) {

            ps.setInt(1, StudentOrderStatus.START.ordinal());
            ps.setInt(2, Integer.parseInt(Config.getProp(DB_LIMIT)));
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                StudentOrder so = getFullStudentOrder(rs);

                res.add(so);
            }
            findChildren(connection, res);

            rs.close();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DaoException(e);
        }

        return res;
    }

    private StudentOrder getFullStudentOrder(ResultSet rs) throws SQLException {
        StudentOrder so = new StudentOrder();
        fillStudentOrder(rs, so);
        fillMarriage(rs, so);
        so.setHusband(fillAdult(rs, "h_"));
        so.setWife(fillAdult(rs, "w_"));
        return so;
    }


    private void setParamsForAdult(PreparedStatement ps, int start, Adult adult) throws SQLException {
        setParamsForPerson(ps, start, adult);
        ps.setString(start + 4,adult.getPassportSerial());
        ps.setString(start + 5, adult.getPassportNum());
        ps.setDate(start + 6, Date.valueOf(adult.getPassportDate()));
        ps.setLong(start + 7, adult.getPassportOffice().getOfficeID());

        setParamsForAdress(ps, start + 8, adult);

        ps.setLong(start +  13, adult.getUni().getUnID());
        ps.setString(start + 14, adult.getStudentId());
    }

    private void setParamsForChild(PreparedStatement ps, Child child) throws SQLException{
        setParamsForPerson(ps, 2, child);
        ps.setString(6, child.getCertNum());
        ps.setDate(7, Date.valueOf(child.getIssueDate()));
        ps.setLong(8, child.getIssueDep().getOfficeID());
        setParamsForAdress(ps, 9, child);
    }

    private void setParamsForPerson(PreparedStatement ps, int start, Person person) throws SQLException {
        ps.setString(start, person.getsName());
        ps.setString(start + 1, person.getfName());
        ps.setString(start + 2, person.getpName());
        ps.setDate(start + 3, Date.valueOf(person.getBd()));
    }

    private void setParamsForAdress(PreparedStatement ps, int start, Person person) throws SQLException {
        Adress adultAdress = person.getAdress();
        ps.setString(start, adultAdress.getPostCode());
        ps.setLong(start + 1, adultAdress.getStreet().getStreet_code());
        ps.setString(start + 2, adultAdress.getBuilding());
        ps.setString(start + 3, "");
        ps.setString(start + 4, adultAdress.getApartment());
    }


    private void fillStudentOrder(ResultSet rs, StudentOrder so) throws SQLException{

        so.setStudentOrderId(rs.getLong("student_order_id"));
        so.setStudentOrderDate(rs.getTimestamp("student_order_date").toLocalDateTime());
        so.setStudentOrderStatus(StudentOrderStatus.fromValue(rs.getInt("student_order_status")));

    }

    private void fillMarriage(ResultSet rs, StudentOrder so) throws SQLException {
        so.setMarriageCertId(rs.getString("certificate_id"));
        so.setMarriageDate(rs.getDate("marriage_date").toLocalDate());

        Long roID = rs.getLong("register_office_id");
        String areaID = rs.getString("r_office_area_id");
        String name = rs.getString("r_office_name");
        RegisterOffice ro = new RegisterOffice(roID, areaID ,name);
        so.setMarriageOffice(ro);

    }

    private Adult fillAdult(ResultSet rs, String prefix) throws SQLException {

        Adult adult = new Adult();
        adult.setsName(rs.getString(prefix + "sur_name"));
        adult.setfName(rs.getString(prefix + "given_name"));
        adult.setpName(rs.getString(prefix + "patronymic"));
        adult.setBd(rs.getDate(prefix + "date_of_birth").toLocalDate());
        adult.setPassportSerial(rs.getString(prefix + "passport_seria"));
        adult.setPassportNum(rs.getString(prefix + "passport_number"));
        adult.setPassportDate(rs.getDate(prefix + "passport_date").toLocalDate());

        Long poID = rs.getLong(prefix + "passport_office_id");
        String poArea = rs.getString(prefix + "p_office_area_id");
        String poName = rs.getString(prefix + "p_office_name");

        PassportOffice po = new PassportOffice(poID, poArea, poName);
        adult.setPassportOffice(po);

        Adress adress = new Adress();
        Street street = new Street(rs.getLong(prefix + "street_code"), "");
        adress.setStreet(street);
        adress.setPostCode(rs.getString(prefix + "post_index"));
        adress.setBuilding(rs.getString(prefix + "building"));
        adress.setBuilding(rs.getString(prefix + "extension"));
        adress.setApartment(rs.getString(prefix + "apartment"));
        adult.setAdress(adress);

        University uni = new University(rs.getLong(prefix + "university_id"), "");
        adult.setUni(uni);
        adult.setStudentId(rs.getString(prefix + "student_number"));

        return adult;
    }


    private Child fillChild(ResultSet rs) throws SQLException {
        String sName = rs.getString("c_sur_name");
        String giveName = rs.getString("c_given_name");
        String patronymic = rs.getString("c_patronymic");
        LocalDate dateOfBirth = rs.getDate("c_date_of_birth").toLocalDate();

        Child child = new Child(sName, giveName, patronymic, dateOfBirth);
        child.setCertNum(rs.getString("c_certificate_number"));
        child.setIssueDate(rs.getDate("c_certificate_date").toLocalDate());

        Long roID = rs.getLong("c_register_office_id");
        String roArea = rs.getString("r_office_area_id");
        String roName = rs.getString("r_office_name");

        RegisterOffice ro = new RegisterOffice(roID, roArea, roName );
        child.setIssueDep(ro);

        Adress adress = new Adress();
        Street street = new Street(rs.getLong("c_street_code"), "");
        adress.setStreet(street);
        adress.setPostCode(rs.getString("c_post_index"));
        adress.setBuilding(rs.getString("c_building"));
        adress.setBuilding(rs.getString("c_extension"));
        adress.setApartment(rs.getString("c_apartment"));
        child.setAdress(adress);

        return child;
    }

    private void saveChildren(Connection connection, StudentOrder so, Long soID) throws SQLException{

        try (PreparedStatement ps = connection.prepareStatement(INSERT_CHILD)) {

            for (Child child : so.getChildren()) {
                ps.setLong(1, soID);
                setParamsForChild(ps, child);
                // если много данных - сделать батч
//                ps.executeUpdate();
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void findChildren(Connection connection, List<StudentOrder> res) throws SQLException {

        String cl = "(" + res.stream().map(so -> String.valueOf(so.getStudentOrderId()))
                .collect(Collectors.joining(",")) + ")";

        Map<Long, StudentOrder> maps =  res.stream().collect(Collectors.toMap(so -> so.getStudentOrderId(), so -> so));

        try (PreparedStatement ps = connection.prepareStatement(SELECT_CHILD + cl)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Child ch = fillChild(rs);
                StudentOrder so = maps.get(rs.getLong("student_order_id"));
                so.addChild(ch);

            }
        }
    }


}
