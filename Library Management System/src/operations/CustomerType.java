package operations;

import java.util.List;

public enum CustomerType {

GOLDEN("Golden Customer"), NORMAL("Normal Customer");
	
	String typeName;
	
	CustomerType(String typeName) {
		this.typeName = typeName;
	}
	
	public String getTypeName() {
		return typeName;
	}

}

