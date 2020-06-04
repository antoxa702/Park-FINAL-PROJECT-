package dao;

import entity.UserType;
import exception.DAOException;

import java.util.List;

public interface UserTypeDao extends AbstractDao<UserType>{
	List<UserType> getAllUserTypes() throws DAOException;
}
