package dao.impl;

import builder.PlantTypeBuilder;
import com.mysql.cj.util.StringUtils;
import dao.PlantTypeDao;
import entity.PlantType;
import exception.ConnectionPoolException;
import exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.DbInitValues.TABLE_PLANT_TYPE_ID;
import static util.DbInitValues.TABLE_PLANT_TYPE_NAME;

public enum PlantTypeDaoImpl implements PlantTypeDao {

	INSTANCE;

	private static final Logger LOGGER = LogManager.getLogger(UserTypeDaoImpl.class);
	private static final String SQL_STATEMENT_GET_ALL_PLANT_TYPE = "SELECT * FROM plant_type;";
	private static final String SQL_STATEMENT_INSERT_PLANT_TYPE = "INSERT INTO plant_type (type_name) VALUES (?);";
	private static final String SQL_STATEMENT_GET_BY_ID_PLANT_TYPE = "SELECT * FROM plant_type WHERE id=?;";
	private static final String SQL_STATEMENT_GET_BY_NAME_PLANT_TYPE = "SELECT * FROM plant_type WHERE type_name=?;";
	private static final String SQL_STATEMENT_UPDATE_PLANT_TYPE = "UPDATE plant_type SET plant_type.type_name=? WHERE plant_type.id=?;";
	private static final String SQL_STATEMENT_DELETE_PLANT_TYPE = "DELETE FROM plant_type WHERE plant_type.id=?;";

	/**
	 * Returns a list of PlantType entities according to database's table plant_type.
	 */
	@Override
	public List<PlantType> getAllPlantTypes() throws DAOException {
		List<PlantType> plantTypeList = new ArrayList<>();
		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_GET_ALL_PLANT_TYPE)) {

			ResultSet resultSet = statement.executeQuery();
			int id;
			String typeName;

			while(resultSet.next()) {
				id = resultSet.getInt(TABLE_PLANT_TYPE_ID);
				typeName = resultSet.getString(TABLE_PLANT_TYPE_NAME);
				plantTypeList.add(new PlantTypeBuilder().withId(id).withName(typeName).build());
			}
		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with getting records from table plant_type");
			throw new DAOException("SQLException while getting records from table plant_type", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with getting records from table plant_type");
			throw new DAOException("ConnectionPoolException while getting records from table plant_type", e1);
		}
		return plantTypeList;
	}

	/**
	 * Finds a record in table plant_type by type_name and returns (if it exists) a PlantType entity
	 * according to this database record.
	 */
	@Override
	public PlantType getByName(String name) throws DAOException {
		PlantType plantType = null;
		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_GET_BY_NAME_PLANT_TYPE)) {
			statement.setString(1, name.trim());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int id = resultSet.getInt(TABLE_PLANT_TYPE_ID);
				String typeName = resultSet.getString(TABLE_PLANT_TYPE_NAME);
				plantType = new PlantTypeBuilder().withId(id).withName(typeName).build();
			} else {
				LOGGER.warn("WARN : Can't find plantType by name");
				throw new DAOException("Error : no such plantType by name");
			}
		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with getting a record from table plant_type");
			throw new DAOException("SQLException while getting a record from table plant_type", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with getting a record from table plant_type");
			throw new DAOException("ConnectionPoolException while getting a record from table plant_type", e1);
		}

		return plantType;
	}

	/**
	 * Adds a record to Park database into table plant_type.
	 */
	@Override
	public void add(PlantType plantType) throws DAOException {

		if (plantType == null) {
			LOGGER.error("ERROR : plantType is null");
			throw new DAOException("ERROR : can't add plantType - plantType is null");
		}

		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_INSERT_PLANT_TYPE)) {

			if (!StringUtils.isNullOrEmpty(plantType.getTypeName())) {
				statement.setString(1, plantType.getTypeName());
			} else {
				LOGGER.warn("WARN : typeName is null");
				statement.setNull(1, Types.NULL);
			}

			if (statement.executeUpdate() == 1) {
				LOGGER.debug("DEBUG : record added successfully");
			} else {
				LOGGER.warn("WARN : record haven't been added");
				throw new SQLException("ERROR : None or few records have been inserted into plant_type");
			}

		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with adding a record into table plant_type");
			throw new DAOException("SQLException while adding a record into table plant_type", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with adding a record into table plant_type");
			throw new DAOException("ConnectionPoolException while adding a record into table plant_type", e1);
		}
	}

	/**
	 * Finds a record in table plant_type by ID and returns (if it exists) a PlantType entity
	 * according to this database record.
	 */
	@Override
	public PlantType getById(int id) throws DAOException {
		PlantType plantType = null;

		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_GET_BY_ID_PLANT_TYPE)) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String typeName = resultSet.getString(TABLE_PLANT_TYPE_NAME);
				plantType = new PlantTypeBuilder().withId(id).withName(typeName).build();
			} else {
				LOGGER.error("ERROR : Can't find plantType by ID");
				throw new DAOException("Error : no such plantType by ID");
			}

		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with getting a record from table plant_type");
			throw new DAOException("SQLException while getting a record from table plant_type", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with getting a record from table plant_type");
			throw new DAOException("ConnectionPoolException while getting a record from table plant_type", e1);
		}
		return plantType;
	}

	/**
	 * Finds a record in table plant_type by matching plant_type ID on entity and table record
	 * and returns number of updated rows.
	 */
	@Override
	public int update(PlantType plantType) throws DAOException {
		if (plantType == null) {
			LOGGER.error ("ERROR : plantType is null");
			throw new DAOException("ERROR : can't update table plant_type by required plantType");
		}

		int updatedRowsCount = 0;

		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_UPDATE_PLANT_TYPE)) {

			if (!StringUtils.isNullOrEmpty(plantType.getTypeName())) {
				statement.setString(1, plantType.getTypeName());
			} else {
				LOGGER.warn("WARN : typeName is null");
				statement.setNull(1, Types.NULL);
			}

			statement.setInt(2, plantType.getId());
			updatedRowsCount = statement.executeUpdate();

			if (updatedRowsCount == 1) {
				LOGGER.debug("DEBUG : record have been updated successfully");
			} else {
				LOGGER.warn("WARN : record haven't been updated");
				throw new SQLException("ERROR : None records have been updated into plant_type");
			}

		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with updating a record into table plant_type");
			throw new DAOException("SQLException while updating a record into table plant_type", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with updating a record into table plant_type");
			throw new DAOException("ConnectionPoolException while updating a record into table plant_type", e1);
		}

		return updatedRowsCount;
	}

	/**
	 * Finds a record in table plant_type by matching plantType ID on entity
	 * and deletes from table this record.
	 * Returns true if deleted 1 row, false - if none have been deleted.
	 */
	@Override
	public boolean delete(PlantType plantType) throws DAOException {
		if (plantType == null) {
			LOGGER.error ("ERROR : PlantType entity is null");
			throw new DAOException("ERROR : can't update table plant_type by required entity");
		}

		return deleteById(plantType.getId());
	}

	/**
	 * Finds a record in table plant_type by matching PlantPark ID on entity
	 * and deletes from table this record.
	 * Returns true if deleted 1 row, false - if none have been deleted.
	 */
	@Override
	public boolean deleteById(int id) throws DAOException {

		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_DELETE_PLANT_TYPE)) {
			statement.setInt(1, id);
			if (statement.executeUpdate() != 1) {
				LOGGER.warn("WARN : None records have been deleted by ID");
				return false;
			}
		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with deleting a record from table plant_type");
			throw new DAOException("SQLException while deleting a record from table plant_type", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with deleting a record from table plant_type");
			throw new DAOException("ConnectionPoolException while deleting a record from table plant_type", e1);
		}

		return true;
	}

	/**
	 * Unsupported operation
	 */
	@Override
	public int updateById(int id) throws DAOException {
		throw new UnsupportedOperationException("Operation doesn't supports with plant_type entity");
	}
}
