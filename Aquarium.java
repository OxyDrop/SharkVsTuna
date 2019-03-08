package aquarium;
import java.util.Scanner;

public class Aquarium extends Thread implements TAILLE{
	PoissonList liste;
	public Aquarium(int nbThons, int nbRequins){
		liste = new PoissonList();
		for(int i=0;i<=nbThons+nbRequins;i++){
			if(i<nbThons)
				liste.add(new Thon());
			else if(i>nbThons && i<=nbThons+nbRequins)
				liste.add(new Requin());
		}
		liste.add(new Cannibale());
	}
	@Override
	public String toString(){
		String s="[";
		for(int i=0;i<liste.size();i++){
			if(i==liste.size()-1)
				s+=liste.get(i).toString()+"]";
			else
				if(i%5==0 && i!=0)
					s+=liste.get(i).toString()+" , \n";
				else
					s+=liste.get(i).toString()+" , ";
		}
		return s;
	}
	public void run(){
		try{
			while(liste.nbThons()!=0  && liste.nbRequin()!=0){
				liste = liste.faireUnPas();
				sleep(1000);
				System.out.println(OthertoString()+"\nnombre poissons = "+liste.size()+"\nnombre thons = "+liste.nbThons()+" nombre requins = "+liste.nbRequin());
			}
		}catch(PoissonInconnuException  | InterruptedException ex){}
	}
	public String OthertoString(){
		String s = "";
		String[][] aqua = new String[TAILLE][TAILLE];
		
		for(Object p : liste)
		{
			if(p instanceof Thon)
				aqua[((Thon)p).getPosition().x][((Thon)p).getPosition().y]=" T ";
			else if(p instanceof Cannibale)
				aqua[((Requin)p).getPosition().x][((Requin)p).getPosition().y]="C"+((Requin)p).getNrj();
			else if(p instanceof Requin)
				aqua[((Requin)p).getPosition().x][((Requin)p).getPosition().y]="R"+((Requin)p).getNrj();
		}
		for(int j=0 ; j<aqua.length ; j++){
			for(int i=0 ; i<aqua.length ; i++){
				if(aqua[i][j]==null)
					aqua[i][j]=" . ";
				s+=aqua[i][j];
			}
			s+="\n";
		}
		return s;
	}
}
