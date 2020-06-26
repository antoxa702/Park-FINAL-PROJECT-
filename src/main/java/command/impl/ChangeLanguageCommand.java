package command.impl;

import command.Command;
import command.PageManager;
import exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.language.LanguageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class ChangeLanguageCommand implements Command {

	private static final Logger LOGGER = LogManager.getLogger(ChangeLanguageCommand.class);

	public static final String RUSSIAN_LANGUAGE = "ru";
	public static final String ENGLISH_LANGUAGE = "en";
	public static final String RUSSIAN_LOCALE = "RU";
	public static final String ENGLISH_LOCALE = "UK";

	@Override
	public PageManager execute(HttpServletRequest request) throws ServiceException, ServiceException {

		LanguageManager languageManager = LanguageManager.INSTANCE;

		if (request.getSession().getAttribute("language") != null){
			languageManager = (LanguageManager) request.getSession().getAttribute("language");
		} else {
			request.setAttribute("language", languageManager);
		}

		String languageInstalled = languageManager.getLanguage();
		switch (languageInstalled) {
			case RUSSIAN_LANGUAGE : {
				languageManager.changeLocale(new Locale(ENGLISH_LANGUAGE, ENGLISH_LOCALE));
				break;
			}
			case ENGLISH_LANGUAGE : {
				languageManager.changeLocale(new Locale(RUSSIAN_LANGUAGE, RUSSIAN_LOCALE));
				break;
			}
			default: {
				LOGGER.warn("WARN : Locale is default");
				languageManager.changeLocale(new Locale(RUSSIAN_LANGUAGE, RUSSIAN_LOCALE));
			}
		}
		request.getSession().setAttribute("language", languageManager);

		return null; //TODO return rewrite
	}
}
