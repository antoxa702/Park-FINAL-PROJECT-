package builder;

import entity.Park;

public class ParkBuilder {
	
	private Park park;
	
	public ParkBuilder() {
		park = new Park();
	}
	
	public void createNewPark() {
		park = new Park();
	}
	
	public ParkBuilder withId(int id) {
		park.setId(id);
		return this;
	}
	
	public ParkBuilder withName(String parkName) {
		park.setName(parkName);
		return this;
	}
	
	public ParkBuilder withArea (double parkArea) {
		park.setArea(parkArea);
		return this;
	}
	
	public Park build() {
		return park;
	}

}
