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
import com.simoncomputing.app.kudos.entity.Activity;
import com.simoncomputing.app.kudos.entity.User;
import com.simoncomputing.app.kudos.service.ActivityService;

import static org.mockito.Mockito.*; 
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "/testApplicationContext.xml" })
public class ActivityRestControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	private ActivityService activityService;
	
	@InjectMocks ActivityRestController activityRestController;
	
	@Before public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(this.activityRestController).build();
	}
	
	@Test
	public void testGetAll() throws Exception {
		
		Activity a = new Activity((long) 1, (long) 1, "LIKE", (long) 1, null, null, null, null, null);
		Activity b = new Activity((long) 2, (long) 2, "COMMENT", (long) 2, null, null, null, "Hay", null);
		
		List<Activity> activitys = Arrays.asList(a, b);
		when(activityService.findAll()).thenReturn(activitys);
		
		mockMvc.perform(get("/activity")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
		        .andExpect(jsonPath("$", hasSize(2)))
		        .andExpect(jsonPath("$[0].id", is(1)))
		        .andExpect(jsonPath("$[0].fromUserId", is(1)))
		        .andExpect(jsonPath("$[0].type", is("LIKE")))
		        .andExpect(jsonPath("$[1].id", is(2)))
		        .andExpect(jsonPath("$[1].fromUserId", is(2)))
		        .andExpect(jsonPath("$[1].type", is("COMMENT")))
        		.andExpect(jsonPath("$[1].comment", is("Hay")));
		
		verify(activityService, times(1)).findAll();	
	}
	
	@Test
	public void testGetOne() throws Exception {
		
		User u = new User((long) 1, "apple", "grannysmith", "Al", "Attson" );

		Activity a = new Activity((long) 1, (long) 1, "LIKE", (long) 1, u, null, null, null, null);
		
		when(activityService.findOne((long) 1)).thenReturn(a);
		when(activityService.findOne((long) 2)).thenReturn(null);
		
		mockMvc.perform(get("/activity/{id}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
		        .andExpect(jsonPath("$.id", is(1)))
		        .andExpect(jsonPath("$.type", is("LIKE")))
		        .andExpect(jsonPath("$.fromUser.id", is(1)))
		        .andExpect(jsonPath("$.fromUser.username", is("apple")));
			

		mockMvc.perform(get("/activity/{id}", 2))
        		.andExpect(status().isNotFound());
		
		verify(activityService, times(1)).findOne((long) 1);		
		verify(activityService, times(1)).findOne((long) 2);		
	}
	
	@Test
	public void testCreateActivity() throws Exception {

		User u = new User((long) 2, "berry", "straw", "Barry", "Beatson");

		Activity a = new Activity((long) 1, (long) 1, "COMMENT", (long) 2, u, null, null, "awesammy", null);

		when(activityService.save(eq(a))).thenReturn(a);
		
		mockMvc.perform(post("/activity")
				.contentType("application/json;charset=UTF-8")
	            .content(asJsonString(a)))
		    	.andExpect(status().isOk())
		        .andExpect(jsonPath("$.id", is(1)))
		        .andExpect(jsonPath("$.type", is("COMMENT")))
		        .andExpect(jsonPath("$.comment", is("awesammy")))
		        .andExpect(jsonPath("$.fromUser.id", is(2)))
		        .andExpect(jsonPath("$.fromUser.username", is("berry")));
	}
	
	@Test
	public void testUpdateActivity() throws Exception {
		
		User u = new User((long) 2, "berry", "straw", "Barry", "Beatson");

		Activity a = new Activity((long) 1, (long) 1, "COMMENT", (long) 2, u, null, null, "awesammy", null);

		when(activityService.save(eq(a))).thenReturn(a);
		
		mockMvc.perform(put("/activity")
				.contentType("application/json;charset=UTF-8")
	            .content(asJsonString(a)))
		    	.andExpect(status().isOk())
		        .andExpect(jsonPath("$.id", is(1)))
		        .andExpect(jsonPath("$.type", is("COMMENT")))
		        .andExpect(jsonPath("$.comment", is("awesammy")))
		        .andExpect(jsonPath("$.fromUser.id", is(2)))
		        .andExpect(jsonPath("$.fromUser.username", is("berry")));
	}
	
	@Test
	public void testDeleteActivity() throws Exception {
		
		User u = new User((long) 2, "berry", "straw", "Barry", "Beatson");

		Activity a = new Activity((long) 1, (long) 1, "COMMENT", (long) 2, u, null, null, "awesammy", null);

		when(activityService.findOne((long) 1)).thenReturn(a);
		when(activityService.findOne((long) 2)).thenReturn(null);
		
		doNothing().when(activityService).delete((long) 1);
		
		mockMvc.perform(delete("/activity/{id}", 1))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType("application/json;charset=UTF-8"))
	        .andExpect(jsonPath("$.id", is(1)))
	        .andExpect(jsonPath("$.type", is("COMMENT")))
	        .andExpect(jsonPath("$.comment", is("awesammy")))
	        .andExpect(jsonPath("$.fromUser.id", is(2)))
	        .andExpect(jsonPath("$.fromUser.username", is("berry")));
		
		mockMvc.perform(delete("/activity/{id}", 2))
	        .andExpect(status().isNotFound());
		
		verify(activityService, times(1)).findOne((long) 1);
		verify(activityService, times(1)).findOne((long) 2);
		verify(activityService, times(1)).delete((long) 1);
	}

	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
