package mts.dao;

import mts.domain.StudentOrder;
import mts.exception.DaoException;

import java.util.List;

public interface StudentOrderDao {

    Long saveStudentOrder (StudentOrder so) throws DaoException;

    List<StudentOrder> getStudentOrders() throws DaoException;
}
