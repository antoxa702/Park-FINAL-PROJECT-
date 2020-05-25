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
	
	public void withId(int id) {
		park.setId(id);
	}
	
	public void withName(String parkName) {
		park.setName(parkName);
	}
	
	public void withArea (double parkArea) {
		park.setArea(parkArea);
	}
	
	public Park build() {
		return park;
	}

}
