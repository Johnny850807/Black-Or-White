package weapon.gameEffects;

import mvc.ActionType;
import mvc.Dir;
import mvc.ImageSequence;
import mvc.Item;
import mvc.Model;
import weapon.guns.Gun;

public abstract class GameEffect {
	private Model model;
	
	public GameEffect(int x , int y , ImageSequence imgs){
		model = new Model(this,Item.EFFECT,x,y,ActionType.HALT,Dir.NORTH,imgs);
	};
	
	public Model getModel() {
		return model;
	}
}
