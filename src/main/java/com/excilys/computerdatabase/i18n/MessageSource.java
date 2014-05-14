package com.excilys.computerdatabase.i18n;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;


public class MessageSource extends AbstractMessageSource {

	@Autowired
	private MessageSource messageSource;
	
	@Override
    protected MessageFormat resolveCode(String key, Locale locale) {
        // Utilisation du service pour récuperer le message traduit
        String message = messageSource.getMessage(key, null, locale);
        // Et renvoie de celui-ci sous la bonne forme
        return createMessageFormat(message, locale);

    }
}
