package com.simoncomputing.app.kudos.service;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.simoncomputing.app.kudos.entity.User;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

@ContextConfiguration(locations = { "/testApplicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
public class UserServiceTest {
	
	@Autowired 
	UserService userService;
	
	private static Connection conn;
	
	@BeforeClass
	public static void setUp() throws LiquibaseException, SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
		conn = DriverManager.getConnection("jdbc:h2:mem:kudostest", "", "");

		Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));

		Liquibase liquibase = new Liquibase("testChangelog.xml",
				new ClassLoaderResourceAccessor(), database);
		liquibase.update("test");
	}
	
	@AfterClass
	public static void closeDB() throws SQLException{
		conn.close();
	}

	@Test public void testFindOne() {
		
		User a = userService.findOne((long) 1);
		
		assertNotNull(a);
		assertEquals((long) 1, a.getId().longValue());
		assertEquals("apple", a.getUsername());
		assertEquals("grannysmith", a.getPassword());
		assertEquals("Al", a.getFirst());
		assertEquals("Attson", a.getLast());
		
		User z = userService.findOne((long) 0);
		
		assertNull(z);
	}
	
	@Test public void testFindAll() {
		
		List<User> all = userService.findAll();
		
		assertNotNull(all);
		
		for (User u : all) {
			assertNotNull(u);
		}
	}
	
	@Test public void testSaveAndDelete() {
		
		User d = new User(null, "diesel", "ram", "Derek", "Draper");
		
		User check = userService.save(d);
		check = userService.findOne(check.getId());
		
		assertNotNull(check);
		
		assertEquals(check.getUsername(), d.getUsername());
		assertEquals(check.getPassword(), d.getPassword());
		assertEquals(check.getFirst(), d.getFirst());
		assertEquals(check.getLast(), d.getLast());
		
		d.setId(check.getId());
		d.setPassword("power");
		d.setFirst("Don");
		
		check = userService.save(d);
		check = userService.findOne(check.getId());
		
		assertNotNull(check);
		
		assertEquals(check.getId(), d.getId());
		assertEquals(check.getUsername(), d.getUsername());
		assertEquals(check.getPassword(), d.getPassword());
		assertEquals(check.getFirst(), d.getFirst());
		assertEquals(check.getLast(), d.getLast());
		
		userService.delete(d.getId());
		
		assertNull(userService.findOne(d.getId()));
	}

}
