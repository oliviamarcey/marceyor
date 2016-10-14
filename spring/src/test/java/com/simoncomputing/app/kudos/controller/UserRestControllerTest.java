package com.simoncomputing.app.kudos.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simoncomputing.app.kudos.entity.User;
import com.simoncomputing.app.kudos.service.UserService;

import static org.mockito.Mockito.*; 
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "/testApplicationContext.xml" })
public class UserRestControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	private UserService userService;
	
	@InjectMocks UserRestController userRestController;
	
	@Before public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(this.userRestController).build();
	}
	
	@Test
	public void testGetAll() throws Exception {
		
		User a = new User((long) 1, "apple", "grannysmith", "Al", "Attson" );
		User b = new User((long) 2, "berry", "straw", "Barry", "Beatson");
		
		List<User> users = Arrays.asList(a, b);
		when(userService.findAll()).thenReturn(users);
		
		mockMvc.perform(get("/user")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
		        .andExpect(jsonPath("$", hasSize(2)))
		        .andExpect(jsonPath("$[0].id", is(1)))
		        .andExpect(jsonPath("$[0].username", is("apple")))
		        .andExpect(jsonPath("$[1].id", is(2)))
		        .andExpect(jsonPath("$[1].username", is("berry")));
		
		verify(userService, times(1)).findAll();		
	}
	
	@Test
	public void testGetOne() throws Exception {
		
		User a = new User((long) 1, "apple", "grannysmith", "Al", "Attson" );
		
		when(userService.findOne((long) 1)).thenReturn(a);
		when(userService.findOne((long) 2)).thenReturn(null);
		
		mockMvc.perform(get("/user/{id}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
		        .andExpect(status().isOk())
		        .andExpect(content().contentType("application/json;charset=UTF-8"))
		        .andExpect(jsonPath("$.id", is(1)))
		        .andExpect(jsonPath("$.username", is("apple")));

		mockMvc.perform(get("/user/{id}", 2))
        		.andExpect(status().isNotFound());
		
		verify(userService, times(1)).findOne((long) 1);		
		verify(userService, times(1)).findOne((long) 2);		
	}
	
	@Test
	public void testCreateUser() throws Exception {

		User a = new User((long) 1, "apple", "grannysmith", "Al", "Attson" );

		when(userService.save(eq(a))).thenReturn(a);
		
		mockMvc.perform(post("/user")
				.contentType("application/json;charset=UTF-8")
	            .content(asJsonString(a)))
		    	.andExpect(status().isOk())
		    	.andExpect(jsonPath("$.id", is(1)))
		        .andExpect(jsonPath("$.username", is("apple")));		
	}
	
	@Test
	public void testUpdateUser() throws Exception {

		User a = new User((long) 1, "apple", "grannysmith", "Al", "Attson");

		when(userService.save(eq(a))).thenReturn(a);
		
		mockMvc.perform(put("/user")
				.contentType("application/json;charset=UTF-8")
	            .content(asJsonString(a)))
		    	.andExpect(status().isOk())
		    	.andExpect(jsonPath("$.id", is(1)))
		        .andExpect(jsonPath("$.username", is("apple")));
	}
	
	@Test
	public void testDeleteUser() throws Exception {
		
		User a = new User((long) 1, "apple", "grannysmith", "Al", "Attson" );

		when(userService.findOne((long) 1)).thenReturn(a);
		when(userService.findOne((long) 2)).thenReturn(null);
		
		doNothing().when(userService).delete((long) 1);
		
		mockMvc.perform(delete("/user/{id}", 1))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType("application/json;charset=UTF-8"))
	        .andExpect(jsonPath("$.id", is(1)))
	        .andExpect(jsonPath("$.username", is("apple")));
		
		mockMvc.perform(delete("/user/{id}", 2))
	        .andExpect(status().isNotFound());
		
		verify(userService, times(1)).findOne((long) 1);
		verify(userService, times(1)).findOne((long) 2);
		verify(userService, times(1)).delete((long) 1);
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
