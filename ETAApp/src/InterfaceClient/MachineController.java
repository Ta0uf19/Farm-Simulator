package InterfaceClient;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import DAO.ClientDAO;
import DAO.DAO;
import DAO.MachineDAO;
import Gestionnaire.Botteleuse;
import Gestionnaire.Client;
import Gestionnaire.Machine;
import Gestionnaire.Moissoneuse;
import Gestionnaire.Tracteur;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;

public class MachineController implements Initializable{
		/*
		 * FXML pour les 
		 */
	
		@FXML private TextField marqueTM;
		@FXML private TextField modeleTM;
		@FXML private TextField etatTM;
		@FXML private TextField capaciteTM;
		@FXML private TextField marqueTA;
		@FXML private TextField modeleTA;
		@FXML private TextField etatTA;
		@FXML private TextField capaciteTA;
		
		
		@FXML private TextField marqueBM;
		@FXML private TextField modeleBM;
		@FXML private TextField etatBM;
		@FXML private TextField typeBM;
		@FXML private TextField marqueBA;
		@FXML private TextField modeleBA;
		@FXML private TextField etatBA;
		@FXML private TextField typeBA;
		
		@FXML private TextField marqueMM;
		@FXML private TextField modeleMM;
		@FXML private TextField etatMM;
		@FXML private TextField lgCoupeMM;
		@FXML private TextField consoRMM;
		@FXML private TextField consoFMM;
		@FXML private TextField lgRouteMM;
		@FXML private TextField hauteurMM;
		@FXML private TextField poidsMM;
		@FXML private TextField capaciteRMM;
		
		@FXML private TextField marqueMA;
		@FXML private TextField modeleMA;
		@FXML private TextField etatMA;
		@FXML private TextField lgCoupeMA;
		@FXML private TextField consoRMA;
		@FXML private TextField consoFMA;
		@FXML private TextField lgRouteMA;
		@FXML private TextField hauteurMA;
		@FXML private TextField poidsMA;
		@FXML private TextField capaciteRMA;
		
		
		@FXML private TableView<Machine> tableMoissoneuse;
		@FXML private TableView<Machine> tableBotteleuse;
		@FXML private TableView<Machine> tableTracteur;
    
	    final TableColumn<Machine, Integer> Idm = new TableColumn<>("Identifiant");
	    final TableColumn<Machine, String> Modelem = new TableColumn<>("Modèle");
	    final TableColumn<Machine, String> Marquem = new TableColumn<>("Marque");
	    final TableColumn<Machine, String> Dispom = new TableColumn<>("Disponibilité");
	    
	    final TableColumn<Machine, Integer> Idb = new TableColumn<>("Identifiant");
	    final TableColumn<Machine, String> Modeleb = new TableColumn<>("Modèle");
	    final TableColumn<Machine, String> Marqueb = new TableColumn<>("Marque");
	    final TableColumn<Machine, String> Dispob = new TableColumn<>("Disponibilité");
	    
	    final TableColumn<Machine, Integer> Idt = new TableColumn<>("Identifiant");
	    final TableColumn<Machine, String> Modelet = new TableColumn<>("Modèle");
	    final TableColumn<Machine, String> Marquet = new TableColumn<>("Marque");
	    final TableColumn<Machine, String> Dispot = new TableColumn<>("Disponibilité");
	    
		private ObservableList<Machine> tracteurs = FXCollections.observableArrayList();
		private ObservableList<Machine> botteleuses = FXCollections.observableArrayList();
		private ObservableList<Machine> moissoneuses = FXCollections.observableArrayList();
	
		
		public void initialize(URL arg0, ResourceBundle arg1) {
			/*
			 * Récupérer la liste des machine
			 */
			List<Moissoneuse> listMoissoneuse = new ArrayList();
			List<Botteleuse> listBotteleuse = new ArrayList();
			List<Tracteur> listTracteur = new ArrayList();
			
			DAO<Machine> machine = new MachineDAO();
    		List<Machine> listMachines = machine.recupererTout();
    		
    		for(Machine list : listMachines) {
    			if(list instanceof Tracteur) {
    				listTracteur.add((Tracteur) list);
    			}
    			if(list instanceof Botteleuse) {
    				listBotteleuse.add((Botteleuse) list);
    			}
    			if(list instanceof Moissoneuse) {
    				listMoissoneuse.add((Moissoneuse) list);
    			}
    		}
    		
    	
    		tracteurs.addAll(listTracteur);
    		moissoneuses.addAll(listMoissoneuse);
    		botteleuses.addAll(listBotteleuse);
			
			Idt.setPrefWidth(100);
			Idt.setCellValueFactory(new PropertyValueFactory<Machine,Integer>("id"));
			Modelet.setPrefWidth(100);
			Modelet.setCellValueFactory(new PropertyValueFactory<Machine,String>("modele"));
			Marquet.setPrefWidth(100);
	        Marquet.setCellValueFactory(new PropertyValueFactory<Machine,String>("marque"));
	        Dispot.setPrefWidth(100);
	        Dispot.setCellValueFactory(new PropertyValueFactory<Machine,String>("dispo"));
	        
	        
	   
	        Idb.setPrefWidth(100);
			Idb.setCellValueFactory(new PropertyValueFactory<Machine,Integer>("id"));
			Modeleb.setPrefWidth(100);
			Modeleb.setCellValueFactory(new PropertyValueFactory<Machine,String>("modele"));
			Marqueb.setPrefWidth(100);
	        Marqueb.setCellValueFactory(new PropertyValueFactory<Machine,String>("marque"));
	        Dispob.setPrefWidth(100);
	        Dispob.setCellValueFactory(new PropertyValueFactory<Machine,String>("dispo"));
	        
	        Idm.setPrefWidth(100);
			Idm.setCellValueFactory(new PropertyValueFactory<Machine,Integer>("id"));
			Modelem.setPrefWidth(100);
			Modelem.setCellValueFactory(new PropertyValueFactory<Machine,String>("modele"));
			Marquem.setPrefWidth(100);
	        Marquem.setCellValueFactory(new PropertyValueFactory<Machine,String>("marque"));
	        Dispom.setPrefWidth(100);
	        Dispom.setCellValueFactory(new PropertyValueFactory<Machine,String>("dispo"));
	        
	        tableTracteur.setItems(tracteurs);
	        tableTracteur.getColumns().addAll(Idt,Modelet,Marquet,Dispot);
	        tableBotteleuse.setItems(botteleuses);
	        tableBotteleuse.getColumns().addAll(Idb,Modeleb,Marqueb,Dispob);
	        tableMoissoneuse.setItems(moissoneuses);
	        tableMoissoneuse.getColumns().addAll(Idm,Modelem,Marquem,Dispom);
	        
	        
	        tableTracteur.setOnMouseClicked(e -> {
	        	Tracteur tracteur = (Tracteur) tableTracteur.getSelectionModel().getSelectedItem();
	        	marqueTM.setText(tracteur.getMarque());
	        	modeleTM.setText(tracteur.getModele());
	        	etatTM.setText(String.valueOf(tracteur.getEtat()));
	        	capaciteTM.setText(tracteur.getCapacite());
	        	//tyV.setTooltip(client.getTypeCl()));
	        });
		
	     }
	       
	     
		}
		
