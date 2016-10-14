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
import com.simoncomputing.app.kudos.entity.Kudos;
import com.simoncomputing.app.kudos.service.KudosService;

import static org.mockito.Mockito.*; 
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "/testApplicationContext.xml" })
public class KudosRestControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	private KudosService kudosService;
	
	@InjectMocks KudosRestController kudosRestController;
	
	@Before public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(this.kudosRestController).build();
	}
	
	@Test
	public void testGetAll() throws Exception {
		
		Kudos a = new Kudos((long) 1, (long) 2, null, (long) 3, null, 100, "You suck ~~", null);
		Kudos b = new Kudos((long) 2, (long) 3, null, (long) 1, null, 50, "I hate you!", null);
		
		List<Kudos> kudoss = Arrays.asList(a, b);
		when(kudosService.findAll()).thenReturn(kudoss);
		
		mockMvc.perform(get("/kudos")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
		        .andExpect(jsonPath("$", hasSize(2)))
		        .andExpect(jsonPath("$[0].id", is(1)))
		        .andExpect(jsonPath("$[0].message", is("You suck ~~")))
		        .andExpect(jsonPath("$[1].id", is(2)))
		        .andExpect(jsonPath("$[1].message", is("I hate you!")));
		
		verify(kudosService, times(1)).findAll();		
	}
	
	@Test
	public void testGetOne() throws Exception {
		
		Kudos a = new Kudos((long) 1, (long) 2, null, (long) 3, null, 100, "You suck ~~", null );
		
		when(kudosService.findOne((long) 1)).thenReturn(a);
		when(kudosService.findOne((long) 2)).thenReturn(null);
		
		mockMvc.perform(get("/kudos/{id}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
		        .andExpect(jsonPath("$.id", is(1)))
		        .andExpect(jsonPath("$.message", is("You suck ~~")));

		mockMvc.perform(get("/kudos/{id}", 2))
        		.andExpect(status().isNotFound());
		
		verify(kudosService, times(1)).findOne((long) 1);		
		verify(kudosService, times(1)).findOne((long) 2);		
	}
	
	@Test
	public void testCreateKudos() throws Exception {

		Kudos k = new Kudos((long) 1, (long) 2, null, (long) 3, null, 100, "You suck ~~", null );

		when(kudosService.save(eq(k))).thenReturn(k);
		
		mockMvc.perform(post("/kudos")
				.contentType("application/json;charset=UTF-8")
	            .content(asJsonString(k)))
		    	.andExpect(status().isOk())
		    	.andExpect(jsonPath("$.id", is(1)))
		        .andExpect(jsonPath("$.message", is("You suck ~~")));		
	}
	
	@Test
	public void testUpdateKudos() throws Exception {

		Kudos k = new Kudos((long) 1, (long) 2, null, (long) 3, null, 100, "I hate you!", null );

		when(kudosService.save(eq(k))).thenReturn(k);
		
		mockMvc.perform(put("/kudos")
				.contentType("application/json;charset=UTF-8")
	            .content(asJsonString(k)))
		    	.andExpect(status().isOk())
		    	.andExpect(jsonPath("$.id", is(1)))
		        .andExpect(jsonPath("$.message", is("I hate you!")));
	}
	
	@Test
	public void testDeleteKudos() throws Exception {
		
		Kudos k = new Kudos((long) 1, (long) 2, null, (long) 3, null, 100, "I hate you!", null);

		when(kudosService.findOne((long) 1)).thenReturn(k);
		when(kudosService.findOne((long) 2)).thenReturn(null);
		
		doNothing().when(kudosService).delete((long) 1);
		
		mockMvc.perform(delete("/kudos/{id}", 1))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType("application/json;charset=UTF-8"))
	        .andExpect(jsonPath("$.id", is(1)))
	        .andExpect(jsonPath("$.message", is("I hate you!")));
		
		mockMvc.perform(delete("/kudos/{id}", 2))
	        .andExpect(status().isNotFound());
		
		verify(kudosService, times(1)).findOne((long) 1);
		verify(kudosService, times(1)).findOne((long) 2);
		verify(kudosService, times(1)).delete((long) 1);
	}

	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
