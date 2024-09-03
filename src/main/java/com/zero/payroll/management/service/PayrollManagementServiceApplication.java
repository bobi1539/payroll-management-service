package com.zero.payroll.management.service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Payroll Management Service",
		version = "1.0",
		description = "Payroll Management Service")
)
public class PayrollManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayrollManagementServiceApplication.class, args);
	}

}
