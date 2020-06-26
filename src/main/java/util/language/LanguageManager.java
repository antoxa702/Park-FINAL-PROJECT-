package util.language;

import com.mysql.cj.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

public enum LanguageManager {

	INSTANCE;
	private static final Logger LOGGER = LogManager.getLogger(LanguageManager.class);

	ResourceBundle bundle ;
	Locale locale;

	private LanguageManager() {
		locale = Locale.getDefault();
		bundle =  ResourceBundle.getBundle("text", locale);
	}


	public void changeLanguage(String language){
		if (!StringUtils.isNullOrEmpty(language)) {
			locale = new Locale(language);
		} else {
			locale = Locale.getDefault();
			LOGGER.warn("WARN : Locale is null or empty");
		}
		bundle = ResourceBundle.getBundle("text", locale);
	}

	public void changeLocale(Locale locale) {
		if(locale != null) {
			this.locale = locale;
		} else {
			this.locale = Locale.getDefault();
			LOGGER.warn("WARN : Locale is null");
		}
		bundle = ResourceBundle.getBundle("text", this.locale);
	}

	public String getLanguage() {
		if (!StringUtils.isNullOrEmpty(bundle.getLocale().getLanguage())) {
			return bundle.getLocale().getLanguage();
		} else {
			return "default";
		}
	}

	public String getValue (String key) {
		return bundle.getString(key);
	}

}
