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

	/**
	 * Next Strings are used for identify column names at database tables
	 *
	 *  ------ table work_type ------
	 */
	public static final String TABLE_WORK_TYPE_ID = "id";
	public static final String TABLE_WORK_TYPE_NAME = "type_name";

	/**
	 * Next Strings are used for identify column names at database tables
	 *
	 *  ------ table application_status ------
	 */
	public static final String TABLE_APPLICATION_STATUS_ID = "id";
	public static final String TABLE_APPLICATION_STATUS_NAME = "status_name";

	/**
	 * Next Strings are used for identify column names at database tables
	 *
	 *  ------ table user_info ------
	 */
	public static final String TABLE_USER_INFO_ID = "id";
	public static final String TABLE_USER_INFO_LOGIN = "login";
	public static final String TABLE_USER_INFO_PASSWORD = "password";
	public static final String TABLE_USER_INFO_FIRST_NAME = "first_name";
	public static final String TABLE_USER_INFO_LAST_NAME = "last_name";
	public static final String TABLE_USER_INFO_PHONE_NUMBER = "phone_number";
	public static final String TABLE_USER_INFO_EMAIL = "email";
	public static final String TABLE_USER_INFO_USER_TYPE_ID = "User_type_id";
	public static final String TABLE_USER_INFO_PARK_ID = "Park_id";

	/**
	 * Next Strings are used for identify column names at database tables
	 *
	 *  ------ table application ------
	 */
	public static final String TABLE_APPLICATION_ID = "id";
	public static final String TABLE_APPLICATION_START_DATE = "start_date";
	public static final String TABLE_APPLICATION_END_DATE = "end_date";
	public static final String TABLE_APPLICATION_IS_APPROVED = "is_approved";
	public static final String TABLE_APPLICATION_OWNER_COMMENT = "owner_comment";
	public static final String TABLE_APPLICATION_EMPLOYEE_COMMENT = "employee_comment";
	public static final String TABLE_APPLICATION_WORK_TYPE_ID = "Type_of_work_id";
	public static final String TABLE_APPLICATION_PLANT_TYPE_ID = "Plants_type_id";
	public static final String TABLE_APPLICATION_PARK_ID = "Park_id";
	public static final String TABLE_APPLICATION_APL_STATUS_ID = "Status_id";
	public static final String TABLE_APPLICATION_EMPLOYEE_ID = "Employee_id";
	public static final String TABLE_APPLICATION_OWNER_ID = "Owner_id";
}
