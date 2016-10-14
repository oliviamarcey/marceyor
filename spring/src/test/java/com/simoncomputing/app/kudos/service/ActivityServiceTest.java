package com.simoncomputing.app.kudos.service;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.simoncomputing.app.kudos.entity.Activity;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

@ContextConfiguration(locations = { "/testApplicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
public class ActivityServiceTest {
	
	@Autowired 
	ActivityService activityService;
	
	private static Connection conn;
	
	@BeforeClass
	public static void setUp() throws LiquibaseException, SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
		conn = DriverManager.getConnection("jdbc:h2:mem:activitytest", "", "");

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
		
		Activity a = activityService.findOne((long) 1);
		
		assertNotNull(a);
		assertEquals((long) 1, a.getId().longValue());
		assertEquals((long) 1, a.getFromUserId().longValue());
		assertEquals((long) 1, a.getKudosId().longValue());
		assertEquals("COMMENT", a.getType());
		assertEquals("dido", a.getComment());
		assertNotNull(a.getFromUser());

		Activity z = activityService.findOne((long) 0);
		
		assertNull(z);
	}
	
	@Test public void testFindAll() {
		
		List<Activity> all = activityService.findAll();
		
		assertNotNull(all);
		
		for (Activity u : all) {
			assertNotNull(u);
			assertNotNull(u.getFromUser());
		}
	}
	
	@Test public void testSaveAndDelete() {

		Activity a = new Activity(null, (long) 2, "LIKE", (long) 3, null, null, null, null, new Date(System.currentTimeMillis()));
		
		Activity check = activityService.save(a);
		check = activityService.findOne(check.getId());
		
		assertNotNull(check);
		
		assertEquals(check.getFromUserId().longValue(), a.getFromUserId().longValue());
		assertEquals(check.getKudosId(), a.getKudosId());
		assertNotNull(check.getFromUser());

		a.setId(check.getId());
		a.setComment("talk about it");
		
		check = activityService.save(a);
		check = activityService.findOne(check.getId());
		
		assertNotNull(check);
		
		assertEquals(check.getId(), a.getId());
		assertEquals(check.getType(), a.getType());
		assertEquals(check.getKudosId(), a.getKudosId());
		assertEquals(check.getComment(), a.getComment());
		assertNotNull(check.getFromUser());

		activityService.delete(a.getId());
		
		assertNull(activityService.findOne(a.getId()));
	}

}
