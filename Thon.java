/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aquarium;

/**
 *
 * @author Acer
 */
public class Thon extends Poisson {
    public Thon(){
		super();
    }
    @Override
    public String toString(){
	return "Thon"+position.toString();
    }
    @Override
    public void move(Point cible){
		if(position.distanceTo(cible)>20){
			position.x=(position.x+TAILLE+(int)(Math.random()*31-15))%TAILLE;
			position.y+=(position.y+TAILLE+(int)(Math.random()*31-15))%TAILLE;
		}else{
			position.x+=position.distanceTo(cible)/8;
			position.y+=position.distanceTo(cible)/8;
        } 
	VerifPosition();
    }
}
