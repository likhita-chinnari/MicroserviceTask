package com.test.controllersTest;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.app.controllers.HomeController;

public class HomeControllerTest {

	HomeController homeController;
	String[] uriArray = new String[] { "http://www.mocky.io/v2/5ecfd5dc3200006200e3d64b",
			"http://www.mocky.io/v2/5ecfd630320000f1aee3d64d", "http://www.mocky.io/v2/5ecfd6473200009dc1e3d64e" };

	@Before
	public void setUp() {
		homeController = new HomeController();
	}

	@Test
	@Order(1)
	public void testFetchFromAPIAndUpdateMap() {
		RestTemplate restTemplate = new RestTemplate();
		for (int i = 0; i < HomeController.uriArray.length; ++i) {
			ResponseEntity<String> result = restTemplate.getForEntity(uriArray[i], String.class);
			Assert.assertEquals(200, result.getStatusCodeValue());
			Assert.assertEquals(true, result.getBody().contains("name"));
			Assert.assertEquals(true, result.getBody().contains("character"));
		}
	}

	@Test
	@Order(2)
	public void testHeroesListSize() {
		Assert.assertEquals(homeController.getAllHeroNamesInAList().size(), 18);
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("HomeController tested");

	}

}
