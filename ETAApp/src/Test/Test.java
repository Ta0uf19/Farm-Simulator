package Test;

import java.util.List;

import DAO.*;
import Gestionnaire.*;

public class Test {
	
	public static void main(String[] args) {
		/*String test = "47.980005";
		DAO<Champ> champ = new ChampDAO();
		List<Champ> champs = champ.recupererTout();
		
		/*for(Champ ch : champs) {
			System.out.println(Double.parseDouble(test));
		}*/
		
		/*List<Machine> machines = (new MachineDAO()).recupererTout();
		
		for(Machine m : machines) {
			System.out.println(m);
		}*/
		
		
		//Tracteur t = new Tracteur(20, "New-holland", "BR 750 A", 1, "Ronde");
		
		
		/*Botteleuse b = new Botteleuse(0, "New-holland", "BR 750 A", 1, "Ronde");
		
		DAO<Machine> m = new MachineDAO();
		m.ajouter(b);*/
		//System.out.println(m.modifier(b));
		//System.out.println(m.recuperer(20));
		
		
		Client c = new Client(1, "Dujardin", "Jeans" , "278421952", "La Couesnerie, Esse");
		DAO<Client> cl = new ClientDAO();
		System.out.println(cl.modifier(c));
		
		
	}

}
