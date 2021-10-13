package com.app.controllers;

import java.util.Iterator;
import com.app.model.FictionalCharacter;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashSet;
import com.app.model.BusinessRuleModel;
import java.util.PriorityQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Component;

@Component
@RestController
@RequestMapping({ "/" })
public class InputsController {
	@Autowired
	HomeController homeController;
	private static PriorityQueue<BusinessRuleModel> priorityQueue;
	private static HashSet<String> hashSet;
	private static final int cacheSize;
	private static int requestCount;

	static {
		InputsController.priorityQueue = null;
		InputsController.hashSet = null;
		System.out.println("I am static block........................................................");
		InputsController.priorityQueue = new PriorityQueue<BusinessRuleModel>();
		InputsController.hashSet = new HashSet<String>();
		cacheSize = 5;
		InputsController.requestCount = 0;
	}

	public static PriorityQueue<BusinessRuleModel> getPriorityQueue() {
		return InputsController.priorityQueue;
	}

	public static void setPriorityQueue(final PriorityQueue<BusinessRuleModel> priorityQueue) {
		InputsController.priorityQueue = priorityQueue;
	}

	public static HashSet<String> getHashSet() {
		return InputsController.hashSet;
	}

	public static void setHashSet(final HashSet<String> hashSet) {
		InputsController.hashSet = hashSet;
	}

	public static int getRequestCount() {
		return InputsController.requestCount;
	}

	public static void setRequestCount(final int requestCount) {
		InputsController.requestCount = requestCount;
	}

	public static int getCachesize() {
		return InputsController.cacheSize;
	}

	@RequestMapping({ "/inputCharacter" })
	public ModelAndView getInputCharacterAndDisplayPower(
			@RequestParam(name = "characterSelectedByUser") final String selectedHero,
			final HttpServletRequest request) {
		if (selectedHero != null) {
			this.cachingInputRequests(selectedHero, InputsController.requestCount++);
		}
		System.out.println("\nPriority Queue elements after adding " + selectedHero + " are :\n");
		for (BusinessRuleModel model : this.priorityQueue) {
			System.out.println(model.toString());
		}
		System.out.println();
		final ModelAndView modelView = new ModelAndView();
		request.setAttribute("selectedHero", (Object) selectedHero);
		request.setAttribute("currentPowerOfHero",
				(Object) this.homeController.getMap().get(selectedHero).getMax_power());
		modelView.setViewName("displayPower");
		return modelView;
	}

	public void cachingInputRequests(final String selectedHero, final int count) {
		if (InputsController.requestCount > InputsController.cacheSize) {
			final BusinessRuleModel model = InputsController.priorityQueue.poll();
			model.changeCount(model.getCount() - 1);
			if (model.getCount() == 0) {
				InputsController.hashSet.remove(model.getCharacterName());
			} else {
				InputsController.priorityQueue.add(model);
			}
		}
		int mPower = this.homeController.getMap().get(selectedHero).getMax_power();
		if (!InputsController.hashSet.contains(selectedHero)) {
			InputsController.priorityQueue.add(new BusinessRuleModel(selectedHero, 1, mPower));
			InputsController.hashSet.add(selectedHero);
		} else {
			final Iterator<BusinessRuleModel> iterator = InputsController.priorityQueue.iterator();
			BusinessRuleModel tempModel = null;
			while (iterator.hasNext()) {
				tempModel = iterator.next();
				if (tempModel.getCharacterName().equals(selectedHero)) {
					break;
				}
			}
			InputsController.priorityQueue.remove(tempModel);
			tempModel.changeCount(tempModel.getCount() + 1);
			tempModel.setPower(mPower);
			InputsController.priorityQueue.add(tempModel);
		}
	}
}