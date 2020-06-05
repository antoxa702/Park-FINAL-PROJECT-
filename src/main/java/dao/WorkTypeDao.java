package dao;

import entity.WorkType;
import exception.DAOException;

import java.util.List;

public interface WorkTypeDao extends AbstractDao<WorkType> {
	WorkType getByName(String name) throws DAOException;
	List<WorkType> getAllWorkTypes() throws DAOException;
}
