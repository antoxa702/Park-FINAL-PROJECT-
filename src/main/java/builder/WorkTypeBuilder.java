package builder;

import entity.WorkType;

public class WorkTypeBuilder {

	WorkType workType;

	public WorkTypeBuilder(){
		workType = new WorkType();
	}

	public WorkTypeBuilder withId(int id){
		workType.setId(id);
		return this;
	}

	public WorkTypeBuilder withTypeName(String typeName){
		workType.setTypeName(typeName);
		return this;
	}

	public WorkType build(){
		return this.workType;
	}
}
