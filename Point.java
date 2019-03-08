package aquarium;
public class Point implements TAILLE{   
	public int x,y;   
	public Point(int x, int y){ 
		this.x=x;this.y=y; 
	}   
	public Point(){      /* initialise x et y entre 0 et 499 */      
		this((int)(Math.random()*TAILLE),(int)(Math.random()*TAILLE));   
	}   
	@Override
	public String toString(){ 
		return "("+x+","+y+") "; }   
	public double distanceTo(Point p){      
		int dx = p.x-x;      
		int dy = p.y-y;      
		return Math.sqrt(dx*dx+dy*dy);   
	}
}
   
