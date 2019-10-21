package com.korosoft.invoice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })

public class InvoiceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceSystemApplication.class, args);
	}

	/*
	 * @Override protected SpringApplicationBuilder
	 * configure(SpringApplicationBuilder builder) { // TODO Auto-generated method
	 * stub return builder.sources(InvoiceSystemApplication.class); }
	 */

}
