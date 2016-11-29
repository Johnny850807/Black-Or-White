package mvc;

import java.awt.Graphics;

public abstract class MapBuilder {
	protected View view;
	public static final int SIZE_IMG = 100;
	public static final char BLOCKING = '*';
	public static final char GROUND_BARRIER = '0';
	public static final char ROAD = '-';
	public static final int SIZEX = 12;  // 1200 x 900 map
	public static final int SIZEY = 9;  // each image gets 100pt*100pt 
	public abstract void buildBlockingBarrier(Graphics g , int x , int y );  //障礙物 不可走 子彈不可過
	public abstract void buildGroundBarrier(Graphics g , int x , int y );  //障礙物 不可走 子彈可過
	public abstract void buildWalkableRoad(Graphics g , int x , int y );  //道路可走 子彈可過
	
	public MapBuilder(){}
	
	public void buildMap(String[] mapMessage , Graphics g){
		if ( mapMessage.length != SIZEY )
			Log.e("Map Parameter String Size Wrong !");
		for ( int i = 0 ; i < SIZEY ; i ++ )
			for ( int j = 0 ; j < SIZEX ; j ++ )
			{
				if ( mapMessage[i].length() != SIZEX )
					Log.e("Map Parameter String Size Wrong !");
				if ( mapMessage[i].charAt(j) == BLOCKING )
					buildBlockingBarrier(g , i * SIZE_IMG , j * SIZE_IMG );
				else if ( mapMessage[i].charAt(j) == GROUND_BARRIER )
					buildGroundBarrier(g , i * SIZE_IMG , j * SIZE_IMG );
				else
					buildWalkableRoad(g , i * SIZE_IMG , j * SIZE_IMG);
			}
	}
}
