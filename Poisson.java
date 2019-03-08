
package aquarium;
public abstract class Poisson implements TAILLE{
	protected Point position;
	protected Poisson(){
		position = new Point();	
	}
	public Point getPosition(){
		return position;
	}
	public abstract void move(Point cible);
	public void VerifPosition(){
		if(position.x<=0 || position.x>=TAILLE-1 || position.y<=0 || position.y>=TAILLE-1){
			position.x%=TAILLE;
			position.y%=TAILLE;
		}
	}
}
