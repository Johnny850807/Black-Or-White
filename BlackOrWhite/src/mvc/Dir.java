package mvc;

public enum Dir {
	NORTH("North"),EAST("East"),SOUTH("South"),WEST("West");
	String message;
	private Dir(String text){
		this.message = text;
	}
	public String getMessage() {
		return message;
	}

}
