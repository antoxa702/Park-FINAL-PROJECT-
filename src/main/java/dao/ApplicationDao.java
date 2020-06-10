package dao;

import entity.Application;
import entity.ApplicationStatus;
import entity.Park;
import entity.User;
import exception.DAOException;

import java.util.List;

public interface ApplicationDao extends AbstractDao<Application> {
	List<Application> getAllApplicationsByOwner(User owner) throws DAOException;
	List<Application> getAllApplications() throws DAOException;
	List<Application> getAllApplicationsByEmployee(User emplyee) throws DAOException;
	List<Application> getAllApplicationsByPark(Park park) throws DAOException;
	List<Application> getAllApplicationsByStatus(ApplicationStatus status) throws DAOException;
	List<Application> getAllApplicationsIsApproved(boolean isApproved) throws DAOException;

}
