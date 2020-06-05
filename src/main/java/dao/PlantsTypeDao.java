package dao;

import entity.Park;
import exception.DAOException;

import java.util.List;

public interface PlantsTypeDao extends AbstractDao<PlantsTypeDao> {
	PlantType getByName(String name) throws DAOException;
	List<PlantType> getAllPlantTypes() throws DAOException;
}
