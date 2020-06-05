package dao;

import entity.PlantType;
import exception.DAOException;

import java.util.List;

public interface PlantTypeDao extends AbstractDao<PlantType> {
	PlantType getByName(String name) throws DAOException;
	List<PlantType> getAllPlantTypes() throws DAOException;
}
