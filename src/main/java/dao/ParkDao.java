package dao;

import java.util.List;

import entity.Park;
import exception.DAOException;

public interface ParkDao extends  AbstractDao<Park> {
	
	Park getByName(String name) throws DAOException;	
	List<Park> getAllParks() throws DAOException;	

}
