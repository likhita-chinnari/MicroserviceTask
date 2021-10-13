package com.app.TestModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import com.app.model.FictionalCharacter;

@SpringBootTest
public class FictionalCharacterTest {
	String name;
	int power;
	private FictionalCharacter demoHero;

	public FictionalCharacterTest() {
		this.name = "Likhita";
		this.power = 80;
	}

	@Before
	public void setUp() throws Exception {
		this.demoHero = new FictionalCharacter(this.name, this.power);
	}

	@Test
	public void test() {
		Assert.assertEquals(this.name, demoHero.getName());
		Assert.assertEquals(this.power, demoHero.getMax_power());
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Model class Test completed");
	}

}