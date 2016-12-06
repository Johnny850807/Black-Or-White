package mvc.gameObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import role.Role;
import weapon.bullets.Bullet;
//所有出現在畫面中的物件
public class GameObjects {
	private List<Role> roles = Collections.checkedList(new ArrayList<Role>(), Role.class); //所有存在角色
	private List<Bullet> bullets  = Collections.checkedList(new ArrayList<Bullet>(), Bullet.class);  //所有存在子彈
	
	public GameObjects(){}
	
	public GameObjectModelsItertor iterator(){
		return new GameObjectModelsItertor(this);
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
	public void removeRole(Role r){
		roles.remove(r);
	}
	public void removeBullet(Bullet b){
		bullets.remove(b);
	}
	//overloading
	public void removeRole(int r){
		roles.remove(r);
	}
	public void removeBullet(int b){
		bullets.remove(b);
	}
	
	
}
