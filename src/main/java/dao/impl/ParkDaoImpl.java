package dao.impl;

import static util.StaticValues.TABLE_PARK_AREA;
import static util.StaticValues.TABLE_PARK_ID;
import static util.StaticValues.TABLE_PARK_NAME;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.cj.util.StringUtils;

import builder.ParkBuilder;
import dao.ParkDao;
import entity.Park;
import exception.ConnectionPoolException;
import exception.DAOException;
import pool.ConnectionPool;

public enum ParkDaoImpl implements ParkDao {
	
	INSTANCE;
	
	private static final Logger LOGGER = LogManager.getLogger(ParkDaoImpl.class);
	private static final String SQL_STATEMENT_GET_ALL_PARK = "SELECT * FROM park;";
	private static final String SQL_STATEMENT_INSERT_PARK = "INSERT INTO park (name, area) VALUES (?,?);";	
	private static final String SQL_STATEMENT_GET_BY_ID_PARK = "SELECT * FROM park WHERE id=?;";
	private static final String SQL_STATEMENT_GET_BY_NAME_PARK = "SELECT * FROM park WHERE name=?;";
	private static final String SQL_STATEMENT_UPDATE_PARK = "UPDATE park SET park.name=?, park.area=? WHERE park.id=?;";
	private static final String SQL_STATEMENT_DELETE_PARK = "DELETE FROM park WHERE park.id=?;";
	
	/**
	 * Adds a record to Park database into table park.
	 */
	@Override
	public void add(Park park) throws DAOException {
		
		if (park == null) {
			LOGGER.error("ERROR : entity is null");
			throw new DAOException("ERROR : can't add park - entity is null");			
		}		
		
		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_INSERT_PARK)) {
			
			if (!StringUtils.isNullOrEmpty(park.getName())) {
				statement.setString(1, park.getName());
			} else {
				LOGGER.warn("WARN : parkName is null");
				statement.setNull(1, Types.NULL);
			}			
			
			statement.setDouble(2, park.getArea());
			
			if (statement.executeUpdate() == 1) {
				LOGGER.debug("DEBUG : record added successful");
			} else {
				LOGGER.warn("WARN : record haven't been added");
				throw new SQLException("ERROR : None or few records have been inserted into park");
			}			
			
		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with adding a record into table park");
			throw new DAOException("SQLException while adding a record into table park", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with adding a record into table park");
			throw new DAOException("ConnectionPoolException while adding a record into table park", e1);
		} 	
		
	}
	
	/**
	 * Finds a record in table Park by ID and returns (if it exists) a Park entity
	 * according to this database record.
	 */
	@Override
	public Park getById(int id) throws DAOException {
		
		Park park = null;		
		
		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_GET_BY_ID_PARK)) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {	
				String parkName = resultSet.getString(TABLE_PARK_NAME);
				double parkArea = resultSet.getDouble(TABLE_PARK_AREA);				
				park = new ParkBuilder().withId(id).withName(parkName).withArea(parkArea).build();					
			} else {
				LOGGER.error("ERROR : Can't find park by ID");
				throw new DAOException("Error : no such park by ID");
			}
			
		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with getting a record from table park");
			throw new DAOException("SQLException while getting a record from table park", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with getting a record from table park");
			throw new DAOException("ConnectionPoolException while getting a record from table park", e1);
		} 	
		
		return park;
	}
	
	/**
	 * Finds a record in table Park by matching park ID on entity and table record 
	 * and returns number of updated rows.
	 */
	@Override
	public int update(Park park) throws DAOException {
		if (park == null) {
			LOGGER.error ("ERROR : park entity is null");
			throw new DAOException("ERROR : can't update table park by requared entity");
		}	
		
		int updatedRowsCount = 0;
			
		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_UPDATE_PARK)) {
			
			if (!StringUtils.isNullOrEmpty(park.getName())) {
				statement.setString(1, park.getName());
			} else {
				LOGGER.warn("WARN : parkName is null");
				statement.setNull(1, Types.NULL);
			}			
			
			statement.setDouble(2, park.getArea());
			statement.setInt(3, park.getId());
			updatedRowsCount = statement.executeUpdate();			
			
			if (updatedRowsCount == 1) {
				LOGGER.debug("DEBUG : record updated successful");
			} else {
				LOGGER.warn("WARN : record haven't been updated");
				throw new SQLException("ERROR : None records have been updated into park");
			}			
			
		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with updating a record into table park");
			throw new DAOException("SQLException while updating a record into table park", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with updating a record into table park");
			throw new DAOException("ConnectionPoolException while updating a record into table park", e1);
		} 	
		
		return updatedRowsCount;
	}	

	/**
	 * Finds a record in table Park by matching park ID on entity 
	 * and deletes from table this record. 
	 * Returns true if deleted 1 row, false - if none have been deleted.
	 */
	@Override
	public boolean delete(Park park) throws DAOException {
		if (park == null) {
			LOGGER.error ("ERROR : park entity is null");
			throw new DAOException("ERROR : can't update table park by requared entity");
		}		
				
		return deleteById(park.getId());		
	}

	/**
	 * Finds a record in table Park by matching park ID on entity 
	 * and deletes from table this record. 
	 * Returns true if deleted 1 row, false - if none have been deleted.
	 */
	@Override
	public boolean deleteById(int id) throws DAOException {	
				
		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_DELETE_PARK)) {
			statement.setInt(1, id);			
			if (statement.executeUpdate() != 1) {	
				LOGGER.warn("WARN : None records have been deleted by ID");
				return false;
			} 			
		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with deleting a record from table park");
			throw new DAOException("SQLException while deleting a record from table park", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with deleting a record from table park");
			throw new DAOException("ConnectionPoolException while deleting a record from table park", e1);
		} 	
		
		return true;
	}

	/**
	 * Finds a record in table Park by name and returns (if it exists) a Park entity
	 * according to this database record.
	 */
	@Override
	public Park getByName(String name) throws DAOException {
		Park park = null;
		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_GET_BY_NAME_PARK)) {
			statement.setString(1, name.trim());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {	
				int id = resultSet.getInt(TABLE_PARK_ID);
				String parkName = resultSet.getString(TABLE_PARK_NAME);
				double parkArea = resultSet.getDouble(TABLE_PARK_AREA);				
				park = new ParkBuilder().withId(id).withName(parkName).withArea(parkArea).build();					
			} else {
				LOGGER.warn("WARN : Can't find park by name");
				throw new DAOException("Error : no such park by name");
			}			
		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with getting a record from table park");
			throw new DAOException("SQLException while getting a record from table park", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with getting a record from table park");
			throw new DAOException("ConnectionPoolException while getting a record from table park", e1);
		} 	
		
		return park;		
	}

	/**
	 * Returns a list of Park entities according to database's table park.
	 */
	@Override
	public List<Park> getAllParks() throws DAOException {
		
		List<Park> parks = new ArrayList<>();
		
		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_GET_ALL_PARK)) {			
			
			ResultSet resultSet = statement.executeQuery();			
			int id; 
			String parkName;
			double parkArea;			
		
			while(resultSet.next()) {
				id = resultSet.getInt(TABLE_PARK_ID);
				parkName = resultSet.getString(TABLE_PARK_NAME);
				parkArea = resultSet.getDouble(TABLE_PARK_AREA);		
				parks.add(new ParkBuilder().withId(id).withName(parkName).withArea(parkArea).build());
			}
		} catch (SQLException e) {
			LOGGER.error("ERROR : problems with getting records from table park");
			throw new DAOException("SQLException while getting records from table park", e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error("ERROR : problems with getting records from table park");
			throw new DAOException("ConnectionPoolException while getting records from table park", e1);
		} 	
			
		return parks;
	}
	
	/**
	 * Unsupported operation
	 */
	@Override
	public int updateById(int id) throws DAOException {
		throw new UnsupportedOperationException("Operation doesn't supports with park entity");
	}	
	
	//TODO test in runner all the methods

}
