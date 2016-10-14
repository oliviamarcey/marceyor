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

import com.simoncomputing.app.kudos.entity.Kudos;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

@ContextConfiguration(locations = { "/testApplicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
public class KudosServiceTest {
	
	@Autowired 
	KudosService kudosService;
	
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
		
		Kudos k = kudosService.findOne((long) 1);
		
		assertNotNull(k);
		assertEquals((long) 1, k.getId().longValue());
		assertEquals((long) 2, k.getFromUserId().longValue());
		assertEquals((long) 3, k.getToUserId().longValue());
		assertNotNull(k.getFromUser());
		assertNotNull(k.getToUser());
		assertEquals(100, k.getAmount().longValue());
		assertEquals("You suck!", k.getMessage());
		
		Kudos z = kudosService.findOne((long) 0);
		
		assertNull(z);
	}
	
	@Test public void testFindAll() {
		
		List<Kudos> all = kudosService.findAll();
		
		assertNotNull(all);
		
		for (Kudos u : all) {
			assertNotNull(u);
			assertNotNull(u.getFromUser());
			assertNotNull(u.getToUser());
		}
	}
	
	@Test public void testSaveAndDelete() {
		
		Kudos k = new Kudos(null, (long) 2, null, (long) 1, null, 70, "Jump off a bridge.", new Date(System.currentTimeMillis()) );
		
		Kudos check = kudosService.save(k);
		check = kudosService.findOne(check.getId());
		
		assertNotNull(check);

		assertEquals(check.getAmount(), k.getAmount());
		assertEquals(check.getMessage(), k.getMessage());
		assertNotNull(check.getFromUser());
		assertNotNull(check.getToUser());

		k.setId(check.getId());
		k.setAmount(10);
		k.setMessage("Seriously");
		
		check = kudosService.save(k);
		check = kudosService.findOne(check.getId());
		
		assertNotNull(check);
		
		assertNotNull(kudosService.findOne(k.getId()));
		
		assertEquals(check.getId(), k.getId());
		assertEquals(check.getAmount(), k.getAmount());
		assertEquals(check.getMessage(), k.getMessage());
		assertNotNull(check.getFromUser());
		assertNotNull(check.getToUser());

		kudosService.delete(k.getId());
		
		assertNull(kudosService.findOne(k.getId()));
	}

}
