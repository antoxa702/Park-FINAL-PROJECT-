package pool;

import static util.StaticValues.DB_DRIVER_NAME;
import static util.StaticValues.DB_PASSWORD_NAME;
import static util.StaticValues.DB_POOLSIZE_NAME;
import static util.StaticValues.DB_PROPERTIES_FILE_NAME;
import static util.StaticValues.DB_URL_NAME;
import static util.StaticValues.DB_USER_NAME;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum ConnectionPool {
	
	INSTANCE;
	
	private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);	
	private final ResourceBundle resource = ResourceBundle.getBundle(DB_PROPERTIES_FILE_NAME);
		
	private BlockingQueue<ProxyConnection> avaliableConnectionQueue;
	private Queue<ProxyConnection> blockedConnectionQueue;
	
	/**
	 * Guarantees, that when you first mind ConnectionPool.INSTANCE
	 * 	your ConnectionPool will be initialized,
	 * 	connections will be created.
	 * 
	 * Lets you not to check if ConnectionPool is initialized at every method.
	 */	
	private ConnectionPool(){
		initPool();
	}
	
	//TODO ??? getConnection()
	
	//TODO releaseConnection()
	
	//TODO closeConnection()
	
	//TODO closePool()
	
	//TODO check methods for threads
	
	//TODO link git with github
	
	
	public Connection getConnection() {
		if (avaliableConnectionQueue.size() > 0) {
			ProxyConnection proxyConnection;
			try {
				
				
				proxyConnection = avaliableConnectionQueue.take();
				blockedConnectionQueue.add(proxyConnection);	
				return proxyConnection.getConnection();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}	
		return null;
	}
	
	public void releaseConnection (ProxyConnection proxyConnection) throws SQLException {
		
	}
		
	/**
	 * Method initializes jdbc driver,
	 *  creates and filling avaliableConnectionList with connections,
	 *  initializes blockedConnectionList  
	 */
	private void initPool() {
		initDriver();
		initAvaliableConnectionQueue();
		initBlockedConnectionQueue();		
	}
	
	/**
	 * initializingblockedConnectionList
	 */
	private void initBlockedConnectionQueue() {
		blockedConnectionQueue = new ArrayDeque<>();		
	}
	
	/**
	 * initializing and filling avaliableConnectionList with connections to database
	 */
	private void initAvaliableConnectionQueue() {		
		avaliableConnectionQueue = new LinkedBlockingQueue<>();
		
		int poolCapacity = Integer.parseInt(resource.getString(DB_POOLSIZE_NAME));
		String dbUrl = resource.getString(DB_URL_NAME);
		String user = resource.getString(DB_USER_NAME);
		String password = resource.getString(DB_PASSWORD_NAME); 
		
		for (int i = 0; i < poolCapacity; i++) {
			try {
				avaliableConnectionQueue.add(new ProxyConnection(DriverManager.getConnection(dbUrl, user, password)));
			} catch (SQLException e) {				
				LOGGER.warn("connection haven't been added");				
			}
		}		
	}
	
	/**
	 * Registering mySQL driver
	 */	
	private void initDriver() {
		try {			
			String driverUrl = resource.getString(DB_DRIVER_NAME);
			Class.forName(driverUrl).getDeclaredConstructor().newInstance();			
		} catch (Exception e) {			
			LOGGER.error("can't register mysql driver");			
		} 
	}
		
}
