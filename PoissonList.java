/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aquarium;
import java.util.ArrayList;
import java.util.stream.*;

/**
 *
 * @author Acer
 */
public class PoissonList extends ArrayList implements TAILLE{
	private static final int SHARKNRJ=50;
	public static int CMT=0;
	public static int CMS=0;
    public PoissonList(){
		super();
	}
	public PoissonList(PoissonList p){ //constructeur par copie de poisson
		super(p);
	}
	public int nbThons() //compte le nombre de thons dans la liste
	{ 
		int nb = 0;
		for(Object p : this){
			if(p instanceof Thon)
				nb++;
		}
		return nb;
    }
	public int nbRequin() //compte le nombre de thons dans la liste
	{ 
		int nb = 0;
		for(Object p : this){
			if(p instanceof Requin)
				nb++;
		}
		return nb;
    }
    public int rangPoissonProche(int index)  //rend l'index du poisson le plus proche de celui dont l'index est passé en argument
	{
		Double dist_min = Double.POSITIVE_INFINITY; //variable pour comparer les distances
		int pos = -1; //l'index qui sera rendu à la fin de la méthode
		Poisson p = (Poisson)this.get(index); //le Poisson dont l'index est passé en argument
		
		for(Object cible : this) //parcours de la liste
		{ 
			if(p.getPosition().distanceTo(((Poisson)cible).getPosition())<dist_min && !(p.equals(cible)) && p.getClass()!=cible.getClass())//Si la distance entre p et cible est <dist_min
			{
				dist_min = p.getPosition().distanceTo(((Poisson)cible).getPosition());
				pos = this.indexOf(cible); //mise à jour distance et index
			}
		}
		return pos;
    }
    public void bougeTousPoisson()
	{
		Point centre = new Point(TAILLE/2,TAILLE/2); //centre de l'aquarium
		Point hg= new Point(0,0); //haut gauche
		Point hd=new Point(0,TAILLE); //haut droite
		Point bg= new Point(TAILLE,0); //bas gauche
		Point bh= new Point(TAILLE,TAILLE); //bas droite
		Point[] coord = {hg,hg,bg,bh}; 
		for(Object p : this)
		{
			Poisson proche = (Poisson)this.get(rangPoissonProche(indexOf(p))); //poisson le plus proche du poisson courant
			
			if(proche instanceof Thon) 
				((Poisson)p).move(proche.getPosition()); //on deplace le poisson courant vers le thon
			else if(p instanceof Cannibale){
				((Poisson)p).move(proche.getPosition());
			}
			else if(proche instanceof Requin)
			{
				Point pr = ((Requin)proche).getPosition();
				double dmax=-1;
				Point max=null; //le point le plus eloigné du requin
				
				for(int i=0 ; i<coord.length ; i++)
					if(coord[i].distanceTo(pr)>dmax){ //trouver le point le plus eloigné
						dmax=coord[i].distanceTo(pr);
						max=coord[i];
					}
				if(max!=null)
					((Poisson)p).move(max);
				else
					((Poisson)p).move(centre);
			}
		}
    }
    public PoissonList faireUnPas() throws PoissonInconnuException 
	{
		bougeTousPoisson(); //deplace tous les poissons de la liste
		PoissonList l2 = new PoissonList(this); //creation d'une liste copie avec appel constructeur par copie
		
		for(Object p : this)
		{
			if(!(p instanceof Thon)&&!(p instanceof Requin)) //si poisson inconnu
				throw new PoissonInconnuException();
			
			if(p instanceof Requin && ((Requin)p).getNrj()<=0){ //si energie requin à 0 on le retire
				l2.remove(p);
				continue;
			}
			Poisson proche = (Poisson)this.get(rangPoissonProche(indexOf(p))); //poisson le plus proche de p
			
			if(((Poisson)p).getPosition().distanceTo(proche.getPosition())<2) //si les deux poissons sont assez proches
			{ 
				if(p instanceof Thon && Math.random()<0.02)
					l2.add(new Thon()); //reproduction
				
				else if(p instanceof Cannibale || proche instanceof Cannibale){
					if(p instanceof Cannibale){
						if(proche instanceof Requin) CMS++;
						else if(proche instanceof Thon) CMT++;
						l2.remove(proche);
						((Requin)p).setNrj(SHARKNRJ);
					}else{
						if(p instanceof Requin) CMS++;
						else if(p instanceof Thon) CMT++;
						l2.remove(p);
						((Requin)proche).setNrj(SHARKNRJ);
					}
				}
				else if(p instanceof Thon && proche instanceof Requin){
					l2.remove(p); //elimination
					((Requin)proche).setNrj(SHARKNRJ);
				}
				else if(p instanceof Requin && proche instanceof Thon){
					l2.remove(proche); //elimination
					((Requin)p).setNrj(SHARKNRJ);
				}	
			}
		}
		return l2;
    }
}
