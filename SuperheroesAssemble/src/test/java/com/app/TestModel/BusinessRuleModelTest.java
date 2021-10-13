package com.app.TestModel;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import com.app.model.BusinessRuleModel;

@SpringBootTest
public class BusinessRuleModelTest {
	BusinessRuleModel requestsCompare;

	@Before
	public void setUp() throws Exception {
		this.requestsCompare = new BusinessRuleModel();
	}

	@Test
	@Order(1)
	public void testRequestCountsEqualAndPowerLess() {
		final BusinessRuleModel firstRequest1 = new BusinessRuleModel("Iron man", 1, 80);
		final BusinessRuleModel secondRequest2 = new BusinessRuleModel("Spider Man", 1, 90);
		final int result = firstRequest1.compareTo(secondRequest2);
		Assert.assertTrue("expected to be equal", result < 0);
	}

	@Test
	@Order(2)
	public void testRequestCountsGreaterThan() {
		final BusinessRuleModel firstRequest3 = new BusinessRuleModel("Iron man", 2, 80);
		final BusinessRuleModel secondRequest4 = new BusinessRuleModel("Spider Man", 1, 90);
		final int result = firstRequest3.compareTo(secondRequest4);
		Assert.assertTrue("expected to be equal", result > 0);
	}

	@Test
	@Order(3)
	public void testRequestCountsLessThan() {
		final BusinessRuleModel firstRequest5 = new BusinessRuleModel("Iron man", 2, 80);
		final BusinessRuleModel secondRequest6 = new BusinessRuleModel("Spider Man", 3, 90);
		final int result = firstRequest5.compareTo(secondRequest6);
		Assert.assertTrue("expected to be less", result < 0);
	}

	@After
	public void tearDown() throws Exception {
		System.out.println(" BusinessRuleModel class test completed");
	}
}
