package mvc;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BasicMapBuilder extends MapBuilder{

	static Image barrier; // pics
	static Image road;
	static Image ground;
	
	public BasicMapBuilder(){
		
	}
	
	// there are three singletons inside each creational function
	
	@Override
	public void buildBlockingBarrier(Graphics g , int x , int y ) {
		System.out.print(MapBuilder.BLOCKING);
		if ( barrier == null ){
			try {
				barrier = ImageIO.read(new File("pics/Map/tree.png"));
			} catch (IOException e) {
				Log.e(e.toString());
			}
		}
		g.drawImage(barrier, x, y, MapBuilder.SIZE_IMG , MapBuilder.SIZE_IMG , null);
	}

	@Override
	public void buildGroundBarrier(Graphics g , int x , int y ) {
		System.out.print(MapBuilder.GROUND_BARRIER);
		if ( ground == null ){
			try {
				ground = ImageIO.read(new File("pics/Map/water.png"));
			} catch (IOException e) {
				Log.e(e.toString());
			}
		}
		g.drawImage(ground, x, y, MapBuilder.SIZE_IMG , MapBuilder.SIZE_IMG , null);
	
	}

	@Override
	public void buildWalkableRoad(Graphics g , int x , int y ) {
		System.out.print(MapBuilder.ROAD);
		if ( road == null ){
			try {
				road = ImageIO.read(new File("pics/Map/grass.png"));
			} catch (IOException e) {
				Log.e(e.toString());
			}
		}
		g.drawImage(road, x, y, MapBuilder.SIZE_IMG , MapBuilder.SIZE_IMG , null);
	}
	
	public static void main(String[] argv){
		MapBuilder bui = new BasicMapBuilder();
		

				
	}

}
