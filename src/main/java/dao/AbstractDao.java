package dao;

import exception.DAOException;

public interface AbstractDao <T> {
	
	void add(T entity) throws DAOException;
	T getById(int id) throws DAOException;
	int update(T entity) throws DAOException;   // to think about returning type
	int updateById(int id) throws DAOException; // see if needed
	boolean delete(T entity) throws DAOException;
	boolean deleteById(int id) throws DAOException;

}
