package dao;

public interface AbstractDao <T> {
	
	T create(T entity) throws DAOException;
	T getById(int id) throws DAOException;
	void update(T entity) throws DAOException;
	void updateById(int id) throws DAOException;
	boolean delete(T entity) throws DAOException;
	boolean deleteById(int id) throws DAOException;
	

}
