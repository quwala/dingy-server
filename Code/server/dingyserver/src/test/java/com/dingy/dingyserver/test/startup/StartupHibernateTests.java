package com.dingy.dingyserver.test.startup;

import com.dingy.dingyserver.startup.Startup;

import static org.junit.Assert.*;

import org.junit.Test;

public class StartupHibernateTests {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testStartupSuccess(){
		assertTrue(Startup.startup());
	}
}


