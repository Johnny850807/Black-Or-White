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
	public Dir getOppositeDir(){
		switch(this)
		{
			case NORTH:
				return SOUTH;
			case SOUTH:
				return NORTH;
			case EAST:
				return WEST;
			case WEST:
				return EAST;
		}
		return NORTH; //default
	}

}
