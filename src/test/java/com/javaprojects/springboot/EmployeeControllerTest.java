package com.javaprojects.springboot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import com.javaprojects.springboot.controller.EmployeeController;
import com.javaprojects.springboot.model.Employee;
import com.javaprojects.springboot.repository.EmployeeRepository;




@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

	@Autowired
	private EmployeeController employeeController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(employeeController).isNotNull();
	}

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private EmployeeRepository repository;

	@Test
	public void shouldReturnOkayHttpStatusAllEmployees() throws Exception {
		this.mockMvc.perform(get("/api/v1/employees")).andDo(print()).andExpect(status().isOk());
	}

	//Create new Employee test
	@Test
	@Rollback(false)
		public void testSaveNewEmployee() throws Exception {
			Employee employee = new Employee("testName","testLastname", "test@test.com");

			repository.save(employee);

			Employee employee2 = repository.findByemailID("test@test.com");

			assertThat(employee2.getEmailID()).isEqualTo("test@test.com");
	}
	
	
	
	//Update Employee Test
	
	
	//Delete Employee Test 
	

}
