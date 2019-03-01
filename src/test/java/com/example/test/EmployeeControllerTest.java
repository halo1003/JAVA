package com.example.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.entity.Employee;
import com.example.service.EmployeeService;

public class EmployeeControllerTest extends DemojenkinsApplicationTests {
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetListEmployee() throws Exception {
		List<Employee> employees = Arrays.asList(new Employee("8458", 79054, "84580"),
				new Employee("8459", 95352, "84590"));

		EmployeeService mock = org.mockito.Mockito.mock(EmployeeService.class);

		when(mock.getListEmployee()).thenReturn(employees);

		RequestBuilder request = MockMvcRequestBuilders.get("/api/employee")
				.contentType("application/json;charset=UTF-8");

		mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$[0].employee_id").value("8458"))
				.andExpect(jsonPath("$[0].salary").value(79054))
				.andExpect(jsonPath("$[0].manager_id").value("84580"))
				.andExpect(jsonPath("$[1].employee_id").value("8459"))
				.andExpect(jsonPath("$[1].salary").value(95352))
				.andExpect(jsonPath("$[1].manager_id").value("84590"));

		verify(mock, times(0)).getListEmployee();
		verifyNoMoreInteractions(mock);
	}

	@Test
	public void testFindEmployeeByID() throws Exception {
		mockMvc.perform(get("/api/employee/8458")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.employee_id").value("8458")).andExpect(jsonPath("$.salary").value(79054))
				.andExpect(jsonPath("$.manager_id").value("84580"));

	}
}
