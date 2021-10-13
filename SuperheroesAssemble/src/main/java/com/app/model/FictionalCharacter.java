package com.app.model;

import java.io.Serializable;

public class FictionalCharacter implements Serializable {
	private String name;
	private int max_power;

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getMax_power() {
		return this.max_power;
	}

	public void setMax_power(final int max_power) {
		this.max_power = max_power;
	}

	public FictionalCharacter(final String name, final int max_power) {
		this.max_power = max_power;
		this.name = name;
	}

	@Override
	public String toString() {
		return String.valueOf(this.name) + " " + this.max_power;
	}
}
