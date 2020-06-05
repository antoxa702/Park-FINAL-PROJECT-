package runner;

import exception.CommandException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Do some tests during writing classes, like testing poll, peek methods and else..
 * @author Anton
 */
public class Runner {

	public static void main(String[] args) throws CommandException {
		String phone = "1231351516479846515";
		Pattern pattern = Pattern.compile("[\\d]{3,12}");
		Matcher matcher = pattern.matcher(phone);
		System.out.println("your phone=" + phone);
		if (matcher.matches()){
			System.out.println("phone=" + phone);;
		} else {
			System.out.println("WRONG PHONE");
		}


		//ParkDaoImpl parkDao = ParkDaoImpl.INSTANCE;

		//String command = CommandType.GET_PARK_LIST.name();
		//String requestCommand = "get_Park_liST";

		//System.out.println(command.equals(requestCommand.toUpperCase()));


//		try {
//			List<Park> parkList = new CommandProvider().getCommand(requestCommand).execute();
//			System.out.println(parkList);
//		}  catch (ParkServiceException e) {
//			e.printStackTrace();
//		}



		// void add (Park park)
		/*	
		ParkBuilder builder = new ParkBuilder();
		Park park = new ParkBuilder().withId(111).withName("aaa").withArea(22.03).build();
		
		try {
			parkDao.add(park);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		// Park getById (int id)
		/*
		try {
			Park park = parkDao.getById(22);
			System.out.println(park);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		// int update (Park park)
		/*
		try {
			Park park = parkDao.getById(14);
			park.setName("bbb");
			parkDao.update(park);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		// Park getByName (String name)	
		/*
		try {
			Park park = parkDao.getByName("bbb");
			System.out.println(park);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		// List<Park> getAllParks ()
		/*
		List<Park> parks;
		try {
			parks = parkDao.getAllParks();
			System.out.println(parks);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		// boolean delete (Park park)
		/*
		try {
			Park park = parkDao.getById(22);
			System.out.println(parkDao.delete(park));
		}catch (Exception e) {
			// TODO: handle exception
		}
		*/
		
		// boolean deleteById (int id) - works anyway
		
		
		

		
		
		/*
		ParkBuilder builder = new ParkBuilder();
		Park park = new ParkBuilder().withId(99).withName(null).withArea(22.03).build();
		
		System.out.println(park);
		*/
		
		/*
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
		*/
		
		
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
