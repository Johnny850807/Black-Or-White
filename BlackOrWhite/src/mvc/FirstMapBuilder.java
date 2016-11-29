package mvc;

public class FirstMapBuilder extends MapBuilder{

	public FirstMapBuilder(View v) {
		super(v);
	}

	@Override
	public void buildBlockingBarrier() {
		System.out.print(MapBuilder.BLOCKING);
		
	}

	@Override
	public void buildGroundBarrier() {
		System.out.print(MapBuilder.GROUND_BARRIER);
	}

	@Override
	public void buildWalkableRoad() {
		System.out.print(MapBuilder.ROAD);
	}
	
	public static void main(String[] argv){
		MapBuilder bui = new FirstMapBuilder(new View());
		
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
				
		bui.buildMap(mapString);
	}

}
