package util;

public class TagValues {

	public static final String DELIMITER = "       ";
	public static final String TEMPERATURE = "Temperature : ";
	public static final String WIND = "Wind : ";
	public static final String PRESSURE = "Pressure : ";
	public static final String DESCRIPTION = "Description : ";
	public static final String HUMIDITY = "Humidity : ";
	public static final String CELSIUS = " oC";
	public static final int BEGIN_INDEX = 10;

	public static final String WEATHER_URL = "https://www.gismeteo.by/weather-minsk-4248/now/";
	public static final String MAIN_DIV_URI = "div[class=forecast_wrap horizontal]";
	public static final String TEMPERATURE_URI = "span[class=unit unit_temperature_c]";
	public static final String DESCRIPTION_URI = "div[class=now__desc]";
	public static final String WIND_URI = "div[class=unit unit_wind_m_s]";
	public static final String PRESSURE_URI = "div[class=unit unit_pressure_mm_hg_atm]";
	public static final String HUMIDITY_URI = "div[class=nowinfo__item nowinfo__item_humidity]";

}
