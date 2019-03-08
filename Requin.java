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
public class Requin extends Poisson {
	protected int energie;
	protected boolean coupFatal;
	public Requin(){
		super();
		energie=100;
		coupFatal=true;
	}
        @Override
        public String toString(){
            return "requin"+position.toString();
        }
        @Override
        public void move(Point cible){
			if(energie>10 || coupFatal){
				position.x+=position.distanceTo(cible)/1.5;
				position.y+=position.distanceTo(cible)/1.5;
			}else{ //DERNIER RECOURS
				position.x=cible.x;
				position.y=cible.y;
				coupFatal=false;
			}
			energie=energie-(int)(Math.random()*10+4);
			if(energie<=10)
				coupFatal=false;
			VerifPosition();
        }
		public int getNrj(){
			return energie;
		}
		public void setNrj(int nrj){
			energie=nrj;
		}
}
