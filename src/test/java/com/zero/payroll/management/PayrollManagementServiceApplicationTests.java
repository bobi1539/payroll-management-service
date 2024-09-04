package com.zero.payroll.management;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PayrollManagementServiceApplicationTests {

	@Test
	void contextLoads() {
		String hello = "hello";
		assertEquals(5, hello.length());
	}

	@Test
	void testMainMethod() {
		try (var mockedSpringApplication = Mockito.mockStatic(SpringApplication.class)) {
			// Call the main method
			PayrollManagementServiceApplication.main(new String[]{});

			// Verify that SpringApplication.run was called once with PayrollManagementServiceApplication.class
			mockedSpringApplication.verify(
					() -> SpringApplication.run(
							PayrollManagementServiceApplication.class, new String[]{}
					), Mockito.times(1)
			);
		}
	}
}
