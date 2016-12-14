package weapon.guns.fallenWeapon;


import java.util.concurrent.TimeUnit;

import mvc.ActionType;
import mvc.Dir;
import mvc.ImageSequence;
import mvc.Item;
import mvc.Model;
import weapon.guns.Gun;

public abstract class FallenItem {
	protected Model model;
	protected Gun gun;  //掉落物品的所屬槍種
	protected int w;
	protected int h;
	public FallenItem(int x , int y , int w , int h ,Gun gun,ImageSequence imgs){
		this.w = w;
		this.h = h;
		this.gun = gun;
		model = new Model(this,Item.GUN,x,y,ActionType.HALT,Dir.NORTH,imgs);
		itemExistTimeCountStart();
	};
	
	public void itemExistTimeCountStart(){
		//計算掉落物存在時間的倒數
		new Thread(){
			@Override
			public void run(){
				try {TimeUnit.SECONDS.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
				model.delete();
			}
		}.start();
	}
	
	public Model getModel() {
		return model;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Gun getGun() {
		return gun;
	}

	public void setGun(Gun gun) {
		this.gun = gun;
	}
	
	
	
	
	
	
}
