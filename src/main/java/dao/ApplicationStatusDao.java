package dao;

import entity.ApplicationStatus;
import exception.DAOException;

import java.util.List;

public interface ApplicationStatusDao extends AbstractDao<ApplicationStatus> {
	ApplicationStatus getByName(String name) throws DAOException;
	List<ApplicationStatus> getAllApplicationStatuses() throws DAOException;
}
