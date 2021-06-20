package com.haulmont.creditProcessor.web;

import com.haulmont.creditProcessor.CreditAppForHaulmontApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CreditAppForHaulmontApplication.class);
	}

}
