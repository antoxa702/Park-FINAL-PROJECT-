package dao;

import entity.User;
import exception.DAOException;

import java.util.List;

public interface UserDao extends AbstractDao<User>{
	List<User> getAllUsers() throws DAOException;
	User getByLogin(String login) throws DAOException;
	List<User> getUsersByParkId(int parkId) throws DAOException;
	List<User> getUsersByUserTypeId(int userTypeId) throws DAOException;
}
