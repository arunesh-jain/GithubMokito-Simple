package com.knoldus.simplemockito;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Optional;

import com.knoldus.simplemockito.controller.EmployeeController;
import com.knoldus.simplemockito.model.Employee;
import com.knoldus.simplemockito.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * the type EmployeeController
 */
@SpringBootTest
//@ContextConfiguration(classes = {EmployeeController.class})
//@ExtendWith(SpringExtension.class)
public class EmployeeControllerTest {


        @Autowired
        private EmployeeController employeeController;

        @MockBean
        private EmployeeRepository employeeRepository;

        /**
         * create employee Record
         * @throws Exception
         */
        @Test
        void createEmployeeRecordTest() throws Exception {
            Employee employee = new Employee();
            employee.setId(123L);
            employee.setFullname("Name");
            employee.setAge(25);
            employee.setLocation("Noida");
            employee.setDesignation("SDE");
            when(this.employeeRepository.save((Employee) any())).thenReturn(employee);

            Employee employee1 = new Employee();
            employee1.setId(123L);
            employee1.setFullname("Name");
            employee1.setAge(25);
            employee1.setLocation("Noida");
            employee1.setDesignation("SDE");
            String content = (new ObjectMapper()).writeValueAsString(employee1);
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employee/save")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content);
            MockMvcBuilders.standaloneSetup(this.employeeController)
                    .build()
                    .perform(requestBuilder)
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                    .andExpect(MockMvcResultMatchers.content()
                            .string("{\"id\":123,\"fullname\":\"Name\",\"age\":25,\"location\":\"Noida\",\"designation\":\"SDE\"}"));
        }

        /**
         * get aal boolRecord test
         * @throws Exception
         */
        @Test
        void getAllEmployeeDetailsTest() throws Exception {
            when(this.employeeRepository.findAll()).thenReturn(new ArrayList<>());
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/getEmployee");
            MockMvcBuilders.standaloneSetup(this.employeeController)
                    .build()
                    .perform(requestBuilder)
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                    .andExpect(MockMvcResultMatchers.content().string("[]"));
        }

        /**
         *
         * @throws Exception
         */
        @Test
        void getAllemployeeRecordsTest2() throws Exception {
            when(this.employeeRepository.findAll()).thenReturn(new ArrayList<>());
            MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/employee/getEmployee");
            getResult.contentType(MediaType.APPLICATION_JSON);
            MockMvcBuilders.standaloneSetup(this.employeeController)
                    .build()
                    .perform(getResult)
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                    .andExpect(MockMvcResultMatchers.content().string("[]"));
        }

        /**
         * get employee by Id test
         * @throws Exception
         */
        @Test
        void getEmployeeByIdTest() throws Exception {
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/getEmployeeByID/123");
            ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.employeeController)
                    .build()
                    .perform(requestBuilder);
            actualPerformResult.andExpect(MockMvcResultMatchers.status().is(200));
        }

    }

