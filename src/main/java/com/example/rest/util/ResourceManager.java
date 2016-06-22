package com.example.rest.util;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class ResourceManager {
	@Autowired
	private static MessageSource messageSource;
	private static Logger logger = Logger.getLogger(ResourceManager.class);

	public static String getProperty(String key) {
		return getProperty(key, null);
	}

	public static String getProperty(String key, Object[] params) {
		Locale locale = getRequestLocale();
		return getProperty(key, params, locale);
	}

	/**
	 * This will return Locale associated with current request. Note: This Note:
	 * This Locale object is saved by spring from request headers.
	 * 
	 * @return Locale
	 */
	public static Locale getRequestLocale() {
		Locale locale = null;

		try {
			locale = LocaleContextHolder.getLocale();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return locale;

	}

	/**
	 * 
	 * @param key
	 * @param params
	 * @param locale
	 * @return
	 */
	public static String getProperty(String key, Object[] params, Locale locale) {
		if (messageSource != null) {
			return messageSource.getMessage(key, params, locale);
		} else {
			logger.info("ResourceManager is null..");
			return "";
		}
	}

	public static MessageSource getMessageSource() {
		return messageSource;
	}

	public static void setMessageSource(MessageSource messageSource) {
		ResourceManager.messageSource = messageSource;
	}

}
