/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aquarium;
import java.util.Scanner;
public class TestAquarium {
	public static void main(String[] args){
		Aquarium aqua = new Aquarium(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
		System.out.println("***La liste des poissons***\n"+aqua.toString());
		System.out.println(aqua.OthertoString());
		
		System.out.println("***La liste des poissons après un pas***");
		System.out.println(aqua.OthertoString()+"\n"+aqua.toString());
		
		aqua.run();
		if(aqua.liste.nbThons()==0)
			System.out.println("!---SHARK VICTORY---!\nLe cannibale a devore\n\t"+aqua.liste.CMT+" thons\n\t"+aqua.liste.CMS+" requins" );
		else if(aqua.liste.nbRequin()==0)
			System.out.println("!---TUNA VICTORY---!\nLe cannibale a devore\n\t"+aqua.liste.CMT+" thons\n\t"+aqua.liste.CMS+" requins" );
		aqua.interrupt();
		
	}
}
