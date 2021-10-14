package com.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Calendar;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import org.springframework.web.client.RestTemplate;
import com.app.model.FictionalCharacter;
import java.util.TreeMap;
import java.util.Random;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@RestController
public class HomeController {
	static Random random;
	public static String[] uriArray;
	private TreeMap<String, FictionalCharacter> map;
	static RestTemplate restTemplate;

	static {
		HomeController.random = new Random();
		HomeController.uriArray = new String[] { "http://www.mocky.io/v2/5ecfd5dc3200006200e3d64b",
				"http://www.mocky.io/v2/5ecfd630320000f1aee3d64d", "http://www.mocky.io/v2/5ecfd6473200009dc1e3d64e" };
		HomeController.restTemplate = new RestTemplate();
	}

	public HomeController() {
		this.map = new TreeMap<String, FictionalCharacter>();
	}

	public Map<String, FictionalCharacter> getMap() {
		return this.map;
	}

	public void setMap(final TreeMap<String, FictionalCharacter> map) {
		this.map = map;
	}

	@Scheduled(fixedRate = 10000)
	@Async
	public void API1Calling() {
		ResponseEntity<String> response = (ResponseEntity<String>) HomeController.restTemplate.getForEntity(HomeController.uriArray[0], (Class) String.class, new Object[0]);
		String jsonString = (String) response.getBody();
		this.fetchFromAPIAndUpdateMap(jsonString, 1);
	}

	@Scheduled(fixedRate = 10000)
	@Async
	public void API2Calling() {
		ResponseEntity<String> response = (ResponseEntity<String>) HomeController.restTemplate
				.getForEntity(HomeController.uriArray[1], (Class) String.class, new Object[0]);
		String jsonString = (String) response.getBody();
		this.fetchFromAPIAndUpdateMap(jsonString, 2);
	}

	@Scheduled(fixedRate = 10000)
	@Async
	public void API3Calling() {
		ResponseEntity<String> response = (ResponseEntity<String>) HomeController.restTemplate
				.getForEntity(HomeController.uriArray[2], (Class) String.class, new Object[0]);
		String jsonString = (String) response.getBody();
		this.fetchFromAPIAndUpdateMap(jsonString, 3);
	}

	public void fetchFromAPIAndUpdateMap(final String jsonString, final int num) {
		System.out.println("Hiiiii " + num + " dateAndTime - " + Calendar.getInstance().getTime());
		JSONObject jsonObject = new JSONObject(jsonString);
		JSONArray jsonArray = jsonObject.getJSONArray("character");
		for (int i = 0; i < jsonArray.length(); ++i) {
			JSONObject obj = jsonArray.getJSONObject(i);
			String heroName = obj.getString("name");
			int maxPower = obj.getInt("max_power");
			if (this.map.containsKey(heroName)) {
				FictionalCharacter currentHero = this.map.get(heroName);
				currentHero.setMax_power(maxPower);
				if (maxPower != currentHero.getMax_power()) {
					currentHero.setMax_power(maxPower);
				} else {
					currentHero.setMax_power(HomeController.random.nextInt(100));
				}
				this.map.put(heroName, currentHero);
			} else {
				FictionalCharacter hero = new FictionalCharacter(heroName, maxPower);
				this.map.put(heroName, hero);
			}
		}
		System.out.println("map data " + num);
		for (final Map.Entry<String, FictionalCharacter> entry : this.map.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
		System.out.println();
	}

	public List<String> getAllHeroNamesInAList() {
		List<String> heroNames = new ArrayList<String>();
		RestTemplate restTemplate = new RestTemplate();
		for (int i = 0; i < HomeController.uriArray.length; ++i) {
			ResponseEntity<String> response = (ResponseEntity<String>) restTemplate
					.getForEntity(HomeController.uriArray[i], (Class) String.class, new Object[0]);
			String jsonString = (String) response.getBody();
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray("character");
			for (int j = 0; j < jsonArray.length(); ++j) {
				JSONObject obj = jsonArray.getJSONObject(j);
				String heroName = obj.getString("name");
				heroNames.add(heroName);
			}
		}
		return heroNames;
	}
}