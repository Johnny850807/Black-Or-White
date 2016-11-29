package mvc;

public class FirstMapBuilder extends MapBuilder{

	public FirstMapBuilder(View v) {
		super(v);
	}

	@Override
	public void buildBlockingBarrier() {
		System.out.print("*");
		
	}

	@Override
	public void buildGroundBarrier() {
		System.out.print("0");
	}

	@Override
	public void buildWalkableRoad() {
		System.out.print("-");
	}
	
	public static void main(String[] argv){
		MapBuilder bui = new FirstMapBuilder(new View());
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
