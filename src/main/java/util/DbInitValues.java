package util;

public class DbInitValues {
	
	private DbInitValues() {}
	
	/**
	 * Next Strings are used for initialize database properties file.
	 * Connection parameters are used for ConnectionPool initialize.
	 */	
	public static final String DB_PROPERTIES_FILE_NAME = "database";
	public static final String DB_DRIVER_NAME = "db.driver";
	public static final String DB_URL_NAME = "db.url";
	public static final String DB_USER_NAME = "db.user";
	public static final String DB_PASSWORD_NAME = "db.password";
	public static final String DB_POOLSIZE_NAME = "db.poolSize";
	public static final String DB_USE_UNICODE_NAME = "db.useUnicode";
	public static final String DB_ENCODING_NAME = "db.encoding";
	
	/**
	 * Next Strings are used for identify column names at database tables 
	 * 
	 *  ------ table park ------
	 */	
	public static final String TABLE_PARK_ID = "id";
	public static final String TABLE_PARK_NAME = "name";
	public static final String TABLE_PARK_AREA = "area";

	/**
	 * Next Strings are used for identify column names at database tables
	 *
	 *  ------ table user_type ------
	 */
	public static final String TABLE_USER_TYPE_ID = "id";
	public static final String TABLE_USER_TYPE_NAME = "type_name";

	/**
	 * Next Strings are used for identify column names at database tables
	 *
	 *  ------ table plant_type ------
	 */
	public static final String TABLE_PLANT_TYPE_ID = "id";
	public static final String TABLE_PLANT_TYPE_NAME = "type_name";

			
}
