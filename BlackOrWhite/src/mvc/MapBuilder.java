package mvc;

public abstract class MapBuilder {
	protected View view;
	public static final char BLOCKING = '*';
	public static final char GROUND_BARRIER = '0';
	public static final char ROAD = '-';
	public static final int SIZE = 10;  // 尚未確定
	public MapBuilder(View v){
		this.view = v;
	}
	public abstract void buildBlockingBarrier();  //障礙物 不可走 子彈不可過
	public abstract void buildGroundBarrier();  //障礙物 不可走 子彈可過
	public abstract void buildWalkableRoad();  //道路可走 子彈可過
	
	public void buildMap(String[] mapMessage){
		if ( mapMessage.length != SIZE )
			Log.e("Map Parameter String Size Wrong !");
		for ( int i = 0 ; i < SIZE ; i ++ )
			for ( int j = 0 ; j < SIZE ; j ++ )
			{
				if ( mapMessage[i].length() != SIZE )
					Log.e("Map Parameter String Size Wrong !");
				if ( mapMessage[i].charAt(j) == BLOCKING )
					buildBlockingBarrier();
				else if ( mapMessage[i].charAt(j) == GROUND_BARRIER )
					buildGroundBarrier();
				else
					buildWalkableRoad();
			}
	}
}
