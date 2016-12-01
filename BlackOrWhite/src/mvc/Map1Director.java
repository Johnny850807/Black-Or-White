package mvc;

public class Map1Director implements MapDirector{
	private static String[] mapString; // singleton
	// 玩家、怪物出生地座標
	public final static int PLAYER_CREATE_X = 200;
	public final static int PLAYER_CREATE_Y = 800;
	public final static int PLAYER2_CREATE_X = 900;
	public final static int PLAYER2_CREATE_Y = 800;
	
	public final static int[] AI_CREATE_X_SET = new int[]{200,500,600,900,0,1100};
	public final static int[] AI_CREATE_Y_SET = new int[]{0,0,0,0,200,200};
	@Override
	public String[] getMapString() {
		if ( mapString == null )  //singleton 12 * 9
		{
			mapString = new String[]
					    /*0*/   {"**-**--**-**",
						/*1*/	 "*---*--*---*",
						/*2*/	 "------------",
						/*3*/	 "*--0*--*0--*",
						/*4*/	 "*--0----0--*",
						/*5*/	 "*----**----*",
						/*6*/    "***------***",
						/*7*/	 "*----------*",
						/*8*/	 "**-******-**"};
		}
		return mapString;
	}
}
