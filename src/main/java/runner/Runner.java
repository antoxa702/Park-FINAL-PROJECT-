package runner;

import dao.impl.ParkDaoImpl;
import entity.Park;
import exception.DAOException;


/**
 * Do some tests during writing classes, like testing poll, peek methods and else..
 * @author Anton
 */
public class Runner {

	public static void main(String[] args) {
		
		
		Park park = new Park(12, "Парк Дививелка", 16.6);
		ParkDaoImpl parkDao = new ParkDaoImpl();
		try {
			parkDao.add(park);
		} catch (DAOException e) {
			System.out.println("something went wrong");
			e.printStackTrace();
		}
		
		
		
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
