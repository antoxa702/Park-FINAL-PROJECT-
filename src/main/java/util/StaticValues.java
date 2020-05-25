package util;

public class StaticValues {
	
	private StaticValues() {}
	
	/**
	 * Next Strings are used for initialize database properties file,
	 * where connection parameters are used for ConnectionPool initialize, too.
	 */	
	public static final String DB_PROPERTIES_FILE_NAME = "database";
	public static final String DB_DRIVER_NAME = "db.driver";
	public static final String DB_URL_NAME = "db.url";
	public static final String DB_USER_NAME = "db.user";
	public static final String DB_PASSWORD_NAME = "db.password";
	public static final String DB_POOLSIZE_NAME = "db.poolSize";
	public static final String DB_USE_UNICODE_NAME = "db.useUnicode";
	public static final String DB_ENCODING_NAME = "db.encoding";
			
}
