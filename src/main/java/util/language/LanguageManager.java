package util.language;

import com.mysql.cj.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

public enum LanguageManager {

	INSTANCE;
	private static final Logger LOGGER = LogManager.getLogger(LanguageManager.class);
	private static final String BUNDLE_NAME = "text";
	private static final String LANGUAGE_DEFAULT = "default";

	private ResourceBundle bundle ;
	private Locale locale;

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	LanguageManager() {
		locale = Locale.getDefault();
		bundle =  ResourceBundle.getBundle(BUNDLE_NAME, locale);
	}

	public void changeLocale(Locale locale) {
		if(locale != null) {
			this.locale = locale;
		} else {
			LOGGER.warn("WARN : Locale is null");
			this.locale = Locale.getDefault();
		}
		bundle = ResourceBundle.getBundle(BUNDLE_NAME, this.locale);
	}

	public String getLanguage() {
		if (!StringUtils.isNullOrEmpty(bundle.getLocale().getLanguage())) {
			return bundle.getLocale().getLanguage();
		} else {
			return LANGUAGE_DEFAULT;
		}
	}

	public String getValue(String key) {
		return bundle.getString(key);
	}

}
