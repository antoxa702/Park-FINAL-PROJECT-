package pool;

import static util.StaticValues.DB_DRIVER_NAME;
import static util.StaticValues.DB_PASSWORD_NAME;
import static util.StaticValues.DB_POOLSIZE_NAME;
import static util.StaticValues.DB_PROPERTIES_FILE_NAME;
import static util.StaticValues.DB_URL_NAME;
import static util.StaticValues.DB_USER_NAME;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.ConnectionPoolException;

public enum ConnectionPool {
	
	INSTANCE;
	
	private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);	
	private final ResourceBundle resource = ResourceBundle.getBundle(DB_PROPERTIES_FILE_NAME);
		
	private BlockingQueue<ProxyConnection> availableConnectionQueue;
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
	
	/**
	 * Returns connection from proxyConnection if there are some available connections
	 * or waiting, while any available appears and then get it.
	 * @return connection
	 * @throws ConnectionPoolException
	 */	
	public ProxyConnection getConnection() throws ConnectionPoolException{		
		ProxyConnection proxyConnection;
		try {						
			proxyConnection = availableConnectionQueue.take();
			blockedConnectionQueue.add(proxyConnection);
			return proxyConnection;
		} catch (InterruptedException e) {
			LOGGER.error("Can't recieve connection from avaliableConnectionQueue");
			throw new ConnectionPoolException("Error during getting connection");
		}				
	}
	
	/**
	 * Removes proxyConnection from blockedConnectionQueue
	 * and returns it into avaliableConnectionQueue
	 * @param proxyConnection
	 * @throws ConnectionPoolException
	 */	
	public void releaseConnection (ProxyConnection proxyConnection) throws ConnectionPoolException{
		if (proxyConnection == null) {
			LOGGER.error("Error : proxyConnection is null");
			throw new ConnectionPoolException("Error : passed parameter 'proxyConnection' is null");
		}
		
		if (blockedConnectionQueue.remove(proxyConnection)) {
			availableConnectionQueue.add(proxyConnection);
		} else {
			LOGGER.error("Error : there is  no such proxyConnection object in blockedConnectionQueue");
			throw new ConnectionPoolException("Error with removing connection from blockedConnectionQueue");
		}		
	}	
	
	/**
	 * Closes all connections in ConnectionPool (for external situations) 
	 */	
	public synchronized void closeAllConnectionsInPool() {
		try {
			closeAvailableConnections();
			closeBlockedConnections();
		} catch (SQLException e) {
			LOGGER.error("Error during closing connections");			
		}				
	}
		
	/**
	 * Closes all available connections in ConnectionPool (for external situations) 
	 */	
	private void closeAvailableConnections() throws SQLException {
		for(ProxyConnection proxyConnection : availableConnectionQueue) {
			proxyConnection.closeInPool();			
		}		
	}
	
	/**
	 * Closes all blocked connections in ConnectionPool (for external situations) 
	 */	
	private void closeBlockedConnections() throws SQLException {		
		for(ProxyConnection proxyConnection : blockedConnectionQueue) {
			proxyConnection.closeInPool();			
		}		
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
		availableConnectionQueue = new LinkedBlockingQueue<>();
		
		int poolCapacity = Integer.parseInt(resource.getString(DB_POOLSIZE_NAME));
		String dbUrl = resource.getString(DB_URL_NAME);
		String user = resource.getString(DB_USER_NAME);
		String password = resource.getString(DB_PASSWORD_NAME); 
		
		for (int i = 0; i < poolCapacity; i++) {
			try {
				availableConnectionQueue.add(new ProxyConnection(DriverManager.getConnection(dbUrl, user, password)));
			} catch (SQLException e) {				
				LOGGER.error("ERROR : connection haven't been added");				
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
