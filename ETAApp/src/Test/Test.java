package Test;

import java.util.List;

import DAO.*;
import Gestionnaire.*;

public class Test {
	
	public static void main(String[] args) {
		String test = "47.980005";
		DAO<Champ> champ = new ChampDAO();
		List<Champ> champs = champ.recupererTout();
		
		/*for(Champ ch : champs) {
			System.out.println(Double.parseDouble(test));
		}*/
		
		
	}

}
