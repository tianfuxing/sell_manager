package com.study;

import com.study.common.aspect.WebLogAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * SpringBoot启动程序
 * @author junior
 *
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {


	private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

	public static void main(String[] args) {
	    SpringApplication.run(Application.class,args);
		LOGGER.info("----------启动成功------------------");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application .class);
	}

}
