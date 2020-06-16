package runner;

import dao.impl.UserDaoImpl;
import entity.User;
import exception.CommandException;
import exception.DAOException;

import java.security.NoSuchAlgorithmException;

/**
 * Do some tests during writing classes, like testing poll, peek methods and else..
 * @author Anton
 */
public class Runner {

	public static void main(String[] args) throws CommandException, NoSuchAlgorithmException {

		//UserTypeDaoImpl userTypeDao = UserTypeDaoImpl.INSTANCE;
//
//		UserType userType = new UserTypeBuilder().withName("owner").build();
//		try{
//			userTypeDao.add(userType);
//		} catch (DAOException e) {
//			e.printStackTrace();
//		}
//
//		List<UserType> userTypeList;
//		try {
//			userTypeList = userTypeDao.getAllUserTypes();
//			System.out.println(userTypeList);
//		} catch (DAOException e) {
//			e.printStackTrace();
//		}

//		UserType userType = null;
//		try{
//			userType = userTypeDao.getById(5);
//		} catch (DAOException e) {
//			e.printStackTrace();
//		}
//		System.out.println(userType);
//		String password = "password";
//		String passwordCheck = "password";
//
//		MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
//		byte[] bytes = sha1.digest(password.getBytes());
//		byte[] bytesCheck = sha1.digest(passwordCheck.getBytes());
//
//		System.out.println("password = [" + password + "]");
//		System.out.println("passwordCheck = [" + passwordCheck + "]");
//
//		for (byte b : bytes) {
//			System.out.print(b);
//		}
//		System.out.println("");
//
//		for (byte b : bytesCheck) {
//			System.out.print(b);
//		}
//		System.out.println("");
//
//		StringBuilder password1 = new StringBuilder();
//		StringBuilder password2 = new StringBuilder();
//		if(bytes.length != bytesCheck.length) {
//			System.out.println("FALSE : passwords have different length");
//		} else{
//			for (int i = 0; i < bytes.length; i++) {
//				password1.append(bytes[i]);
//				password2.append(bytesCheck[i]);
//			}
//
//			if(password1.toString().equals(password2.toString())){
//				System.out.println("TRUE : passwords are equal");
//			}else {
//				System.out.println("FALSE : passwords are not equal");
//			}
//
//		}

//		UserFieldsValidator validator = new UserFieldsValidator();
//
//		if (!StringUtils.isNullOrEmpty("anton") && validator.validateLogin("anton")) {
//			System.out.println("");;
//		} else {
//			LOGGER.warn("WARN : login is null or incorrect");
//			//statement.setNull(1, Types.NULL);
//		}
//
//		char[] pass = {'a', 'n' , 't' ,'o' ,'n'};
//		System.out.println(Arrays.toString(pass));

		//applicationStatus
//		ApplicationStatusDaoImpl statusDao = ApplicationStatusDaoImpl.INSTANCE;
//		ApplicationStatus statusAdd  = null;
//		ApplicationStatus statusGet  = null;
//		ApplicationStatus statusUpdate  = null;
//		ApplicationStatus statusDelete  = null;

//		try {
//
//			statusAdd = new ApplicationStatusBuilder().withStatusName("test_status").build();
//			statusDao.add(statusAdd);
//			statusGet = statusDao.getByName("В работе");
//			statusUpdate = statusDao.getByName("test_status");
//			statusUpdate.setStatusName("test_status_update");
//			statusDao.update(statusUpdate);
//			statusDelete = statusDao.getByName("test_status_update");
//			statusDao.delete(statusDelete);
//
//		} catch (DAOException e) {
//			e.printStackTrace();
//		}
//		System.out.println(statusAdd);
//		System.out.println(statusGet);
//		System.out.println(statusUpdate);
//		System.out.println(statusDelete);

		//userType
//		UserTypeDaoImpl userTypeDao = UserTypeDaoImpl.INSTANCE;
//		UserType userTypeAdd = null;
//		UserType userTypeGet = null;
//		UserType userTypeUpdate = null;
//		UserType userTypeDelete = null;

//		try {

//			userTypeAdd = new UserTypeBuilder().withName("test_status").build();
//			userTypeDao.add(userTypeAdd);
//			userTypeGet = userTypeDao.getByName("owner");
//			userTypeUpdate = userTypeDao.getByName("test_status");
//			userTypeUpdate.setNameType("test_status_update");
//			userTypeDao.update(userTypeUpdate);
//			userTypeDelete = userTypeDao.getByName("test_status_update");
//			userTypeDao.delete(userTypeDelete);
//
//		} catch ( DAOException e) {
//			e.printStackTrace();
//		}
//		System.out.println(userTypeAdd);
//		System.out.println(userTypeGet);
//		System.out.println(userTypeUpdate);
//		System.out.println(userTypeDelete);

		//PlantsType
//		PlantTypeDaoImpl plantTypeDao = PlantTypeDaoImpl.INSTANCE;
//		PlantType plantTypeAdd = null;
//		PlantType plantTypeGet = null;
//		PlantType plantTypeUpdate = null;
//		PlantType plantTypeDelete = null;
//
//		try {
//			plantTypeAdd = new PlantTypeBuilder().withName("test_status").build();
//			plantTypeDao.add(plantTypeAdd);
//			plantTypeGet = plantTypeDao.getByName("Лианы");
//			plantTypeUpdate = plantTypeDao.getByName("test_status");
//			plantTypeUpdate.setTypeName("test_status_update");
//			plantTypeDao.update(plantTypeUpdate);
//			plantTypeDelete = plantTypeDao.getByName("test_status_update");
//			plantTypeDao.delete(plantTypeDelete);
//
//		} catch ( DAOException e) {
//			e.printStackTrace();
//		}
//		System.out.println(plantTypeAdd);
//		System.out.println(plantTypeGet);
//		System.out.println(plantTypeUpdate);
//		System.out.println(plantTypeDelete);

		//workType
//		WorkTypeDaoImpl workTypeDao = WorkTypeDaoImpl.INSTANCE;
//		WorkType workTypeAdd = null;
//		WorkType workTypeGet = null;
//		WorkType workTypeUpdate = null;
//		WorkType workTypeDelete = null;

//		try {
//			workTypeAdd = new WorkTypeBuilder().withTypeName("test_status").build();
//			workTypeDao.add(workTypeAdd);
//			workTypeGet = workTypeDao.getByName("Лечение");
//			workTypeUpdate = workTypeDao.getByName("test_status");
//			workTypeUpdate.setTypeName("test_status_update");
//			workTypeDao.update(workTypeUpdate);
//			workTypeDelete = workTypeDao.getByName("test_status_update");
//			workTypeDao.delete(workTypeDelete);
//
//		} catch ( DAOException e) {
//			e.printStackTrace();
//		}
//		System.out.println(workTypeAdd);
//		System.out.println(workTypeGet);
//		System.out.println(workTypeUpdate);
//		System.out.println(workTypeDelete);

		//park
//		ParkDaoImpl parkDao = ParkDaoImpl.INSTANCE;
//		Park park = null;


		//user

//		UserDaoImpl userDao = UserDaoImpl.INSTANCE;
//		User userAdd = null;
//		User userGet = null;
//		User userUpdate = null;
//		User userDelete = null;
//		Park park = null;
//		UserType userType= null;
//
//		try {
//			userType = UserTypeDaoImpl.INSTANCE.getById(2);
//			park = ParkDaoImpl.INSTANCE.getById(5);
//			userAdd = new UserBuilder().withLogin("test_status").withPassword("testpassword").
//					withFirstName("ttt").withLastName("aaa").withPhoneNumber("55555").
//					withEmail("tt@oo.dd").withUserType(userType).withPark(park).build();
//			userDao.add(userAdd);
//			userGet = userDao.getById(3);
//			userUpdate = userDao.getByLogin("test_status");
//			userUpdate.setLogin("test_status_update");
//			userDao.update(userUpdate);
//			userDelete = userDao.getByLogin("test_status_update");
//			userDao.delete(userDelete);
//
//		} catch (DAOException e) {
//			e.printStackTrace();
//		}
//		System.out.println(userAdd);
//		System.out.println(userGet);
//		System.out.println(userUpdate);
//		System.out.println(userDelete);

		//application
//		ApplicationDaoImpl applicationDao = ApplicationDaoImpl.INSTANCE;
//		Application applicationAdd = null;
//		Application applicationGet = null;
//		Application applicationUpdate = null;
//		Application applicationDelete = null;
//
//		try {
//			User owner =  UserDaoImpl.INSTANCE.getById(3);
//			User employee = UserDaoImpl.INSTANCE.getById(4);
//			ApplicationStatus status = ApplicationStatusDaoImpl.INSTANCE.getById(1);
//			PlantType plantType = PlantTypeDaoImpl.INSTANCE.getById(3);
//			WorkType workType = WorkTypeDaoImpl.INSTANCE.getById(2);
//			Park park = ParkDaoImpl.INSTANCE.getById(5);
//
//			applicationAdd = new ApplicationBuilder().withStartDate(new java.sql.Date(new Date().getTime())).
//								withIsApproved(false).withWorkType(workType).
//								withPlantType(plantType).withPark(park).
//								withApplicationStatus(status).withOwner(owner).
//								withEmployee(employee).build();
//			applicationDao.add(applicationAdd);
//			applicationGet = applicationDao.getById(1);
//			applicationUpdate = applicationDao.getById(2);
//			applicationUpdate.setOwnerComment("go fuck yourself");
//			applicationDao.update(applicationUpdate);
//			applicationDelete = applicationDao.getById(2);
//			applicationDao.delete(applicationDelete);

//		} catch (DAOException e) {
//			e.printStackTrace();
//		}
//		System.out.println(applicationAdd);
//		System.out.println(applicationGet);
//		System.out.println(applicationUpdate);
//		System.out.println(applicationDelete);
//
		try {



			User user  = UserDaoImpl.INSTANCE.getByLogin("admin");
			System.out.println("login=" + user.getLogin());
			System.out.println("password=" + user.getPassword());
		} catch (DAOException e) {
			System.out.println("something wrong with dao");;
		}















		/*
		String phone = "1231351516479846515";
		Pattern pattern = Pattern.compile("[\\d]{3,12}");
		Matcher matcher = pattern.matcher(phone);
		System.out.println("your phone=" + phone);
		if (matcher.matches()){
			System.out.println("phone=" + phone);;
		} else {
			System.out.println("WRONG PHONE");
		}
*/

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
