package com.app.model;

public class BusinessRuleModel implements Comparable<BusinessRuleModel>, Cloneable {
	String characterName;
	int count;
	int power;

	public String getCharacterName() {
		return this.characterName;
	}

	public void setCharacterName(final String characterName) {
		this.characterName = characterName;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(final int count) {
		this.count = count;
	}

	public int getPower() {
		return this.power;
	}

	public void setPower(final int power) {
		this.power = power;
	}

	public BusinessRuleModel(final String characterName, final int count, final int power) {
		this.characterName = characterName;
		this.count = count;
		this.power = power;
	}

	public BusinessRuleModel() {
	}

	@Override
	public String toString() {
		return "[characterName=" + this.characterName + ", inputCount=" + this.count + ", power= " + this.power + " ]";
	}

	@Override
	public int compareTo(final BusinessRuleModel o) {
		if (this.getCount() != o.getCount()) {
			return this.getCount() - o.getCount();
		}
		return this.getPower() - o.getPower();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		final BusinessRuleModel copy = new BusinessRuleModel();
		copy.power = this.power;
		copy.characterName = this.characterName;
		copy.count = this.count;
		return copy;
	}

	public void changeCount(final int count) {
		this.count = count;
	}
}