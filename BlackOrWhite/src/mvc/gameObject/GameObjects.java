package mvc.gameObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import mvc.Log;
import role.Role;
import weapon.bullets.Bullet;
import weapon.guns.fallenWeapon.FallenItem;
//所有出現在畫面中的物件
public class GameObjects {
	private static GameObjects gameObjects;  //singleton
	private List<Role> roles = Collections.checkedList(new ArrayList<Role>(), Role.class); //所有存在角色
	private List<Bullet> bullets  = Collections.checkedList(new ArrayList<Bullet>(), Bullet.class);  //所有存在子彈
	private List<FallenItem> fallItems = Collections.checkedList(new ArrayList<FallenItem>(), FallenItem.class);  //所有掉落槍枝
	private GameObjects(){} //singleton
	
	public static GameObjects getGameObjects(){
		if ( gameObjects == null ) //singleton
			gameObjects = new GameObjects();
		return gameObjects;
	}
	
	public GameObjectModelsItertor iterator(){
		return new GameObjectModelsItertor(this);
	}
	public Iterator rolesIterator(){
		return roles.iterator();
	}
	public Iterator bulletsIterator(){
		return bullets.iterator();
	}
	public List<FallenItem> getFallItems() {
		return fallItems;
	}

	public void setFallItems(List<FallenItem> fallItems) {
		this.fallItems = fallItems;
	}

	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public List<Bullet> getBullets() {
		return bullets;
	}
	public void setBullets(List<Bullet> bullets) {
		this.bullets = bullets;
	}
	public void addRole(Role r){
		roles.add(r);
	}
	public void addBullet(Bullet b){
		bullets.add(b);
	}
	public void addFallenGun(FallenItem f){
		fallItems.add(f);
	}
	public void removeRole(Role r){
		roles.remove(r);
	}
	public void removeBullet(Bullet b){
		bullets.remove(b);
	}
	public void removeFallenItem(FallenItem f){
		fallItems.remove(f);
	}
	//overloading
	public void removeRole(int r){
		roles.remove(r);
	}
	public void removeBullet(int b){
		bullets.remove(b);
	}
	public Role getRole(int idx){
		return roles.get(idx);
	}
	public Bullet getBullet(int idx){
		return bullets.get(idx);
	}
	//overloading
	public int getIndexOf(Role r){
		return roles.indexOf(r);
	}
	public int getIndexOf(Bullet b){  // index -> ex: roles : 0 1 2 3 4 5 bullets : 6 7 8 ... 
		return roles.size()+bullets.indexOf(b);
	}
	public int rolesSize(){
		return roles.size();
	}
	public int bulletSize(){
		return bullets.size();
	}
	public int fallenItemSize(){
		return fallItems.size();
	}
	
	
}
