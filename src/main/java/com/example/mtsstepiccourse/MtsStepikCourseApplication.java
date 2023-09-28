package com.example.mtsstepiccourse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MtsStepikCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MtsStepikCourseApplication.class, args);
		Logger logger = LoggerFactory.getLogger(MtsStepikCourseApplication.class);
		logger.trace("trace");
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");

	}


}
