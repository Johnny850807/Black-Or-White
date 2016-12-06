package mvc.gameObject;

import java.util.ArrayList;
import java.util.Iterator;

import mvc.Model;
import role.Role;
import weapon.bullets.Bullet;

public class GameObjectModelsItertor implements Iterator<Model> {
	private GameObjects gameObjects;
	private ArrayList<Model> models;
	public GameObjectModelsItertor(GameObjects gameObjects){
		this.gameObjects = gameObjects;
		models = new ArrayList<Model>();
		// 放入所有models
		for ( Role r : gameObjects.getRoles() )
			models.add(r.getModel());
		for ( Bullet b : gameObjects.getBullets() )
			models.add(b.getModel());
	}
	@Override
	public boolean hasNext() {
		
		return false;
	}

	@Override
	public Model next() {

		return null;
	}

}
