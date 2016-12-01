package mvc;

public enum ActionType {
	HALT("Halt"),WALK("Walk"),SHOOT("Shoot"),DIE("");
	String message;
	private ActionType(String text){
		this.message = text;
	}
	public String getMessage() {
		return message;
	}

}
