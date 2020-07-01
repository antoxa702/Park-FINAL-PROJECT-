package command.impl;

import command.Command;
import command.PageManager;
import exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.language.LanguageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

import static util.FrontControllerValues.LANGUAGE_MANAGER;

public class ChangeLanguageCommand implements Command {

	private static final Logger LOGGER = LogManager.getLogger(ChangeLanguageCommand.class);

	public static final String RUSSIAN_LANGUAGE = "ru";
	public static final String ENGLISH_LANGUAGE = "en";
	public static final String RUSSIAN_COUNTRY = "RU";
	public static final String ENGLISH_COUNTRY = "UK";
	public static final Locale RUSSIAN_LOCALE = new Locale(RUSSIAN_LANGUAGE, RUSSIAN_COUNTRY);
	public static final Locale ENGLISH_LOCALE = new Locale(ENGLISH_LANGUAGE, ENGLISH_COUNTRY);

	@Override
	public PageManager execute(HttpServletRequest request) throws ServiceException, ServiceException {

		LanguageManager languageManager = LanguageManager.INSTANCE;

		if (request.getSession().getAttribute(LANGUAGE_MANAGER) != null){
			languageManager = (LanguageManager) request.getSession().getAttribute(LANGUAGE_MANAGER);
		} else {
			request.setAttribute(LANGUAGE_MANAGER, languageManager);
		}

		String languageInstalled = languageManager.getLanguage();
		switch (languageInstalled) {
			case RUSSIAN_LANGUAGE : {
				languageManager.changeLocale(ENGLISH_LOCALE);
				break;
			}
			case ENGLISH_LANGUAGE : {
				languageManager.changeLocale(RUSSIAN_LOCALE);
				break;
			}
		}
		request.getSession().setAttribute(LANGUAGE_MANAGER, languageManager);
		return PageManager.THIS; //TODO return rewrite
	}
}
