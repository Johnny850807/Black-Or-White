package mvc;

import java.awt.Graphics;

public abstract class MapBuilder {
	protected View view;
	public static final char BLOCKING = '*';
	public static final char GROUND_BARRIER = '0';
	public static final char ROAD = '-';
	public static final int SIZE = 10;  // 尚未確定
	public MapBuilder(View v){
		this.view = v;
	}
	public abstract void buildBlockingBarrier(Graphics g , int x , int y );  //障礙物 不可走 子彈不可過
	public abstract void buildGroundBarrier(Graphics g , int x , int y );  //障礙物 不可走 子彈可過
	public abstract void buildWalkableRoad(Graphics g , int x , int y );  //道路可走 子彈可過
	
	public void buildMap(String[] mapMessage , Graphics g){
		if ( mapMessage.length != SIZE )
			Log.e("Map Parameter String Size Wrong !");
		for ( int i = 0 ; i < SIZE ; i ++ )
			for ( int j = 0 ; j < SIZE ; j ++ )
			{
				if ( mapMessage[i].length() != SIZE )
					Log.e("Map Parameter String Size Wrong !");
				if ( mapMessage[i].charAt(j) == BLOCKING )
					buildBlockingBarrier(g);
				else if ( mapMessage[i].charAt(j) == GROUND_BARRIER )
					buildGroundBarrier(g);
				else
					buildWalkableRoad(g);
			}
	}
}
