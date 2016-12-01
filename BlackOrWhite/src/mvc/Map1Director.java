package mvc;

import java.util.ArrayList;

public class Map1Director implements MapDirector{
	private static String[] mapString; // singleton
	// 玩家、怪物出生地、障礙物座標
	public final static int PLAYER_CREATE_X = 200;
	public final static int PLAYER_CREATE_Y = 800;
	public final static int PLAYER2_CREATE_X = 900;
	public final static int PLAYER2_CREATE_Y = 800;
	public static ArrayList<Integer> BARRIER_X_SET;  //障礙物座標集合
	public static ArrayList<Integer> BARRIER_Y_SET;
	public final static int[] AI_CREATE_X_SET = new int[]{200,500,600,900,0,1100};
	public final static int[] AI_CREATE_Y_SET = new int[]{0,0,0,0,200,200};
	
	public Map1Director(){
		String[] map = getMapString();
		createBarrierSet();
	}
	public static String[] getMapString() {
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
	
	private void createBarrierSet(){
		BARRIER_X_SET =  new ArrayList<Integer>();
		BARRIER_Y_SET =  new ArrayList<Integer>();
		for ( int i = 0 ; i < MapBuilder.SIZEY ; i ++ )
			for ( int j = 0 ; j < MapBuilder.SIZEX  ; j ++ )
			{
				if (mapString[i].charAt(j) == '*' || mapString[i].charAt(j) == '0')
				{
					BARRIER_X_SET.add(j*100);
					BARRIER_Y_SET.add(i*100);
				}
			}
		Log.d("障礙物座標:"+"X:"+BARRIER_X_SET+"\nY:"+BARRIER_Y_SET);
	}
	
}
