package dao;

import entity.User;
import exception.DAOException;

import java.util.List;

public interface UserDao extends AbstractDao<User>{
	List<User> getAllUsers() throws DAOException;
}
