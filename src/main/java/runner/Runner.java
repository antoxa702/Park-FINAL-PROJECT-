package runner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import builder.ParkBuilder;
import entity.Park;
import exception.ConnectionPoolException;
import pool.ConnectionPool;


/**
 * Do some tests during writing classes, like testing poll, peek methods and else..
 * @author Anton
 */
public class Runner {

	public static void main(String[] args) {
		/*
		ParkBuilder builder = new ParkBuilder();
		Park park = new ParkBuilder().withId(99).withName(null).withArea(22.03).build();
		
		System.out.println(park);
		*/
		
		Park park = null;		
		int idNumber = 20;
		
		try(Connection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM park WHERE id=?;")) {
			statement.setInt(1, idNumber);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {	
				String parkName = resultSet.getString("name");
				double parkArea = resultSet.getDouble("area");				
				park = new ParkBuilder().withId(idNumber).withName(parkName).withArea(parkArea).build();					
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		System.out.println(park);
		
		
		
		/*
		Park park = new Park(12, "Парк Дививелка", 16.6);
		ParkDaoImpl parkDao = ParkDaoImpl.INSTANCE;
		try {
			parkDao.add(park);
		} catch (DAOException e) {
			System.out.println("something went wrong");
			e.printStackTrace();
		}
		*/
		
		
		/*
		Statement statement = null;
		//Connection connection = null;
		ResultSet resultSet = null;
		ProxyConnection proxyConnection = null;
		
		try {			
			ConnectionPool pool = ConnectionPool.INSTANCE;			
			proxyConnection = pool.getConnection();
			System.out.println("connection created");
			
			statement = proxyConnection.createStatement();
			String query = "SELECT * FROM park";
			resultSet = statement.executeQuery(query);
			
			int id;
			String parkName;
			double parkArea;
			
			while(resultSet.next()) {
				id = resultSet.getInt("id");
				parkName = resultSet.getString("name");
				parkArea = resultSet.getDouble("area");
				
				System.out.println("------------------------------------------");
				System.out.println("id=" + id + ", parkName=" + parkName + ", parkarea=" + parkArea);
				System.out.println("------------------------------------------\n");
			}
			//pool.releaseConnection(proxyConnection);
				
		}  catch (ConnectionPoolException e) {
			System.out.println("something is wrong with initializing connection pool");
		} catch (SQLException e) {
			System.out.println("something is wrong with creating statement or getting resultSet");
		} finally {			
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				
				if (statement != null) {
					statement.close();
				}
				
				if (proxyConnection != null) {
					proxyConnection.close();
				}			
				
			} catch (SQLException e) {
				System.out.println("something is wrong with closing connection, statement or resultSet");
			}				
		}*/
	}	
}
