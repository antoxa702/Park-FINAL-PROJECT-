package builder;

import entity.PlantType;

public class PlantTypeBuilder {

	private PlantType plantType;

	public PlantTypeBuilder() {
		plantType = new PlantType();
	}

	public PlantTypeBuilder withId(int id){
		plantType.setId(id);
		return this;
	}

	public PlantTypeBuilder withName(String name) {
		plantType.setTypeName(name);
		return this;
	}

	public PlantType build() {
		return this.plantType;
	}
}
