package mvc;

import java.awt.Graphics;

public class BasicMapBuilder extends MapBuilder{

	public BasicMapBuilder(View v) {
		super(v);
	}

	@Override
	public void buildBlockingBarrier(Graphics g , int x , int y ) {
		System.out.print(MapBuilder.BLOCKING);
		
	}

	@Override
	public void buildGroundBarrier(Graphics g , int x , int y ) {
		System.out.print(MapBuilder.GROUND_BARRIER);
	}

	@Override
	public void buildWalkableRoad(Graphics g , int x , int y ) {
		System.out.print(MapBuilder.ROAD);
	}
	
	public static void main(String[] argv){
		MapBuilder bui = new BasicMapBuilder(new View());
		
		// this is the director example for 10x10 map 
		String[] mapString=   
				
			         { "**********" , 
				       "*00000000*" ,
				       "*-**--**-*" , 
				       "*--------*" ,
				       "---**-----" , 
				       "*---000***" ,
				       "*0----00-*" , 
				       "*--**----*" ,
				       "*--------*" , 
				       "**-****-**" };
				
	}

}
